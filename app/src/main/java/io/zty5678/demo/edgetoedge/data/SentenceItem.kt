package io.zty5678.demo.edgetoedge.data

import android.os.Parcel
import android.os.Parcelable

enum class SentenceCategory {
    SENTENCE, JOKE
}


data class SentenceItem(val category: SentenceCategory?, val content: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        category = SentenceCategory.valueOf(parcel.readString() ?: ""),
        content = parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category?.name)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SentenceItem> {
        override fun createFromParcel(parcel: Parcel): SentenceItem {
            return SentenceItem(parcel)
        }

        override fun newArray(size: Int): Array<SentenceItem?> {
            return arrayOfNulls(size)
        }
    }
}

