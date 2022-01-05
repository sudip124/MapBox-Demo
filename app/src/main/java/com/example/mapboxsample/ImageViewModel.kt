package com.example.mapboxsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ImageViewModel : ViewModel() {
    private val mutablePhotosListLiveData = MutableLiveData<List<ImageModel>>()
    private val imageListLiveData: LiveData<List<ImageModel>> = mutablePhotosListLiveData

    var imageResponseAdapter = SearchResponseViewAdapter()

    fun loadSearchedImages(searchedText: String): LiveData<List<ImageModel>> {
        viewModelScope.launch {
            val searchResponse = FlickrSearchClient.CLIENT.fetchImages(searchedText)
            val photosList = searchResponse.photos.photo.map { photo ->
                ImageModel(
                    imageID = photo.id,
                    imageURL = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    imageTitle = photo.title
                )
            }
            mutablePhotosListLiveData.postValue(photosList)
        }
        return imageListLiveData
    }
}