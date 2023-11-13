package br.edu.ifpb.gerasenha

import android.content.ContentValues
import android.content.Context
import java.util.Calendar

class PasswordDAO {
    private val db: DataBaseHelper

    constructor(context: Context) {
        this.db = DataBaseHelper(context)
    }

    fun insert(passwd: Password) {
        val dateHour = Calendar.getInstance().timeInMillis
        val cv = ContentValues()

        cv.put("description", passwd.getDescription())
        cv.put("generated_password", passwd.getGeneratedPassword())
        cv.put("created_date", dateHour)
        cv.put("updated_date", dateHour)
        this.db.writableDatabase.insert("passwd", null, cv)
    }

    fun select(): List<Password>{
        var passwdList = ArrayList<Password>()
        val col = arrayOf("id", "description", "generated_password", "created_date", "updated_date")
        val c = this.db.readableDatabase.query("passwd", col, null, null, null,null, null)

        c.moveToFirst()
        for (i in 1..c.count){
            val id = c.getInt(0)
            val description = c.getString(1)
            val generatedPassword = c.getString(2)
            val createdDate = c.getInt(3)
            val updatedDate = c.getInt(4)
            val passwd = Password(id, description, generatedPassword, createdDate, updatedDate)
            passwdList.add(passwd)
            c.moveToNext()
        }

        return passwdList
    }

    fun find(id: Int): Password?{
        val col = arrayOf("id", "description", "generated_password", "created_date", "updated_date")
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())
        val c = this.db.readableDatabase.query("passwd", col, where, pWhere, null,null, null)

        c.moveToFirst()
        if (c.count == 1){
            val id = c.getInt(0)
            val description = c.getString(1)
            val generatedPassword = c.getString(2)
            val createdDate = c.getInt(3)
            val updatedDate = c.getInt(4)
            return Password(id, description, generatedPassword, createdDate, updatedDate)
        }
        return null
    }

    fun delete(id: Int){
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())
        this.db.writableDatabase.delete("passwd", where, pWhere)
    }

    fun update(passwd: Password){
        val where = "id = ?"
        val pWhere = arrayOf(passwd.getId().toString())
        val cv = ContentValues()
        cv.put("description", passwd.getDescription())
        cv.put("generatedPassword", passwd.getGeneratedPassword())
        cv.put("updated_date", Calendar.getInstance().timeInMillis)
        this.db.writableDatabase.update("passwd", cv, where, pWhere)
    }

}