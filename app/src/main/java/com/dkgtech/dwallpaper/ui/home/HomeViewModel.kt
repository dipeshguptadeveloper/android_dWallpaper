package com.dkgtech.dwallpaper.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dkgtech.dwallpaper.repo.WallpaperRepository
import com.dkgtech.dwallpaper.model.CuratedWallpaperModel
import com.dkgtech.dwallpaper.model.Photos
import com.dkgtech.dwallpaper.model.PhotosModel
import com.dkgtech.dwallpaper.model.WallpaperModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel (val wallpaperRepository: WallpaperRepository) : ViewModel() {

    val listPhotos = MutableLiveData<List<PhotosModel>>()
    val curatedListPhotos = MutableLiveData<List<Photos>>()
    val errMsg = MutableLiveData<String>()

    fun getSearchWallpaper(auth: String, search: String, perPage: Int) {

        wallpaperRepository.getSearchWallpaper(auth, search, perPage)
            .enqueue(object : Callback<WallpaperModel> {
                override fun onResponse(
                    call: Call<WallpaperModel>?,
                    response: Response<WallpaperModel>?
                ) {
                    if (response?.code() == 200) {
                        listPhotos.postValue(response.body()!!.photosModel)
                        Log.d("Response", response.body().toString())
                    } else {
                        Log.d("Error", "${response?.errorBody()}, ${response?.code()}")
                        errMsg.postValue(
                            "Error : ${
                                response?.errorBody()
                            },${
                                response?.code()
                            }"
                        )
                    }
                }

                override fun onFailure(call: Call<WallpaperModel>?, t: Throwable?) {
                    Log.d("Network Error", "${t?.message}")
                    t?.printStackTrace()
                    errMsg.postValue("Network Error : ${t?.message}")
                }

            })

    }

    fun getCuratedWallpaper(auth: String, perPage: Int) {
        wallpaperRepository.getCuratedWallpaper(auth, perPage)
            .enqueue(object : Callback<CuratedWallpaperModel> {
                override fun onResponse(
                    call: Call<CuratedWallpaperModel>?,
                    response: Response<CuratedWallpaperModel>?
                ) {
                    if (response?.code() == 200) {
                        curatedListPhotos.postValue(response.body()!!.photos)
                        Log.d("Response", response.body().toString())
                    } else {
                        Log.d("Error", "${response?.errorBody()}, ${response?.code()}")
                        errMsg.postValue(
                            "Error : ${
                                response?.errorBody()
                            },${
                                response?.code()
                            }"
                        )
                    }
                }

                override fun onFailure(call: Call<CuratedWallpaperModel>?, t: Throwable?) {
                    Log.d("Network Error", "${t?.message}")
                    t?.printStackTrace()
                    errMsg.postValue("Network Error : ${t?.message}")
                }

            })
    }

}