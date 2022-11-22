package com.edward.myapplication.Customer.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSeekBar;

import com.edward.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Filtering extends BottomSheetDialogFragment {

    AppCompatSeekBar seekbar;
    TextView txtPrice, txtClear;
    ImageView ImgCancel;
    Button btnNew, btnTop, btnFeatured, btnApply;

    public Filtering() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filtering, container, false);
        seekbar = view.findViewById(R.id.seekbar);
        txtPrice = view.findViewById(R.id.price);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtPrice.setText("0$ - "+ String.valueOf(progress)+ "$");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtClear = view.findViewById(R.id.txtClear);
        ImgCancel = view.findViewById(R.id.ImgCancel);
        btnNew = view.findViewById(R.id.btnNew);
        btnTop = view.findViewById(R.id.btnTop);
        btnFeatured = view.findViewById(R.id.btnFeatured);
        btnApply = view.findViewById(R.id.btnApply);
    }
}
