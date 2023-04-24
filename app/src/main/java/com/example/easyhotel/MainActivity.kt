package com.example.easyhotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easyhotel.Adapter.HotDeal
import com.example.easyhotel.Adapter.HotDealsAdapter
import com.example.easyhotel.Adapter.Room
import com.example.easyhotel.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference
import java.util.*
import kotlin.collections.ArrayList
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private lateinit var ds: ArrayList<Room>
    private lateinit var dbRef: DatabaseReference
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up action bar
        setSupportActionBar(binding.toolbar)

        // Đăng ký sự kiện click cho nút back
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Đăng ký sự kiện cho RecyclerView
        binding.hotDealsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.hotDealsRecyclerView.adapter = HotDealsAdapter(getHotDeals())

        // Đăng ký sự kiện click cho nút đặt phòng nhanh
        binding.bookNowButton.setOnClickListener {
            val intent = Intent(this, InsertActivity::class.java)
            startActivity(intent)
        }

        // Đăng ký sự kiện click cho nút tìm kiếm
        binding.searchButton.setOnClickListener {
            val intent = Intent(this, RoomListActivity::class.java)
            startActivity(intent)
        }

        // Đăng ký sự kiện click cho nút lịch sử đặt phòng
        binding.historyButton.setOnClickListener {
            val intent = Intent(this, LoginSignupActivity::class.java)
            startActivity(intent)
            // Xử lý sự kiện khi nút lịch sử được bấm
        }

        // Đăng ký sự kiện click cho thanh menu
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_search -> {
                    // Xử lý sự kiện khi người dùng chọn item Search
                    Toast.makeText(this, "Search selected", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_person -> {
                    val intent = Intent(this, LoginSignupActivity::class.java)
                    startActivity(intent)
                    true
                }
                // Thêm các item khác tương tự ở đây
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_search -> {
                Toast.makeText(this, "Search selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_person -> {
                val intent = Intent(this, LoginSignupActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
