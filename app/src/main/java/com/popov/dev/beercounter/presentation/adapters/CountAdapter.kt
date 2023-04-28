package com.popov.dev.beercounter.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.popov.dev.beercounter.R
import com.popov.dev.beercounter.domain.models.CountItem


class CountAdapter :
    ListAdapter<CountItem, CountItemViewHolder>(CountItemDiffCallback()) {

    var onCountItemLongClickListener: ((CountItem) -> Unit)? = null
    var onCountItemClickListener: ((CountItem) -> Unit)? = null
    var onCountItemButtonClickListener: ((CountItem) -> Unit)? = null

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = -1
        const val MAX_POOL_SIZE = 15
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.count_item_enabled
            VIEW_TYPE_DISABLED -> R.layout.count_item_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return CountItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountItemViewHolder, position: Int) {
        holder.tvName.text = getItem(position).name
        holder.tvCount.text = getItem(position).count.toString()
        holder.view.setOnLongClickListener {
            onCountItemLongClickListener?.invoke(getItem(position))
            true
        }
        holder.view.setOnClickListener {
            onCountItemClickListener?.invoke(getItem(position))
        }
        holder.btnAddBeer.setOnClickListener {
            onCountItemButtonClickListener?.invoke(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }
}