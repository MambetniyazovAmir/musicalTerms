package com.musicalTerms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.musicalTerms.data.Term
import kotlinx.android.synthetic.main.item_term.view.*

class TermListAdapter : RecyclerView.Adapter<TermListAdapter.TermListViewHolder>() {

    var models: List<Term> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class TermListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun populateModel(term: Term) {
            itemView.tvTerm.text = term.word
            itemView.tvTranslation.text = term.translation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermListViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_term, parent, false)
        return TermListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TermListViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int = models.size
}