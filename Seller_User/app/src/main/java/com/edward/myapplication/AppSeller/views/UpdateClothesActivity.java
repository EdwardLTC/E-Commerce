package com.edward.myapplication.AppSeller.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrequest.ClothesPropertyReq;
import com.edward.myapplication.model.modelrequest.ClothesReq;
import com.edward.myapplication.model.modelrespon.CategoryRes;
import com.edward.myapplication.model.modelrespon.ClothesPropertiesRes;
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResGetCategory;
import com.edward.myapplication.model.modelrespon.ResGetListCategory;
import com.edward.myapplication.model.modelrespon.Respon;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mcdev.quantitizerlibrary.AnimationStyle;
import com.mcdev.quantitizerlibrary.HorizontalQuantitizer;
import com.mcdev.quantitizerlibrary.QuantitizerListener;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UpdateClothesActivity extends AppCompatActivity implements View.OnClickListener {

    private static ProgressDialog mProgressDialog;

    HorizontalQuantitizer hqUpdateClothes;
    Button btSizeS, btSizeM, btSizeL, btSizeXL, btUpdateCategoryClothes, btUpdateClothes;
    ImageButton ibUpdateCloth1, ibUpdateCloth2, ibUpdateCloth3;
    ImageView ivBackFromUpdateClothesToClothesActivity;
    EditText edtUpdateDescriptionClothes, edtUpdateNameClothes, edtUpdatePriceClothes;
    private int quantity = 0;
    private int sizeCheck = 0;
    private int chooseImageCheck = 0;
    private BottomSheetDialog bottomSheetDialog;
    Uri imageUri;

    private int idSeller = 11;
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

    private ClothesRes clothesUpdate;
    private List<ClothesPropertiesRes> lsClothesPropertiesUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_clothes);
        initViews();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Wait");
        mProgressDialog.setCanceledOnTouchOutside(false);
        // fill value to update
        clothesUpdate = (ClothesRes)getIntent().getSerializableExtra("clothesUpdate");
        lsClothesPropertiesUpdate = (List<ClothesPropertiesRes>) getIntent().getSerializableExtra("lsClothesPropertiesUpdate");
        fillValueUpdate(clothesUpdate);
        // end fill value
        lsSize = new ArrayList<>();
        lsCategories = new ArrayList<>();
        lsIdCategories = new ArrayList<>();
