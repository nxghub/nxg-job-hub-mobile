package com.nxtgenhub.mynda.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.nxtgenhub.mynda.R
import com.nxtgenhub.mynda.databinding.ActivityMainBinding
import com.nxtgenhub.mynda.viewmodel.OnBoardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val onBoardViewModel by viewModels<OnBoardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        onBoardViewModel.onBoardingComplete.observe(this) { completed ->
            navController.graph = if (completed) {
                navController.graph.apply {
                    setStartDestination(R.id.selectRoleFragment)
                }
            } else {
                navController.graph.apply {
                    setStartDestination(R.id.onboard_fragment)
                }
            }
        }
    }
}