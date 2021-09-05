package com.dasarp.employeedetails.ui.view.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val spaceSize : Int,private val orientation : String) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        when(orientation){
            "VERTICAL" -> {
                with(outRect){
                    if(parent.getChildAdapterPosition(view) == 0){
                        top = spaceSize
                    }
                    left = spaceSize
                    right = spaceSize
                    bottom = spaceSize
                }
            }
            "HORIZONTAL" -> {
                with(outRect){
                    if(parent.getChildAdapterPosition(view) == 0){
                        left = spaceSize
                    }
                    right = spaceSize
                    top = spaceSize
                    bottom = spaceSize
                }
            }
        }
    }
}