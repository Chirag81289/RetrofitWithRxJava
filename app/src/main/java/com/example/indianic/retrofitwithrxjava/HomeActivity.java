package com.example.indianic.retrofitwithrxjava;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Class uses for display user detail
 */
public class HomeActivity extends AppCompatActivity {
    private final static String TAG = HomeActivity.class.getSimpleName();
    private Dialog mProgressDialog;
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

                showProgressDialog(activity);
            }

            @Override
            public void onNext(UserModel mModel) {
                dismissProgressDialog();

                if (mModel != null) {
                    Log.e(TAG,"Title:- " + mModel.getTitle());
                }
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                Log.d(TAG, "UserDetail API===== Error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "UserDetail API===== Complete");
            }
        };
    }

    /**
     * Uses for display dialog
     * @param mContext Context
     * @return Dialog
     */
    private Dialog showProgressDialog(final Context mContext) {
        if (mContext != null) {
            if (mProgressDialog == null) {
                mProgressDialog = new Dialog(mContext);
                mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            }

            if (mProgressDialog.getWindow() != null) {
                mProgressDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                mProgressDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mProgressDialog.getWindow().setStatusBarColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            }

            mProgressDialog.setContentView(R.layout.progress_layout);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setCanceledOnTouchOutside(false);

            if (mProgressDialog != null && !mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        }
        return mProgressDialog;
    }

    /**
     * Dismiss Progress dialog if it is showing
     */
    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
