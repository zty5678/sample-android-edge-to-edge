package io.zty5678.demo.edgetoedge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import io.zty5678.demo.edgetoedge.data.SentenceItem
import io.zty5678.demo.edgetoedge.databinding.ItemListBinding
import io.zty5678.demo.edgetoedge.ui.HomeListAdapter.ItemVH

class HomeListAdapter : ListAdapter<SentenceItem, ItemVH>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        return ItemVH.from(parent)
    }

    override fun onBindViewHolder(vh: ItemVH, position: Int) {
        val data = currentList[position]
        vh.bind(data, position)
        vh.itemView.setOnClickListener { onItemClickListener?.onItemClicked(it, data, position) }
    }

    private var onItemClickListener: OnItemClickListener<SentenceItem>? = null
    fun setOnItemClickListener(listener: OnItemClickListener<SentenceItem>) {
        onItemClickListener = listener
    }

    interface OnItemClickListener<T> {
        fun onItemClicked(v: View?, data: T, position: Int)
    }

    class ItemVH(private val binding: ItemListBinding) : ViewHolder(binding.root) {
        fun bind(item: SentenceItem, position: Int) {
            binding.txtvContent.text = item.content
        }

        companion object {
            fun from(parent: ViewGroup): ItemVH {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemListBinding.inflate(inflater, parent, false)
                return ItemVH(binding)
            }
        }

    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<SentenceItem>() {
            override fun areItemsTheSame(oldItem: SentenceItem, newItem: SentenceItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SentenceItem, newItem: SentenceItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}