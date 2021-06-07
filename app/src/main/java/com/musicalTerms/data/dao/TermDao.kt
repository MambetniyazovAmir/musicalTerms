package com.musicalTerms.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.musicalTerms.data.Term

@Dao
interface TermDao {
    @Query("SELECT*FROM term")
    fun getAllTerms() : List<Term>

    @Query("SELECT * FROM term WHERE word like :word")
    fun searchByName(word: String): List<Term>

    @Query("SELECT *FROM term WHERE id = :id")
    fun getWordByID(id: Int) : Term

    @Update
    fun updateWord(term : Term)

    @Query("SELECT * FROM term WHERE favourite = 1")
    fun getFavourite() : List<Term>
}