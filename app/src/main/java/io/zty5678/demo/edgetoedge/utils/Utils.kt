package io.zty5678.demo.edgetoedge.utils

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import io.zty5678.demo.edgetoedge.DetailActivity
import io.zty5678.demo.edgetoedge.data.SentenceItem

//if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//    intent.getParcelableExtra(DetailActivity.BUNDLE_KEY_SENTENCE, SentenceItem::class.java)
//} else {
//    intent.getParcelableExtra<SentenceItem>(DetailActivity.BUNDLE_KEY_SENTENCE)
//}

inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key)
    } else {
        getParcelableExtra(key) as? T
    }
}