//        lsCategoriesSelected = new ArrayList<>();
//        lsIdCategoriesSelected = new ArrayList<>();
        loadHorizontalQuantitizer();
        loadListCategories();

        btSizeS.setOnClickListener(this);
        btSizeM.setOnClickListener(this);
        btSizeL.setOnClickListener(this);
        btSizeXL.setOnClickListener(this);
        ibUpdateCloth1.setOnClickListener(this);
        ibUpdateCloth2.setOnClickListener(this);
        ibUpdateCloth3.setOnClickListener(this);
        ivBackFromUpdateClothesToClothesActivity.setOnClickListener(this);
        btUpdateCategoryClothes.setOnClickListener(this);
        btUpdateClothes.setOnClickListener(this);
    }


    private void fillValueUpdate(ClothesRes clothesUpdate) {
        edtUpdateNameClothes.setText(clothesUpdate.getName());
        edtUpdateDescriptionClothes.setText(clothesUpdate.getDes());
        edtUpdatePriceClothes.setText(getIntent().getStringExtra("priceClothesUpdate").substring(1));

        Log.d(">>>>>>", clothesUpdate.getIdCategory()+"");
        ServiceAPI.serviceApi.GetCategoryWhere(clothesUpdate.getIdCategory())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetCategory>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                      mProgressDialog.show();
                    }

                    @Override
                    public void onNext(ResGetCategory resGetCategory) {
                        if (resGetCategory.get_Respon().getRespone_code() == 200) {
                            btUpdateCategoryClothes.setText(resGetCategory.get_CategoryRes().getName());
                        }
                        Log.e(resGetCategory.get_Respon().getRespone_code()+"","");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressDialog.dismiss();
                        Log.d("Loi", "err");
                    }

                    @Override
                    public void onComplete() {
                        mProgressDialog.dismiss();
                    }
                });

        btSizeS.setBackgroundResource(getBackgroundResource(btSizeS));
        btSizeM.setBackgroundResource(getBackgroundResource(btSizeM));
        btSizeL.setBackgroundResource(getBackgroundResource(btSizeL));
        btSizeXL.setBackgroundResource(getBackgroundResource(btSizeXL));

        Glide.with(this).load(clothesUpdate.getImgsUrl().get(0)).into(ibUpdateCloth1);
        Glide.with(this).load(clothesUpdate.getImgsUrl().get(1)).into(ibUpdateCloth2);

        if (clothesUpdate.getImgsUrl().size() > 2) {
            Glide.with(this).load(clothesUpdate.getImgsUrl().get(2)).into(ibUpdateCloth3);
        }

    }

    private int getBackgroundResource(Button bt) {
        for (ClothesPropertiesRes cloth : lsClothesPropertiesUpdate) {
            if (bt.getText().toString().equals(cloth.getSize()))
                return R.drawable.background_size_clothes_selected;
        }
        return R.drawable.background_size_clothes;
    }

    private void initViews() {
        hqUpdateClothes = findViewById(R.id.hqUpdateClothes);
        btSizeS = findViewById(R.id.btSizeSUpdate);
        btSizeM = findViewById(R.id.btSizeMUpdate);
        btSizeL = findViewById(R.id.btSizeLUpdate);
        btSizeXL = findViewById(R.id.btSizeXLUpdate);
        ibUpdateCloth1 = findViewById(R.id.ibUpdateImageClothes1);
        ibUpdateCloth2 = findViewById(R.id.ibUpdateImageClothes2);
        ibUpdateCloth3 = findViewById(R.id.ibUpdateImageClothes3);
        ivBackFromUpdateClothesToClothesActivity = findViewById(R.id.ivBackFromUpdateClothesToClothesInformationActivity);
        btUpdateCategoryClothes = findViewById(R.id.btUpdateCategoryClothes);
        btUpdateClothes = findViewById(R.id.btUpdateClothes);
        edtUpdateDescriptionClothes = findViewById(R.id.edtUpdateDescriptionClothes);
        edtUpdateNameClothes = findViewById(R.id.edtUpdateNameClothes);
        edtUpdatePriceClothes = findViewById(R.id.edtUpdatePriceClothes);
    }

    private void loadHorizontalQuantitizer() {

        hqUpdateClothes.setValue(getQuantityClothes());
        hqUpdateClothes.setTextAnimationStyle(AnimationStyle.FALL_IN);
        hqUpdateClothes.setQuantitizerListener(new QuantitizerListener() {
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

    private int getQuantityClothes() {
        int size = lsClothesPropertiesUpdate.size();
        if (size == 4)
            return clothesUpdate.getQuantily() / 4;
        else if (size == 3)
            return clothesUpdate.getQuantily() / 3;
        else if (size == 2)
            return clothesUpdate.getQuantily() / 2;
        return clothesUpdate.getQuantily();

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
            case R.id.ibUpdateImageClothes1:
                chooseImageCheck = 1;
                showBottomSheetDialog();
                break;
            case R.id.ibUpdateImageClothes2:
                chooseImageCheck = 2;
                showBottomSheetDialog();
                break;
            case R.id.ibUpdateImageClothes3:
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
            case R.id.ivBackFromUpdateClothesToClothesInformationActivity:
                startActivity(new Intent(this, SellerClothesInformationActivity.class));
                break;
            case R.id.btUpdateCategoryClothes:
                showDialogSelectCategories();
                break;
            case R.id.btUpdateClothes:
                updateClothes();
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

    private void updateClothes() {
        String des = edtUpdateDescriptionClothes.getText().toString();
        String name = edtUpdateNameClothes.getText().toString();
        String price = edtUpdatePriceClothes.getText().toString();
        List<ClothesPropertyReq> lsClothesProperty = getListClothesProperties(quantity, price);
        List<String> lsImageUrl = new ArrayList<>();
        lsImageUrl.add("https://images.unsplash.com/photo-1607345366928-199ea26cfe3e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        lsImageUrl.add("https://images.unsplash.com/photo-1603252109303-2751441dd157?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80");
        lsImageUrl.add("https://images.unsplash.com/photo-1620799140408-edc6dcb6d633?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1072&q=80");

        ClothesReq clothesReq = new ClothesReq(clothesUpdate.getId(), idSeller, idCategorySelected, des, name,lsImageUrl, lsClothesProperty);
        ServiceAPI.serviceApi.UpdateClothes(clothesReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(UpdateClothesActivity.this, "Please wait");
                    }

                    @Override
                    public void onNext(Respon respon) {
                        if (respon.getRespone_code() == 200) {
                            PopupDialog.getInstance(UpdateClothesActivity.this)
                                    .setStyle(Styles.SUCCESS)
                                    .setHeading("Well Done")
                                    .setHeading("You have successfully" +
                                            " Updateed")
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
                        Log.d("Update clothe ne: ", "err");

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
                        ProgressDialogCustom.showProgressDialog(UpdateClothesActivity.this, "Please wait");
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
                btUpdateCategoryClothes.setText(name);
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
                            fillImage(imageUri, ibUpdateCloth1);
                            break;
                        case 2:
                            fillImage(imageUri, ibUpdateCloth2);
                            break;
                        case 3:
                            fillImage(imageUri, ibUpdateCloth3);
                            break;
                    }
                }
                break;
            case 101:
                if (resultCode == RESULT_OK) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    switch (chooseImageCheck) {
                        case 1:
                            fillImageBitmap(bitmap, ibUpdateCloth1);
                            break;
                        case 2:
                            fillImageBitmap(bitmap, ibUpdateCloth2);
                            break;
                        case 3:
                            fillImageBitmap(bitmap, ibUpdateCloth3);
                            break;
                    }
                }
                break;

        }
    }

    private void fillImage(Uri imageUri1, ImageButton ib) {
        ib.setImageURI(imageUri1);
        ib.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    private void fillImageBitmap(Bitmap bitmap, ImageButton ib) {
        ib.setImageBitmap(bitmap);
        ib.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    public void enableRuntimePermission(){
        if (ContextCompat.checkSelfPermission(UpdateClothesActivity.this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            ActivityCompat.requestPermissions(UpdateClothesActivity.this,new String[]{
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