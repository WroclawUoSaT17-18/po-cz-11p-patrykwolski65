package com.exchange.extchange.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Spinner;
import android.widget.Toast;

import com.exchange.extchange.R;
import com.exchange.extchange.network.Rate;

import java.util.List;


public class CalculateActivity extends AppCompatActivity {


    private RatingsCalculateAdapter ratingsAdapter;

    private Spinner spinner;

    private TextInputEditText inputEditText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindUi();
    }

    private void bindUi() {

        setContentView(R.layout.activity_calculate);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        spinner = findViewById(R.id.spinnerCurrencies);

        List<Rate> rates = (List<Rate>) getIntent().getSerializableExtra("ITEMS");

        RatingBaseListAdapter adapter = new RatingBaseListAdapter(rates);
        spinner.setAdapter(adapter);
        inputEditText = findViewById(R.id.edit_count);

        findViewById(R.id.btn_calculate).setOnClickListener((view) -> calculate());

        ratingsAdapter = new RatingsCalculateAdapter(rates, (Rate) spinner.getSelectedItem(), 1d);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(ratingsAdapter);
        ratingsAdapter.updateItems((Rate) spinner.getSelectedItem(), 1d);
    }

    private void calculate() {

        if (inputEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Wpisz wartość", Toast.LENGTH_SHORT).show();
        } else {

            ratingsAdapter.updateItems((Rate) spinner.getSelectedItem(), Double.parseDouble(inputEditText.getText().toString()));
        }
    }
}
