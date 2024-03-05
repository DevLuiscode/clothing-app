package com.luigguidev.clothing_app.ui.addScreenClothe

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.luigguidev.clothing_app.R
import com.luigguidev.clothing_app.config.navigation.ViewModelNavControllerState
import com.luigguidev.clothing_app.config.routes.AppRoutes
import com.luigguidev.clothing_app.viewmodel.ClotheScreenViewModel

@Composable
fun AddScreenClothe(
//    navController: NavHostController,
//    clotheScreenViewModel: ClotheScreenViewModel
    viewModelNavControllerState: ViewModelNavControllerState,
    onClick:()->Unit,
    onClickCamera: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppBar(
                onClick = {
                    onClick()
                }
            )
        }
    ) {
        BodySection(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            viewModelNavControllerState.clotheScreenViewModel,
            onClickCamera = {
                onClickCamera()
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
                IconButton(onClick = {
              onClick()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = null,
                    //tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Agregar Prenda",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        },
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun BodySection(
    modifier: Modifier,
    clotheScreenViewModel: ClotheScreenViewModel,
    onClickCamera: () -> Unit,
) {
    val name by clotheScreenViewModel.nameClothe.collectAsState()
    val description by clotheScreenViewModel.description.collectAsState()
    val priceHigher by clotheScreenViewModel.priceHigher.collectAsState()
    val priceUnit by clotheScreenViewModel.priceUnit.collectAsState()
    val imageUri by clotheScreenViewModel.image.collectAsState()
    val btn by clotheScreenViewModel.btnAdd.collectAsState()

    val colorsTextFields = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedContainerColor = MaterialTheme.colorScheme.secondary,
        unfocusedContainerColor = MaterialTheme.colorScheme.secondary
    )

    val permissionState =
        rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }
    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                clotheScreenViewModel.setImage(uri.toString())
            }
        }
    )


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                    ) {
                        if (imageUri != "") {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(Uri.parse(imageUri))
                                    .build(),
                                contentDescription = "",
                                placeholder = rememberAsyncImagePainter(model = R.drawable.noimage),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(
                                        RoundedCornerShape(
                                            bottomStart = 12.dp,
                                            bottomEnd = 12.dp,
                                            topStart = 12.dp,
                                            topEnd = 12.dp
                                        )
                                    ),
                                contentScale = ContentScale.Crop,
                            )
                        } else {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(
                                        RoundedCornerShape(
                                            bottomStart = 12.dp,
                                            bottomEnd = 12.dp,
                                            topStart = 12.dp,
                                            topEnd = 12.dp
                                        )
                                    ),
                                painter = rememberAsyncImagePainter(model = R.drawable.noimage),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }

                    }
                    Row {
                        IconButton(
                            onClick = {
                               onClickCamera()
                            },
                        ) {
                            val imageVector = painterResource(id = R.drawable.camera)
                            Icon(painter = imageVector, contentDescription = null)
                        }
                        IconButton(onClick = {
                            galleryLauncher.launch("image/*")
                        }) {
                            val imageVector = painterResource(id = R.drawable.gallery)
                            Icon(painter = imageVector, contentDescription = null)
                        }
                    }
                }

                TextField(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    value = name,
                    onValueChange = {
                        clotheScreenViewModel.validateForm(
                            it,
                            description,
                            priceHigher,
                            priceUnit,
                            imageUri
                        )
                    },
                    label = {
                        Text(text = "Nombre prenda")
                    },
                    maxLines = 1,
                    shape = CircleShape,
                    colors = colorsTextFields,
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Create, contentDescription = null)
                    }
                )
                TextField(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    value = description,
                    onValueChange = {
                        clotheScreenViewModel.validateForm(
                            name,
                            it,
                            priceHigher,
                            priceUnit,
                            imageUri
                        )
                    },
                    label = {
                        Text(text = "Description")
                    },
                    maxLines = 1,
                    shape = CircleShape,
                    colors = colorsTextFields,
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Create, contentDescription = null)
                    }
                )
                TextField(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    value = priceUnit,
                    onValueChange = {
                        clotheScreenViewModel.validateForm(
                            name,
                            description,
                            priceHigher,
                            it,
                            imageUri
                        )
                    },
                    label = {
                        Text(text = "Precio Unidad")
                    },
                    maxLines = 1,
                    shape = CircleShape,
                    colors = colorsTextFields,
                    leadingIcon = {
                        Text(text = "S/.")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                TextField(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    value = priceHigher,
                    onValueChange = {
                        clotheScreenViewModel.validateForm(
                            name,
                            description,
                            it,
                            priceUnit,
                            imageUri
                        )
                    },
                    label = {
                        Text(text = "Precio/mayor")
                    },
                    maxLines = 1,
                    shape = CircleShape,
                    colors = colorsTextFields,
                    leadingIcon = {
                        Text(text = "S/.")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

                Button(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    onClick = {
                        clotheScreenViewModel.insertClothe()
                    },
                    enabled = btn
                ) {
                    Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
                }
            }
        }


    }
}
