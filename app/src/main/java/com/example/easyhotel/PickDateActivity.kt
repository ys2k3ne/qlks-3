package com.example.easyhotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import com.google.firebase.database.FirebaseDatabase
import java.util.*
class PickDateActivity : AppCompatActivity() {

    private lateinit var checkInDatePicker: DatePicker
    private lateinit var checkOutDatePicker: DatePicker
    private lateinit var confirmButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_date)


        checkInDatePicker = findViewById(R.id.check_in_date_picker)
        checkOutDatePicker = findViewById(R.id.check_out_date_picker)
        confirmButton = findViewById(R.id.confirm_button)

        // Set click listener for the confirm button to return selected dates to previous activity
        confirmButton.setOnClickListener {
            val checkInDate = getDateFromDatePicker(checkInDatePicker)
            val checkOutDate = getDateFromDatePicker(checkOutDatePicker)
            val returnIntent = Intent()
            returnIntent.putExtra("checkInDate", checkInDate.time)
            returnIntent.putExtra("checkOutDate", checkOutDate.time)
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }

    // Helper function to get a Date object from a DatePicker
    private fun getDateFromDatePicker(datePicker: DatePicker): Date {
        val day = datePicker.dayOfMonth
        val month = datePicker.month
        val year = datePicker.year

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        return calendar.time
    }
}
