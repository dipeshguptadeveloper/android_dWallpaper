package com.dkgtech.dwallpaper.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dkgtech.dwallpaper.api.ApiHelper
import com.dkgtech.dwallpaper.databinding.FragmentHomeBinding
import com.dkgtech.dwallpaper.repo.WallpaperRepository

class HomeFragment() : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    lateinit var homeViewModel: HomeViewModel

    companion object {
        val IMAGE_KEY = "imgUrl"
    }


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

        val apiHelper = ApiHelper.create()

        val repo = WallpaperRepository(apiHelper)
        val homeViewModel =
            ViewModelProvider(
                this@HomeFragment,
                HomeViewModelFactory(repo)
            )[(HomeViewModel::class.java)]

        with(binding) {
            rcViewCuratedWallpaper.layoutManager = GridLayoutManager(context, 2)
        }





        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}