package io.zty5678.demo.edgetoedge.data

enum class SentenceCategory {
    SENTENCE, JOKE
}
data class SentenceItem(val category: SentenceCategory, val content:String)