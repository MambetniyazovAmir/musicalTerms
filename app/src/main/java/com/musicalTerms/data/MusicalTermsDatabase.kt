package com.musicalTerms.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.musicalTerms.data.dao.TermDao


@Database(entities = [Term::class], version = 1, exportSchema = false)
abstract class MusicalTermsDatabase : RoomDatabase() {
    companion object {
        private lateinit var INSTANCE: MusicalTermsDatabase

        fun getInstance(context: Context): MusicalTermsDatabase =
            Room.databaseBuilder(
                context,
                MusicalTermsDatabase::class.java,
                "term.db"
            )
                .createFromAsset("term.db")
                .allowMainThreadQueries()
                .build()
    }


    abstract fun dao(): TermDao
}