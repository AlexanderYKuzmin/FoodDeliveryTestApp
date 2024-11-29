package com.kuzmin.fooddeliverytestapp.extension

import android.Manifest
import android.app.Activity
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatCallback
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import com.kuzmin.fooddeliverytestapp.R

fun Activity.requestLocationPermission() {
    runOnUiThread {
        val alert = AlertDialog.Builder(this, R.style.DialogTheme)
            .setTitle(resources.getString(R.string.alert_dialog_permission_title))
            .setMessage(resources.getString(R.string.alert_dialog_permission_message))
            .setCancelable(false)
            .setPositiveButton("Ok") { _, _ ->
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    RUNTIME_PERMISSION_REQUEST_CODE
                )
            }.show()
    }
}

fun Activity.showAlert(message: String) {
    runOnUiThread {
        val alert = AlertDialog.Builder(this)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Ok") { _, _ -> }
            .show()
    }
}

fun Activity.checkNetworkConnection(connectivityManager: ConnectivityManager): Boolean {
    val activeNetwork: Network? = connectivityManager.activeNetwork
    if ( activeNetwork != null) {
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        if (networkCapabilities != null) {
            return if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                Log.d("NetworkCapability", "Network CAPABILITY_INTERNET")
                true
            } else {
                showAlert(getString(R.string.restricted_internet_connection))
                false
            }
        }
    } else {
        showAlert(getString(R.string.failed_internet_connection))
    }
    return false
}

private const val RUNTIME_PERMISSION_REQUEST_CODE = 2