package com.dkgtech.dwallpaper.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dkgtech.dwallpaper.R
import com.dkgtech.dwallpaper.adapter.RecyclerCategoryAdapter
import com.dkgtech.dwallpaper.adapter.RecyclerColorAdapter
import com.dkgtech.dwallpaper.adapter.RecyclerCuratedWallpaperAdapter
import com.dkgtech.dwallpaper.api.ApiHelper
import com.dkgtech.dwallpaper.databinding.FragmentHomeBinding
import com.dkgtech.dwallpaper.model.CategoryModel
import com.dkgtech.dwallpaper.model.ColorModel
import com.dkgtech.dwallpaper.repo.WallpaperRepository


class HomeFragment() : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    lateinit var homeViewModel: HomeViewModel

    val arrColors = ArrayList<ColorModel>()

    val arrCategory = ArrayList<CategoryModel>()

    var query = "nature"


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        arrColors.apply {
            add(ColorModel(R.color.purple_200, "#BB86FC"))
            add(ColorModel(R.color.black, "#000000"))
            add(ColorModel(R.color.brown, "#5D5A58"))
            add(ColorModel(R.color.grey, "#B7C1C8"))
            add(ColorModel(R.color.peach, "#FCBFC1"))
            add(ColorModel(R.color.red, "#FF1100"))
            add(ColorModel(R.color.teal_200, "#03DAC5"))
            add(ColorModel(R.color.yellow, "#FF9A0B"))
        }

        arrCategory.apply {
            add(CategoryModel("Abstract", R.drawable.cat_abstract))
            add(CategoryModel("Nature", R.drawable.cat_nature))
            add(CategoryModel("Minimal", R.drawable.cat_minimal))
            add(CategoryModel("Technology", R.drawable.cat_technology))
            add(CategoryModel("Universe", R.drawable.cat_universe))
            add(CategoryModel("Business", R.drawable.cat_business))
            add(CategoryModel("Beautiful", R.drawable.cat_beautiful))

        }

        val apiHelper = ApiHelper.create()

        val repo = WallpaperRepository(apiHelper)

        homeViewModel =
            ViewModelProvider(this, HomeViewModelFactory(repo))[HomeViewModel::class.java]

        with(binding) {

            rcViewColor.layoutManager =
                LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

            rcViewColor.adapter = RecyclerColorAdapter(requireContext(), arrColors)

            rcViewCategories.layoutManager = GridLayoutManager(activity, 2)

            rcViewCategories.adapter = RecyclerCategoryAdapter(requireContext(), arrCategory)

            rcViewWallpaper.layoutManager =
                LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

            homeViewModel.getCuratedWallpaper(
                "rAwzTmcXYsSfCf6C5uUThO0izNTor82oYH3S78hab8COjw9IpYCC6FA3", 30

            )

            btnSearch.setOnClickListener {
                val mQuery = edtSearch.text.toString()
                query = mQuery
                homeViewModel.getSearchWallpaper(
                    "rAwzTmcXYsSfCf6C5uUThO0izNTor82oYH3S78hab8COjw9IpYCC6FA3",
                    query,
                    80, ""
                )
            }

            homeViewModel.curatedListPhotos.observe(viewLifecycleOwner) { photos ->
                rcViewWallpaper.adapter = RecyclerCuratedWallpaperAdapter(requireContext(), photos)
            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun getWallpaperAccToColor(colorHex: String) {
        homeViewModel.getSearchWallpaper(
            "rAwzTmcXYsSfCf6C5uUThO0izNTor82oYH3S78hab8COjw9IpYCC6FA3",
            query,
            80,
            colorHex
        )
    }
}