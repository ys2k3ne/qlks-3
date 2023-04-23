package com.example.easyhotel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.easyhotel.Adapter.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var buttonRegister: Button
    private lateinit var textViewLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextPhone = findViewById(R.id.editTextPhone)
        buttonRegister = findViewById(R.id.buttonRegister)
        textViewLogin = findViewById(R.id.textViewLogin)

        buttonRegister.setOnClickListener {
            // Xử lý đăng ký tài khoản ở đây
            val name = editTextName.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val phone = editTextPhone.text.toString()

            // Kiểm tra dữ liệu có hợp lệ hay không
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phone.isNotEmpty()) {
                // Đăng ký tài khoản bằng email và password sử dụng Firebase Authentication
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Lưu thông tin người dùng vào csdl Firebase Realtime Database
                            val userId = FirebaseAuth.getInstance().currentUser?.uid
                            val database = FirebaseDatabase.getInstance().reference
                            val user = User(name, email, password, phone)
                            database.child("users").child(userId!!).setValue(user)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        // Đăng ký tài khoản thành công, chuyển đến màn hình chính
                                        startActivity(Intent(this, MainActivity::class.java))
                                        finish()
                                    } else {
                                        // Lỗi khi lưu thông tin người dùng vào csdl
                                        Toast.makeText(this, "Lỗi khi lưu thông tin người dùng", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        } else {
                            // Lỗi khi đăng ký tài khoản
                            Toast.makeText(this, "Lỗi khi đăng ký tài khoản", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                // Hiển thị thông báo lỗi khi dữ liệu không hợp lệ
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }

        }

        textViewLogin.setOnClickListener {
            // Chuyển sang màn hình đăng nhập ở đây
        }

//        textViewForgotPassword.setOnClickListener {
//            // Xử lý quên mật khẩu ở đây
//        }
//
//        textViewTermsAndConditions.setOnClickListener {
//            // Xử lý điều khoản sử dụng ở đây
//        }
    }
}
