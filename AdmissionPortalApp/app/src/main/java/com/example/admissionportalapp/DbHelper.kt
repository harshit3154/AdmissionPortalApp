package com.example.admissionportalapp
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "CSE226", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        // Create table query
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS record (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                fname TEXT NOT NULL,
                mname TEXT NOT NULL,
                course TEXT,
                isadmin BOOLEAN,
                address TEXT NOT NULL,
                image BLOB NOT NULL
            )
        """.trimIndent()

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drop the old table if it exists
        db?.execSQL("DROP TABLE IF EXISTS record")
        onCreate(db)
    }

    fun deleteTable(db:SQLiteDatabase) {
        try {
            // SQL statement to drop the table
            val dropTableQuery = "DROP TABLE IF EXISTS record"
            // Execute the drop table query
                db.execSQL(dropTableQuery)
            val createTableQuery = """
            CREATE TABLE IF NOT EXISTS record (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                fname TEXT NOT NULL,
                mname TEXT NOT NULL,
                course TEXT,
                isadmin BOOLEAN,
                address TEXT NOT NULL,
                image BLOB NOT NULL,
                email TEXT NOT NULL,
                password TEXT NOT NULL
            )
        """.trimIndent()

            db?.execSQL(createTableQuery)


        } catch (e: Exception) {
            println("Error deleting table: ${e.message}")
        }
    }


    // Method to add details to the database
    fun addDetails(name: String, fname: String, mname: String, address: String, course: String,bitmap:Bitmap,email:String,password:String) {
        val values = ContentValues().apply {
            put("name", name)
            put("fname", fname)
            put("mname", mname)
            put("course", course)
            put("isadmin", false)
            put("address", address)
            put("Image",getBitmapAsByteArray(bitmap))
            put("email",email)
            put("password",password)
        }

        val db = this.writableDatabase
        db.insert("record", null, values)
        db.close()
    }

    // Method to read data from the database
    fun readData(str:String): MutableList<RecycleData> {
        val list = mutableListOf<RecycleData>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(str, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val fname = cursor.getString(cursor.getColumnIndexOrThrow("fname"))
            val mname = cursor.getString(cursor.getColumnIndexOrThrow("mname"))
            val course = cursor.getString(cursor.getColumnIndexOrThrow("course"))
            val imageBlob = cursor.getBlob(cursor.getColumnIndexOrThrow("image"))
            val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            val address = cursor.getString(cursor.getColumnIndexOrThrow("address"))

            // Add data to the list
            list.add(RecycleData(name, fname, mname, byteArrayToBitmap(imageBlob),course,address,email))
        }

        cursor.close()
        db.close()
        return list
    }

    fun readData(query: String, username: String, userpassword: String): MutableList<RecycleData> {
        val list = mutableListOf<RecycleData>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val fname = cursor.getString(cursor.getColumnIndexOrThrow("fname"))
            val mname = cursor.getString(cursor.getColumnIndexOrThrow("mname"))
            val course = cursor.getString(cursor.getColumnIndexOrThrow("course"))
            val imageBlob = cursor.getBlob(cursor.getColumnIndexOrThrow("image"))
            val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
            val address = cursor.getString(cursor.getColumnIndexOrThrow("address"))
            val password = cursor.getString(cursor.getColumnIndexOrThrow("password"))

            // Check if credentials match
            if (name == username && userpassword == password) {
                list.add(RecycleData(name, fname, mname, byteArrayToBitmap(imageBlob), course, address, email))
            }
        }

        cursor.close()
        db.close()
        return list
    }



    fun isUserValid(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM record WHERE name = ? AND password = ?"
        val cursor = db.rawQuery(query, arrayOf(username, password))

        // Check if there is any record that matches the credentials
        val isValid = cursor.count > 0
        cursor.close()
        db.close()
        return isValid
    }
    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }


    fun getBitmapAsByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }
}
