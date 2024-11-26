package com.example.erp_qr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 상단바 설정
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.navigation_view)

        // Drawer 설정
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                //R.id.nav_profile -> loadFragment(ProfileFragment())
                //R.id.nav_settings -> loadFragment(SettingsFragment())
                R.id.nav_logout -> logout()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // 하단 네비게이션 설정
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> loadFragment(MainFragment())
                R.id.nav_vacation -> loadFragment(VacationFragment())
                R.id.nav_attendance -> loadFragment(AttendanceFragment())
                R.id.nav_salary -> loadFragment(SalaryFragment())
            }
            true
        }

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

    private fun logout() {
        // 로그아웃 처리 로직
        // 예: SharedPreferences 초기화 후 로그인 화면으로 이동
    }
}