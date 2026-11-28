package com.example.pr01.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

class LocationPermissionState(
    val hasPermission: Boolean,
    private val launcher: ActivityResultLauncher<Array<String>>,
    private val permissions: Array<String>
) {
    fun launchPermissionRequest() {
        if (!hasPermission) launcher.launch(permissions)
    }
}

@Composable
fun rememberLocationPermissionHelper(
    onGranted: () -> Unit,
    onDenied: () -> Unit = {}
): LocationPermissionState {
    val context = LocalContext.current
    val permissions = remember {
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    var hasPermission by remember(context) {
        mutableStateOf(
            permissions.any {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }
        )
    }

    val currentOnGranted by rememberUpdatedState(onGranted)
    val currentOnDenied by rememberUpdatedState(onDenied)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        val granted = result.values.any { it }
        hasPermission = granted
        if (granted) currentOnGranted() else currentOnDenied()
    }

    return remember(hasPermission, launcher) {
        LocationPermissionState(hasPermission, launcher, permissions)
    }
}

@SuppressLint("MissingPermission")
fun getCurrentDeviceLocation(
    context: Context,
    onSuccess: (Location) -> Unit,
    onError: (String) -> Unit = {}
) {
    val client = LocationServices.getFusedLocationProviderClient(context)
    client.lastLocation.addOnSuccessListener { last ->
        if (last != null) {
            onSuccess(last)
        } else {
            client.getCurrentLocation(
                Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                CancellationTokenSource().token
            ).addOnSuccessListener { current ->
                if (current != null) onSuccess(current)
                else onError("Координаты недоступны")
            }.addOnFailureListener {
                onError("Ошибка GPS")
            }
        }
    }
}
