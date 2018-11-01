package com.example.mely.androidroomkotlin

import android.os.AsyncTask
import android.util.Log
import org.jetbrains.annotations.NotNull

object DatabaseInitializer {


    private val TAG: String = DatabaseInitializer::class.java.name

    fun populateAsync(db: AppDatabase) {
        val task =  PopulateDbAsync (db)
        task.execute()
    }

    private fun addUser(db: AppDatabase, user: User): User {
        db.userDAO().insertAll(user)
        return user
    }

    private fun populateWithTestData(db: AppDatabase) {
        val user = User()
        user.firstName = "Menglong"
        user.lastName = "Ly"
        user.age = 24
        addUser(db, user)

        val userList = db.userDAO().getAll()

        Log.d(DatabaseInitializer.TAG, "Rows Count: " + userList.size)
    }

    private class PopulateDbAsync internal constructor(private val mDb: AppDatabase) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg p0: Void?): Void? {
            populateWithTestData(mDb)
            return null
        }

    }

}

