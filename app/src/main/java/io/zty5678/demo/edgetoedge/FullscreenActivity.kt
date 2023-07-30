package io.zty5678.demo.edgetoedge

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updatePadding
import io.zty5678.demo.edgetoedge.utils.LogUtils
import io.zty5678.demo.edgetoedge.utils.WindowPreferencesManager
import io.zty5678.demo.edgetoedge.utils.doOnApplyWindowInsets

class FullscreenActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FullscreenActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowPreferencesManager(this).applyEdgeToEdgePreference(window)

        // 设置布局
        setContentView(R.layout.activity_fullscreen)

        val view: View = findViewById(R.id.rootview);
        val btn_test: Button = findViewById(R.id.btn_test);


        btn_test.setOnClickListener {
            LogUtils.d("isFullscreen=" + isFullscreen)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                window.decorView.windowInsetsController!!.setSystemBarsBehavior(
                    WindowInsetsController.BEHAVIOR_DEFAULT);
                if (!isFullscreen) {
                    window.decorView.windowInsetsController!!.hide(WindowInsets.Type.systemBars())
                } else {
                    window.decorView.windowInsetsController!!.show(WindowInsets.Type.systemBars())
                }
            } else {
                if (!isFullscreen) {
                    window.setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN
                    )
                } else {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
            }

            isFullscreen = !isFullscreen;
            btn_test.text = if (isFullscreen) "退出全屏" else "进入全屏";

            LogUtils.d("end click isFullscreen=" + isFullscreen)
        }
        btn_test.text = if (isFullscreen) "退出全屏" else "进入全屏";

        LogUtils.d("height=" + getStatusBarHeight())

        view.doOnApplyWindowInsets { view, insets, initialPadding ->
            LogUtils.d(" initialPadding.top=" + initialPadding.top + ", insets.top=" + insets.top)
            if (isFullscreen) {
                view.updatePadding(top = initialPadding.top + insets.top + getStatusBarHeight())
            } else {
                view.updatePadding(top = initialPadding.top + insets.top)
            }

        }
    }

    fun getStatusBarHeight(): Int {
        var statusBarHeight = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId)
        }
        return statusBarHeight
    }

    var isFullscreen = false;
}