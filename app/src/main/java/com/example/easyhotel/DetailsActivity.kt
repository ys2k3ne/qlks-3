package com.example.easyhotel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.easyhotel.Adapter.Room
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    private lateinit var hotelImg: ImageView
    private lateinit var hotelName: TextView
    private lateinit var hotelPrice: TextView
    private lateinit var hotelAddress: TextView
    private lateinit var hotelDesc: TextView
    private lateinit var hotelRatingBar: RatingBar
    private lateinit var hotelStatus: TextView
    private lateinit var bookButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        hotelImg = findViewById(R.id.hotel_img)
        hotelName = findViewById(R.id.hotel_name_text_view)
        hotelPrice = findViewById(R.id.hotel_price_text_view)
        hotelAddress = findViewById(R.id.hotel_address)
        hotelDesc = findViewById(R.id.motaHotel)
        hotelRatingBar = findViewById(R.id.hotelRatingBar)
        hotelStatus = findViewById(R.id.hotel_status)
        bookButton = findViewById(R.id.book_button)

        // Lấy thông tin về khách sạn từ intent và hiển thị lên các view
        val room: Room? = if (intent.hasExtra("room")) {
            intent.getSerializableExtra("room") as? Room
        } else {
            null
        }
        if (room != null) {
            // Load image from Firebase URL using Picasso
            val imgUrl = room.roomImage
            Picasso.get()
                .load(imgUrl)
                .fit()
                .centerCrop()
                .into(hotelImg)

            hotelName.text = room.roomName
            hotelPrice.text = "Giá: ${room.roomPrice} VND"
            hotelAddress.text = "Địa chỉ: ${room.hotelAddress}"
            hotelRatingBar.rating = room.roomRating
            hotelDesc.text = room?.description
            hotelStatus.text = if (room?.available == true) "Trạng thái: available" else "Trạng thái: not available"
        }

        // Sự kiện khi click vào nút "Đặt phòng"
        bookButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:123456789")
            startActivity(intent)
        }
    }
}
