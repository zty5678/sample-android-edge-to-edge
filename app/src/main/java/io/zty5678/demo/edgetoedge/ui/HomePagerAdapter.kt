package io.zty5678.demo.edgetoedge.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.zty5678.demo.edgetoedge.R
import io.zty5678.demo.edgetoedge.data.SentenceCategory
import java.util.Locale.Category

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

class HomePagerAdapter(private val activity: FragmentActivity) :
    FragmentStateAdapter(activity) {

    fun getTitle(i: Int): String {
        return activity.resources.getString(TAB_TITLES[i])
    }

    override fun getItemCount(): Int {
        return TAB_TITLES.size
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return PlaceholderListFragment.newInstance(SentenceCategory.SENTENCE)
            1 -> return PlaceholderListFragment.newInstance(SentenceCategory.JOKE)
        }

        return PlaceholderListFragment.newInstance(SentenceCategory.SENTENCE)
    }
}