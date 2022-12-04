package com.edward.myapplication.AppSeller.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.edward.myapplication.LoginActivity;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.helper.MyHelper;
import com.edward.myapplication.model.modelrequest.ClothesPropertyReq;
import com.edward.myapplication.model.modelrequest.ClothesReq;
import com.edward.myapplication.model.modelrespon.CategoryRes;
import com.edward.myapplication.model.modelrespon.ClothesPropertiesRes;
import com.edward.myapplication.model.modelrespon.ResGetListCategory;
import com.edward.myapplication.model.modelrespon.ResGetListProperties;
import com.edward.myapplication.model.modelrespon.Respon;
import com.github.guilhe.recyclerpickerdialog.RecyclerPickerDialogFragment;
import com.github.guilhe.recyclerpickerdialog.SelectionType;
import com.github.guilhe.recyclerpickerdialog.SelectorType;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mcdev.quantitizerlibrary.AnimationStyle;
import com.mcdev.quantitizerlibrary.HorizontalQuantitizer;
import com.mcdev.quantitizerlibrary.QuantitizerListener;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddClothesActivity extends AppCompatActivity implements View.OnClickListener {

    HorizontalQuantitizer hqAddClothes;
    Button btSizeS, btSizeM, btSizeL, btSizeXL, btAddCategoryClothes, btAddClothes;
    ImageButton ibAddCloth1, ibAddCloth2, ibAddCloth3;
    ImageView ivBackFromAddClothesToClothesActivity;
    EditText edtAddDescriptionClothes, edtAddNameClothes, edtAddPriceClothes;
    TextView tvCountChooseImage;
    private int quantity = 0;
    private int sizeCheck = 0;
    private int chooseImageCheck = 0;
    private BottomSheetDialog bottomSheetDialog;

    Uri imageUri;
    List<Uri> lsImageUri;

    private int idSeller = LoginActivity.PERSONRES.getId();

    // picker dialog
    List<String> lsCategories;
    List<Integer> lsIdCategories;
    int idCategorySelected = -1;
    int posDialogCategories = 0;
//    List<String> lsCategoriesSelected;
//    List<Integer> lsIdCategoriesSelected;
    // end picker dialog

    String sizeS = "", sizeM = "", sizeL = "", sizeXL = "";
    List<String> lsSize;

    private ProgressDialog mProgressDialog;

    List<String> lsImageUrl = new ArrayList<>();

    private int countChooseImage = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);
        initViews();
        mProgressDialog = new ProgressDialog(this);
        lsSize = new ArrayList<>();
        lsCategories = new ArrayList<>();
        lsIdCategories = new ArrayList<>();
        lsImageUri = new ArrayList<>();
//        lsCategoriesSelected = new ArrayList<>();
//        lsIdCategoriesSelected = new ArrayList<>();
        loadHorizontalQuantitizer();
        loadListCategories();

        btSizeS.setOnClickListener(this);
        btSizeM.setOnClickListener(this);
        btSizeL.setOnClickListener(this);
        btSizeXL.setOnClickListener(this);
        ibAddCloth1.setOnClickListener(this);
        ibAddCloth2.setOnClickListener(this);
        ibAddCloth3.setOnClickListener(this);
        ivBackFromAddClothesToClothesActivity.setOnClickListener(this);
        btAddCategoryClothes.setOnClickListener(this);
        btAddClothes.setOnClickListener(this);


//        String[] a = lsCategories.toArray(new String[0]);
//        System.out.println(a.length);
//        ServiceAPI.serviceApi.GetAllClothesProperties(108)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResGetListProperties>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ResGetListProperties resGetListProperties) {
//                        Log.d("Size: ", resGetListProperties.get_ClothesPropertiesRes().size()+"");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    private void initViews() {
        hqAddClothes = findViewById(R.id.hqAddClothes);
        btSizeS = findViewById(R.id.btSizeS);
        btSizeM = findViewById(R.id.btSizeM);
        btSizeL = findViewById(R.id.btSizeL);
        btSizeXL = findViewById(R.id.btSizeXL);
        ibAddCloth1 = findViewById(R.id.ibAddImageClothes1);
        ibAddCloth2 = findViewById(R.id.ibAddImageClothes2);
        ibAddCloth3 = findViewById(R.id.ibAddImageClothes3);
        ivBackFromAddClothesToClothesActivity = findViewById(R.id.ivBackFromAddClothesToClothesActivity);
        btAddCategoryClothes = findViewById(R.id.btAddCategoryClothes);
        btAddClothes = findViewById(R.id.btAddClothes);
        edtAddDescriptionClothes = findViewById(R.id.edtAddDescriptionClothes);
        edtAddNameClothes = findViewById(R.id.edtAddNameClothes);
        edtAddPriceClothes = findViewById(R.id.edtAddPriceClothes);
        tvCountChooseImage = findViewById(R.id.tvCountChooseImage);
    }

    private void loadHorizontalQuantitizer() {
        hqAddClothes.setTextAnimationStyle(AnimationStyle.FALL_IN);
        hqAddClothes.setQuantitizerListener(new QuantitizerListener() {
            @Override
            public void onIncrease() {

            }

            @Override
            public void onDecrease() {
            }

            @Override
            public void onValueChanged(int i) {
                quantity = i;
            }
        });
    }

    private void showBottomSheetDialog() {

        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.dialog_options_get_image);

