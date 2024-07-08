package com.developers.studyviewer

import android.app.Activity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView


class Librarian(private val activity: Activity) {
    private var cur = 0


    private val singleMap: Map<String, String> = mapOf(
        "어문 계열" to "korean",
        "상경 계열" to "하이"
    )
    private val firstToSecondMap: Map<String, List<String>> = mapOf(

    )
    private val secondToThirdMap: Map<String, List<String>> = mapOf(

    )
    private val getCsv: Map<String, String> = mapOf(

    )
    private lateinit var listView1: ListView
    private lateinit var listView2: ListView
    private lateinit var listView3: ListView

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
            }
            3 -> {
                activity.setContentView(R.layout.third)
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
        if(number == 1) {
            listView1 = activity.findViewById(R.id.selector1)

            val firstList = singleMap.keys.toList()
            val adapter1 = ArrayAdapter(activity, android.R.layout.simple_list_item_1, firstList)
            listView1.adapter = adapter1



        }
    }
}
