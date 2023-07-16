package com.dkgtech.dwallpaper.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dkgtech.dwallpaper.databinding.FragmentHomeBinding
import com.dkgtech.dwallpaper.databinding.SearchWallpaperRowBinding
import com.dkgtech.dwallpaper.databinding.WallpaperRowBinding
import com.dkgtech.dwallpaper.model.PhotosModel

class RecyclerWallpaperAdapter(val context: Context, val listPhotos: List<PhotosModel>) :
    RecyclerView.Adapter<RecyclerWallpaperAdapter.ViewHolder>() {
    class ViewHolder(val binding: SearchWallpaperRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchWallpaperRowBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listPhotos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            Glide.with(context).load(Uri.parse(listPhotos[position].srcModel!!.portrait))
                .into(imgSrc)
        }
    }
}