//package cn.shef.msc5.todo.base
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import cn.shef.msc5.todo.R
//
///**
// * @author Zhecheng Zhao
// * @email zzhao84@sheffield.ac.uk
// * @date Created in 03/11/2023 06:28
// */
//@Composable
//fun EmptyScreen(
//    modifier: Modifier = Modifier
//) {
//    Column(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            modifier = Modifier.padding(8.dp),
//            text = stringResource(id = R.string.app_name),
//            style = MaterialTheme.typography.titleLarge,
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.primary
//        )
//        Text(
//            modifier = Modifier.padding(horizontal = 8.dp),
//            text = stringResource(id = R.string.app_name),
//            style = MaterialTheme.typography.bodySmall,
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.outline
//        )
//        androidx.compose.material3.Button(
//            onClick = {
//                mainActivity.startActivity()
//            },
//            colors = ButtonDefaults.buttonColors(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                contentColor = MaterialTheme.colorScheme.primary
//            ),
//            elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
//            content = {
//                Text("Dodo ")
//            }
//        )
//    }
//}
//
//@Preview
//@Composable
//fun EmptyScreen() {
//    EmptyScreen()
//}
