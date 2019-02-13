package palla_boitard_mubanzo.readroid.listeners

import android.content.Context
import android.view.MotionEvent
import android.support.v7.widget.RecyclerView
import android.text.method.Touch.onTouchEvent
import android.view.GestureDetector
import android.view.View
import palla_boitard_mubanzo.readroid.models.Post


class RecyclerPostClickListener(posts: MutableList<Post>, context: Context, private val mListener: OnItemClickListener?) :
    RecyclerView.OnItemTouchListener {

    internal var mGestureDetector: GestureDetector

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    init {
        mGestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }
        })
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView = view.findChildViewUnder(e.x, e.y)
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildLayoutPosition(childView))
            return true
        }
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}