//        Button btLogout = bottomSheetDialog.findViewById(R.id.btLogout);
        Button btCancel = bottomSheetDialog.findViewById(R.id.btn_cancel);
        Button btCamera = bottomSheetDialog.findViewById(R.id.btn_camera);
        Button btGallery = bottomSheetDialog.findViewById(R.id.btn_gallery);
//
//        btLogout.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        btCamera.setOnClickListener(this);
        btGallery.setOnClickListener(this);

        bottomSheetDialog.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btSizeS:
                if (!sizeS.equals("S")) {
                    sizeS = "S";
                    btSizeS.setBackgroundResource(R.drawable.background_size_clothes_selected);
                    lsSize.add(sizeS);
                } else {
                    lsSize.remove(sizeS);
                    sizeS = "";
                    btSizeS.setBackgroundResource(R.drawable.background_size_clothes);
                }
                break;
            case R.id.btSizeM:
                if (!sizeM.equals("M")) {
                    sizeM = "M";
                    btSizeM.setBackgroundResource(R.drawable.background_size_clothes_selected);
                    lsSize.add(sizeM);
                } else {
                    lsSize.remove(sizeM);
                    sizeM = "";
                    btSizeM.setBackgroundResource(R.drawable.background_size_clothes);
                }
                break;
            case R.id.btSizeL:
                if (!sizeL.equals("L")) {
                    sizeL = "L";
                    btSizeL.setBackgroundResource(R.drawable.background_size_clothes_selected);
                    lsSize.add(sizeL);
                } else {
                    lsSize.remove(sizeL);
                    sizeL = "";
                    btSizeL.setBackgroundResource(R.drawable.background_size_clothes);
                }
                break;
            case R.id.btSizeXL:
                if (!sizeXL.equals("XL")) {
                    sizeXL = "XL";
                    btSizeXL.setBackgroundResource(R.drawable.background_size_clothes_selected);
                    lsSize.add(sizeXL);
                } else {
                    lsSize.remove(sizeXL);
                    sizeXL = "";
                    btSizeXL.setBackgroundResource(R.drawable.background_size_clothes);
                }
                break;
            case R.id.ibAddImageClothes1:
                chooseImageCheck = 1;
                showBottomSheetDialog();
                break;
            case R.id.ibAddImageClothes2:
                chooseImageCheck = 2;
                showBottomSheetDialog();
                break;
            case R.id.ibAddImageClothes3:
                chooseImageCheck = 3;
                showBottomSheetDialog();
                break;
            case R.id.btn_cancel:
                bottomSheetDialog.dismiss();
                break;
            case R.id.btn_camera:
                enableRuntimePermission();
                bottomSheetDialog.dismiss();
                break;
            case R.id.btn_gallery:
                openGallery();
                bottomSheetDialog.dismiss();
                break;
            case R.id.ivBackFromAddClothesToClothesActivity:
                startActivity(new Intent(this, ClothesManagementActivity.class));
                break;
            case R.id.btAddCategoryClothes:
                showDialogSelectCategories();
                break;
            case R.id.btAddClothes:
                upload(lsImageUri);
                break;
        }
    }

    private List<ClothesPropertyReq> getListClothesProperties(int quantity, String price) {
        List<ClothesPropertyReq> lsClothesProperty = new ArrayList<>();
        for (String size : lsSize) {
            lsClothesProperty.add(new ClothesPropertyReq(size, quantity, price));
        }
        return lsClothesProperty;
    };

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
                    lsImageUrl.add(Objects.requireNonNull(resultData.get("url")).toString());
                    if (lsImageUrl.size() == uri.size()) {
                        Log.e(lsImageUrl.size()+"","");
                        //call api hear, after that clear list
                        //mProgressDialog.dismiss();
                        String des = edtAddDescriptionClothes.getText().toString();
                        String name = edtAddNameClothes.getText().toString();
                        String price = edtAddPriceClothes.getText().toString();
                        List<ClothesPropertyReq> lsClothesProperty = getListClothesProperties(quantity, price);


                        ClothesReq clothesReq = new ClothesReq(1, idSeller, idCategorySelected, des, name,lsImageUrl, lsClothesProperty);
                        ServiceAPI.serviceApi.CreateClothes(clothesReq)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<Respon>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                        ProgressDialogCustom.showProgressDialog(AddClothesActivity.this, "Please wait");
                                    }

                                    @Override
                                    public void onNext(Respon respon) {
                                        if (respon.getRespone_code() == 200) {
                                            PopupDialog.getInstance(AddClothesActivity.this)
                                                    .setStyle(Styles.SUCCESS)
                                                    .setHeading("Well Done")
                                                    .setHeading("You have successfully" +
                                                            " added")
                                                    .setCancelable(false)
                                                    .showDialog(new OnDialogButtonClickListener() {
                                                        @Override
                                                        public void onDismissClicked(Dialog dialog1) {
                                                            super.onDismissClicked(dialog1);
                                                            mProgressDialog.dismiss();
                                                        }
                                                    });
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.d("add clothe ne: ", "err");

                                    }

                                    @Override
                                    public void onComplete() {
                                        ProgressDialogCustom.dismissProgressDialog();

                                    }
                                });

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

    private void addClothes() {
        String des = edtAddDescriptionClothes.getText().toString();
        String name = edtAddNameClothes.getText().toString();
        String price = edtAddPriceClothes.getText().toString();
        List<ClothesPropertyReq> lsClothesProperty = getListClothesProperties(quantity, price);
        List<String> lsImageUrl = new ArrayList<>();
        lsImageUrl.add("https://images.unsplash.com/photo-1607345366928-199ea26cfe3e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        lsImageUrl.add("https://images.unsplash.com/photo-1603252109303-2751441dd157?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        lsImageUrl.add("https://images.unsplash.com/photo-1620799140408-edc6dcb6d633?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1072&q=80");

        ClothesReq clothesReq = new ClothesReq(1, idSeller, idCategorySelected, des, name,lsImageUrl, lsClothesProperty);
        ServiceAPI.serviceApi.CreateClothes(clothesReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(AddClothesActivity.this, "Please wait");
                    }

                    @Override
                    public void onNext(Respon respon) {
                        if (respon.getRespone_code() == 200) {
                            PopupDialog.getInstance(AddClothesActivity.this)
                                    .setStyle(Styles.SUCCESS)
                                    .setHeading("Well Done")
                                    .setHeading("You have successfully" +
                                            " added")
                                    .setCancelable(false)
                                    .showDialog(new OnDialogButtonClickListener() {
                                        @Override
                                        public void onDismissClicked(Dialog dialog1) {
                                            super.onDismissClicked(dialog1);
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("add clothe ne: ", "err");

                    }

                    @Override
                    public void onComplete() {
                        ProgressDialogCustom.dismissProgressDialog();

                    }
                });

    }

    private void loadListCategories() {
        ServiceAPI.serviceApi.GetAllCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListCategory>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(AddClothesActivity.this, "Please wait");
                    }

                    @Override
                    public void onNext(ResGetListCategory resGetListCategory) {
                        Log.d(">>>>>>>>", resGetListCategory.get_Respon().getRespone_code()+"");

                        if (resGetListCategory.get_Respon().getRespone_code() == 200) {
                            List<CategoryRes> lsAllCategories = resGetListCategory.get_CategoryRes();
                            for (CategoryRes item: lsAllCategories) {
                                lsCategories.add(item.getName());
                                lsIdCategories.add(item.getId());
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(">>>>>>>>", "er");
                    }

                    @Override
                    public void onComplete() {
                        ProgressDialogCustom.dismissProgressDialog();

                    }
                });
    }

    private void showDialogSelectCategories() {

        String[] arrCategories = lsCategories.toArray(new String[0]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MaterialThemeDialog);
        builder.setTitle("Select categories");
        builder.setSingleChoiceItems(arrCategories, posDialogCategories, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                idCategorySelected = lsIdCategories.get(i);
                posDialogCategories = i;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = arrCategories[posDialogCategories];
                btAddCategoryClothes.setText(name);
            }
        });
        builder.create();
        builder.show();
    }


    private void openCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 101);
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    imageUri = data.getData();
                    switch (chooseImageCheck) {
                        case 1:
                            fillImage(imageUri, ibAddCloth1);
                            break;
                        case 2:
                            fillImage(imageUri, ibAddCloth2);
                            break;
                        case 3:
                            fillImage(imageUri, ibAddCloth3);
                            break;
                    }
                }
                break;
            case 101:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    switch (chooseImageCheck) {
                        case 1:
                            fillImageBitmap(bitmap, ibAddCloth1);
                            break;
                        case 2:
                            fillImageBitmap(bitmap, ibAddCloth2);
                            break;
                        case 3:
                            fillImageBitmap(bitmap, ibAddCloth3);
                            break;
                    }
                }
                break;

        }
    }

    private void fillImage(Uri imageUri1, ImageButton ib) {
        lsImageUri.add(imageUri1);
        countChooseImage++;
        tvCountChooseImage.setText(countChooseImage + "/3");
        ib.setImageURI(imageUri1);
        ib.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    private void fillImageBitmap(Bitmap bitmap, ImageButton ib) {
        Uri uri = MyHelper.getImageUri(this, bitmap);
        lsImageUri.add(uri);
        tvCountChooseImage.setText(countChooseImage + "/3");
        ib.setImageBitmap(bitmap);
        ib.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    public void enableRuntimePermission(){
        if (ContextCompat.checkSelfPermission(AddClothesActivity.this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            ActivityCompat.requestPermissions(AddClothesActivity.this,new String[]{
                    Manifest.permission.CAMERA}, 102);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
        super.onRequestPermissionsResult(requestCode, permissions, result);
        switch (requestCode) {
            case 102:
                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                }
                break;
        }
    }


}