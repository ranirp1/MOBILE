package cn.shef.msc5.todo.base.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.model.SortOrder
import cn.shef.msc5.todo.model.SortType
import cn.shef.msc5.todo.ui.theme.PurpleGrey40
import cn.shef.msc5.todo.ui.theme.Teal
import cn.shef.msc5.todo.ui.theme.TealGrey
import cn.shef.msc5.todo.ui.theme.TealGrey40
import cn.shef.msc5.todo.utilities.Constants.Companion.SORT_DUE
import cn.shef.msc5.todo.utilities.Constants.Companion.SORT_LOCATION
import cn.shef.msc5.todo.utilities.Constants.Companion.SORT_PRIORITY

@Composable
fun SortingMenu(
    sortType: SortType,
    onSelect: (SortType) -> Unit
) {
    var showSortingMenu by remember { mutableStateOf(false) }
    var sortTpyeSelected : SortType by remember { mutableStateOf(sortType) }

    // default sort by priority in ascending order (sortOrder == true)
    var sortOrder by remember { mutableStateOf(true) }
    var sortString by remember { mutableStateOf(SORT_PRIORITY) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            TextButton(
                onClick = { showSortingMenu = true },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.FilterAlt,
                        tint = Teal,
                        contentDescription = "Filter icon"
                    )
                    Text(
                        text = " $sortString"
                    )
                }
            )

            DropdownMenu(
                modifier = Modifier.padding(horizontal = 5.dp),
                expanded = showSortingMenu,
                onDismissRequest = { showSortingMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = SORT_PRIORITY, color = TealGrey40) },
                    onClick = {
                        showSortingMenu = false
                        sortString = SORT_PRIORITY
                        sortTpyeSelected = SortType.Priority(sortTpyeSelected.sortOrder)
                        onSelect(sortTpyeSelected)
                    }
                )

                DropdownMenuItem(
                    text = { Text(text = SORT_DUE, color = PurpleGrey40) },
                    onClick = {
                        showSortingMenu = false
                        sortString = SORT_DUE
                        sortTpyeSelected = SortType.DueDate(sortTpyeSelected.sortOrder)
                        onSelect(sortTpyeSelected)
                    }
                )

                DropdownMenuItem(
                    text = { Text(text = SORT_LOCATION, color = PurpleGrey40) },
                    onClick = {
                        showSortingMenu = false
                        sortString = SORT_LOCATION
                        sortTpyeSelected = SortType.Location(sortTpyeSelected.sortOrder)
                        onSelect(sortTpyeSelected)
                    }
                )
            }
        }

        Divider(
            color = TealGrey,
            modifier = Modifier
                .height(25.dp)
                .width(1.dp)
        )

        IconButton(onClick = {
            sortOrder = !sortOrder
            if(sortOrder){
                sortTpyeSelected = sortTpyeSelected.reorder(SortOrder.Ascending)
                onSelect(sortTpyeSelected)
            }else{
                sortTpyeSelected = sortTpyeSelected.reorder(SortOrder.Descending)
                onSelect(sortTpyeSelected)
            }
        }) {
            if (sortOrder) {
                Icon(
                    imageVector = Icons.Default.ArrowDownward,
                    contentDescription = "Sort by ascending order",
                    tint = Teal,
                )
            } else {
                Icon(
                    imageVector = Icons.Default.ArrowUpward,
                    contentDescription = "Sort by descending order",
                    tint = Teal
                )
            }
        }
    }
}