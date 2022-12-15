package com.avijekrl.proald.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import org.koin.dsl.module
import androidx.datastore.preferences.core.Preferences

var databaseModule = module {

    fun provideDatastore(context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    single {
        provideDatastore(get())
    }
}

val Context.dataStore by preferencesDataStore(name = "settings")
