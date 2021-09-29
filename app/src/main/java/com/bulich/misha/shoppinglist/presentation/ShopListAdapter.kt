package com.bulich.misha.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bulich.misha.shoppinglist.R
import com.bulich.misha.shoppinglist.databinding.ItemShopDisabledBinding
import com.bulich.misha.shoppinglist.databinding.ItemShopEnabledBinding
import com.bulich.misha.shoppinglist.databinding.ItemShopEnabledBindingImpl
import com.bulich.misha.shoppinglist.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter :
    ListAdapter<ShopItem, ShopListAdapter.ShopListViewHolder>(ShopIItemDiffCallback()) {

//    var shopList = listOf<ShopItem>()
//        set(value) {
//            val callback = ShopListDiffCallback(shopList, value)
//            val diffResult = DiffUtil.calculateDiff(callback)
//            diffResult.dispatchUpdatesTo(this)
//            field = value
//        }

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    class ShopListViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return ShopListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = holder.binding
        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        when (binding) {
            is ItemShopDisabledBinding -> {
                binding.tvName.text = shopItem.name
                binding.tvCount.text = shopItem.count.toString()
            }
            is ItemShopEnabledBinding -> {
                binding.tvName.text = shopItem.name
                binding.tvCount.text = shopItem.count.toString()
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }


    companion object {
        const val VIEW_TYPE_DISABLED = 100
        const val VIEW_TYPE_ENABLED = 101

        const val MAX_POOL_SIZE = 15
    }
}