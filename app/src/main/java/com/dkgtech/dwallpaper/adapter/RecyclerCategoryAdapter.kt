package com.dkgtech.dwallpaper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dkgtech.dwallpaper.databinding.CategoryRowBinding
import com.dkgtech.dwallpaper.model.CategoryModel

class RecyclerCategoryAdapter(val context: Context, val arrCategories: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<RecyclerCategoryAdapter.ViewHolder>() {
    class ViewHolder(val binding: CategoryRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoryRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrCategories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            imgCategory.setImageResource(arrCategories[position].categoryImage)
            txtCategoryName.text = arrCategories[position].categoryName
        }
    }
}