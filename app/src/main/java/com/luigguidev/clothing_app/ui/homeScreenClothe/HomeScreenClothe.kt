package com.luigguidev.clothing_app.ui.homeScreenClothe

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.luigguidev.clothing_app.R
import com.luigguidev.clothing_app.config.theme.ClothingappTheme

@Composable
fun HomeScreenClothe() {

    Scaffold(
        topBar = {
            AppBar()
        }
    ) {
        BodySection(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 12.dp)
                .fillMaxSize()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
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
                onClick = { /*TODO*/ },
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
    modifier: Modifier
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
            modifier = Modifier
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListClothesSection(
    modifier: Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        content = {
            items(10) {
                Card(
                    modifier = Modifier
                        .size(220.dp),
                    onClick = { /*TODO*/ },
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
                                .weight(1f),
                        ) {
                            Image(
                                modifier = Modifier
                                    .clip(
                                        RoundedCornerShape(
                                            bottomStart = 12.dp,
                                            bottomEnd = 12.dp
                                        )
                                    ),
                                painter = painterResource(id = R.drawable.polera3),
                                contentScale = ContentScale.Crop,
                                contentDescription = null
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
                                Icon(
                                    modifier = Modifier
                                        .padding(8.dp),
                                    imageVector = Icons.Outlined.ShoppingCart,
                                    contentDescription = null
                                )
                            }
                        }
                        Text(
                            modifier = Modifier
                                .padding(vertical = 4.dp),
                            text = "Bomber Jackets",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Text(
                            text = "$/ 49.90",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }


                }
            }
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
        items(10) {
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    ClothingappTheme {
        HomeScreenClothe()
    }
}
