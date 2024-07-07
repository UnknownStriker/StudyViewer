package com.developers.studyviewer

import android.app.Activity
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class DynamicBook(private val activity: Activity, private val librarian: Librarian) {
    private var id = "" // Book ID
    private var cur = 0 // Current page
    private var origin = 0 // Originating shelf
    private var maxPage = 0
    private var isFolding = false // Is the book open
    private val texts = arrayListOf<TextView>()

    private fun update() {
        if (texts.isNotEmpty()) {

        }
    }

    private fun unload() {
        cur = 0
        maxPage = 0
        isFolding = false
        id = ""
        texts.clear()
        librarian.loadShelf(origin)
        origin = 0
    }

    fun loadBook(key: String, t: Int) {
        origin = t
        cur = 1
        id = key
        maxPage = 10
        activity.setContentView(R.layout.bookopen)
        setupNavigationButtons()
        populateTextViews()
        update()
    }

    private fun setupNavigationButtons() {
        activity.findViewById<ImageButton>(R.id.next_button).setOnClickListener {
            if (cur < maxPage) {
                cur++
                update()
            }
        }
        activity.findViewById<ImageButton>(R.id.prev_button).setOnClickListener {
            if (cur > 1) {
                cur--
                update()
            }
        }
    }

    private fun populateTextViews() {
        val viewGroup = activity.findViewById<ConstraintLayout>(R.id.bookopen)
        for (i in 0 until viewGroup.childCount) {
            val child = viewGroup.getChildAt(i)
            if (child is TextView) {
                texts.add(child)
            }
        }
    }
}
