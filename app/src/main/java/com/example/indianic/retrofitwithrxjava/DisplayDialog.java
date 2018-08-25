package com.example.indianic.retrofitwithrxjava;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

public class DisplayDialog {

    private static final DisplayDialog ourInstance = new DisplayDialog();
    /**
     * A Progress Dialog object
     */
    private Dialog mProgressDialog;


    private DisplayDialog() {
    }

    public static DisplayDialog getInstance() {
        return ourInstance;
    }

    /**
     * Uses for display dialog
     *
     * @param mContext Context
     * @return Dialog
     */
    Dialog showProgressDialog(final Context mContext) {
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
    void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
