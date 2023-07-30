package io.zty5678.demo.edgetoedge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updatePadding
import io.zty5678.demo.edgetoedge.databinding.ActivityEdgeToEdgeDemoBinding
import io.zty5678.demo.edgetoedge.databinding.ActivityMainBinding
import io.zty5678.demo.edgetoedge.utils.WindowPreferencesManager
import io.zty5678.demo.edgetoedge.utils.doOnApplyWindowInsets

class MainActivity : AppCompatActivity() {
    var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowPreferencesManager(this).applyEdgeToEdgePreference(window)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.doOnApplyWindowInsets { view, insets, initialPadding ->
            view.updatePadding(top = initialPadding.top + insets.top)
        }

        binding.btnEdgeToEdge.setOnClickListener {
            startActivity(EdgeToEdgeDemoActivity.newIntent(this))
        }
        binding.btnFullscreen.setOnClickListener {
            startActivity(FullscreenActivity.newIntent(this))
        }


    }

}