package com.edward.myapplication.helper;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.widget.Button;

import com.edward.myapplication.R;

public class MyHelper {

    // thêm hiệu ứng khi click
    public static void addClickEffect(View view)
    {
        Drawable drawableNormal = view.getBackground();

        Drawable drawablePressed = view.getBackground().getConstantState().newDrawable();
        drawablePressed.mutate();
        drawablePressed.setColorFilter(Color.argb(50, 0, 0, 0), PorterDuff.Mode.SRC_ATOP);

        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(new int[] {android.R.attr.state_pressed}, drawablePressed);
        listDrawable.addState(new int[] {}, drawableNormal);
        view.setBackground(listDrawable);
    }

    public static String getSizeClothes(int sizeCheck) {
        switch (sizeCheck) {
            case 1:
                return "S";
            case 2:
                return "M";
            case 3:
                return "L";
            case 4:
                return "XL";
        }
        return "";
    }

    public static void checkButtonSize(Button bt1, Button bt2, Button bt3, Button bt4) {
        bt1.setBackgroundResource(R.drawable.background_size_clothes_selected);
        bt2.setBackgroundResource(R.drawable.background_size_clothes);
        bt3.setBackgroundResource(R.drawable.background_size_clothes);
        bt4.setBackgroundResource(R.drawable.background_size_clothes);


    }
}
