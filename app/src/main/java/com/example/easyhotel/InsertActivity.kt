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
    private lateinit var dbRef : DatabaseReference
    private lateinit var btnSave : Button
    private lateinit var edtName : EditText
    private lateinit var edtPrice : EditText
    private lateinit var edtAdd : EditText
    private lateinit var edtPrdImg : EditText
    private lateinit var rate : RatingBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        btnSave = findViewById(R.id.btnSave)
        edtName = findViewById(R.id.edtNameHotel)
        edtPrice = findViewById(R.id.edtPriceHotel)
        edtAdd = findViewById(R.id.edtAddressHotel)
        edtPrdImg = findViewById(R.id.edtPrdImg)
        rate = findViewById(R.id.ratingBar)
        dbRef = FirebaseDatabase.getInstance().getReference("hotels")

        // xử lí sự kiện khi click vòa nút save
        btnSave.setOnClickListener {
            saveProductData()
        }
    }

    private fun saveProductData() {
        // Lấy dữ liệu
        val hotelName = edtName.text.toString()
        val hotelPrice = edtPrice.text.toString()
        val hotelAdd = edtAdd.text.toString()
        val hotelImg = edtPrdImg.text.toString()
        val rating : Float = rate.rating

        // Kiểm tra dữ liệu có hợp lệ không
        if (hotelName.isEmpty() || hotelPrice.isEmpty() || hotelAdd.isEmpty() || hotelImg.isEmpty() ) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        // Đẩy dữ liệu vào Firebase Database
        val hotelId = dbRef.push().key!!
        val hotel = Room(hotelId, hotelName, hotelAdd, hotelPrice, rating , hotelImg )
        dbRef.child(hotelId).setValue(hotel)
            .addOnCompleteListener{
                // Đẩy thành công, chuyển về trang danh sách
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
                edtName.setText("")
                edtPrice.setText("")
                edtAdd.setText("")
                edtPrdImg.setText("")
                val intent = Intent(this, RoomListActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener{err->
                // Đẩy thất bại, thông báo lỗi
                Toast.makeText(this, "Thêm thất bại. Lỗi: ${err.message}", Toast.LENGTH_SHORT).show()
            }
    }
}