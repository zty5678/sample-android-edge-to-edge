package io.zty5678.demo.edgetoedge

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import io.zty5678.demo.edgetoedge.utils.WindowPreferencesManager
import io.zty5678.demo.edgetoedge.databinding.ActivityMainBinding
import io.zty5678.demo.edgetoedge.ui.HomePagerAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowPreferencesManager(this).applyEdgeToEdgePreference(window)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(
            binding.root

        ) { _, insetsCompat: WindowInsetsCompat ->

            val insets: Insets = insetsCompat.getInsets(WindowInsetsCompat.Type.systemBars())

            binding.rootview.setPadding(0, insets.top, 0, 0)

            if (binding.fab.layoutParams is ViewGroup.MarginLayoutParams) {
                val layoutParams = (binding.fab.layoutParams as ViewGroup.MarginLayoutParams)
                layoutParams.bottomMargin = insets.bottom
            }

            insetsCompat
        }


        val sectionsPagerAdapter = HomePagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter


        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = sectionsPagerAdapter.getTitle(position)
        }.attach()

        val fab: FloatingActionButton = binding.fab

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()


        }

    }
}