package com.hackerlopers.gamesretrofit.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hackerlopers.gamesretrofit.ui.theme.CustomBlack
import com.hackerlopers.gamesretrofit.viewModel.GamesViewModel

@Composable
fun SearchGameView(viewModel: GamesViewModel, navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = CustomBlack
    ) { innerPadding ->
        contentSearchGameView(viewModel, Modifier.padding(innerPadding), navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun contentSearchGameView(
    viewModel: GamesViewModel,
    modifier: Modifier,
    navController: NavController
) {
//    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val games by viewModel.games.collectAsState()
    val searchFieldValue by viewModel.searchFlow.collectAsState()
    SearchBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        query = searchFieldValue,
        onQueryChange = { viewModel.setSearchValue(it) },
        onSearch = { active = false },
        active = active,
        onActiveChange = { active = it },
        colors = SearchBarDefaults.colors(
            containerColor = Color.White,
            inputFieldColors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black
            )
        ),
        placeholder = { Text(text = "Search...") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "")
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "",
                modifier = Modifier.clickable { navController.popBackStack() })
        }
    ) {
        games.forEach { item ->
            TextButton(
                modifier = Modifier.padding(bottom = 10.dp, start = 10.dp),
                onClick = {
                    navController.navigate("DetailView/${item.id}")
                },
            ) {
                Text(
                    text = item.name,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
