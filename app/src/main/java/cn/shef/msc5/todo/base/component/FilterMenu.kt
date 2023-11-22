package cn.shef.msc5.todo.base.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.ui.theme.Purple40

@Preview
@Composable
fun FilterMenu(){
    var showFilterMenu by remember{mutableStateOf(false)}
    var filterType by remember{ mutableStateOf("Priority") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Box{
            TextButton(
                onClick = { showFilterMenu = true },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.FilterAlt,
                        tint = Purple40,
                        contentDescription = "Filter icon"
                    )
                    Text(
                        text = " " + filterType
                    )
                }
            )

            DropdownMenu(
                expanded = showFilterMenu,
                onDismissRequest = { showFilterMenu = false }
            ) {
                DropdownMenuItem(onClick = {
                    showFilterMenu = false
                    filterType = "Priority"
                }) {
                    Text(text = "Priority")
                }

                DropdownMenuItem(onClick = {
                    showFilterMenu = false
                    filterType = "Date Due"
                }) {
                    Text(text = "Due Date")
                }

                DropdownMenuItem(onClick = {
                    showFilterMenu = false
                    filterType = "Date Created"
                }) {
                    Text(text = "Date Created")
                }
            }
        }

    }
}