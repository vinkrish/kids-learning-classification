package com.vinkrish.classification;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;

/**
 * Created by vinkrish on 14/03/16.
 */
public class AnimationUtils {

    public static class TrajectoryAnimation extends Animation {
        final int targetX;
        final int targetY;
        final float targetRotation;
        View view;
        int startX;
        int startY;
        float startRotation;

        public TrajectoryAnimation(View view, int targetX, int targetY, float targetRotation, int startX, int startY, float startRotation) {
            this.view = view;
            this.targetX = targetX;
            this.targetY = targetY;
            this.targetRotation = targetRotation;
            this.startX = startX;
            this.startY = startY;
            this.startRotation = startRotation;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int newX = (int) (startX + ((targetX - startX) * interpolatedTime));
            int newY = (int) (startY + ((targetY - startY) * interpolatedTime));
            ((RelativeLayout.LayoutParams) view.getLayoutParams()).leftMargin = newX;
            ((RelativeLayout.LayoutParams) view.getLayoutParams()).topMargin = newY;
            ((RelativeLayout.LayoutParams) view.getLayoutParams()).rightMargin = -2000;
            ((RelativeLayout.LayoutParams) view.getLayoutParams()).bottomMargin = -2000;

            float newRotation = startRotation - ((startRotation - targetRotation) * interpolatedTime);
            view.setRotation(newRotation);
            view.requestLayout();
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }

    public static class ZigZagTrajectory extends Animation {
        final int totalX;
        final int numberOfWaves;
        final int amplitude;
        final View view;
        final int startX;
        final int startY;
        final int waveLength;

        public ZigZagTrajectory(View view, int totalX, int numberOfWaves, int amplitude, int startX, int startY) {
            this.view = view;
            this.totalX = totalX;
            this.numberOfWaves = numberOfWaves;
            this.amplitude = amplitude;
            this.startX = startX;
            this.startY = startY;
            this.waveLength = totalX / (numberOfWaves);
            //Log.d("Amplitude", amplitude+"");
            //Log.d("Wavelength", waveLength+"");
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int newX = (int) (startX + ((totalX - startX) * interpolatedTime));
            int offsetX = newX % waveLength;
            while (offsetX > waveLength)
                offsetX %= waveLength;
            int newY = startY - (offsetX < (waveLength / 2) ? (amplitude * offsetX / (waveLength / 2)) : (amplitude * (waveLength - offsetX) / offsetX));

            //Log.d("X", newX+"");
            //Log.d("Y", newY+"");

            ((RelativeLayout.LayoutParams) view.getLayoutParams()).leftMargin = newX;
            ((RelativeLayout.LayoutParams) view.getLayoutParams()).topMargin = newY;

            ((RelativeLayout.LayoutParams) view.getLayoutParams()).rightMargin = -2000;
            ((RelativeLayout.LayoutParams) view.getLayoutParams()).bottomMargin = -2000;

            view.requestLayout();
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }

    public static class SineWaveTrajectory extends Animation {
        final int totalX;
        final int numberOfWaves;
        final int amplitude;
        final View view;
        final int startX;
        final int startY;
        final int waveLength;

        public SineWaveTrajectory(View view, int totalX, int numberOfWaves, int amplitude, int startX, int startY) {
            this.view = view;
            this.totalX = totalX;
            this.numberOfWaves = numberOfWaves;
            this.amplitude = amplitude;
            this.startX = startX;
            this.startY = startY;
            this.waveLength = totalX / (numberOfWaves);
            //Log.d("Amplitude", amplitude+"");
            //Log.d("Wavelength", waveLength+"");
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int newX = (int) (startX + ((totalX - startX) * interpolatedTime));
            int newY = (int) (startY - (amplitude * Math.sin(Math.toRadians((newX * numberOfWaves) / Math.PI))));

            //Log.d("X", newX+"");
            //Log.d("Y", newY+"");

            ((RelativeLayout.LayoutParams) view.getLayoutParams()).leftMargin = newX;
            ((RelativeLayout.LayoutParams) view.getLayoutParams()).topMargin = newY;

            ((RelativeLayout.LayoutParams) view.getLayoutParams()).rightMargin = -2000;
            ((RelativeLayout.LayoutParams) view.getLayoutParams()).bottomMargin = -2000;

            view.requestLayout();
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }

    public static void alphaScale(final View view, Context context, int duration) {
        //view.setVisibility(View.INVISIBLE);
        AnimationSet animationSet = new AnimationSet(context, null);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.3f, 1f, 0.3f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //TranslateAnimation translateAnimation = new TranslateAnimation(initialX, finalX, initialY, finalY);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1f);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        //animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(duration);
        animationSet.setFillAfter(true);
        view.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void fadeView (final View view, Context context) {
        AlphaAnimation fadeAnimation = new AlphaAnimation(1f, 0f);
        fadeAnimation.setDuration(300);
        view.startAnimation(fadeAnimation);
        fadeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
