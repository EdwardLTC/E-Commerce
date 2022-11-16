package com.edward.adminapp.adapter;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogCustom {
    private static ProgressDialog mProgressDialog;

    public static void showProgressDialog(Context context, String message) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }

    public static void dismissProgressDialog() {
        mProgressDialog.dismiss();
    }
}
