package com.edward.myapplication.AppSeller.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.edward.myapplication.R;
import com.edward.myapplication.helper.MyHelper;
import com.edward.myapplication.model.modelrespon.CategoryRes;
import com.github.guilhe.recyclerpickerdialog.RecyclerPickerDialogFragment;
import com.github.guilhe.recyclerpickerdialog.SelectionType;
import com.github.guilhe.recyclerpickerdialog.SelectorType;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mcdev.quantitizerlibrary.AnimationStyle;
import com.mcdev.quantitizerlibrary.HorizontalQuantitizer;
import com.mcdev.quantitizerlibrary.QuantitizerListener;

import java.util.ArrayList;
import java.util.List;

public class AddClothesActivity extends AppCompatActivity implements View.OnClickListener {

    HorizontalQuantitizer hqAddClothes;
    Button btSizeS, btSizeM, btSizeL, btSizeXL, btAddCategoryClothes;
    ImageButton ibAddCloth1, ibAddCloth2, ibAddCloth3;
    ImageView ivBackFromAddClothesToClothesActivity;
    private int quantity = 0;
    private int sizeCheck = 0;
    private int chooseImageCheck = 0;
    private BottomSheetDialog bottomSheetDialog;
    Uri imageUri;

    // picker dialog
    String title = "";
    Boolean showSearchBar = false;
    String inputHint = "";
    String buttonText = "OK";
    Boolean resetValueOnShow = true;
    Boolean dismissKeyboardOnSelection = true;
    Boolean dismissOnSelection = true;
    Boolean isChoiceMandatory = true;
    List<CategoryRes> lsCategory = new ArrayList<>();
    int dialogHeight = ViewGroup.LayoutParams.MATCH_PARENT;
    LifecycleOwner lifecycleOwner = null;
    // end picker dialog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);
        initViews();
        loadHorizontalQuantitizer();

        btSizeS.setOnClickListener(this);
        btSizeM.setOnClickListener(this);
        btSizeL.setOnClickListener(this);
        btSizeXL.setOnClickListener(this);
        ibAddCloth1.setOnClickListener(this);
        ibAddCloth2.setOnClickListener(this);
        ibAddCloth3.setOnClickListener(this);
        ivBackFromAddClothesToClothesActivity.setOnClickListener(this);
        btAddCategoryClothes.setOnClickListener(this);

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
                sizeCheck = 1;
                MyHelper.checkButtonSize(btSizeS, btSizeL, btSizeM, btSizeXL);
               break;
            case R.id.btSizeM:
                sizeCheck = 2;
                MyHelper.checkButtonSize(btSizeM, btSizeL, btSizeS, btSizeXL);
                break;
            case R.id.btSizeL:
                sizeCheck = 3;
                MyHelper.checkButtonSize(btSizeL, btSizeS, btSizeM, btSizeXL);
                break;
            case R.id.btSizeXL:
                sizeCheck = 4;
                MyHelper.checkButtonSize(btSizeXL, btSizeL, btSizeM, btSizeS);
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
//                showDialogSelectCategories();
                break;
        }
    }

//    private void showDialogSelectCategories() {
//        Toast.makeText(this, "Heller", Toast.LENGTH_SHORT).show();
//        RecyclerPickerDialogFragment.class.newInstance(
//                SelectionType.MULTIPLE,
//                SelectorType.CHECK_BOX,
//                R.style.DialogA,
//
//        )
//        RecyclerPickerDialogFragment nwq = new RecyclerPickerDialogFragment();
//    }

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
        ib.setImageURI(imageUri1);
        ib.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    private void fillImageBitmap(Bitmap bitmap, ImageButton ib) {
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