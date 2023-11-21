package com.diabeat.ease.pro.db

import com.diabeat.ease.pro.app
import com.diabeat.ease.pro.databinding.Sugar

object DbHelper {
    private val dbHelper: DbManager by lazy { DbManager(app) }

    fun insert(data: Sugar) {
        dbHelper.writableDatabase.apply {
            execSQL(
                "insert into ${DbManager.tableSugar}(level,data,time, kind,unit,showTime) values(?,?,?,?,?,?)",
                arrayOf<String>("${data.level}", "${data.data}", "${data.time}", data.kind, data.unit, data.showTime)
            )
            close()
        }
    }

    fun update(data: Sugar) {
        dbHelper.writableDatabase.apply {
            execSQL(
                "update ${DbManager.tableSugar} set level=?,data=?,time=?,kind=?,unit=?,showTime=? where id=?",
                arrayOf<String>("${data.level}", "${data.data}", "${data.time}", data.kind, data.unit, data.showTime, "${data.id}")
            )
            close()
        }
    }

    fun delete(data: Sugar) {
        dbHelper.writableDatabase.apply {
            execSQL(
                "delete from ${DbManager.tableSugar} where id=?",
                arrayOf<String>("${data.id}")
            )
            close()
        }
    }

    fun queryByTime(showTime: String): List<Sugar> {
        return mutableListOf<Sugar>().let { list ->
            dbHelper.readableDatabase.let {
                it.rawQuery("select * from ${DbManager.tableSugar} where showTime=? order by time desc", arrayOf(showTime)).apply {
                    moveToFirst()
                    while (!isAfterLast) {
                        list.add(Sugar(getInt(0), getInt(1), getFloat(2), getLong(3), getString(4), getString(5),getString(6)))
                        moveToNext()
                    }
                    if (!isClosed) {
                        close()
                    }
                }
                it.close()
            }
            list
        }
    }

    fun queryByKind(kind: String): List<Sugar> {
        return mutableListOf<Sugar>().let { list ->
            dbHelper.readableDatabase.let {
                it.rawQuery("select * from ${DbManager.tableSugar} where kind=? order by time desc", arrayOf(kind)).apply {
                    moveToFirst()
                    while (!isAfterLast) {
                        list.add(Sugar(getInt(0), getInt(1), getFloat(2), getLong(3), getString(4), getString(5),getString(6)))
                        moveToNext()
                    }
                    if (!isClosed) {
                        close()
                    }
                }
                it.close()
            }
            list

        }
    }

    fun query(): List<Sugar> {
        return mutableListOf<Sugar>().let { list ->
            dbHelper.readableDatabase.let {
                it.rawQuery("select * from ${DbManager.tableSugar} order by time desc", null).apply {
                    moveToFirst()
                    while (!isAfterLast) {
                        list.add(Sugar(getInt(0), getInt(1), getFloat(2), getLong(3), getString(4), getString(5),getString(6)))
                        moveToNext()
                    }
                    if (!isClosed) {
                        close()
                    }
                }
                it.close()
            }
            list

        }
    }
}