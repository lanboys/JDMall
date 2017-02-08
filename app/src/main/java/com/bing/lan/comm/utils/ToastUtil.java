package com.bing.lan.comm.utils;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author 赵坤
 * @email artzok@163.com
 */
public class ToastUtil {
    private static Toast textToast;
    private static Toast iconToast;

    public static void show(Context context, String msg) {
        if (textToast == null) {
            textToast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            textToast.setText(msg);
        }
        textToast.show();
    }

    public static void show(Context context, String msg, int iconResId) {
        String tag = "toast_image";
        if (iconToast == null) {
            iconToast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
            // icon image view
            ImageView image = new ImageView(context.getApplicationContext());
            image.setImageResource(iconResId);
            image.setTag(tag);
            // add icon
            ViewGroup group = (ViewGroup) iconToast.getView();
            group.addView(image, 0);
        } else {
            iconToast.setText(msg);
            ImageView image = (ImageView) iconToast.getView().findViewWithTag(tag);
            image.setImageResource(iconResId);
        }
        iconToast.show();
    }
}
