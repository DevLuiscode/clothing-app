package com.luigguidev.clothing_app.ui.homeScreenClothe

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.luigguidev.clothing_app.R
import com.luigguidev.clothing_app.config.navigation.CardViewModelState
import com.luigguidev.clothing_app.config.navigation.ViewModelNavControllerState
import com.luigguidev.clothing_app.viewmodel.CardScreenViewModel
import com.luigguidev.clothing_app.viewmodel.ClotheScreenViewModel

@Composable
fun HomeScreenClothe(
//    clotheScreenViewModel: ClotheScreenViewModel,
//    navController: NavHostController
    viewModelNavControllerState: ViewModelNavControllerState,
    cardViewModelState: CardViewModelState,
    onClick: () -> Unit,
    onClickCardNavigation: () -> Unit
) {

    Scaffold(
        topBar = {
            AppBar(
                onClickCardNavigation = {
                    onClickCardNavigation()
                }
            )
        },
        floatingActionButton = {
            FloatingButton(
                onClick = {
                    onClick()
                }
            )
        }

    ) {
        BodySection(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 12.dp)
                .fillMaxSize(),
            clotheScreenViewModel = viewModelNavControllerState.clotheScreenViewModel,
            cardScreenViewModel = cardViewModelState.cardScreenViewModel

        )
    }
}

@Composable
fun FloatingButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            onClick()
        },
    ) {
        Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onClickCardNavigation: () -> Unit
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = "Bienvenido de vuelta!",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = "Luis Store",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    onClickCardNavigation()
                },
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BodySection(
    modifier: Modifier,
    clotheScreenViewModel: ClotheScreenViewModel,
    cardScreenViewModel: CardScreenViewModel
) {
    Column(
        modifier = modifier,
    ) {
        SearchSection(
            modifier = Modifier
                .fillMaxWidth()
        )
        CategorySection(
            modifier = Modifier
        )
        ListClothesSection(
            modifier = Modifier,
            clotheScreenViewModel = clotheScreenViewModel,
            cardScreenViewModel = cardScreenViewModel
        )
    }

}

@Composable
private fun ListClothesSection(
    modifier: Modifier,
    clotheScreenViewModel: ClotheScreenViewModel,
    cardScreenViewModel: CardScreenViewModel
) {


    val uiState = clotheScreenViewModel.uiState
    val painter = rememberAsyncImagePainter(model = R.drawable.noimage)

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),

        content = {
            items(
                items = uiState.clotheList,
                key = {
                    it.clotheModel.id!!
                })
            { clothe ->

                Card(
                    modifier = Modifier
                        .size(220.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(clothe.uri)
                                    .build(),
                                placeholder = painter,
                                contentDescription = "",
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
                            Card(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 4.dp),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                ),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 20.dp
                                )


                            ) {
                                IconButton(onClick = {
                                    cardScreenViewModel.addClotheToCard(clothe.clotheModel)
                                }) {
                                    Icon(
                                        modifier = Modifier
                                            .padding(8.dp),
                                        imageVector = Icons.Outlined.ShoppingCart,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                        Text(
                            modifier = Modifier
                                .padding(vertical = 4.dp),
                            text = clothe.clotheModel.name,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Text(
                            text = "S/. ${clothe.clotheModel.priceUnit}",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }


                }
            }

            //Codigo anterior

//            items(
//                clotheList.size,
//                key = { index ->
//                clotheList[index].id!!
//            })
//            { index ->
//                val clothe = clotheList[index]
//                val image = remember {
//                    Uri.parse(clothe.imagePath)
//                }
//                Card(
//                    modifier = Modifier
//                        .size(220.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = MaterialTheme.colorScheme.secondary
//                    )
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(bottom = 4.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .weight(1f)
//                                .fillMaxWidth(),
//                        ) {
//                            AsyncImage(
//                                model = ImageRequest.Builder(LocalContext.current)
//                                    .data(image)
//                                    .build(),
//                                placeholder = painter,
//                                contentDescription = "",
//                                modifier = Modifier
//                                    .fillMaxSize()
//                                    .clip(
//                                        RoundedCornerShape(
//                                            bottomStart = 12.dp,
//                                            bottomEnd = 12.dp,
//                                            topStart = 12.dp,
//                                            topEnd = 12.dp
//                                        )
//                                    ),
//                                contentScale = ContentScale.Crop,
//                            )
//                            Card(
//                                modifier = Modifier
//                                    .align(Alignment.BottomCenter)
//                                    .padding(bottom = 4.dp),
//                                shape = CircleShape,
//                                colors = CardDefaults.cardColors(
//                                    containerColor = MaterialTheme.colorScheme.primary,
//                                ),
//                                elevation = CardDefaults.cardElevation(
//                                    defaultElevation = 20.dp
//                                )
//
//
//                            ) {
//                                IconButton(onClick = {
//                                    cardScreenViewModel.addClotheToCard(clothe)
//                                }) {
//                                    Icon(
//                                        modifier = Modifier
//                                            .padding(8.dp),
//                                        imageVector = Icons.Outlined.ShoppingCart,
//                                        contentDescription = null
//                                    )
//                                }
//                            }
//                        }
//                        Text(
//                            modifier = Modifier
//                                .padding(vertical = 4.dp),
//                            text = clothe.name,
//                            fontSize = 13.sp,
//                            color = MaterialTheme.colorScheme.tertiary
//                        )
//                        Text(
//                            text = "S/. ${clothe.priceUnit}",
//                            color = MaterialTheme.colorScheme.primary,
//                            fontWeight = FontWeight.SemiBold
//                        )
//                    }
//
//
//                }
//            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategorySection(
    modifier: Modifier
) {
    LazyRow(
        modifier = modifier
    ) {
        items(5) {
            Card(
                modifier = Modifier
                    .padding(end = 12.dp),
                onClick = { /*TODO*/ },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    text = "All chompa $it",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 15.sp
                )
            }
        }
    }

}

@Composable
private fun SearchSection(
    modifier: Modifier
) {
    TextField(
        modifier = modifier,
        value = "",
        onValueChange = {},
        label = {
            Text(
                text = "Que prenda deseas buscar...",
                fontSize = 13.sp
            )
        },
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
        },
        shape = CircleShape,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.secondary,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondary
        )
    )
}

