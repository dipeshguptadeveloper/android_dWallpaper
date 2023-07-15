package com.dkgtech.dwallpaper.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dkgtech.dwallpaper.repo.WallpaperRepository

class HomeViewModelFactory(val wallpaperRepository: WallpaperRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(wallpaperRepository) as T
    }

}