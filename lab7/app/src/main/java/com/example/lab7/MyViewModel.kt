package com.example.lab7

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Item(val name:String, val address:String)

class MyViewModel:ViewModel() {
    val itemsLiveData=MutableLiveData<ArrayList<Item>>()

    private val items=ArrayList<Item>()
    var longClickItem:Int=-1

    init {
        addItem(Item("james", "Seoul"))
        addItem(Item("tom", "Inchon"))
        addItem(Item("tom", "Inchon"))
        addItem(Item("tom", "Inchon"))
    }

    fun getItem(pos:Int)=items[pos]
    val itemsSize
        get()=items.size

    fun addItem(item:Item){
        items.add(item)
        itemsLiveData.value=items
    }

    fun updateItem(item:Item, pos:Int){
        items[pos]=item
        itemsLiveData.value=items
    }

    fun deleteItem(pos:Int){
        items.removeAt(pos)
        itemsLiveData.value=items
    }
}