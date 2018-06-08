package com.exchange.extchange.ui.currencyList;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.exchange.extchange.R;
import com.exchange.extchange.network.CoursesTableResponse;
import com.exchange.extchange.network.NBPApiClient;
import com.exchange.extchange.ui.CalculateActivity;

import java.io.Serializable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {


    private final CompositeDisposable compositeDisposable = new CompositeDisposable();


    private ProgressBar preloader;

    private RatingsAdapter ratingsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindUi();
        loadData();
    }

    private void bindUi() {

        setContentView(R.layout.activity_main);
        preloader = findViewById(R.id.preloader);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ratingsAdapter = new RatingsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(ratingsAdapter);
        findViewById(R.id.fab_calculate).setOnClickListener((view) -> {

            Intent intent = new Intent(this, CalculateActivity.class);
            intent.putExtra("ITEMS", (Serializable) ratingsAdapter.getItems());
            startActivity(intent);
        });
    }

    private void loadData() {

        preloader.setVisibility(View.VISIBLE);

        compositeDisposable.add(NBPApiClient.getInstance().getCourserForTableA()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(createObserver()));
    }

    private DisposableSingleObserver<CoursesTableResponse> createObserver() {
        return new DisposableSingleObserver<CoursesTableResponse>() {
            @Override
            public void onSuccess(CoursesTableResponse coursesTableResponse) {

                preloader.setVisibility(View.GONE);

                updateUi(coursesTableResponse);
            }

            @Override
            public void onError(Throwable e) {

                preloader.setVisibility(View.GONE);

                Toast.makeText(getBaseContext(), "Nie udało się pobrać danych: " + e, Toast.LENGTH_LONG).show();
            }
        };
    }

    private void updateUi(CoursesTableResponse coursesTableResponse) {
        ratingsAdapter.updateItems(coursesTableResponse.getRates());
    }

    @Override
    protected void onPause() {
        compositeDisposable.clear();
        super.onPause();
    }
}
