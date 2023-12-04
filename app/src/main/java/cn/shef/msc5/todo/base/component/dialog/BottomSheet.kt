package cn.shef.msc5.todo.base.component.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onSelect: (Boolean) -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        modifier = Modifier.height(200.dp),
        onDismissRequest = { onSelect(false) }
    ) {
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onSelect(false)
                    }
                }
            }
        ) {
            Text(text = "Choose from gallery")
        }

        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onSelect(false)
                    }
                }
            }) {
            Text(text = "Open camera")
        }
    }
}
