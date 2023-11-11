//package cn.shef.msc5.todo.activity
//
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.Edit
//import androidx.compose.material.icons.filled.FavoriteBorder
//import androidx.compose.material3.Card
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.SnackbarDuration
//import androidx.compose.material3.SnackbarHostState
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import cn.shef.msc5.todo.base.BaseScaffold
//import cn.shef.msc5.todo.base.BaseActivity
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//
///**
// * @author Zhecheng Zhao
// * @email zzhao84@sheffield.ac.uk
// * @date Created in 02/11/2023 06:45
// */
//class DetailActivity : BaseActivity() {
//
//    val TAG = "DetailActivity"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MaterialTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    DetailScreen(this)
//                }
//            }
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        //TODO request permissions
//        Log.d(TAG, "onStart: ")
//    }
//
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DetailScreen(detailActivity: DetailActivity) {
//    //get context
//    val context = LocalContext.current
//    val scope = rememberCoroutineScope()
//    val snackbarHostState = remember { SnackbarHostState() }
//    BaseScaffold(
//        topBar = {
//            TopAppBar(
//                scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
//                title = {
//                    Text(text = "ToDos")
//                },
//                navigationIcon = {
//                    IconButton(onClick = {
////                        detailActivity.finish()
//                        scope.launch {
//                            Log.d("onClick", "onClick1")
//                            snackbarHostState.showSnackbar("123",
//                                duration = SnackbarDuration.Short)
//                            Log.d("onClick", "onClick3")
//                        }
//                    }) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "Go back"
//                        )
//                    }
//                },
//                actions = {
//                    IconButton(onClick = {
//
//                    }) {
//                        Icon(
//                            imageVector = Icons.Default.FavoriteBorder,
//                            contentDescription = "Mark as favorite"
//                        )
//                    }
//                    IconButton(onClick = {
//
//                    }) {
//                        Icon(
//                            imageVector = Icons.Default.Edit,
//                            contentDescription = "Edit notes"
//                        )
//                    }
//                }
//            )
//        },
//        content = {
//            Content()
//        }
//    )
//
//
//}
//
//@Composable
//fun Content() {
//    Box(
//        modifier = Modifier
//            .background(MaterialTheme.colorScheme.error)
//            .fillMaxSize()
//            .padding(horizontal = 15.dp),
//    ) {
//        LazyColumn(
//            contentPadding = PaddingValues(vertical = 10.dp),
//            verticalArrangement = Arrangement.spacedBy(10.dp)
//        ) {
////            ContentShimmer()
//        }
//    }
//}
//
//@Composable
//fun ContentShimmer(
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier
//            .fillMaxWidth()
//            .height(100.dp),
//        shape = RoundedCornerShape(8.dp),
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(vertical = 10.dp, horizontal = 10.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Box(
//                contentAlignment = Alignment.CenterEnd,
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .width(24.dp)
//                    .background(color = Color.LightGray)
//            ) {
//                Box(
//                    Modifier
//                        .fillMaxHeight()
//                        .width(24.dp)
//                        .background(color = Color.LightGray)
//                ) {
//                }
//            }
//        }
//    }
//}