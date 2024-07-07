package com.developers.studyviewer
import android.app.Activity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout


class Librarian(private val activity: Activity) {
    private var book = DynamicBook(activity)
    public fun loadShelf(num: Int) {
        when(num) {
            1 -> {
                activity.setContentView(R.layout.library_one)
                val tmp: ViewGroup = activity.findViewById<ConstraintLayout>(R.id.library_one)
                for (i in 0 until tmp.childCount) {
                    val child = tmp.getChildAt(i)
                    if(child is ImageButton) {
                        child.setOnClickListener {
                            book.loadBook(child.contentDescription.toString(), 1)
                        }
                    }
                }
            }
            //2 -> activity.setContentView(R.layout.library_two) 구현 안됨
            //3 -> activity.setContentView(R.layout.library_three) 구현 안됨
            //4 -> activity.setContentView(R.layout.library_four) 구현 안됨

        }
    }

    public fun initialize() {
        loadShelf(1)
    }
}