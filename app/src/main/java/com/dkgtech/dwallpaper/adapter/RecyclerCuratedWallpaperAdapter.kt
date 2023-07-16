package com.dkgtech.dwallpaper.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dkgtech.dwallpaper.databinding.WallpaperRowBinding
import com.dkgtech.dwallpaper.model.Photos

class RecyclerCuratedWallpaperAdapter(val context: Context, val listCuratedPhotos: List<Photos>) :
    RecyclerView.Adapter<RecyclerCuratedWallpaperAdapter.ViewHolder>() {
    class ViewHolder(val binding: WallpaperRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(WallpaperRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return listCuratedPhotos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            Glide.with(context).load(Uri.parse(listCuratedPhotos[position].src!!.portrait))
                .into(imgSrc)
        }
    }
}