package cn.shef.msc5.todo.utilities

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.util.concurrent.ConcurrentHashMap

/**
 * @author Zhecheng Zhao
 * @email zzhao84@sheffield.ac.uk
 * @date Created in 07/12/2023 19:47
 */
object DataStoreFactory {

    private const val USER_PREFERENCES = "default_user_preferences"
    private lateinit var defaultDataStore: DataStore<Preferences>
    private val dataStoreMaps = ConcurrentHashMap<String, DataStore<Preferences>>()

    private val applicationScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Default + CoroutineExceptionHandler { _, throwable ->
            Log.e("",
                "applicationScope:\n${throwable.message.toString()}", throwable
            )
        })

    fun init(appContext: Context) {
        getDefaultPreferencesDataStore(appContext)
    }

    private fun getDefaultPreferencesDataStore(appContext: Context): DataStore<Preferences> {
        if (this::defaultDataStore.isInitialized.not()) {
            defaultDataStore = createPreferencesDataStore(appContext, USER_PREFERENCES)
        }
        return defaultDataStore
    }

    fun getDefaultPreferencesDataStore() = defaultDataStore

    fun getPreferencesDataStore(appContext: Context, name: String): DataStore<Preferences> =
        dataStoreMaps.getOrPut(name) {
            createPreferencesDataStore(appContext, name)
        }

    private fun createPreferencesDataStore(
        appContext: Context,
        name: String
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(
                SharedPreferencesMigration(
                    appContext,
                    name
                )
            ),
            applicationScope,
            produceFile = { appContext.preferencesDataStoreFile(name) }
        )
    }
}
