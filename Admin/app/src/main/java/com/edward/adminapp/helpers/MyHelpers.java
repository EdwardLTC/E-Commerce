package com.edward.adminapp.helpers;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Locale;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class MyHelpers {

    // gõ xong tự ẩn bàn phím
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

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

    public static String getHashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static Boolean isVerifiedHash(String password, String passwordHash) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), passwordHash);
        return result.verified;
    }
}
