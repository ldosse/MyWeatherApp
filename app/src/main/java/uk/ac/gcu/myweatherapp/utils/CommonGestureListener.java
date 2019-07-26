package uk.ac.gcu.myweatherapp.utils;

import android.app.Activity;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import uk.ac.gcu.myweatherapp.R;

public class CommonGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static String TAG_COMMON_GESTURE_DETECTOR = "COMMON_GESTURE_DETECTOR";

    public View srcView = null;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity activity = null;

    public View getSrcView() {
        return srcView;
    }

    public void setSrcView(View srcView) {
        this.srcView = srcView;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d(TAG_COMMON_GESTURE_DETECTOR, buildMessage("onDown()"));
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.d(TAG_COMMON_GESTURE_DETECTOR, buildMessage("onShowPress()"));
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.d(TAG_COMMON_GESTURE_DETECTOR, buildMessage("onSingleTapUp()"));
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(TAG_COMMON_GESTURE_DETECTOR, buildMessage("onScroll()"));
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.d(TAG_COMMON_GESTURE_DETECTOR, buildMessage("onLongPress()"));
        if(srcView instanceof LinearLayout){
            System.out.println("LOOOOOOOOOOOOONG");

        }
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(TAG_COMMON_GESTURE_DETECTOR, buildMessage("onFling()"));

        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        this.activity.findViewById(R.id.previousFAB).setAlpha(1f);
        Log.d(TAG_COMMON_GESTURE_DETECTOR, buildMessage("onSingleTapConfirmed()"));
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Log.d(TAG_COMMON_GESTURE_DETECTOR, buildMessage("onDoubleTap()"));
        this.activity.findViewById(R.id.previousFAB).setAlpha(0.8f);
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        this.activity.findViewById(R.id.previousFAB).setAlpha(1f);
        Log.d(TAG_COMMON_GESTURE_DETECTOR, buildMessage("onDoubleTapEvent()"));
        return true;
    }

//    @Override
//    public boolean onDrag(View v, DragEvent event) {
//        return false;
//    }

//    public boolean onDrag()

    private String buildMessage(String message)
    {
        StringBuffer retBuf = new StringBuffer();
        if(srcView instanceof TextView)
        {
            retBuf.append("Text view gesture : ");
        }
        retBuf.append(message);
        return retBuf.toString();
    }
}
