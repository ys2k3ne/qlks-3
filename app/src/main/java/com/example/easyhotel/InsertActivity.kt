package com.example.easyhotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.example.easyhotel.Adapter.Room
import com.example.easyhotel.R
import com.example.easyhotel.RoomListActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var btnSave: Button
    private lateinit var edtName: EditText
    private lateinit var edtPrice: EditText
    private lateinit var edtAdd: EditText
    private lateinit var edtImg: EditText
    private lateinit var ratingBar: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        btnSave = findViewById(R.id.btnSave)
        edtName = findViewById(R.id.edtNameHotel)
        edtPrice = findViewById(R.id.edtPriceHotel)
        edtAdd = findViewById(R.id.edtAddressHotel)
        edtImg = findViewById(R.id.edtPrdImg)
        ratingBar = findViewById(R.id.ratingBar)
        dbRef = FirebaseDatabase.getInstance().getReference("hotels")

        // Xử lý sự kiện khi click vào nút save
        btnSave.setOnClickListener {
            saveRoomData()
        }
    }

    private fun saveRoomData() {
        // Lấy dữ liệu
        val roomName = edtName.text.toString()
        val roomPrice = edtPrice.text.toString()
        val hotelAddress = edtAdd.text.toString()
        val roomImage = edtImg.text.toString()
        val roomRating = ratingBar.rating

        // Kiểm tra dữ liệu có hợp lệ không
        if (roomName.isEmpty() || roomPrice.isEmpty() || hotelAddress.isEmpty() || roomImage.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        // Đẩy dữ liệu vào Firebase Database
        val roomId = dbRef.push().key!!
        val room = Room(roomId, roomName, hotelAddress, roomPrice, roomRating, roomImage, "", true)
        dbRef.child(roomId).setValue(room)
            .addOnCompleteListener{
                // Đẩy thành công, chuyển về trang danh sách
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
                edtName.setText("")
                edtPrice.setText("")
                edtAdd.setText("")
                edtImg.setText("")
                ratingBar.rating = 0f
                val intent = Intent(this, RoomListActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener{err->
                // Đẩy thất bại, thông báo lỗi
                Toast.makeText(this, "Thêm thất bại. Lỗi: ${err.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
