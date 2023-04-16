package io.zty5678.demo.edgetoedge.data;

import android.content.Context
import io.zty5678.demo.edgetoedge.utils.LogUtils
import io.zty5678.demo.edgetoedge.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.concurrent.thread

class OfflineItemsRepository(private val context: Context) : ItemsRepository {
    override fun getAllSentenceList(category: SentenceCategory): Flow<List<SentenceItem>> {

        var list = emptyList<String>()

        when (category) {
            SentenceCategory.SENTENCE -> {
                list = context.resources.getStringArray(R.array.sample_sentence_list).asList()

            }
            SentenceCategory.JOKE -> {
                list = context.resources.getStringArray(R.array.sample_joke_list).asList()

            }
        }

        var itemList: List<SentenceItem> = list.map { sentence ->
            SentenceItem(category, sentence)
        }

        return flow {
            emit(itemList)
        }


    }

}


