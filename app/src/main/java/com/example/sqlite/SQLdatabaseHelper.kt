package com.example.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLdatabaseHelper(context: Context) : SQLiteOpenHelper(context, "ContactsDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE contacts (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS contacts")
        onCreate(db)
    }

    fun addContact(name: String, phone: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("phone", phone)

        val result = db.insert("contacts", null, values)
        return result != -1L
    }

    fun updateContact(name: String, phone: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("phone", phone)

        val result = db.update("contacts", values, "name=?", arrayOf(name))
        return result > 0
    }

    fun deleteContact(name: String): Boolean {
        val db = this.writableDatabase
        val result = db.delete("contacts", "name=?", arrayOf(name))
        return result > 0
    }

    fun getAllContacts(): String {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM contacts", null)
        val stringBuilder = StringBuilder()

        while (cursor.moveToNext()) {
            stringBuilder.append("ID: ${cursor.getInt(0)}\nName: ${cursor.getString(1)}\nPhone: ${cursor.getString(2)}\n")
        }

        cursor.close()
        return stringBuilder.toString()
    }
}