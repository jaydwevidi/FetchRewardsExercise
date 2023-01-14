package com.example.fetchrewardsexercise.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewards.models.NameObject
import com.example.fetchrewardsexercise.R

//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation
//Add Animation



class MainRvAdapter (var dataSet : NameObject): RecyclerView.Adapter<MainRvAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemID : TextView
        val name : TextView
        val listID : TextView

        init {
            itemID = view.findViewById(R.id.itemID)
            name = view.findViewById(R.id.itemName)
            listID = view.findViewById(R.id.listID)
        }
    }

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
    }

    override fun getItemCount() = dataSet.size


}