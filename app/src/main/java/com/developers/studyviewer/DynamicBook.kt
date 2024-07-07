package com.developers.studyviewer

import android.app.Activity
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class DynamicBook(private val activity: Activity, private val librarian: Librarian) {
    private var cur = 0 // Current page
    private var origin = 0 // Originating shelf
    private var maxPage = 0
    private var isFolding = false // Is the book open
    private val texts = arrayListOf<TextView>()
    private var pageData = arrayListOf<ArrayList<String>>()

    private fun update() {
        if (texts.isNotEmpty()) {
            for(i in 0 until texts.size) {
                texts[i].text = pageData[cur-1][i]
            }
        }
    }

    private fun unload() {
        cur = 0
        maxPage = 0
        isFolding = false
        texts.clear()
        librarian.loadShelf(origin)
        origin = 0
    }

    fun loadBook(key: String, t: Int) {
        origin = t
        cur = 1
        activity.setContentView(R.layout.bookopen)
        setPages(key)
        setupNavigationButtons()
        populateTextViews()
        update()
    }

    private fun setPages(key: String) {
        pageData = arrayListOf()
        val assetManager = activity.assets
        val inputStream: InputStream = assetManager.open("$key.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String?
        var cnt = 0
        while (reader.readLine().also { line = it } != null) {
            cnt++
            val rowData = line!!.split(",").map { it.trim() }
            pageData.add(ArrayList(rowData))
        }
        maxPage = cnt
        reader.close()
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
        activity.findViewById<ImageButton>(R.id.stop_button).setOnClickListener {
            unload()
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