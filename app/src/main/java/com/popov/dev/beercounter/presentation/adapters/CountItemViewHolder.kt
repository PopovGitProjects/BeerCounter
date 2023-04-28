package com.popov.dev.beercounter.presentation.adapters

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.popov.dev.beercounter.R

class CountItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvName: TextView = view.findViewById(R.id.tvName)
    val tvCount: TextView = view.findViewById(R.id.tvCount)
    val btnAddBeer: Button = view.findViewById(R.id.btnAddBeer)
}