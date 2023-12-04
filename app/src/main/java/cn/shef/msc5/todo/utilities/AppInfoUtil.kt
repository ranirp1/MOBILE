package cn.shef.msc5.todo.utilities

import android.content.Context
import android.os.Build
import cn.shef.msc5.todo.model.viewmodel.MainViewModel
import java.time.LocalDate

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 29/11/2023 13:55
 */
class AppInfoUtil {
    companion object {
        fun getAppVersionCode(context : Context): Int? {
            var appVersionCode = 0
            val packageInfo = context.getApplicationContext()
                .getPackageManager()
                .getPackageInfo(context.getPackageName(), 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                appVersionCode = packageInfo.getLongVersionCode().toInt();
            } else {
                appVersionCode = packageInfo.versionCode;
            }
            return appVersionCode;
        }
        fun getAppVersionName(context : Context): String? {
            var appVersionName = ""
            val packageInfo = context.getApplicationContext()
                .getPackageManager()
                .getPackageInfo(context.getPackageName(), 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                appVersionName = packageInfo.versionName
            } else {
                appVersionName = packageInfo.versionName;
            }
            return appVersionName;
        }
        fun test(mainViewModel: MainViewModel) {
            mainViewModel.addTask(
                "title",
                "description",
                2,
                1.11F,
                1.11F,
                "imageUrl",
                java.sql.Date.valueOf(LocalDate.now().toString()),
                java.sql.Date.valueOf(
                    LocalDate.now().toString()
                ),
                java.sql.Date.valueOf(LocalDate.now().toString()),
                0,
                "remark",
                null
            )
        }
    }
}