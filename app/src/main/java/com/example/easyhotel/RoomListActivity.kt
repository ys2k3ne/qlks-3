package com.example.easyhotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyhotel.Adapter.Room
import com.example.easyhotel.Adapter.RoomAdapter
import com.example.easyhotel.Adapter.RoomAdapter.onItemClickListener
import com.example.easyhotel.databinding.ActivityRoomListBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private lateinit var binding: ActivityRoomListBinding

class RoomListActivity : AppCompatActivity() {
    private lateinit var mAdapter: RoomAdapter
    private var ds: MutableList<Room> = mutableListOf()
    private lateinit var dbRef: DatabaseReference
    private lateinit var query: Query

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dbRef = FirebaseDatabase.getInstance().getReference("hotels")
        query = dbRef.orderByChild("available").equalTo(true)

// Khởi tạo mAdapter
        mAdapter = RoomAdapter(ds, object : LSInterface {
            override fun onClickRoom(pos: Int) {
                val intent = Intent(this@RoomListActivity, DetailsActivity::class.java)
                intent.putExtra("roomId", ds[pos].roomId)
                startActivity(intent)

                Toast.makeText(
                    this@RoomListActivity,
                    "Bạn chọn ${ds[pos].roomName}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        binding.filterTv.setOnClickListener {
            // Lấy giá trị của bộ lọc
            val hasAvailableRooms = binding.filterCheckbox.isChecked

            // Nếu bộ lọc "khách sạn có sẵn phòng" được chọn, thêm điều kiện cho câu truy vấn
            val filteredQuery = if (hasAvailableRooms) {
                query
            } else {
                dbRef
            }

            // Thực hiện câu truy vấn và xử lý kết quả
            filteredQuery.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    ds.clear()
                    for (roomSnapshot in snapshot.children) {
                        val room = roomSnapshot.getValue(Room::class.java)
                        room?.let { ds.add(it) }
                    }
                    mAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@RoomListActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }






        // Gán sự kiện cho view filterCheckBox
        binding.filterCheckbox.setOnCheckedChangeListener { _, isChecked ->
            // Xử lý khi check/uncheck filterCheckBox
        }

        // Gán sự kiện cho view priceSeekBar



        FirebaseApp.initializeApp(this)
        // Khởi tạo Adapter
        binding.hotelList.layoutManager = LinearLayoutManager(this)
        binding.hotelList.setHasFixedSize(true)

        ds = ArrayList<Room>() // Khởi tạo biến ds với một danh sách rỗng
        GetThongTin()

        // Lấy reference đến node "bookedRooms" trong database
        val bookedRoomsRef = FirebaseDatabase.getInstance().getReference("bookedRooms")

        // Lấy ngày hiện tại
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        val MAX_ROOM_BOOKED = 10

        // Kiểm tra số lượng phòng đã đặt trong ngày đó
        bookedRoomsRef.child(currentDate).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val bookedRoomCount = snapshot.getValue(Int::class.java) ?: 0

                // Nếu số lượng phòng đã đặt chưa đạt tối đa
                if (bookedRoomCount < MAX_ROOM_BOOKED) {
                    // Đặt phòng và cập nhật lại số lượng phòng đã đặt
                    val newBookedRoomCount = bookedRoomCount + 1
                    bookedRoomsRef.child(currentDate).setValue(newBookedRoomCount)

                    // Thực hiện đặt phòng ở đây
                    // ...
                } else {
                    // Hiển thị thông báo cho người dùng biết rằng không thể đặt thêm phòng
                    // ...
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý lỗi nếu có
                // ...
            }
        })
    }


    private fun GetThongTin() {
        dbRef = FirebaseDatabase.getInstance().getReference("hotels")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ds.clear()
                if (snapshot.exists()){
                    for(prdSnap in snapshot.children){
                        val prdData = prdSnap.getValue(Room::class.java)
                        ds.add(prdData!!)
                    }
                    // Cập nhật dữ liệu mới nhất vào Adapter
//                    mAdapter.updateData(ds)
                    val mAdapter = RoomAdapter(ds, object : LSInterface {
                        override fun onClickRoom(pos: Int) {
                            // xử lí sự kiện click đến activity_details.xml
                            val intent = Intent(this@RoomListActivity, DetailsActivity::class.java)
                            intent.putExtra("roomId", ds[pos].roomId)
                            startActivity(intent)

                            Toast.makeText(
                                this@RoomListActivity,
                                "Bạn chọn ${ds[pos].roomName}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    })
                    // Gán adapter mới với RecyclerView
                    binding.hotelList.adapter = mAdapter
                    // code lắng nghe sự kiện lên item rv
                    mAdapter.setOnItemClickListener(object : onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@RoomListActivity, DetailsActivity::class.java)
                            // put Extra
                            intent.putExtra("roomId", ds[position].roomId)
                            intent.putExtra("roomName", ds[position].roomName)
                            intent.putExtra("hotelAddress", ds[position].hotelAddress)
                            intent.putExtra("roomPrice", ds[position].roomPrice)
                            intent.putExtra("roomRating", ds[position].roomRating)
                            intent.putExtra("roomImage", ds[position].roomImage)
                            startActivity(intent)
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lí lỗi khi đọc dữ liệu từ Firebase thất bại
                Toast.makeText(
                    this@RoomListActivity,
                    "Đã có lỗi xảy ra khi đọc dữ liệu từ Firebase",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

}
