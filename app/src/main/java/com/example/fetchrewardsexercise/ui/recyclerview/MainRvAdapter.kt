package com.example.fetchrewardsexercise.ui.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewards.models.NameObject
import com.example.fetchrewardsexercise.R


class MainRvAdapter (var dataSet : NameObject): RecyclerView.Adapter<MainRvAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemID : TextView
        val name : TextView
        val listID : TextView
        val cardView : CardView

        init {
            itemID = view.findViewById(R.id.itemID)
            name = view.findViewById(R.id.itemName)
            listID = view.findViewById(R.id.listID)
            cardView = view.findViewById(R.id.cardView)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataset(newDataSet : NameObject){
        dataSet = newDataSet
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recyclerview_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemID.text = dataSet[position].id.toString()
        viewHolder.listID.text = dataSet[position].listId.toString()
        viewHolder.name.text = dataSet[position].name

        viewHolder.cardView.startAnimation(
            AnimationUtils.loadAnimation(viewHolder.itemView.context , R.anim.anim_one) ,
        )
    }

    override fun getItemCount() = dataSet.size


}