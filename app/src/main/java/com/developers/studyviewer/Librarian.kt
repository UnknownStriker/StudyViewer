package com.developers.studyviewer

import android.app.Activity
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout

class Librarian(private val activity: Activity) {
    private val book = DynamicBook(activity, this)

    fun loadShelf(num: Int) {
        when (num) {
            1 -> {
                activity.setContentView(R.layout.library_one)
                setupShelf(R.id.library_one, 1)
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
