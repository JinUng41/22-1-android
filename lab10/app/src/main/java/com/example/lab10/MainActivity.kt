package com.example.lab10

import android.Manifest
import android.content.*
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val broadcastReceiver = MyBroadcastReceiver()
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    val items = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = MyAdapter(this, items)
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED) {
            val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                // if true false
            }
            launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        val collection = if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q)
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        else
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val project = arrayOf(MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.TITLE)
        val cursor = contentResolver.query(collection, project, null, null, null)
        items.clear()
        cursor?.apply {
            val idCol = getColumnIndex(MediaStore.Images.ImageColumns._ID)
            val idTitle = getColumnIndex(MediaStore.Images.ImageColumns.TITLE)
            while (moveToNext()){
                val id = getLong(idCol)
                val title = getString(idTitle)
                items.add(Item(id, title))

                println(title)
            }
            close()
        }

    }

    override fun onStart() {
        super.onStart()
        val ifilter = IntentFilter()
        ifilter.addAction(ACTION_MY_BROADCAST)
        registerReceiver(broadcastReceiver, ifilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    inner class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            when (p1?.action) {
                ACTION_MY_BROADCAST -> {
                    binding.textViewBroadcast.text = ACTION_MY_BROADCAST
                }
            }
        }
    }

    companion object {
        const val ACTION_MY_BROADCAST = "ACTION_MY_BROADCAST"
    }
}