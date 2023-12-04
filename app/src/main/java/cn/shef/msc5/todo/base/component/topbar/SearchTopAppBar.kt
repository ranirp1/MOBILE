package cn.shef.msc5.todo.base.component.topbar

import androidx.appcompat.widget.SearchView
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @author Cheng Man Li
 * @email cmli1@sheffield.ac.uk
 * @date Created on 16/11/2023 23:58
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    title: String,
    showFirstIcon: Boolean = true,
    firstIcon: ImageVector = Icons.Default.Search,
) {
    // val context = LocalContext.current
    var showSearchBar by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }
    if (!showSearchBar) {
        TopAppBar(
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
            title = {
                Text(text = title)
            },
            actions = {
                if (showFirstIcon) {
                    IconButton(onClick = { showSearchBar = !showSearchBar }) {
                        Icon(
                            imageVector = firstIcon,
                            contentDescription = "search ToDos"
                        )
                    }
                }
            }
        )
    } else {
        SearchBar(
            query = inputText,
            onQueryChange = { inputText = it },
            onSearch = { showSearchBar = false; inputText = "" },
            active = showSearchBar,
            onActiveChange = { showSearchBar = it; inputText = "" },
            placeholder = { Text(text = "Search") },
            trailingIcon = {
                if (showSearchBar) {
                    IconButton(onClick = {
                        if (inputText.isNotEmpty()) {
                            inputText = ""
                        } else {
                            showSearchBar = false
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close search icon"
                        )
                    }
                }
            }
        ) {
            // TODO search history?
        }
    }
}