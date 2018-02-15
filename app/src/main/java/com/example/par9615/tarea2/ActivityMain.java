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
        if(selectedSizeButton != null)
            outState.putInt(SIZE, selectedSizeButton.getId());
    }

    public void restore(Bundle savedInstanceState)
    {
        color.check(savedInstanceState.getInt(COLOR));
        if(savedInstanceState.getInt(SIZE, -1) != -1) {
            selectedSizeButton = findViewById(savedInstanceState.getInt(SIZE));
            selectedSizeButton.setSelected(true);
        }

    }

    public void like(View view) {
        Toast.makeText(ActivityMain.this, "+1 to Cruise Bicycle", Toast.LENGTH_SHORT).show();
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
            addToCartButton.setText("Added to Cart");

            Snackbar.make(view, "Added to Cart", Snackbar.LENGTH_INDEFINITE)
                    .setAction("UNDO", new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            addToCartButton.setSelected(false);
                            addToCartButton.setText("Add to Cart");
                        }
                    })
                    .show();

        }

        else {
            Toast.makeText(ActivityMain.this, "Item already is int Cart", Toast.LENGTH_SHORT).show();
        }
    }

}