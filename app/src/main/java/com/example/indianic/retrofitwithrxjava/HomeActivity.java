package com.example.indianic.retrofitwithrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Class uses for display user detail
 */
public class HomeActivity extends AppCompatActivity {
    private final static String TAG = HomeActivity.class.getSimpleName();
    private Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        //Api Call
        callGetUserDetail();
    }

    /**
     * Uses for call GetUserDetail Api
     */
    private void callGetUserDetail() {
        if (NetworkUtils.isNetworkAvailable(getApplicationContext())) {
            Observable<UserModel> observable = ServiceWrapper.getInstance()
                    .getService()
                    .getUserDetail(
                            //pass request params here
                    )
                    .compose(App.getInstance().<UserModel>applyObservableAsync());

            observable.subscribe(getUserDetail());

        } else {
            Toast.makeText(this, "Network not available", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Observer Get GetUserDetail
     *
     * @return Observer<UserModel>
     */
    private Observer<UserModel> getUserDetail() {
        return new Observer<UserModel>() {
            @Override
            public void onSubscribe(Disposable d) {

                DisplayDialog.getInstance().showProgressDialog(activity);
            }

            @Override
            public void onNext(UserModel mModel) {
                DisplayDialog.getInstance().dismissProgressDialog();

                if (mModel != null) {
                    Log.e(TAG,"Title:- " + mModel.getTitle());
                }
            }

            @Override
            public void onError(Throwable e) {
                DisplayDialog.getInstance().dismissProgressDialog();
                Log.d(TAG, "UserDetail API===== Error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "UserDetail API===== Complete");
            }
        };
    }
}
