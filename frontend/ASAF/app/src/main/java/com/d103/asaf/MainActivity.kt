package com.d103.asaf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.d103.asaf.common.config.ApplicationClass
import com.d103.asaf.common.config.BaseActivity
import com.d103.asaf.common.model.dto.Member
import com.d103.asaf.common.util.MyFirebaseMessagingService
import com.d103.asaf.databinding.ActivityMainBinding
import com.d103.asaf.ui.login.LoginFragmentViewModel
import com.d103.asaf.ui.home.pro.ProHomeFragment
import com.d103.asaf.ui.schedule.NotiRegisterFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import com.ssafy.template.util.SharedPreferencesUtil
import com.tbuonomo.morphbottomnavigation.MorphBottomNavigationView
import java.sql.Date

private const val TAG = "MainActivity"
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){

    private lateinit var user : Member
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    var waitTime = 0L
    private val viewModel : SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.bottomNaviPro.visibility = View.GONE
        binding.bottomNaviStudent.visibility = View.GONE
        setupNavHost()
        setSupportActionBar(findViewById(com.airbnb.lottie.R.id.action_bar));


        // 토큰 가져오기
//        MyFirebaseMessagingService().getFirebaseToken()
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d(TAG, "토큰 생성: $token")
            if (token != null) {
                ApplicationClass.sharedPreferences.addFCMToken(token)
            }
//            Log.d(TAG, msg)1111
//            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
        initDynamicLink()


        // Check if the user is logged in
        val isLoggedIn = ApplicationClass.sharedPreferences.getString("memberEmail")?.isNotEmpty() == true

        if (isLoggedIn) {
            // Perform any actions or initialization needed for a logged-in user
            // For example, you might want to navigate the user to the appropriate screen
            val userAuthority = ApplicationClass.sharedPreferences.getString("authority")
            if (userAuthority == "교육생") {
                // Navigate to the student home fragment
                findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.action_login_fragment_to_StudentHomeFragment)
//                findNavController(R.id.nav_host_fragment_activity_main).popBackStack()
            } else {
                // Navigate to the pro home fragment
                findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.action_loginFragment_to_ProhomeFragment)
//                findNavController(R.id.nav_host_fragment_activity_main).popBackStack()
            }
        } else {
            // The user is not logged in or there is no saved data
            // Perform any other necessary initialization for a new session
            // For example, you might want to show the login fragment
            findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.login_fragment)
        }
    }
    private fun initDynamicLink() {
        val dynamicLinkData = intent.extras
        if (dynamicLinkData != null) {
            var dataStr = "DynamicLink 수신받은 값\n"
            for (key in dynamicLinkData.keySet()) {
                dataStr += "key: $key / value: ${dynamicLinkData.getString(key)}\n"
            }

            Log.d(TAG, "알림: $dataStr")
        }
    }

    private fun setupNavHost() {
        // NavHostFragment를 가져와서 설정합니다.
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.login_fragment -> hideBottomNavigationBar()
                R.id.navigation_setting -> hideBottomNavigationBar()
                R.id.ProhomeFragment -> showProBottomNavigationBarFromFragment()
                R.id.scheduleFragment -> showProBottomNavigationBarFromFragment()
                R.id.StudentHomeFragment -> {
                    showStudentBottomNaviagtionBarFromFragment()
                    // 없던 부분
                    val bottomNavigationView =
                        findViewById<MorphBottomNavigationView>(R.id.bottom_navi_student)
                    bottomNavigationView.setupWithNavController(navController)
                }
            }
        }


//        // 없던 부분
        val bottomNavigationView =
            findViewById<MorphBottomNavigationView>(R.id.bottom_navi_pro)
        bottomNavigationView.setupWithNavController(navController)
    }


    // Create a public method to hide the bottom navigation bar.
    fun hideBottomNavigationBarFromFragment() {
        hideBottomNavigationBar()
    }
//    fun showBottomNavigationBarFromFragment() {
//        showBottomNavigationBar()
//    }
    fun showProBottomNavigationBarFromFragment() {
        showProBottomNavigationBar()
        //바텀 네브 바 상단에 fragment 위치시키는 코드
        val layout = findViewById<View>(R.id.nav_host_fragment_activity_main)
        val layoutParams = layout.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.bottomToTop = R.id.bottom_navi_pro
        layout.layoutParams = layoutParams
    }

    fun showStudentBottomNaviagtionBarFromFragment() {
        showStudentBottomNavigationBar()


        //바텀 네브 바 상단에 fragment 위치시키는 코드
        val layout = findViewById<View>(R.id.nav_host_fragment_activity_main)
        val layoutParams = layout.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.bottomToTop = R.id.bottom_navi_student
        layout.layoutParams = layoutParams
    }








}