package com.developers.studyviewer

import KeyValueAdapter
import android.app.Activity
import android.widget.Button


class Librarian(private val activity: Activity) {
    private var cur = 0
    private lateinit var adapter1: KeyValueAdapter
    private lateinit var adapter2: KeyValueAdapter
    private lateinit var adapter3: KeyValueAdapter

    private val firstToSecondMap: Map<String, List<String>>? = null
    private val secondToThirdMap: Map<String, List<String>>? = null
    private val getCsv: Map<String, String>? = null

    fun initializeMenu() {
        activity.findViewById<Button>(R.id.btn1).setOnClickListener {
            moveTo(1)
        }
        activity.findViewById<Button>(R.id.btn2).setOnClickListener {
            moveTo(2)
        }
        activity.findViewById<Button>(R.id.btn3).setOnClickListener {
            moveTo(3)
        }
        activity.findViewById<Button>(R.id.btn4).setOnClickListener {
            moveTo(4)
        }
    }
    fun moveTo(num: Int) {
        if(cur == num) return

        when (num) {
            1 -> {
                activity.setContentView(R.layout.first)
                setUp(1)
            }
            2 -> {
                activity.setContentView(R.layout.second)
                sel3 = " "
            }
            3 -> {
                activity.setContentView(R.layout.third)
                sel3 = " "
            }
            4 -> {
                activity.setContentView(R.layout.fourth)
            }
        }
        initializeMenu()
    }

    private fun readMap(number: Int) {
        if(number == 1) {

        }
        if(number == 2) {

        }
        if(number == 3) {

        }
        if(number == 4) {

        }
    }
    private fun setUp(number: Int) {
        readMap(number)
        
    }
}
