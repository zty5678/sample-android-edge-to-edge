package io.zty5678.demo.edgetoedge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import io.zty5678.demo.edgetoedge.data.ItemsRepository
import io.zty5678.demo.edgetoedge.data.SentenceCategory
import io.zty5678.demo.edgetoedge.data.SentenceItem
import io.zty5678.demo.edgetoedge.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class MyViewModelFactory(private val repository: ItemsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
            return PageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}


class PageViewModel(val repository: ItemsRepository) : ViewModel() {


    var chatSentenceList = MutableLiveData<List<SentenceItem>>(emptyList())

    fun loadData(category: SentenceCategory) {

        viewModelScope.launch(Dispatchers.IO) {
            val sentenceList = repository.getAllSentenceList(category)
             withContext(Dispatchers.Main) {
                chatSentenceList.value = sentenceList.single()
            }
        }
    }
}