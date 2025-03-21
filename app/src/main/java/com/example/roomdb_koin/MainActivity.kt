package com.example.roomdb_koin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdb_koin.adapter.UserAdapter
import com.example.roomdb_koin.databinding.ActivityMainBinding
import com.example.roomdb_koin.model.User
import com.example.roomdb_koin.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedUser: User? = null

    // Inject ViewModel bằng Koin
    private val viewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = UserAdapter { user ->
            selectedUser = user
            binding.nameEditText.setText(user.name)
            binding.emailEditText.setText(user.email)
        }

        binding.recyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewModel.allUsers.observe(this) { users ->
            adapter.submitList(users)
        }

        binding.addButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            if (name.isNotEmpty() && email.isNotEmpty()) {
                viewModel.insert(User(name = name, email = email))
                clearInputs()
            }
        }

        binding.updateButton.setOnClickListener {
            selectedUser?.let { user ->
                val name = binding.nameEditText.text.toString()
                val email = binding.emailEditText.text.toString()
                if (name.isNotEmpty() && email.isNotEmpty()) {
                    viewModel.update(user.copy(name = name, email = email))
                    clearInputs()
                    selectedUser = null
                }
            }
        }
    }

    private fun clearInputs() {
        binding.nameEditText.text.clear()
        binding.emailEditText.text.clear()
    }
}