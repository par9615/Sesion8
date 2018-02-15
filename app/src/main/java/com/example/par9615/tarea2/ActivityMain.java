package com.example.par9615.tarea2;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActivityMain extends AppCompatActivity {
    private static String COLOR = "COLOR";
    private static String SIZE = "SIZE";
    private static String CART = "CART";

    Button selectedSizeButton, addToCartButton;
    RadioGroup color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///IDs setting
        color = findViewById(R.id.activity_main_color_radio_group);
        addToCartButton = findViewById((R.id.activity_main_cart_button));
        selectedSizeButton = null;
        ///
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        save(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restore(savedInstanceState);
    }

    public void save(Bundle outState) {
        outState.putInt(COLOR, color.getCheckedRadioButtonId());
        outState.putBoolean(CART, addToCartButton.isSelected());
        if(selectedSizeButton != null)
            outState.putInt(SIZE, selectedSizeButton.getId());
    }

    public void restore(Bundle savedInstanceState)
    {
        color.check(savedInstanceState.getInt(COLOR));
        addToCartButton.setSelected(savedInstanceState.getBoolean(CART));
        if(savedInstanceState.getInt(SIZE, -1) != -1) {
            selectedSizeButton = findViewById(savedInstanceState.getInt(SIZE));
            selectedSizeButton.setSelected(true);
        }

    }

    public void like(View view) {
        Toast.makeText(ActivityMain.this, getString(R.string.activity_main_like_text), Toast.LENGTH_SHORT).show();
    }

    public void selectSize(View view) {
        if(selectedSizeButton != null)
            selectedSizeButton.setSelected(false);

        selectedSizeButton = (Button) view;
        System.out.println(view.getId());
        selectedSizeButton.setSelected(true);
    }

    public void addToCart(View view)
    {
        if(!addToCartButton.isSelected()) {

            addToCartButton.setSelected(true);
            addToCartButton.setText(getString(R.string.activity_main_cart_title_added));

            Snackbar.make(view, getString(R.string.activity_main_cart_title_added), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.activity_main_snackbar_undo), new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            addToCartButton.setSelected(false);
                            addToCartButton.setText(getString(R.string.activity_main_cart_title));
                        }
                    })
                    .show();

        }

        else {
            Toast.makeText(ActivityMain.this, getString(R.string.activity_main_cart_already), Toast.LENGTH_SHORT).show();
        }
    }

}