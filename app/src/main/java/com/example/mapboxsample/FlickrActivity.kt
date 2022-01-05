package com.example.mapboxsample

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.content_flickr.photosRecyclerView

class FlickrActivity : AppCompatActivity() {

    val FLICKR_PARAM = "com.example.mapboxsample.searchString"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var searchString = intent.getStringExtra(FLICKR_PARAM)
        if (searchString == null)
            searchString = "Cocker Spaniel"

        setContentView(R.layout.content_flickr)

        val imageViewModel: ImageViewModel by viewModels()
        photosRecyclerView.adapter = imageViewModel.imageResponseAdapter
        photosRecyclerView.layoutManager = GridLayoutManager(this, 3)
        imageViewModel.loadSearchedImages(searchString).observe(this,
            Observer<List<ImageModel>> { list ->
                with(imageViewModel.imageResponseAdapter) {
                    images.clear()
                    images.addAll(list)
                    notifyDataSetChanged()
                }
            })
    }
}