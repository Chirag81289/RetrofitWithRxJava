package com.example.indianic.retrofitwithrxjava;

import android.app.Application;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Application level class
 */
public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        App.instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    /**
     * Generate reusable observable source object.
     *
     * @return ObservableTransformer
     */
    public <T> ObservableTransformer<T, T> applyObservableAsync() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
