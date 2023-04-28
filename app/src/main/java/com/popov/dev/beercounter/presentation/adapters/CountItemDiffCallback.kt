package com.popov.dev.beercounter.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.popov.dev.beercounter.domain.models.CountItem

class CountItemDiffCallback : DiffUtil.ItemCallback<CountItem>() {
    override fun areItemsTheSame(oldItem: CountItem, newItem: CountItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CountItem, newItem: CountItem): Boolean {
        return oldItem == newItem
    }
}