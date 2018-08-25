package com.example.indianic.retrofitwithrxjava;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

/**
 * Purpose of this Class is to check internet connection of phone and perform actions on user's input
 */
class NetworkUtils {

    /**
     * Checks the Network availability.
     *
     * @return true if internet available, false otherwise
     */
    static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;

        return (activeNetwork != null && activeNetwork.isConnectedOrConnecting());
    }
}
