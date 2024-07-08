package com.developers.studyviewer

import android.app.Activity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.opencsv.CSVReader
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class Librarian(private val activity: Activity) {
    private var cur = 0


    private var relative: MutableMap<String, List<String>> = mutableMapOf (

    ) //String : 계열, List<String> 속한 학과


    private var subject: MutableMap<String, List<String>> = mutableMapOf( //String 과목, List<String> 소과목

    )
    private var onetwo: MutableMap<String, List<String>> = mutableMapOf( //String 전문 1 2 List<String> 계열

    )
    private var subrelative: MutableMap<String, List<String>> = mutableMapOf( //String 계열 List<String> 내부 과목

    )

    private val content: MutableMap<String, List<String>> = mutableMapOf( //String 이름 List<String> 내부 내용들

    )
    private lateinit var listView1: ListView
    private lateinit var listView2: ListView
    private lateinit var listView3: ListView

    private fun readMap(filename: String, target: MutableMap<String, List<String>>) {
        var reader: CSVReader? = null
        try {
            val inputStream = activity.assets.open(filename)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            reader = CSVReader(bufferedReader)
            var line: Array<String>?
            while (reader.readNext().also { line = it } != null) {
                target[line!![0]] = line!!.sliceArray(1 until line!!.size).toList()
            }
        }
        catch (e: IOException) {
            e.printStackTrace()
        } finally {
            reader?.close()
        }
    }
    init {
        readMap("relative.csv", relative)
        readMap("content.csv", content)
        readMap("subject.csv", subject)
        readMap("subrelative.csv", subrelative)
        readMap("onetwo.csv", onetwo)
    }
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

    private fun setUp() {
        when (cur) {
            1 -> {
                listView1 = activity.findViewById(R.id.selector1)

                val firstList = relative.keys.toList()
                val adapter1 = ArrayAdapter(activity, android.R.layout.simple_list_item_1, firstList)
                listView1.adapter = adapter1
                listView1.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                    val selectedKey = (listView1.adapter as ArrayAdapter<String>).getItem(position)
                    selectedKey?.let {
                        update(selectedKey)
                    }
                }
            }
            2, 3-> {
                listView1 = activity.findViewById(R.id.selector1)
                listView2 = activity.findViewById(R.id.selector2)
                val target = if (cur == 2) relative else subject
                val firstList = target.keys.toList()
                val adapter1 = ArrayAdapter(activity, android.R.layout.simple_list_item_1, firstList)
                listView1.adapter = adapter1
                listView1.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                    val selectedKey = (listView1.adapter as ArrayAdapter<String>).getItem(position)
                    selectedKey?.let {
                        val secondList = target[it] ?: emptyList()
                        val adapter2 = ArrayAdapter(activity, android.R.layout.simple_list_item_1, secondList)
                        listView2.adapter = adapter2
                        listView2.onItemClickListener = AdapterView.OnItemClickListener { _, _, position2, _ ->
                            val selectedKey2 = (listView2.adapter as ArrayAdapter<String>).getItem(position2)
                            selectedKey2?.let {
                                update(selectedKey2)
                            }
                        }
                    }

                }
            }
            4-> {
                listView1 = activity.findViewById(R.id.selector1)
                listView2 = activity.findViewById(R.id.selector2)
                listView3 = activity.findViewById(R.id.selector3)
                val firstList = onetwo.keys.toList()
                val adapter1 = ArrayAdapter(activity, android.R.layout.simple_list_item_1, firstList)
                listView1.adapter = adapter1
                listView1.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                    listView3.adapter = null
                    val selectedKey = (listView1.adapter as ArrayAdapter<String>).getItem(position)
                    selectedKey?.let {
                        val secondList = onetwo[it] ?: emptyList()
                        val adapter2 = ArrayAdapter(activity, android.R.layout.simple_list_item_1, secondList)
                        listView2.adapter = adapter2
                        listView2.onItemClickListener = AdapterView.OnItemClickListener { _, _, position2, _ ->
                            val selectedKey2 = (listView2.adapter as ArrayAdapter<String>).getItem(position2)
                            selectedKey2?.let {
                                val thirdList = subrelative[it] ?: emptyList()
                                val adapter3 = ArrayAdapter(activity, android.R.layout.simple_list_item_1, thirdList)
                                listView3.adapter = adapter3
                                listView3.onItemClickListener = AdapterView.OnItemClickListener { _, _, position3, _ ->
                                    val selectedKey3 = (listView3.adapter as ArrayAdapter<String>).getItem(position3)
                                    selectedKey3?.let {
                                        update(selectedKey3)
                                    }
                                }

                            }
                        }
                    }

                }
            }
        }

    }


    private fun update(filename: String) {
        when(cur) {
            1 -> {
                activity.findViewById<TextView>(R.id.text1).text = content[filename]?.elementAt(0)
                activity.findViewById<TextView>(R.id.text2).text = content[filename]?.elementAt(1)
                activity.findViewById<TextView>(R.id.text3).text = content[filename]?.elementAt(2)
                activity.findViewById<TextView>(R.id.text4).text = content[filename]?.elementAt(3)
            }
            2 -> {
                activity.findViewById<TextView>(R.id.text1).text = content[filename]?.elementAt(0)
                activity.findViewById<TextView>(R.id.text2).text = content[filename]?.elementAt(1)
                activity.findViewById<TextView>(R.id.text3).text = content[filename]?.elementAt(2)
                activity.findViewById<TextView>(R.id.text4).text = content[filename]?.elementAt(3)
                activity.findViewById<TextView>(R.id.text5).text = content[filename]?.elementAt(4)
                activity.findViewById<TextView>(R.id.text6).text = content[filename]?.elementAt(5)
                activity.findViewById<TextView>(R.id.text7).text = content[filename]?.elementAt(6)
                activity.findViewById<TextView>(R.id.text8).text = content[filename]?.elementAt(7)
                activity.findViewById<TextView>(R.id.text9).text = content[filename]?.elementAt(8)
                activity.findViewById<TextView>(R.id.text10).text = content[filename]?.elementAt(9)
                activity.findViewById<TextView>(R.id.text11).text = content[filename]?.elementAt(10)
                activity.findViewById<TextView>(R.id.text12).text = content[filename]?.elementAt(11)
                activity.findViewById<TextView>(R.id.text13).text = content[filename]?.elementAt(12)

            }
            3 -> {
                activity.findViewById<TextView>(R.id.text1).text = content[filename]?.elementAt(0)
                activity.findViewById<TextView>(R.id.text2).text = content[filename]?.elementAt(2)
                activity.findViewById<TextView>(R.id.text3).text = content[filename]?.elementAt(8)
                activity.findViewById<TextView>(R.id.text4).text = content[filename]?.elementAt(1)
                activity.findViewById<TextView>(R.id.text5).text = content[filename]?.elementAt(6)
                activity.findViewById<TextView>(R.id.text6).text = content[filename]?.elementAt(5)
                activity.findViewById<TextView>(R.id.text7).text = content[filename]?.elementAt(7)
                activity.findViewById<TextView>(R.id.text8).text = content[filename]?.elementAt(4)
            }
            4 -> {
                activity.findViewById<TextView>(R.id.text1).text = content[filename]?.elementAt(1)
            }
        }
    }
}

