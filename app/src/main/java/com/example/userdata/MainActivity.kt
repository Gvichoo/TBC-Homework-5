package com.example.userdata

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userdata.databinding.ActivityMainBinding

data class Users(val Fullname : String , val Email : String)
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val lisOfUser = mutableListOf<Users>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.AddUser.setOnClickListener {
            addUser()
        }

        binding.GetUserInfoBtn.setOnClickListener {
            getUserInfo()
        }
    }

    private fun addUser() {
        val fullName = binding.Fullname.text.toString()
        val email = binding.Email.text.toString()

        if (fullName.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Enter FullName and Email(maybe one of them is empty)! ", Toast.LENGTH_SHORT).show()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Enter correct Email!", Toast.LENGTH_SHORT).show()
            return
        }

        lisOfUser.add(Users(fullName, email))
        updateUserList()

        Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()

        binding.Fullname.text.clear()
        binding.Email.text.clear()
    }

    private fun getUserInfo() {
        val email = binding.SearchEmail.text.toString()

        if (email.isEmpty()) {
            Toast.makeText(this, "Enter Email!", Toast.LENGTH_SHORT).show()
            return
        }

        val user = lisOfUser.find { it.Email == email }

        if (user != null) {
            binding.UserInfo.text = "FullName: ${user.Fullname}\nEmail: ${user.Email}"
        } else {
            binding.UserInfo.text = "User not found"
        }
        binding.SearchEmail.text.clear()
    }

    private fun updateUserList() {
        binding.NumOfUsers.text = "Users -> ${lisOfUser.size}"
    }
}