package com.example.funnylearning.others;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.funnylearning.R;

import java.util.List;

public class ColumnView extends View {

    private String[] transverse;    //横列的刻度值数组
    private String[] vertical;      //竖列的刻度值数组
    private List<Integer> colors;
    private int[] high;           //柱状图高度数值数组

    private int xScale;
    private int yScale;

    private int margin = 20;

    private int xPoint;
    private int yPoint;
    private Paint paintCoordinate;
    private Paint paintRectF;
    private Paint paintRectShadowF;

    public ColumnView(Context context) {
        super(context);
    }

    public ColumnView(Context context, String[] transverse, String[] vertical, List<Integer> colors, int[] high) {
        super(context);
        this.transverse = transverse;
        this.vertical = vertical;
        this.high = high;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        drawCoordinate(canvas, paintCoordinate);
        drawColumnShadow(canvas,paintRectShadowF,high);
        drawColumn(canvas, paintRectF, high);

    }

    public void init(){
        xPoint = margin;
        yPoint = this.getHeight() - margin;
        xScale = (this.getWidth() - 2 * margin) / (transverse.length - 1) - 10;
        yScale = (this.getHeight() - 2 * margin) / (vertical.length - 1);
        paintCoordinate = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCoordinate.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        paintCoordinate.setTextSize(32f);
        //rectangle
        paintRectF = new Paint();
        paintRectF.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        paintRectF.setStyle(Paint.Style.FILL);
        paintRectF.setDither(true);
        paintRectF.setAntiAlias(true);
        //rectangle shadow
        paintRectShadowF = new Paint();
        paintRectShadowF.setColor(ContextCompat.getColor(getContext(), R.color.colorShadow));
        paintRectShadowF.setStyle(Paint.Style.FILL);
        paintRectShadowF.setDither(true);
        paintRectShadowF.setAntiAlias(true);
    }

    private void drawCoordinate(Canvas canvas, Paint paint) {
        // X轴坐标
        for (int i = 0; i <= (transverse.length - 1); i++) {
            paint.setTextAlign(Paint.Align.CENTER);
            int startX = xPoint + i * xScale;
            canvas.drawText(transverse[i], startX - 10, this.getHeight() - margin / 6, paint);
        }
    }

    private void drawColumn(Canvas canvas, Paint paint, int data[]) {
        for (int i = 1; i <= (transverse.length - 1); i++) {
            int startX = xPoint + i * xScale;
            RectF rect = new RectF(startX - 20, toY(data[i - 1]), startX + 5, this.getHeight() - margin*2);
            canvas.drawRoundRect(rect,10,10, paint);
        }
    }

    private void drawColumnShadow(Canvas canvas, Paint paint, int data[]) {
        for (int i = 1; i <= (transverse.length - 1); i++) {
            int startX = xPoint + i * xScale;
            RectF rectShadow = new RectF(startX - 20, toY(data[i - 1]+10), startX + 5, this.getHeight() - margin*2);
            canvas.drawRoundRect(rectShadow,10,10, paint);
        }
    }

    private float toY(int num) {
        float y;
        try {
            float a = (float) num / 15.0f;//数据按比例转换坐标
            y = yPoint - a * yScale;
        } catch (Exception e) {
            return 0;
        }
        return y;
    }
}
