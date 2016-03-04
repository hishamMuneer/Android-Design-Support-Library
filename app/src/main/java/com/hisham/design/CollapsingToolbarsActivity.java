package com.hisham.design;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by faisal pc on 6/9/2015.
 *
 * <div class="jd-descr">


 <h2 style="margin-bottom: 0px;">Class Overview</h2><hr>
 <p itemprop="articleBody">CollapsingToolbarLayout is a wrapper for <code><a href="/reference/android/support/v7/widget/Toolbar.html">Toolbar</a></code> which implements a collapsing app bar.
 It is designed to be used as a direct child of a <code><a href="/reference/android/support/design/widget/AppBarLayout.html">AppBarLayout</a></code>.
 CollapsingToolbarLayout contains the following features:

 </p><h3>Collapsing title</h3>
 A title which is larger when the layout is fully visible but collapses and becomes smaller as
 the layout is scrolled off screen. You can set the title to display via
 <code><a href="/reference/android/support/design/widget/CollapsingToolbarLayout.html#setTitle(java.lang.CharSequence)">setTitle(CharSequence)</a></code>. The title appearance can be tweaked via the
 <code>collapsedTextAppearance</code> and <code>expandedTextAppearance</code> attributes.

 <h3>Content scrim</h3>
 A full-bleed scrim which is show or hidden when the scroll position has hit a certain threshold.
 You can change this via <code><a href="/reference/android/support/design/widget/CollapsingToolbarLayout.html#setContentScrim(android.graphics.drawable.Drawable)">setContentScrim(Drawable)</a></code>.

 <h3>Status bar scrim</h3>
 A scrim which is show or hidden behind the status bar when the scroll position has hit a certain
 threshold. You can change this via <code><a href="/reference/android/support/design/widget/CollapsingToolbarLayout.html#setStatusBarScrim(android.graphics.drawable.Drawable)">setStatusBarScrim(Drawable)</a></code>. This only works
 on <code><a href="/reference/android/os/Build.VERSION_CODES.html#LOLLIPOP">LOLLIPOP</a></code> devices when we set to fit system windows.

 <h3>Parallax scrolling children</h3>
 Child views can opt to be scrolled within this layout in a parallax fashion.
 See <code><a href="/reference/android/support/design/widget/CollapsingToolbarLayout.LayoutParams.html#COLLAPSE_MODE_PARALLAX">COLLAPSE_MODE_PARALLAX</a></code> and
 <code><a href="/reference/android/support/design/widget/CollapsingToolbarLayout.LayoutParams.html#setParallaxMultiplier(float)">setParallaxMultiplier(float)</a></code>.

 <h3>Pinned position children</h3>
 Child views can opt to be pinned in space globally. This is useful when implementing a
 collapsing as it allows the <code><a href="/reference/android/support/v7/widget/Toolbar.html">Toolbar</a></code> to be fixed in place even though this layout is
 moving. See <code><a href="/reference/android/support/design/widget/CollapsingToolbarLayout.LayoutParams.html#COLLAPSE_MODE_PIN">COLLAPSE_MODE_PIN</a></code>.<p></p>





 </div>
 *
 */
public class CollapsingToolbarsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaping_toolbars_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(getSupportActionBar()!= null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollapsingToolbarsActivity.this.finish();
            }
        });

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setCollapsedTitleTextColor(Color.BLUE);
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbar.setTitle("Wow Koala");
        loadBackdrop(collapsingToolbar);
    }

    private void loadBackdrop(final CollapsingToolbarLayout collapsingToolbarLayout) {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
//        Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
        imageView.setImageResource(R.drawable.koala);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Image Clicked.", Snackbar.LENGTH_INDEFINITE).show();
            }
        });
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                applyPalette(palette, collapsingToolbarLayout);
            }
        });
    }

    private void applyPalette(Palette palette, CollapsingToolbarLayout collapsingToolbarLayout) {
        int primaryDark = getResources().getColor(R.color.primary_dark);
        int primary = getResources().getColor(R.color.primary);
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        collapsingToolbarLayout.setCollapsedTitleTextColor(palette.getDarkMutedColor(primaryDark));
        supportStartPostponedEnterTransition();
    }
}
