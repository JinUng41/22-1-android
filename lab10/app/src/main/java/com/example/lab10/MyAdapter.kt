package com.example.lab10

import android.content.ContentUris
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab10.databinding.ItemBinding

data class Item (val id: Long, val title : String)

class MyViewHolder (val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter(private val context : Context, private val items: MutableList<Item>):
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]

        holder.binding.textView.text = item.title

        val collection = if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.Q)
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        else
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val contentUri = ContentUris.withAppendedId(collection, item.id)
        val bitmap = context.contentResolver.openInputStream(contentUri)?.use { BitmapFactory.decodeStream(it) }
        holder.binding.imageView.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}