package com.example.pr01.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pr01.R
import com.example.pr01.ViewModel.LoginViewModel
import com.example.pr01.components.ShippingAddressCard
import com.example.pr01.utils.CameraPermissionHelper
import com.example.pr01.utils.getCurrentDeviceLocation
import com.example.pr01.utils.rememberLocationPermissionHelper

@Composable
fun ProfileScreen(
    viewModel: LoginViewModel,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val cameraHelper = remember { CameraPermissionHelper(context) }

    val merriweatherBold = FontFamily(Font(R.font.merriweather_bold, FontWeight.Bold))
    val nunitoSans = FontFamily(
        Font(R.font.nunito_sans, FontWeight.Normal),
        Font(R.font.nunito_sans_bold, FontWeight.Bold)
    )

    val profile = viewModel.userProfile

    var tempPhotoUri by remember { mutableStateOf<Uri?>(null) }
    var avatarUri by remember { mutableStateOf<Uri?>(null) }
    var shippingAddress by rememberSaveable { mutableStateOf("") }

    val loadLocation = {
        getCurrentDeviceLocation(
            context = context,
            onSuccess = { location ->
                shippingAddress = "Широта: ${location.latitude}, Долгота: ${location.longitude}"
            }
        )
    }

    val locationPermission = rememberLocationPermissionHelper(
        onGranted = { loadLocation() }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { isSaved: Boolean ->
        if (isSaved && tempPhotoUri != null) {
            avatarUri = tempPhotoUri
        }
    }

    fun openCamera() {
        val uri = cameraHelper.createImageUri()
        tempPhotoUri = uri
        cameraLauncher.launch(uri)
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            openCamera()
        }
    }

    // При запуске экрана запрашиваем только геолокацию
    LaunchedEffect(Unit) {
        if (locationPermission.hasPermission) {
            loadLocation()
        } else {
            locationPermission.launchPermissionRequest()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchProfile()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )
            }

            Text(
                text = "Profile",
                fontFamily = merriweatherBold,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF303030),
                textAlign = TextAlign.Center
            )

            IconButton(
                onClick = onLogout,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = "Logout",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF0F0F0))
                    .clickable {
                        // Запрос разрешения на камеру только при нажатии на иконку
                        if (cameraHelper.hasCameraPermission()) {
                            openCamera()
                        } else {
                            cameraPermissionLauncher.launch(CameraPermissionHelper.CAMERA_PERMISSION)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                if (avatarUri != null) {
                    AsyncImage(
                        model = avatarUri,
                        contentDescription = "User photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile_placeholder),
                        contentDescription = "Default avatar",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column {
                val fullName = listOfNotNull(profile?.firstName, profile?.lastName)
                    .filter { it.isNotBlank() }
                    .joinToString(" ")
                    .ifEmpty { "User" }

                Text(
                    text = fullName,
                    fontFamily = nunitoSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF303030)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = profile?.email ?: "",
                    fontFamily = nunitoSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color(0xFF808080)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        ShippingAddressCard(
            addressText = shippingAddress,
            onClick = {
                if (locationPermission.hasPermission) {
                    loadLocation()
                } else {
                    locationPermission.launchPermissionRequest()
                }
            },
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}
