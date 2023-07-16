package com.dkgtech.dwallpaper

import android.app.WallpaperManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dkgtech.dwallpaper.databinding.ActivityFullScreenWallpaperBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FullScreenWallpaperActivity : AppCompatActivity() {
    lateinit var binding: ActivityFullScreenWallpaperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val imageUrl = intent.getStringExtra(SearchWallpaperActivity.IMAGE_KEY)

            imageUrl?.let {
                Glide.with(this@FullScreenWallpaperActivity).load(Uri.parse(imageUrl))
                    .into(imgFullWallpaper)
            }

            btnSet.setOnClickListener {

                GlobalScope.launch(Dispatchers.IO) {
                    val bitmap = Picasso.get().load(Uri.parse(imageUrl)).get()
                    val wallManager = WallpaperManager.getInstance(this@FullScreenWallpaperActivity)
                    wallManager.setBitmap(bitmap)
                }
                Toast.makeText(
                    this@FullScreenWallpaperActivity,
                    "Wallpaper Set Successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }

}