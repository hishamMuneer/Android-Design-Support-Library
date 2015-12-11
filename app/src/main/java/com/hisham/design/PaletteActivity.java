package com.hisham.design;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class PaletteActivity extends BaseActivity {

    private final int[] images = new int[]{R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4,
            R.drawable.i5, R.drawable.i6, R.drawable.i7, R.drawable.i10, R.drawable.koala, R.drawable.tulips};
    private TextView tvLabel;
    private ImageView imageView;
    private View relativeParent;
    private final View.OnClickListener colorChanger = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


//
//            // Getting bitmap out of image
//            imageView.setDrawingCacheEnabled(true);
//            imageView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//            imageView.layout(0, 0,
//                    imageView.getMeasuredWidth(), imageView.getMeasuredHeight());
//            imageView.buildDrawingCache(true);
//
//            imageView.setDrawingCacheEnabled(true);
            //Bitmap bitmap = imageBg.getDrawingCache();


//        The Palette object will try to find 16 colors from the image by default,
//        but there are six color profiles you will use most often:
//        Vibrant
//        Vibrant Dark
//        Vibrant Light
//        Muted
//        Muted Dark
//        Muted Light
// https://www.bignerdranch.com/blog/extracting-colors-to-a-palette-with-android-lollipop/
//        Palette palette = Palette.generate(bitmap);
//        int vibrant = palette.getVibrantColor(0x000000);
//        int vibrantLight = palette.getLightVibrantColor(0x000000);
//        int vibrantDark = palette.getDarkVibrantColor(0x000000);
//        int muted = palette.getMutedColor(0x000000);
//        int mutedLight = palette.getLightMutedColor(0x000000);
//        int mutedDark = palette.getDarkMutedColor(0x000000);

//            final Palette.Swatch swatch = new Palette.Swatch(Color.YELLOW, 100);
            int i = new Random().nextInt(images.length);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), images[i]);
            imageView.setDrawingCacheEnabled(true);
//            Bitmap bitmap = imageView.getDrawingCache();
//            imageView.setImageResource(images[i]);
            imageView.setImageBitmap(bitmap);
            // Synchronous
            Palette p = Palette.from(bitmap).generate();
            // If we have a vibrant color update the title TextView
            relativeParent.setBackgroundColor(p.getDarkMutedColor(0x000000));
            tvLabel.setTextColor(p.getVibrantColor(0x000000));
//            // Asynchronous
//            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//                public void onGenerated(Palette palette) {
//                    Palette.Swatch swatch = palette.getVibrantSwatch();
//                    if (swatch != null) {
//                        // If we have a vibrant color update the title TextView
//                        relativeParent.setBackgroundColor(swatch.getRgb());
//                        tvLabel.setTextColor(swatch.getTitleTextColor());
//                    }
//                }
//            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pallete);

        tvLabel = (TextView) findViewById(R.id.label);
        relativeParent = findViewById(R.id.relativeParent);
        imageView = (ImageView) findViewById(R.id.imageBg);
        imageView.setOnClickListener(colorChanger);

    }
}
