package com.example.erp_qr.Login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.erp_qr.MainActivity
import com.example.erp_qr.R
import com.example.erp_qr.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val loginViewModel: LoginViewModel? by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = loginViewModel
        binding.activity = this
        binding.lifecycleOwner = this
        setObserve()

    }

    fun setObserve(){
        loginViewModel?.loginSuccess?.observe(this){
            if(it) {
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}