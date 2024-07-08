package com.developers.studyviewer

import android.app.Activity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class Librarian(private val activity: Activity) {
    private var cur = 0


    private var singleMap: ArrayList<String> = arrayListOf<String>(

    )
    private var firstToSecondMap: Map<String, List<String>> = mapOf(

    )
    private var secondToThirdMap: Map<String, List<String>> = mapOf(

    )
    private val getCsv: Map<String, String> = mapOf(
        "어문 계열" to "korean",
        "상경 계열" to "economy"
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
        /*activity.findViewById<Button>(R.id.btn3).setOnClickListener {
            moveTo(3)
        }
        activity.findViewById<Button>(R.id.btn4).setOnClickListener {
            moveTo(4)
        }*/
    }
    fun moveTo(num: Int) {
        if(cur == num) return
        cur = num
        when (num) {
            1 -> {
                activity.setContentView(R.layout.first)
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
        setUp()
        initializeMenu()
    }

    private fun readMap(number: Int) {
        singleMap.clear()
        firstToSecondMap = emptyMap()
        secondToThirdMap = emptyMap()

        if(number == 1) {
            singleMap.add("어문 계열")
            singleMap.add("상경 계열")
        }
        if(number == 2) {

        }
        if(number == 3) {

        }
        if(number == 4) {

        }
    }
    private fun setUp() {
        readMap(cur)
        if(cur == 1) {
            listView1 = activity.findViewById(R.id.selector1)

            val firstList = singleMap.toList()
            val adapter1 = ArrayAdapter(activity, android.R.layout.simple_list_item_1, firstList)
            listView1.adapter = adapter1
            listView1.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedKey = (listView1.adapter as ArrayAdapter<String>).getItem(position)
                selectedKey?.let {
                    update(getCsv[selectedKey])
                }
            }
        }
        else if(cur == 4) {
        }
        else {
            listView1 = activity.findViewById(R.id.selector1)
            listView2 = activity.findViewById(R.id.selector2)
            val firstList = firstToSecondMap.keys.toList()
            val adapter1 = ArrayAdapter(activity, android.R.layout.simple_list_item_1, firstList)
            listView1.adapter = adapter1
            listView1.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedKey = (listView1.adapter as ArrayAdapter<String>).getItem(position)
                selectedKey?.let {
                    val secondList = firstToSecondMap[it] ?: emptyList()
                    val adapter2 = ArrayAdapter(activity, android.R.layout.simple_list_item_1, secondList)
                    listView2.adapter = adapter2
                }

            }
        }
    }


    private fun update(filename: String?) {
        val assetManager = activity.assets
        val inputStream: InputStream = assetManager.open("$filename.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))
        var tmp = arrayListOf<String?>()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            tmp.add(line)
        }

        if(cur == 1) {
            activity.findViewById<TextView>(R.id.text1).text = tmp[0]
            activity.findViewById<TextView>(R.id.text2).text = tmp[1]
            activity.findViewById<TextView>(R.id.text3).text = tmp[2]
            activity.findViewById<TextView>(R.id.text4).text = tmp[3]

        }
        reader.close()
    }
}

