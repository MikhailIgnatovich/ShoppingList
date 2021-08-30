package com.bulich.misha.shoppinglist.data

import com.bulich.misha.shoppinglist.domain.ShopItem
import com.bulich.misha.shoppinglist.domain.ShopListRepository
import java.lang.RuntimeException

object ShopListRepositoryIMPL : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementID = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementID++
        }
        shopList.add(shopItem)
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId }
            ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        shopList.add(shopItem)

    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }
}