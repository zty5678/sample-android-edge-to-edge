package io.zty5678.demo.edgetoedge

import android.R
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.core.view.updatePadding
import io.zty5678.demo.edgetoedge.data.SentenceItem
import io.zty5678.demo.edgetoedge.databinding.ActivityDetailBinding
import io.zty5678.demo.edgetoedge.utils.WindowPreferencesManager
import io.zty5678.demo.edgetoedge.utils.doOnApplyWindowInsets
import io.zty5678.demo.edgetoedge.utils.getParcelableExtraCompat

class DetailActivity : AppCompatActivity() {
    companion object {

        private val BUNDLE_KEY_SENTENCE = "sentence"

        fun start(context: Context?, sentenceItem: SentenceItem) {
            val starter = Intent(context, DetailActivity::class.java)
            starter.putExtra(BUNDLE_KEY_SENTENCE, sentenceItem)
            context?.startActivity(starter)
        }
    }

    var _binding: ActivityDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowPreferencesManager(this).applyEdgeToEdgePreference(window)

        _binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.root.doOnApplyWindowInsets { view, insets, initialPadding ->
            view.updatePadding(top = initialPadding.top + insets.top)
        }
        binding.scrollContainer.doOnApplyWindowInsets { view, insets, initialPadding ->
            view.updatePadding(bottom = initialPadding.bottom + insets.bottom)
        }

        setSupportActionBar(binding.toolbar)

        with(supportActionBar) {
            this?.setDisplayHomeAsUpEnabled(true)
            this?.setDisplayShowHomeEnabled(true)
        }

        val sentenceItem = intent.getParcelableExtraCompat<SentenceItem>(BUNDLE_KEY_SENTENCE)
        binding.txtvContent.text = sentenceItem?.content

        onBackPressedDispatcher.addCallback(
            this,
            _backPressedCallback
        )
    }

    private val _backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }
}