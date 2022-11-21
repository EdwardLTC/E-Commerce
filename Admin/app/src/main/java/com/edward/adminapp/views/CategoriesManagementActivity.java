package com.edward.adminapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edward.adminapp.API.ServiceAPI;
import com.edward.adminapp.R;
import com.edward.adminapp.adapter.ProgressDialogCustom;
import com.edward.adminapp.adapters.CategoriesAdapter;
import com.edward.adminapp.helpers.MyHelpers;
import com.edward.adminapp.model.modelrequest.CategoryReq;
import com.edward.adminapp.model.modelrespon.CategoryRes;
import com.edward.adminapp.model.modelrespon.ResGetListCategory;
import com.edward.adminapp.model.modelrespon.Respon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoriesManagementActivity extends AppCompatActivity  implements View.OnClickListener {
    RecyclerView rcvCategoriesManagement;
    CardView cvBackToHomeFromCategories;
    TextView tvRefreshCategories, tvCantFindCategories, tvTryAgainCategories;
    CategoriesAdapter categoriesAdapter;
    EditText edtSearchCategories;
    FloatingActionButton fabAddCategories;
    List<CategoryRes> ls;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_management);
        ls = new ArrayList<>();
        initViews();
        initRecyclerView();
        loadRecycleView();

        // handle listener
        cvBackToHomeFromCategories.setOnClickListener(this);
        tvRefreshCategories.setOnClickListener(this);

        edtSearchCategories.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String find = textView.getText().toString();
                    if (!find.isEmpty()) {
                        searchCategories(find);
//                        searchSellers(find);
                    }
                    MyHelpers.hideKeyboard(CategoriesManagementActivity.this);
                    return true;
                }

                return false;
            }
        });

        fabAddCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCreateCategory();
            }
        });
    }
    private void initViews() {
        rcvCategoriesManagement = findViewById(R.id.rcvCategoriesManagement);
        edtSearchCategories = findViewById(R.id.edtSearchCategories);
        cvBackToHomeFromCategories = findViewById(R.id.cvBackToHomeFromCategories);

        tvRefreshCategories = findViewById(R.id.tvRefreshCategories);
        tvRefreshCategories.setPaintFlags(tvRefreshCategories.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvCantFindCategories = findViewById(R.id.tvCantFindCategories);
        tvTryAgainCategories = findViewById(R.id.tvTryAgainCategories);

        fabAddCategories = findViewById(R.id.fabAddCategories);
    }

    private void initRecyclerView() {
        rcvCategoriesManagement.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rcvCategoriesManagement.setLayoutManager(layoutManager);
    }

    private void loadRecycleView() {

        ServiceAPI.serviceApi.GetAllCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListCategory>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(CategoriesManagementActivity.this, "please wait");

                    }

                    @Override
                    public void onNext(ResGetListCategory resGetListCategory) {
                        if (resGetListCategory.get_Respon().getRespone_code() == 200) {
                            ls = resGetListCategory.get_CategoryRes();
                            categoriesAdapter = new CategoriesAdapter(CategoriesManagementActivity.this, ls);
                            rcvCategoriesManagement.setAdapter(categoriesAdapter);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        ProgressDialogCustom.dismissProgressDialog();

                    }
                });
    }

    private void addCategory(String name) {
        CategoryReq categoryReq = new CategoryReq(1, name);
        ServiceAPI.serviceApi.CreateCategory(categoryReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respon respon) {
                        if (respon.getRespone_code() == 200) {
                            PopupDialog.getInstance(CategoriesManagementActivity.this)
                                    .setStyle(Styles.SUCCESS)
                                    .setHeading("Well Done")
                                    .setHeading("You have successfully"+
                                            " added")
                                    .setCancelable(false)
                                    .showDialog(new OnDialogButtonClickListener() {
                                        @Override
                                        public void onDismissClicked(Dialog dialog1) {
                                            super.onDismissClicked(dialog1);
                                            loadRecycleView();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void updateCategory(CategoryReq categoryReq) {
        ServiceAPI.serviceApi.UpdateCategory(categoryReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respon respon) {
                        if (respon.getRespone_code() == 200) {
                            PopupDialog.getInstance(CategoriesManagementActivity.this)
                                    .setStyle(Styles.SUCCESS)
                                    .setHeading("Well Done")
                                    .setHeading("You have successfully"+
                                            " updated")
                                    .setCancelable(false)
                                    .showDialog(new OnDialogButtonClickListener() {
                                        @Override
                                        public void onDismissClicked(Dialog dialog1) {
                                            super.onDismissClicked(dialog1);
                                            loadRecycleView();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void deleteCategories(CategoryRes categoryRes) {
        ServiceAPI.serviceApi.DeleteCategory(categoryRes.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(">>>>>> ", "subscribe");

                    }

                    @Override
                    public void onNext(Respon respon) {
                        Log.d(">>>>>> ", respon.getRespone_code()+"");
                        if (respon.getRespone_code() == 200) {
                            PopupDialog.getInstance(CategoriesManagementActivity.this)
                                    .setStyle(Styles.SUCCESS)
                                    .setHeading("Well Done")
                                    .setHeading("You have successfully"+
                                            " deleted")
                                    .setCancelable(false)
                                    .showDialog(new OnDialogButtonClickListener() {
                                        @Override
                                        public void onDismissClicked(Dialog dialog1) {
                                            super.onDismissClicked(dialog1);
                                            loadRecycleView();
                                        }
                                    });
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d(">>>>>> ", "error");

                    }

                    @Override
                    public void onComplete() {
                        Log.d(">>>>>> ", "complete");
                        ProgressDialogCustom.dismissProgressDialog();
                    }
                });

    }

    public void showDialogDeleteCategory(CategoryRes categoryRes) {
        PopupDialog.getInstance(this)
                .setStyle(Styles.IOS)
                .setHeading("Delete")
                .setDescription("Are you sure you want to delete this category?"+
                        " You won't be able to see them again.")
                .setPositiveButtonText("Delete")
                .setPositiveButtonTextColor(R.color.red_blur)
                .setCancelable(false)
                .showDialog(new OnDialogButtonClickListener() {
                    @Override
                    public void onPositiveClicked(Dialog dialog) {
                        deleteCategories(categoryRes);
                        super.onPositiveClicked(dialog);
                    }

                    @Override
                    public void onNegativeClicked(Dialog dialog) {
                        super.onNegativeClicked(dialog);
                    }
                });
    }

    public void showDialogCreateCategory() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_category);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogFade2;

        Button btAddCategory = dialog.findViewById(R.id.btAddCategory);
        Button btCancelDialogAddCategory = dialog.findViewById(R.id.btCancelDialogAddCategory);
        EditText edtCreateCategory = dialog.findViewById(R.id.edtCreateNameCategory);

        btCancelDialogAddCategory.setOnClickListener(this);
        btAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtCreateCategory.getText().toString();
                if (name.length() != 0) {
                    addCategory(name);
                    dialog.dismiss();
                }
            }

        });

        Window window = dialog.getWindow();
        if (window == null)
            return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    public void showDialogUpdateCategory(CategoryRes categoryRes) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update_category);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogFade2;

        Button btUpdateCategory = dialog.findViewById(R.id.btUpdateCategory);
        Button btCancelDialogUpdateCategory = dialog.findViewById(R.id.btCancelDialogUpdateCategory);
        EditText edtUpdateCategory = dialog.findViewById(R.id.edtUpdateNameCategory);

        edtUpdateCategory.setText(categoryRes.getName());



        btCancelDialogUpdateCategory.setOnClickListener(this);
        btUpdateCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtUpdateCategory.getText().toString();
                if (name.length() != 0) {
                    CategoryReq categoryReq = new CategoryReq(categoryRes.getId(), name);
                    updateCategory(categoryReq);
                    dialog.dismiss();
                }
            }

        });

        Window window = dialog.getWindow();
        if (window == null)
            return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    private void searchCategories(String find) {
        List<CategoryRes> lsSearch = new ArrayList<>();
        for (CategoryRes categoryRes : ls) {
            if (categoryRes.getName().toLowerCase().contains(find.toLowerCase())) {
                lsSearch.add(categoryRes);
            }
        }
        categoriesAdapter = new CategoriesAdapter(this, lsSearch);
        rcvCategoriesManagement.setAdapter(categoriesAdapter);

        if (lsSearch.size() == 0) {
            tvTryAgainCategories.setVisibility(View.VISIBLE);
            tvCantFindCategories.setVisibility(View.VISIBLE);
        } else {
            tvTryAgainCategories.setVisibility(View.INVISIBLE);
            tvCantFindCategories.setVisibility(View.INVISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cvBackToHomeFromCategories:
                startActivity(new Intent(CategoriesManagementActivity.this, MainActivity.class));
                break;
            case R.id.tvRefreshCategories:
                loadRecycleView();
                edtSearchCategories.setText("");
                break;
            case R.id.btCancelDialogAddCategory:
                dialog.dismiss();
                break;
            case R.id.btCancelDialogUpdateCategory:
                dialog.dismiss();
                break;

        }
    }

}