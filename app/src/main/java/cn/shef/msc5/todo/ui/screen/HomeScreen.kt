package cn.shef.msc5.todo.ui.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.shef.msc5.todo.R
import cn.shef.msc5.todo.model.User
import cn.shef.msc5.todo.model.viewmodel.UserViewModel

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 04/11/2023 15:57
 */
@Composable
fun HomeScreen(context: Context, userViewModel: UserViewModel) {

    val data = userViewModel.repository

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.purple_200)),
    ) {

        item {
            Toolbar()
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                MyBooks(context)
            }
        }
        item {
            Text(
                context.getString(R.string.app_name),
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 8.dp),
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
//        items(data.c) { list ->
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//            ) {
////                subList.forEach { book ->
////                    BestSellerItem(book = book, context = context)
////                }
//                list
//            }
//        }
        // Avoid over-lapping with bottom navigation bar
        item {
            Spacer(modifier = Modifier.height(50.dp))
        }

    }
}

@Composable
fun MyBooks(context: Context) {

    Spacer(modifier = Modifier.height(22.dp))
    Text(
        context.getString(R.string.app_name),
        color = Color.White,
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(start = 4.dp),
    )
    Spacer(modifier = Modifier.height(12.dp))
//    MyBooksList(bookList = BooksRepository.getMyBooks(), context)
}

@Composable
fun Toolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 6.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(25.dp)
                .clickable {}
        )

        Spacer(modifier = Modifier.width(8.dp))

        var searchValue by remember { mutableStateOf("") }
        TextField(
            value = searchValue,
            onValueChange = { searchValue = it },
            modifier = Modifier
                .width(230.dp)
                .height(45.dp)
                .background(
                    color = colorResource(id = R.color.purple_200),
                    shape = CircleShape
                ),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = androidx.core.R.drawable.ic_call_answer_video),
                    null,
                    tint = Color.White
                )
            }
        )

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .width(25.dp)
                .height(25.dp)
                .clickable {}
        )

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(44.dp)
                .padding(vertical = 8.dp, horizontal = 6.dp)
                .clickable {}
        )
    }

}

//@Composable
//fun MyBooksList(userList: ArrayList<User>, context: Context) {
//    LazyRow {
//        items(userList) { user ->
//            MyBookItem(user = User, context = context)
//        }
//    }
//}

@Composable
fun MyBookItem(user: User, context: Context) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .width(140.dp)
            .padding(8.dp)
            .clickable {
//                openBookDetailsActivity(context, book)
            }
    ) {
//        Image(
//            painter = painterResource(id = book.bookImage),
//            null,
//            modifier = Modifier
//                .height(180.dp)
//                .width(140.dp),
//            contentScale = ContentScale.FillHeight
//        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            user.username,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Italic,
            maxLines = 2,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            user.username,
            color = Color.Gray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic,
            maxLines = 2,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
//            Text(
//                "${(book.bookProgress * 5).toInt()}%",
//                color = Color.White,
//                fontSize = 13.sp,
//                fontWeight = FontWeight.Light
//            )
        }

    }
}
//
//@Composable
//fun BestSellerItem(book: Book, context: Context) {
//    Column(
//        modifier = Modifier
//            .wrapContentHeight()
//            .wrapContentHeight()
//            .padding(8.dp)
//            .clickable { openBookDetailsActivity(context, book) },
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Image(
//            painter = painterResource(id = book.bookImage),
//            null,
//            modifier = Modifier
//                .height(200.dp)
//                .width(170.dp),
//            contentScale = ContentScale.Inside
//        )
//
//        Spacer(modifier = Modifier.height(4.dp))
//
//        Text(
//            book.bookName,
//            color = Color.White,
//            fontSize = 18.sp,
//            fontFamily = robotoCondenseFamily,
//            fontWeight = FontWeight.SemiBold,
//            fontStyle = FontStyle.Italic,
//            maxLines = 3,
//            textAlign = TextAlign.Center
//        )
//
//        Text(
//            book.authorName,
//            color = Color.Gray,
//            fontSize = 16.sp,
//            fontFamily = robotoCondenseFamily,
//            fontWeight = FontWeight.Normal,
//            fontStyle = FontStyle.Italic,
//            maxLines = 2,
//        )
//    }
//}
//
//fun openBookDetailsActivity(context: Context, book: Book, isBookmarked: Boolean = false) {
//    Intent(context, BookDetailActivity::class.java).run {
//        putExtra(key_book, book)
//        putExtra(key_is_bookmarked, isBookmarked)
//        context.startActivity(this)
//    }
//}
