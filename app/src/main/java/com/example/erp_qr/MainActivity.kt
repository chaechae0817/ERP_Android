package com.example.erp_qr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.erp_qr.attendance.AttendanceFragment
import com.example.erp_qr.databinding.ActivityMainBinding
import com.example.erp_qr.databinding.NavHeaderBinding
import com.example.erp_qr.fragment.SalaryFragment
import com.example.erp_qr.fragment.VacationFragment
import com.example.erp_qr.login.LoginActivity
import com.example.erp_qr.notification.NotificationActivity
import com.example.erp_qr.qr.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        viewModel.getUnreadNotificationCountAndData()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        setSupportActionBar(binding.toolbar)

        setupNavigationHeader()
        setupDrawerNavigation()
        setupBottomNavigation()
        setObserve()

        // 초기 화면 설정
        if (savedInstanceState == null) {
            loadFragment(MainFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment)
            .commit()
    }


    private fun setupNavigationHeader() {
        val navigationView = binding.navigationView
        val headerView = navigationView.getHeaderView(0)

        val headerBinding = NavHeaderBinding.bind(headerView) // 바인딩 객체 생성
        headerBinding.viewModel = viewModel
        headerBinding.lifecycleOwner = this

        // SharedPreferences에서 이미지 경로 가져오기
        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("profile_image_path", "/storage/emulated/0/Download/profile_image.jpg")
        editor.apply()
        val imagePath = sharedPreferences.getString("profile_image_path", null)

        // Glide를 사용하여 이미지 표시
        if (imagePath != null) {
            Glide.with(this)
                .load(imagePath) // 저장된 파일 경로
                .into(headerBinding.navProfileImage) // 이미지 뷰에 로드

        } else {
            // 기본 이미지 설정 (이미지 경로가 없을 경우)
            headerBinding.navProfileImage.setImageResource(R.drawable.profile_placeholder)
        }
    }

    private fun setObserve(){
        viewModel.isLoggedOut.observe(this){
            if(it) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
        viewModel.notification.observe(this){
            if(it) {
                startActivity(Intent(this,NotificationActivity::class.java))
            }
        }
        viewModel.unreadCount.observe(this) { unreadCount ->
            // 배지 알림 상태 업데이트
            Log.d("MainViewModel", "Unread count updated: $unreadCount")
            binding.badgeNotification.text = unreadCount.toString()
            binding.badgeNotification.visibility = if (unreadCount > 0) View.VISIBLE else View.GONE
        }
    }

    private fun setupDrawerNavigation() {
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_logout -> viewModel.logout()
                // 다른 메뉴 처리
            }
            binding.drawLayout.closeDrawer(GravityCompat.START)
            true
        }
    }


    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    loadFragment(MainFragment())
                    binding.toolbar.title = "모바일 열람"
                }
                R.id.nav_vacation -> {
                    loadFragment(VacationFragment())
                    binding.toolbar.title = "휴가 열람"
                }
                R.id.nav_attendance -> {
                    loadFragment(AttendanceFragment())
                    binding.toolbar.title = "근태 열람"
                }
                R.id.nav_salary -> {
                    loadFragment(SalaryFragment())
                    binding.toolbar.title = "급여 열람"
                }
            }
            true
        }
    }
}