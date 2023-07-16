package com.dkgtech.dwallpaper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dkgtech.dwallpaper.databinding.ColorRowBinding
import com.dkgtech.dwallpaper.model.ColorModel
import com.dkgtech.dwallpaper.ui.home.HomeFragment

class RecyclerColorAdapter(val context: Context, val arrColors: ArrayList<ColorModel>) :
    RecyclerView.Adapter<RecyclerColorAdapter.ViewHolder>() {
    class ViewHolder(val binding: ColorRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ColorRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrColors.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            imgColor.setImageResource(arrColors[position].colorPath)

//            imgColor.setOnClickListener {
//                (context as HomeFragment).getWallpaperAccToColor(arrColors[position].colorHex)
//            }

        }
    }
}