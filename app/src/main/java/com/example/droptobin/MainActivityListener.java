package com.example.droptobin;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivityListener implements View.OnDragListener, View.OnTouchListener {

    MainActivity mainActivity;
    ConstraintLayout constraintLayout;

    public MainActivityListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        constraintLayout = mainActivity.clLayout;
    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                if (view.getId() == mainActivity.ivTrash.getId())
                    mainActivity.ivTrash.startAnimation(loadAnimation(R.anim.enlarge));
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                if (view.getId() == mainActivity.ivTrash.getId())
                    mainActivity.ivTrash.startAnimation(loadAnimation(R.anim.shrink));
                break;
            case DragEvent.ACTION_DROP:
                //Objekt, das bewegt wurde
                View ball = (View) dragEvent.getLocalState();
                //Wo wird Ball gedropped?
                if (view.getId() == R.id.clLayout) {
                    //ConstraintLayout ziel = (ConstraintLayout) view;
                    constraintLayout.removeView(ball);
                    ball.setX(dragEvent.getX() - convertDpToPixel(25, mainActivity));
                    ball.setY(dragEvent.getY() - convertDpToPixel(25, mainActivity));
                    constraintLayout.addView(ball);
                    ball.setVisibility(View.VISIBLE);
                }
                if (view.getId() == R.id.ivTrash) {
                    constraintLayout.removeView(ball);
                    mainActivity.ivTrash.startAnimation(loadAnimation(R.anim.shrink));
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDragAndDrop(null, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        }
        return false;
    }

    private Animation loadAnimation(int res) {
        return AnimationUtils.loadAnimation(mainActivity, res);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.omAdd) {
            mainActivity.ivShape.setX(100);
            mainActivity.ivShape.setY(100);
            if(mainActivity.ivShape.getVisibility() != View.VISIBLE) {
                constraintLayout.addView(mainActivity.ivShape);
                mainActivity.ivShape.setVisibility(View.VISIBLE);
            }
        }
        return true;
    }
}
