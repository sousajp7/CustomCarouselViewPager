package com.example.e202.myapplication;

import android.support.v4.view.ViewPager;
import android.view.View;

public class ZoomPagerItemTransformer implements ViewPager.PageTransformer {
        private float minScale;
        private ViewPager viewPager;
        public ZoomPagerItemTransformer(ViewPager viewPager, float minScale) {
            this.minScale = minScale;
            this.viewPager = viewPager;
        }

        @Override
        public void transformPage(View page, float position) {
            int pageWidth = viewPager.getMeasuredWidth() - viewPager.getPaddingLeft() - viewPager.getPaddingRight();
            int paddingLeft = viewPager.getPaddingLeft();
            float transformPos = (float) (page.getLeft() - (viewPager.getScrollX() + paddingLeft)) / pageWidth;
            if (transformPos < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.setScaleX(minScale);
                page.setScaleY(minScale);
            } else if (transformPos <= 1) { // [-1,1]
                //page.setTranslationY(max * (1-Math.abs(transformPos)));
                final float normalizedposition2 = Math.abs(Math.abs(position) - 1);
                float calculatedScale=normalizedposition2 / 2 + minScale;
                if(calculatedScale>=minScale){
                    page.setScaleX(calculatedScale);
                    page.setScaleY(calculatedScale);
                }
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                page.setScaleX(minScale);
                page.setScaleY(minScale);
            }

        }
}
