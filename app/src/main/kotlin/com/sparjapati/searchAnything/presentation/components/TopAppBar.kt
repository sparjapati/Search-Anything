package com.sparjapati.searchAnything.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TopHeader(
    isClearIconVisible: Boolean,
    modifier: Modifier = Modifier,
    onClearClicked: () -> Unit,
) {
    TopAppBar(
        title = { Text("Search Anything", color = Color.White) }, backgroundColor = MaterialTheme.colors.background,
        actions = {
            if (isClearIconVisible) {
                IconButton(onClick = onClearClicked) {
                    Icon(Icons.Default.Warning, "Clear Database")
                }
            }
        },
        modifier = modifier.fillMaxWidth()
    )
}