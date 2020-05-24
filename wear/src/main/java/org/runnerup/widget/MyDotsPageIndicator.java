package org.runnerup.widget;

import android.graphics.Point;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;

import org.runnerup.R;


public class MyDotsPageIndicator implements GridViewPager.OnPageChangeListener, GridViewPager.OnAdapterChangeListener {

    // margin, size
    private final Point unselected = new Point(4,2);
    private final Point selected = new Point(6,1);

    private final LinearLayout layout;
    private GridViewPager pager;

    public MyDotsPageIndicator(LinearLayout layout) {
        this.layout = layout;
    }

    public void setPager(GridViewPager pager) {
        this.pager = pager;
        this.onDataSetChanged();
        this.onPageSelected(0, 0);
    }

    @Override
    public void onPageScrolled(int i, int i2, float v, float v2, int i3, int i4) {

    }

    @Override
    public void onPageSelected(int row, int col) {
        for (int i = 0; i < layout.getChildCount(); i++)
            configDot((Button) layout.getChildAt(i), false);
        if (row < layout.getChildCount())
            configDot((Button) layout.getChildAt(row), true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onAdapterChanged(GridPagerAdapter gridPagerAdapter, GridPagerAdapter gridPagerAdapter2) {

    }

    @Override
    public void onDataSetChanged() {
        layout.removeAllViews();

        /* skip dot for only 1 row */
        if (pager.getAdapter().getRowCount() <= 1)
            return;

        for (int i = 0; i < pager.getAdapter().getRowCount(); i++) {
            Button b = new Button(layout.getContext());
            layout.addView(configDot(b, false));
        }
    }

    private Button configDot(Button btn, boolean selected) {
        btn.setBackgroundResource(R.drawable.dot);
        Point measures = selected ? this.selected : this.unselected;
        int size = getPxFromDp(measures.x);
        int margin = getPxFromDp(measures.y);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(size, size);
        if (layout.getOrientation() == LinearLayout.VERTICAL) {
            p.gravity = Gravity.CENTER_HORIZONTAL;
            p.setMargins(0, margin, 0, margin);
        } else {
            p.gravity = Gravity.CENTER_VERTICAL;
            p.setMargins(margin, 0, margin, 0);
        }
        btn.setLayoutParams(p);
        return btn;
    }

    private int getPxFromDp(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                layout.getResources().getDisplayMetrics());
    }
}
