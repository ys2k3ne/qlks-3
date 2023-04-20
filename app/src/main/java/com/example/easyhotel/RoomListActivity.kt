package com.example.easyhotel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.example.easyhotel.Adapter.HotDeal
import com.example.easyhotel.Adapter.Room
import com.example.easyhotel.Adapter.RoomAdapter
import com.google.firebase.database.DatabaseReference
import java.util.*
import kotlin.collections.ArrayList

class RoomListActivity : AppCompatActivity() {

    private lateinit var priceSpinner: Spinner
    private lateinit var ratingSpinner: Spinner
    private lateinit var amenitiesSpinner: Spinner

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_list)

        priceSpinner = findViewById(R.id.price_spinner)
        ratingSpinner = findViewById(R.id.rating_spinner)
        amenitiesSpinner = findViewById(R.id.amenities_spinner)



        // Thiết lập sự kiện cho Spinner giá
        priceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val priceOption = parent?.getItemAtPosition(position) as String
                // Thực hiện xử lý tương ứng với lựa chọn giá
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Thiết lập sự kiện cho Spinner xếp hạng
        ratingSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val ratingOption = parent?.getItemAtPosition(position) as String
                // Thực hiện xử lý tương ứng với lựa chọn xếp hạng
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Thiết lập sự kiện cho Spinner tiện nghi
        amenitiesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val amenitiesOption = parent?.getItemAtPosition(position) as String
                // Thực hiện xử lý tương ứng với lựa chọn tiện nghi
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Tiếp tục xử lý danh sách phòng với RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.room_list)

// Tạo danh sách các phòng để hiển thị trên RecyclerView


// Tạo đối tượng RoomAdapter và truyền danh sách phòng vào

    }
    private fun getHotDeals(): List<Room> {
        val rooms = listOf(
            Room(1, "Phòng 1", 100.0, 4.0f, R.drawable.room1),
            Room(2, "Phòng 2", 200.0, 3.5f, R.drawable.room2),
            Room(3, "Phòng 3", 150.0, 4.5f, R.drawable.room3),
            Room(4, "Phòng 4", 180.0, 4.0f, R.drawable.room4),
            Room(5, "Phòng 5", 120.0, 3.0f, R.drawable.room5)
        )

        // Thêm các chương trình khuyến mãi khác vào đây

        return rooms
    }


}
