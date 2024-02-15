package com.luigguidev.clothing_app.ui.cardScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.luigguidev.clothing_app.R
import com.luigguidev.clothing_app.config.navigation.CardViewModelState
import com.luigguidev.clothing_app.viewmodel.CardScreenViewModel

@Composable
fun CardScreen(
    cardViewModelState: CardViewModelState,
    cardScreenViewModel: CardScreenViewModel
) {
    Scaffold(
        topBar = {
            AppBar()
        },
        containerColor = MaterialTheme.colorScheme.secondary

    ) {
        BodySection(
            modifier = Modifier
                .padding(it)
                .padding(
                    vertical = 4.dp,
                    horizontal = 12.dp
                )
                .fillMaxSize(),
            cardScreenViewModel = cardScreenViewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    TopAppBar(title = {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Card",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp

        )
    }, navigationIcon = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
        }
    })
}

@Composable
private fun BodySection(
    modifier: Modifier,
    cardScreenViewModel: CardScreenViewModel
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            ListCard(
                modifier = Modifier
                    .fillMaxWidth(),
                cardScreenViewModel = cardScreenViewModel
            )
        }
        Box(
            modifier = Modifier
        ) {
            PriceSection(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }


    }

}

@Composable
private fun PriceSection(
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(space = 4.dp)
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Sub Total",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = "$/49.99",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Descuento",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = "$/5.00",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Divider(
        )
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = "$44.99",
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Button(
            modifier = modifier,
            onClick = { /*TODO*/ },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "CheckOut")
                Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = null)
            }
        }
    }

}

@Composable
private fun ListCard(
    modifier: Modifier,
    cardScreenViewModel: CardScreenViewModel
) {
    val card by cardScreenViewModel.cardCurrent.collectAsState()
    val painter = rememberAsyncImagePainter(model = R.drawable.noimage)
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {

        items(card.size) { index ->
            val clotheList = card[index]
            Card(
                modifier = modifier
                    .padding(bottom = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp),
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(clotheList.image)
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
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 12.dp)
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = clotheList.name,
                            color = MaterialTheme.colorScheme.tertiary,
                        )
                        Text(
                            text = "$${clotheList.priceUnit}",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Outlined.Add,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }
                            Text(
                                text = "${clotheList.amount}",
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Outlined.Add,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }

                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        IconButton(onClick = {
                            cardScreenViewModel.deleteClothe(clotheList)
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = null,
                                tint = Color.Red
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Outlined.Add,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }
                            Text(
                                text = "${clotheList.amount}",
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Outlined.Add,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }

                        }
                    }
                }
            }
        }

    }
}

