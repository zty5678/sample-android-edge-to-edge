package io.zty5678.demo.edgetoedge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updatePadding
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import io.zty5678.demo.edgetoedge.utils.WindowPreferencesManager
import io.zty5678.demo.edgetoedge.databinding.ActivityEdgeToEdgeDemoBinding
import io.zty5678.demo.edgetoedge.ui.HomePagerAdapter
import io.zty5678.demo.edgetoedge.utils.doOnApplyWindowInsets

class EdgeToEdgeDemoActivity : AppCompatActivity() {
    var _binding: ActivityEdgeToEdgeDemoBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, EdgeToEdgeDemoActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowPreferencesManager(this).applyEdgeToEdgePreference(window)

        _binding = ActivityEdgeToEdgeDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)



        binding.root.doOnApplyWindowInsets { view, insets, initialPadding ->
            view.updatePadding(top = initialPadding.top + insets.top)
        }
        binding.fab.doOnApplyWindowInsets { view, insets, initialPadding ->
            if (view.layoutParams is ViewGroup.MarginLayoutParams) {
                val layoutParams = (view.layoutParams as ViewGroup.MarginLayoutParams)
                layoutParams.bottomMargin = insets.bottom
            }
        }

        val sectionsPagerAdapter = HomePagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter


        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = sectionsPagerAdapter.getTitle(position)
        }.attach()


        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()


        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }
}