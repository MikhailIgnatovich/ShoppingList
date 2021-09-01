package com.bulich.misha.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bulich.misha.shoppinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupRecycler()
        viewModel.shopList.observe(this) {
            adapter.shopList = it
        }
    }

    private fun setupRecycler() {
        val recycler = findViewById<RecyclerView>(R.id.rv_shop_list)
        adapter = ShopListAdapter()
        recycler.adapter = adapter
    }
}