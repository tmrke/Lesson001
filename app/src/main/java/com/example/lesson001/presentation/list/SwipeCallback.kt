package com.example.lesson001.presentation.list

import android.graphics.Canvas
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeCallback<K, VH : RecyclerView.ViewHolder>(
    private val frontLayerSelector: (VH) -> View,
    private val actionIconSelector: (VH) -> View,
    private val keySelector: (VH) -> K,
    private val onSwipe: (K) -> Unit,
) : ItemTouchHelper.SimpleCallback(
    /* dragDirs = */ 0,
    /* swipeDirs = */ ItemTouchHelper.START or ItemTouchHelper.END,
) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder,
    ) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        @Suppress("UNCHECKED_CAST")
        (viewHolder as? VH)?.let { onSwipe(keySelector(it)) }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean,
    ) {
        @Suppress("UNCHECKED_CAST")
        (viewHolder as? VH)?.let {
            frontLayerSelector(it).translationX = dX
            actionIconSelector(it).updateLayoutParams<FrameLayout.LayoutParams> {
                gravity = if (dX > 0) {
                    Gravity.START or Gravity.CENTER_VERTICAL
                } else {
                    Gravity.END or Gravity.CENTER_VERTICAL
                }
            }
        }
    }
}
