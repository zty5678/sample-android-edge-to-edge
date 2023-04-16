package io.zty5678.demo.edgetoedge.data

import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    fun getAllSentenceList(category: SentenceCategory): Flow<List<SentenceItem>>
 }