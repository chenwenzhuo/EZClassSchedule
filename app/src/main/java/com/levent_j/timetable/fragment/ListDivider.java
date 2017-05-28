package com.levent_j.timetable.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ListDivider extends RecyclerView.ItemDecoration {
    private Context mContext;
    private Drawable mDividerHor;
    private int mOrientation;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    public static final int[] ATTRS_HOR = new int[]{
            android.R.attr.horizontalDivider
    };

    ListDivider(Context context, int orientation) {
        this.mContext = context;
        final TypedArray typedArrayHor = context.obtainStyledAttributes(ATTRS_HOR);
        this.mDividerHor = typedArrayHor.getDrawable(0);
        typedArrayHor.recycle();
        setOrientation(orientation);
    }

    private void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("Invalid orientation!");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == HORIZONTAL_LIST) {
            drawVerticalLineAndMargin(canvas, parent, state);
        } else {
            drawHorizontalLineAndMargin(canvas, parent, state);
        }
    }

    //画item之间的水平分隔线
    private void drawHorizontalLineAndMargin(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        //画item之间的水平分隔线
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDividerHor.getIntrinsicHeight();

            mDividerHor.setBounds(left, top, right, bottom);
            mDividerHor.draw(canvas);
        }
    }

    //画item之间的竖直分隔线
    private void drawVerticalLineAndMargin(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDividerHor.getIntrinsicWidth();

            mDividerHor.setBounds(left, top, right, bottom);
            mDividerHor.draw(canvas);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == HORIZONTAL_LIST) {
            outRect.set(0, 0, 0, mDividerHor.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDividerHor.getIntrinsicWidth(), 0);
        }
    }
}
