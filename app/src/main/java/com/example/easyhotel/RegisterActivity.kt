package com.example.easyhotel

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.easyhotel.Adapter.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var buttonRegister: Button
    private lateinit var textViewLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextPhone = findViewById(R.id.editTextPhone)
        buttonRegister = findViewById(R.id.buttonRegister)
        textViewLogin = findViewById(R.id.textViewLogin)

        buttonRegister.setOnClickListener {
            registerUser()
        }

        textViewLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun registerUser() {
        val name = editTextName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        val phone = editTextPhone.text.toString().trim()

        if (name.isEmpty()) {
            editTextName.error = "Tên không được để trống"
            editTextName.requestFocus()
            return
        }

        if (email.isEmpty()) {
            editTextEmail.error = "Email không được để trống"
            editTextEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.error = "Email không hợp lệ"
            editTextEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            editTextPassword.error = "Mật khẩu không được để trống"
            editTextPassword.requestFocus()
            return
        }

        if (password.length < 6) {
            editTextPassword.error = "Mật khẩu phải có ít nhất 6 kí tự"
            editTextPassword.requestFocus()
            return
        }

        if (phone.isEmpty()) {
            editTextPhone.error = "Số điện thoại không được để trống"
            editTextPhone.requestFocus()
            return
        }

        if (!Patterns.PHONE.matcher(phone).matches()) {
            editTextPhone.error = "Số điện thoại không hợp lệ"
            editTextPhone.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userId = user?.uid ?: ""

                    val userInfo = User(userId, name, email, phone, password)

                    database.child("users").child(userId).setValue(userInfo)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this, "Đăng ký thành công",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(
                                    this, "Lỗi đăng ký",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                } else {
                    Toast.makeText(
                        this, "Đăng ký thất bại",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
