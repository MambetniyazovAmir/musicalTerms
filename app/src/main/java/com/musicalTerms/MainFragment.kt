package com.musicalTerms

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.musicalTerms.data.MusicalTermsDatabase
import com.musicalTerms.data.Term
import com.musicalTerms.data.dao.TermDao
import com.musicalTerms.detail.DetailActivity
import com.musicalTerms.detail.WordItemClickListener
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment), WordItemClickListener  {

    private val myAdapter = TermListAdapter(this)
    private lateinit var dao: TermDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = myAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        dao = MusicalTermsDatabase.getInstance(requireContext()).dao()
        editTxt.addTextChangedListener {
            val result = dao.searchByName("%${it.toString()}%")
            setData(result)
        }
        setData(dao.getAllTerms())
    }
    private fun setData(data: List<Term>) {
        myAdapter.models = data
    }

    override fun wordItemClick(id: Int) {
        val mIntent = Intent(requireContext(),DetailActivity::class.java)
        mIntent.putExtra(DetailActivity.WORD_ID,id)
        startActivity(mIntent)
    }
}