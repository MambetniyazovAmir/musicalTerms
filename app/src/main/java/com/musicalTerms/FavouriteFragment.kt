package com.musicalTerms

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.musicalTerms.data.MusicalTermsDatabase
import com.musicalTerms.data.Term
import com.musicalTerms.data.dao.TermDao
import com.musicalTerms.detail.DetailActivity
import com.musicalTerms.detail.WordItemClickListener
import kotlinx.android.synthetic.main.favourite_fragment.*

class FavouriteFragment : Fragment(R.layout.favourite_fragment), WordItemClickListener {

    private val myAdapter = TermListAdapter(this)
    private lateinit var dao: TermDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favRecyclerView.adapter = myAdapter
        favRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        // Check the code below
        dao = MusicalTermsDatabase.getInstance(requireContext()).dao()

    }

    private fun setData(data: List<Term>) {
        myAdapter.models = data
    }

    override fun onStart() {
        setData(dao.getFavourite())
        fav_empty.isVisible = dao.getFavourite().isEmpty()
        super.onStart()
    }

    override fun wordItemClick(id: Int) {
        val myIntent = Intent(requireContext(), DetailActivity::class.java)
        myIntent.putExtra(DetailActivity.WORD_ID, id)
        startActivity(myIntent)
    }

}