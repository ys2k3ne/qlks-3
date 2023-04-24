package com.example.easyhotel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.easyhotel.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso

private lateinit var binding: ActivityDetailsBinding
class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setValueToView()
    }

    private fun setValueToView() {
        val roomName = intent.getStringExtra("roomName")
        val hotelAddress = intent.getStringExtra("hotelAddress")
        val roomPrice = intent.getStringExtra("roomPrice")
        val roomRating = intent.getFloatExtra("roomRating", 0f)
        val roomImage = intent.getStringExtra("roomImage")

        binding.hotelNameTextView.text = roomName
        binding.hotelAddress.text = hotelAddress
        binding.hotelPriceTextView.text = roomPrice
        binding.hotelRatingBar.rating = roomRating

        if (roomImage != null) {
            // Load image from Firebase URL using Picasso
            Picasso.get()
                .load(roomImage)
                .into(binding.hotelImg)
        } else {
            // Set default image or hide image view
            // For example, set a default image from resources:
            binding.hotelImg.setImageResource(R.drawable.pic2)
        }
    }



    // set giá trị cho các thành phần khác
    }

