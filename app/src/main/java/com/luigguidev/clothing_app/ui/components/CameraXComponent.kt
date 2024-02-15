package com.luigguidev.clothing_app.ui.components

import android.Manifest
import android.content.Context
import android.view.ViewGroup
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.luigguidev.clothing_app.R
import com.luigguidev.clothing_app.config.navigation.ViewModelNavControllerState
import com.luigguidev.clothing_app.config.routes.AppRoutes
import com.luigguidev.clothing_app.viewmodel.ClotheScreenViewModel
import java.io.File
import java.util.concurrent.Executor


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraXComponent(
//    clotheScreenViewModel: ClotheScreenViewModel,
//    navController: NavHostController
    viewModelNavControllerState: ViewModelNavControllerState
) {
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }
    val context = LocalContext.current
    val cameraController = remember {
        LifecycleCameraController(context)
    }
    val lifecycle = LocalLifecycleOwner.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {


                val executor = ContextCompat.getMainExecutor(context)
                takePicture(
                    cameraController = cameraController,
                    executor = executor,
                    viewModelNavControllerState.clotheScreenViewModel
                )


//                viewModelNavControllerState.navController.navigate(AppRoutes.AddScreenClothe.route) {
//                    popUpTo(AppRoutes.CameraX.route) {
//                        inclusive = false
//                    }
//
//                }

            }
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = null,
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        if (permissionState.status.isGranted) {
            CameraView(
                modifier = Modifier.padding(it),
                context,
                cameraController,
                lifecycle
            )
        } else {
            Text(text = "Permiso devocado")
        }
    }


}

fun takePicture(
    cameraController: LifecycleCameraController,
    executor: Executor,
    clotheScreenViewModel: ClotheScreenViewModel,
) {
    val file = File.createTempFile("image", ".jpg")
    val outputDirectory = ImageCapture.OutputFileOptions.Builder(file).build()


    cameraController.takePicture(
        outputDirectory,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val image = outputFileResults.savedUri
                clotheScreenViewModel.setImage(image.toString())

            }

            override fun onError(exception: ImageCaptureException) {
            }

        }
    )

}

@Composable
private fun CameraView(
    modifier: Modifier,
    context: Context,
    cameraController: LifecycleCameraController,
    lifecycle: LifecycleOwner

) {

    cameraController.bindToLifecycle(lifecycle)
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val previewView = PreviewView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
            }
            previewView.controller = cameraController
            previewView
        },
    )
}
