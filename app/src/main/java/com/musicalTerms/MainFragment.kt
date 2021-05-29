package com.musicalTerms

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.musicalTerms.data.MusicalTermsDatabase
import com.musicalTerms.data.dao.TermDao
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(R.layout.main_fragment) {

    private val myAdapter = TermListAdapter()
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
        setData()
    }

    fun setData() {
        myAdapter.models = dao.getAllTerms()
    }
}