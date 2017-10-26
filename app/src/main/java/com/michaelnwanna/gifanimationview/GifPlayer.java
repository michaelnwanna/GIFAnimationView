package com.michaelnwanna.gifanimationview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.io.InputStream;

/**
 * Created by mac on 9/16/17.
 */

public class GifPlayer extends View {

    Movie movie;
    InputStream is=null;
    long moviestart;
    public GifPlayer(Context context) {
        super(context);
         init();
    }

    public GifPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GifPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.WHITE);
        super.onDraw(canvas);
        long now=android.os.SystemClock.uptimeMillis();
        System.out.println("now="+now);
        if (moviestart == 0) { // first time
            moviestart = now;

        }
        System.out.println("\tmoviestart="+moviestart);
        int relTime = (int)((now - moviestart) % movie.duration()) ;
        System.out.println("time="+relTime+"\treltime="+movie.duration());
        movie.setTime(relTime);


        Double scalefactorx= (double)this.getWidth() / (double)movie.width();
        Double scalefactory = (double)this.getHeight() / (double)movie.height();
        canvas.scale(scalefactorx.floatValue(),scalefactory.floatValue());
        //movie.draw(canvas,this.getWidth()/2-20,this.getHeight()/2-40);
        movie.draw(canvas, ((float) this.getWidth() / (float) movie.width()),
                (float) this.getHeight() / (float) movie.height());

        Paint p = new Paint();
        p.setAntiAlias(true);
        setLayerType(LAYER_TYPE_SOFTWARE, p);
        this.invalidate();
    }

    private void init() {

        is=getContext().getResources().openRawResource(R.raw.img_animated);
        movie= Movie.decodeStream(is);
        /* inflate views */

    }
}