package com.musicalTerms.detail

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.toColor
import com.musicalTerms.R
import com.musicalTerms.data.MusicalTermsDatabase
import com.musicalTerms.data.Term
import com.musicalTerms.data.dao.TermDao
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val WORD_ID = "wordId"
    }

    private var wordId: Int = 0
    private lateinit var currentWord: Term
    private lateinit var dao: TermDao
    private var menuItem: MenuItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar1: Toolbar? = toolbar_detail
        setSupportActionBar(toolbar1)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        dao = MusicalTermsDatabase.getInstance(this).dao()
        wordId = intent.getIntExtra(WORD_ID, 0)
        currentWord = dao.getWordByID(wordId)
        tvDetailTerm.text = currentWord.word
        tvDetailTranslation.text = currentWord.translation
        val upArrow = resources.getDrawable(R.drawable.ic_baseline_arrow_back_24)
        upArrow.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(upArrow)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu?.findItem(R.id.item_bookmark)
        setFavouriteIcon()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.item_bookmark -> setFavourite()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavourite() {
        if (currentWord.favourite == null) currentWord.favourite = 1
        else currentWord.favourite = 1 - currentWord.favourite!!
        setFavouriteIcon()
        dao.updateWord(currentWord)
    }

    private fun setFavouriteIcon() {
        if (currentWord.favourite == 1) {
            menuItem?.setIcon(R.drawable.ic_baseline_bookmark_24)
        } else {
            menuItem?.setIcon(R.drawable.ic_baseline_bookmark_border_24)
        }
    }
}