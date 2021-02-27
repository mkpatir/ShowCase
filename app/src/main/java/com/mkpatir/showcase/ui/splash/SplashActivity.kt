package com.mkpatir.showcase.ui.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.mkpatir.showcase.R
import com.mkpatir.showcase.databinding.ActivitySplashBinding
import com.mkpatir.showcase.ui.base.BaseActivity
import com.mkpatir.showcase.ui.base.EmptyViewModel
import com.mkpatir.showcase.ui.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding,EmptyViewModel>() {

    override fun setLayout(): Int = R.layout.activity_splash

    override fun setViewModel(): Lazy<EmptyViewModel> = viewModel()

    override fun setupUI() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        },1000L)
    }

}