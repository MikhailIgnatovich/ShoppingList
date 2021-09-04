package com.bulich.misha.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.bulich.misha.shoppinglist.domain.ShopItem

class ShopIItemDiffCallback : DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}