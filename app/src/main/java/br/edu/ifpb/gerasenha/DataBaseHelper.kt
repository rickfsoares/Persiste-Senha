package br.edu.ifpb.gerasenha

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, "passwd.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table passwd(" +
                "id integer primary key autoincrement, " +
                "description text, " +
                "generated_password text, " +
                "created_date integer, " +
                "updated_date integer" +
                ")"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, before: Int, actual: Int) {
        db?.execSQL("drop table passwd")
        this.onCreate(db)
    }
}