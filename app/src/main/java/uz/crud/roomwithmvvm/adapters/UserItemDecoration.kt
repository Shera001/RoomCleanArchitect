package uz.crud.roomwithmvvm.adapters

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import uz.crud.roomwithmvvm.R

class UserItemDecoration(
    context: Context
) : RecyclerView.ItemDecoration() {

    private var mDivider: Drawable = ContextCompat.getDrawable(context, R.drawable.item_divider)!!

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            top = 16
            bottom = 16
            left = 32
            right = 32
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        // left margin for the divider
        val dividerLeft = 32

        // right margin for the divider with
        // reference to the parent width
        val dividerRight: Int = parent.width - 32

        // this loop creates the top and bottom
        // divider for each items in the RV
        // as each items are different
        for (i in 0 until parent.childCount) {

            // this condition is because the last
            // and the first items in the RV have
            // no dividers in the list
            val child: View = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            // calculating the distance of the
            // divider to be drawn from the top
            val dividerTop: Int = child.bottom + params.bottomMargin
            val dividerBottom: Int = dividerTop + mDivider.intrinsicHeight

            mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            mDivider.draw(c)
        }
    }
}