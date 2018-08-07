package com.szpejsoft.mydiet.screens.nourishments

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.szpejsoft.mydiet.R
import com.szpejsoft.mydiet.bind
import com.szpejsoft.mydiet.domain.DATE_FORMAT
import com.szpejsoft.mydiet.domain.Nourishment

class NourishmentListAdapter(val context: Context) : RecyclerView.Adapter<NourishmentListAdapter.NourishmentViewHolder>() {

    private val nourishmentList = mutableListOf<Nourishment>()
    private lateinit var navigateToDetailsCallback: (Nourishment) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NourishmentViewHolder =
            NourishmentViewHolder(LayoutInflater.from(context).inflate(R.layout.nourishment_list_item, parent, false), navigateToDetailsCallback)

    override fun getItemCount(): Int = nourishmentList.size

    override fun onBindViewHolder(holder: NourishmentViewHolder, position: Int) {
        val nourishment: Nourishment = nourishmentList[position]
        holder.fruits.text = "${nourishment.fruitsConsumed}"
        holder.vegetables.text = "${nourishment.vegetablesConsumed}"
        holder.grain.text = "${nourishment.grainConsumed}"
        holder.dairy.text = "${nourishment.dairyConsumed}"
        holder.meat.text = "${nourishment.meatConsumed}"
        holder.fat.text = "${nourishment.fatConsumed}"
        holder.date.text = nourishment.date.toString(DATE_FORMAT)
        holder.root.setOnClickListener { holder.clickCallback(nourishment) }
    }

    fun setNavigateToDetailsCallback(callback: (Nourishment) -> Unit) {
        navigateToDetailsCallback = callback
    }

    fun setNourishments(nourishments: List<Nourishment>) {
        nourishmentList.clear()
        nourishmentList.addAll(nourishments)
        notifyDataSetChanged()
    }


    inner class NourishmentViewHolder(itemView: View, internal val clickCallback: (Nourishment) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val fruits: AppCompatTextView by bind(R.id.eatenFruits)
        val vegetables: AppCompatTextView by bind(R.id.eatenVegetables)
        val grain: AppCompatTextView by bind(R.id.eatenGrain)
        val dairy: AppCompatTextView by bind(R.id.eatenDairy)
        val meat: AppCompatTextView by bind(R.id.eatenMeat)
        val fat: AppCompatTextView by bind(R.id.eatenFat)
        val date: AppCompatTextView by bind(R.id.date)
        val root: View by bind(R.id.root)
    }
}