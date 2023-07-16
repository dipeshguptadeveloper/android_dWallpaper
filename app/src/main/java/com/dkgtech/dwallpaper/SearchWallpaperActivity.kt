package com.dkgtech.dwallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dkgtech.dwallpaper.adapter.RecyclerCuratedWallpaperAdapter
import com.dkgtech.dwallpaper.adapter.RecyclerWallpaperAdapter
import com.dkgtech.dwallpaper.api.ApiHelper
import com.dkgtech.dwallpaper.databinding.ActivitySearchWallpaperBinding
import com.dkgtech.dwallpaper.repo.WallpaperRepository
import com.dkgtech.dwallpaper.ui.home.HomeViewModel
import com.dkgtech.dwallpaper.ui.home.HomeViewModelFactory

class SearchWallpaperActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchWallpaperBinding

    lateinit var homeViewModel: HomeViewModel

    var query = "nature"

    companion object {
        val IMAGE_KEY = "imageUrl"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiHelper = ApiHelper.create()

        val repo = WallpaperRepository(apiHelper)

        homeViewModel =
            ViewModelProvider(this, HomeViewModelFactory(repo))[HomeViewModel::class.java]

        with(binding) {

            rcViewSearchWallpaper.layoutManager = GridLayoutManager(this@SearchWallpaperActivity, 2)

            btnSearch.setOnClickListener {
                val mQuery = edtSearch.text.toString()
                if (mQuery != "") {
                    query = mQuery
                    homeViewModel.getSearchWallpaper(
                        "rAwzTmcXYsSfCf6C5uUThO0izNTor82oYH3S78hab8COjw9IpYCC6FA3",
                        query,
                        80, ""
                    )

                    homeViewModel.listPhotos.observe(this@SearchWallpaperActivity) { photos ->
                        rcViewSearchWallpaper.adapter =
                            RecyclerWallpaperAdapter(this@SearchWallpaperActivity, photos)
                    }

                } else {
                    Toast.makeText(
                        this@SearchWallpaperActivity,
                        "Enter Search Keyword",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}