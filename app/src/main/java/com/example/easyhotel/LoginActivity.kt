package com.example.easyhotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Lấy đối tượng Button từ ID
        val buttonLogin: Button = findViewById(R.id.buttonLogin)
        val edtUserName: EditText = findViewById(R.id.edtUserName)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)

        // Thiết lập sự kiện click cho nút đăng nhập
        buttonLogin.setOnClickListener {
            // Lấy giá trị tên đăng nhập và password từ EditText
            val username: String = edtUserName.text.toString().trim()
            val password: String = editTextPassword.text.toString().trim()

            // Kiểm tra tên đăng nhập và password có rỗng không
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            // Đăng nhập bằng FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Đăng nhập thành công, chuyển đến activity chính
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        // Đăng nhập thất bại, hiển thị thông báo lỗi
                        Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}