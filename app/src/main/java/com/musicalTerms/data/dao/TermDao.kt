package com.musicalTerms.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.musicalTerms.data.Term

@Dao
interface TermDao {
    @Query("SELECT*FROM term")
    fun getAllTerms() : List<Term>
}