package com.example.a13703.test_12;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView ;
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.t) ;
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    v.animate().translationZBy(1000);
                    flag = false;
                }else {
                    v.animate().translationZ(0);
                    flag = true;
                }
            }
        });
        View view1 = findViewById(R.id.tv_cicle);

        ViewOutlineProvider viewOutlineProvider1 = new ViewOutlineProvider() {

            @Override
            public void getOutline(View view, Outline outline) {
                //修改outline为特定的形状
                outline.setOval(0, 0, view.getWidth(), view.getHeight());

            }
        };
        view1.setOutlineProvider(viewOutlineProvider1);
        set();
    }
    private int changeColor(int rgb) {
        int red = rgb >> 16 & 0xFF;
        int green = rgb >> 8 & 0xFF;
        int blue = rgb & 0xFF;
        red = (int) Math.floor(red * (1 - 0.2));
        green = (int) Math.floor(green * (1 - 0.2));
        blue = (int) Math.floor(blue * (1 - 0.2));
        return Color.rgb(red, green, blue);
    }
    private void set(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                if (vibrant == null) {
                    for (Palette.Swatch swatch : palette.getSwatches()) {
                        vibrant = swatch;
                        break;
                    }
                }
                int rbg = vibrant.getRgb();
                ActionBar actionBar = getSupportActionBar();
                actionBar.setBackgroundDrawable(new ColorDrawable(rbg));
                if (Build.VERSION.SDK_INT > 21) {
                    Window window = getWindow();
                    window.setStatusBarColor(changeColor(rbg));
                }
            }
        });
    }
}
