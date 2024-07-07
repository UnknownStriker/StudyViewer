package com.developers.studyviewer

import android.app.Activity
import android.widget.Toast



class DynamicBook(private val activity: Activity) {
    private var id = ""
    private var cur = 0
    private var origin = 0;
    private var isFolding = false

    private fun update() {
        
    }
    private fun unload() {

    }
    public fun loadBook(key: String, t: Int) {
        if(isFolding) unload()
        origin = t
        cur = 1
        activity.setContentView(R.layout.bookopen)
        id = key
        update()
    }


}