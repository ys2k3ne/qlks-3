package com.example.easyhotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyhotel.Adapter.HotDeal
import com.example.easyhotel.Adapter.HotDealsAdapter
import com.example.easyhotel.Adapter.Room
import com.example.easyhotel.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var ds:ArrayList<Room>
    private lateinit var dbRef: DatabaseReference
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bookNowButton.setOnClickListener {
            // Xử lý sự kiện khi nút đặt phòng nhanh được bấm
        }
        binding.drawerLayout
        binding.searchButton.setOnClickListener {
            val intent = Intent(this,RoomListActivity::class.java)
            startActivity(intent)
            // Xử lý sự kiện khi nút tìm kiếm được bấm
        }
        binding.historyButton.setOnClickListener {
            // Xử lý sự kiện khi nút lịch sử được bấm
        }
        // Đặt sự kiện cho thanh công cụ
        // Đăng ký thanh công cụ với activity
//        setSupportActionBar(binding.toolbar)
//
//// Đặt tiêu đề cho thanh công cụ
//        supportActionBar?.title = "EasyHotel"
//
//// Hiển thị nút home để người dùng có thể trở về activity trước đó
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//// Xử lý sự kiện khi nút home được bấm
//        binding.toolbar.setNavigationOnClickListener {
//            onBackPressed()
//        }

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            // Xử lý sự kiện khi người dùng chọn một mục trong thanh menu
            true
        }
        // Đặt sự kiện cho RecyclerView
        binding.hotDealsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.hotDealsRecyclerView.adapter = HotDealsAdapter(getHotDeals())
    }
    // Hàm lấy danh sách phòng khách sạn đang hot hoặc các chương trình khuyến mãi
    private fun getHotDeals(): List<HotDeal> {
        val hotDeals = ArrayList<HotDeal>()
        hotDeals.add(
            HotDeal(
                "Giảm giá đặt phòng sớm",
                "Đặt phòng trước 7 ngày để được giảm giá 10%",
                Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)
            )
        )
        hotDeals.add(
            HotDeal(
                "Ưu đãi đặc biệt cho thành viên",
                "Thành viên của chúng tôi sẽ được giảm giá 20% khi đặt phòng trực tuyến",
                Date(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000)
            )
        )
        // Thêm các chương trình khuyến mãi khác vào đây

        return hotDeals
    }

}
