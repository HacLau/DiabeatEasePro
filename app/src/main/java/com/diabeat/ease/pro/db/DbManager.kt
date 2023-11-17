package com.diabeat.ease.pro.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbManager(context: Context):SQLiteOpenHelper(context,DataBaseName,null, version) {
    companion object{
        const val DataBaseName = "DiabeatEasePro.db"
        const val version = 1
        const val tableSugar= "sugar_table"
        const val createSugar:String = "create table " +
                tableSugar +
                "(" +
                "id integer primary key autoincrement," +
                "level integer," +
                "data integer," +
                "time integer," +
                "kind varchar(50)" +
                ")"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createSugar)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}