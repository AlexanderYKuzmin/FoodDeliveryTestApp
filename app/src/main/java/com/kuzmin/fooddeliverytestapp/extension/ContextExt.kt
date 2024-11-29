package com.kuzmin.fooddeliverytestapp.extension

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.TypedValue
import android.widget.Toast
import androidx.core.content.ContextCompat

fun Context.hasPermission(permissionType: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permissionType) ==
            PackageManager.PERMISSION_GRANTED
}

fun Context.hasRequiredRuntimePermissions() : Boolean {
    return hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) &&
            hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION) &&
            hasPermission(Manifest.permission.INTERNET) &&
            hasPermission(Manifest.permission.ACCESS_NETWORK_STATE)
}

fun Context.dpToPx(dp: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        this.resources.displayMetrics
    )
}

fun Context.showToast(text: Int) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

fun Context.showToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()