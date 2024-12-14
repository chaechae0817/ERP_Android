package com.example.erp_qr.notification

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.erp_qr.MainViewModel
import com.example.erp_qr.R
import com.example.erp_qr.adapter.NotificationAdapter
import com.example.erp_qr.databinding.ActivityNotificationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = NotificationAdapter { notification ->
        viewModel.markNotificationAsRead(notification.id)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_notification)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setObserve()
        setRecyclerView()

    }

    private fun setRecyclerView(){
        binding.notificaitonRv.adapter = adapter
        binding.notificaitonRv.layoutManager = LinearLayoutManager(this)
    }


    private fun setObserve(){
        viewModel.notificationData.observe(this){ notificationList ->
            adapter.submitList(notificationList)
        }
    }
}