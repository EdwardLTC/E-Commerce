package com.edward.myapplication;

import android.app.ProgressDialog;
import android.net.Uri;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DemoUploadImg extends AppCompatActivity {

    private ProgressDialog mProgressDialog = new ProgressDialog(this);

    private List<String> list = new ArrayList<>();
    private void upload(List<Uri> uri) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "di34trzsa");
        config.put("api_key", "498634466359264");
        config.put("api_secret", "SardrENb4IqdE80-Q72RtEu9xGQ");
        MediaManager.init(this, config);
        for (Uri ur : uri) {
            if (!mProgressDialog.isShowing())
                mProgressDialog.show();
            MediaManager.get().upload(ur).callback(new UploadCallback() {
                @Override
                public void onStart(String requestId) {

                }

                @Override
                public void onProgress(String requestId, long bytes, long totalBytes) {

                }

                @Override
                public void onSuccess(String requestId, Map resultData) {
                    Log.e("CHECK", Objects.requireNonNull(resultData.get("url")).toString());
                    list.add(Objects.requireNonNull(resultData.get("url")).toString());
                    if (list.size() == uri.size()) {
                        Log.e(list.size()+"","");
                        //call api hear, after that clear list
                        //mProgressDialog.dismiss();
                    }
                }

                @Override
                public void onError(String requestId, ErrorInfo error) {
                    Log.e("CHECK", "onError: " + error);
                }

                @Override
                public void onReschedule(String requestId, ErrorInfo error) {
                    Log.e("CHECK", "onReschedule: " + error);
                }
            }).dispatch();
        }
    }
}
