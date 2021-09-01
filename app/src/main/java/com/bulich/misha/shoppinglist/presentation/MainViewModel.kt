package com.bulich.misha.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulich.misha.shoppinglist.data.ShopListRepositoryIMPL
import com.bulich.misha.shoppinglist.domain.DeleteShopItemUseCase
import com.bulich.misha.shoppinglist.domain.EditShopItemUseCase
import com.bulich.misha.shoppinglist.domain.GetShopListUseCase
import com.bulich.misha.shoppinglist.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryIMPL

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()


    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}