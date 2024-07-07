package com.developers.studyviewer

import android.app.Activity
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.abs

class Librarian(private val activity: Activity) {
    private val book = DynamicBook(activity, this)
    private var isLibraryView = false
    private var curLib = 0;

    fun loadNextShelf() {
        if(curLib + 1 <= 2) {
            curLib++
            loadShelf(curLib)
        }
    }

    fun loadPreviousShelf() {
        if(curLib - 1 > 0) {
            curLib--
            loadShelf(curLib)
        }
    }
    fun loadShelf(num: Int) {
        when (num) {
            1 -> {
                activity.setContentView(R.layout.library_one)
                setupShelf(R.id.library_one, 1)
            }
            2 -> {
                activity.setContentView(R.layout.library_two)
                setupShelf(R.id.library_two, 2)
            }
        }
    }

    fun initialize() {
        loadShelf(1)
    }

    private fun setupShelf(layoutId: Int, shelfNumber: Int) {
        val viewGroup = activity.findViewById<ConstraintLayout>(layoutId)
        for (i in 0 until viewGroup.childCount) {
            val child = viewGroup.getChildAt(i)
            if (child is ImageButton) {
                child.setOnClickListener {
                    book.loadBook(child.contentDescription.toString(), shelfNumber)
                }
            }
        }
    }
}
