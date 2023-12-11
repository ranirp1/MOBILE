package cn.shef.msc5.todo.utilities

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import android.graphics.ImageDecoder
import android.os.Environment
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File

class ImageUtil {
    /**
     * create a image file
     */
    fun newImageFile(context: Context): File {
        val timeMillis = System.currentTimeMillis().toString()
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("SNAP-$timeMillis", ".jpg", storageDir)
    }

    /**
     * get the image bitmap
     */
    fun getImageBitmap(contentResolver: ContentResolver, uri: Uri?): ImageBitmap? {
        var result: ImageBitmap? = null
        if (uri != null) {
            result = ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(contentResolver, uri)
            ).asImageBitmap()
        }
        return result
    }
}