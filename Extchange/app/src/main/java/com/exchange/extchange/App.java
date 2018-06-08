package com.exchange.extchange;


import android.app.Application;

import com.exchange.extchange.network.NBPApiClient;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        NBPApiClient.init(getString(R.string.url));
    }
}
