package com.example.adas.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;



import com.example.adas.LoginActivity;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Helpers {


    // Show Toast
    public static void showToast(Context context, String msg) {
        Toast myToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        myToast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 100);
        myToast.show();
    }


    // Return Resource Image ID
    public static int getResoureImageID(Context context, String imageName) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    // Return Resource Name By ID
    public static String getResoureNameByID(Context context, int resourceID) {
        return context.getResources().getResourceEntryName(resourceID);
    }

    public static void setupUI(View view, final Activity activity) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    try {
                        hideSoftKeyboard(activity);
                    } catch (Exception ex) {
                        Log.e("Error_Keyboard", ex.getMessage());
                    }

                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, activity);
            }
        }
    }

    public static Calendar getTime(String time) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        try {
            cal.setTime(sdf.parse(time));// all done
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return cal;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static String showTime(Calendar calendar, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);  // 12:25 PM
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String showTime(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);  // 12:25 PM
        return simpleDateFormat.format(date);
    }


    public static String getDatabasePath(String fileNname, Context context) {
        return context.getDatabasePath(fileNname).getAbsolutePath();
    }

    //encode Image
    public static byte[] encodeImage(ImageView imageView) {
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();
        return data;
    }

    public static void showDialog(ProgressBar mprogressBar) {
        mprogressBar.setVisibility(View.VISIBLE);

    }

    public static void hideDialog(ProgressBar mprogressBar) {
        if (mprogressBar.getVisibility() == View.VISIBLE) {
            mprogressBar.setVisibility(View.INVISIBLE);
        }
    }

    public static void redirectLoginScreen(String TAG, Activity activity) {
        Log.d(TAG, "redirectLoginScreen: redirecting to login screen.");
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    public static String[] filenameExtensionSplitter(String filename) {
        String[] filenamePieces = filename.split("\\.");
        String extension = "." + filenamePieces[filenamePieces.length - 1];
        StringBuilder filenameStem = new StringBuilder();
        for (String filenamePiece : filenamePieces) {
            filenameStem.append(filenamePiece);
        }
        return new String[]{filenameStem.toString(), extension};
    }

}
