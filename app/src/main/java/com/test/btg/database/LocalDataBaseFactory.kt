package com.test.btg.database

import android.content.Context
import androidx.room.Room

object LocalDataBaseFactory {

    private lateinit var dataBase: LocalDataBase

    fun provideDatabase(context: Context): LocalDataBase {
        if (::dataBase.isInitialized) return dataBase

        dataBase = Room.databaseBuilder(
            context.applicationContext,
            LocalDataBase::class.java, DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

        return dataBase
    }

    private const val DB_NAME = "projeto_faculdade"

}