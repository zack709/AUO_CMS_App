package com.auo.shelf.cmsapp.ui.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

import com.auo.shelf.cmsapp.R;
import com.auo.shelf.cmsapp.utility.Utility;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.renderer.PieChartRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class CustomPieChartRenderer extends PieChartRenderer {

    private Context context;

    public CustomPieChartRenderer(PieChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
        context = chart.getContext();
    }

    @Override
    public void drawExtras(Canvas c) {
        super.drawExtras(c);

        MPPointF center = mChart.getCenterCircleBox();
        Drawable d = Utility.getResizeDrawable(context, R.mipmap.icon_storage, 32, 32);

        if(d != null) {
            float halfWidth = d.getIntrinsicWidth() / 2;
            float halfHeight = d.getIntrinsicHeight() / 2;

            d.setBounds((int)(center.x - halfWidth), (int)(center.y - halfHeight), (int)(center.x + halfWidth), (int)(center.y + halfHeight));
            d.draw(c);
        }
    }


}
