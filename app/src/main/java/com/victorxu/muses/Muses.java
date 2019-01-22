package com.victorxu.muses;

import android.app.Application;
import androidx.annotation.NonNull;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

public class Muses extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fragmentation.builder()
                .stackViewMode(Fragmentation.NONE)
                .debug(true)
                .handleException((Exception e)->{})
                .install();
    }
}
