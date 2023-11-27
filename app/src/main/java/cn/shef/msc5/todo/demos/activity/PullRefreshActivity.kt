package cn.shef.msc5.todo.demos.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.shef.msc5.todo.base.BaseActivity
import cn.shef.msc5.todo.model.viewmodel.DetailViewModel

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 02/11/2023 06:45
 */
class PullRefreshActivity : BaseActivity() {

    val TAG = "DetailActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PullRefreshScreen()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        //TODO request permissions
        Log.d(TAG, "onStart: ")
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefreshScreen(
    modifier: Modifier = Modifier,
    detailViewModel : DetailViewModel = viewModel()
) {
    //get context
    val pullRefreshState = rememberPullRefreshState(
        refreshing = detailViewModel.isRefreshing,
        onRefresh = {
            detailViewModel.getItems()
        })
//    val items by detailViewModel.items.collectAsStateWithLifecycle(initialValue = emptyList())
    val items by detailViewModel.items.collectAsState(initial = emptyList())

    // Box is used to place the pull to refresh indicator on top of the content
    Box(modifier.pullRefresh(pullRefreshState)) {
        // Your list or content
        ItemsList(data = items, modifier = Modifier.fillMaxSize())
        // the pull to refresh indicator
        PullRefreshIndicator(
            refreshing = detailViewModel.isRefreshing,
            state = pullRefreshState,
            Modifier.align(Alignment.TopCenter),
            contentColor = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun ItemsList(data: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(data) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(text = it, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DetailScreen() {
//    //get context
//    val scope = rememberCoroutineScope()
//    val snackbarHostState = remember { SnackbarHostState() }
//    BaseScaffold(
//        showTopBar = false,
//        floatingActionButton = {
//            BaseFloatingActionBar(
//                onClick = {
//                    scope.launch {
//                        snackbarHostState.showSnackbar("Snackbar")
//                    }
//                }
//            )
//        },
//        hostState = snackbarHostState,
//    ) {
//
//    }
//
//}

@Composable
fun Content() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.error)
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
//            ContentShimmer()
        }
    }
}

@Composable
fun ContentShimmer(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(24.dp)
                    .background(color = Color.LightGray)
            ) {
                Box(
                    Modifier
                        .fillMaxHeight()
                        .width(24.dp)
                        .background(color = Color.LightGray)
                ) {
                }
            }
        }
    }
}