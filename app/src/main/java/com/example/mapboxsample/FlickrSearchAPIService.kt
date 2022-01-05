package com.example.mapboxsample


import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrSearchAPIService {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=3e7cc266ae2b0e0d78e279ce8e361736")
    suspend fun fetchImages(@Query("text") text: String): PhotosSearchResponse

}
