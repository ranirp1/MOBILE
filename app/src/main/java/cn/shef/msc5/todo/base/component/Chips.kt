package cn.shef.msc5.todo.base.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.shef.msc5.todo.model.enums.getTemplateStr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chips(
    preSelect: String,
    chipsTitle: String,
    itemsList: List<String>,
    onSelected: (String) -> Unit
) {
    var selectedItem by remember { mutableStateOf(preSelect) }

    Row(
        modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = chipsTitle)

        LazyRow(
            verticalAlignment = Alignment.CenterVertically
        ) {
            item{  }
            items(itemsList) { item ->
                FilterChip(
                    modifier = Modifier.padding(horizontal = 6.dp),
                    selected = (item == selectedItem),
                    onClick = {
                        selectedItem = item
                        onSelected(selectedItem)
                    },
                    label = {
                        Text(text = item)
                    }
                )
            }
        }
    }

}

@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewChips() {
    Chips("", "Templates: ", getTemplateStr()) {
    }
}
