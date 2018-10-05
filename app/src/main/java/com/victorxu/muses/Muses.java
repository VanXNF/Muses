package com.victorxu.muses;

import android.app.Application;

import me.yokeyword.fragmentation.Fragmentation;

public class Muses extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /*
         *  Register Fragmentation
         */
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true)
                .install();
    }
}
