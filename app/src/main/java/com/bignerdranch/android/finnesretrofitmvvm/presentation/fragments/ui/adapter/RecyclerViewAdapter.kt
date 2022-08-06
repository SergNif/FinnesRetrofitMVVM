package com.bignerdranch.android.finnesretrofitmvvm.presentation.fragments.ui.adapter

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.finnesretrofitmvvm.R
import com.bignerdranch.android.finnesretrofitmvvm.domain.models.menu.MenuDay

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.RecycleViewHolder>() {

    val TAG = "RecyclerViewAdapter Part2 "
    var menuList = mutableListOf<MenuDay>()
    private lateinit var itemListener: onItemClickListenerRecyclViewAdapter

    interface onItemClickListenerRecyclViewAdapter {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListenerRecyclViewAdapter) {
        itemListener = listener
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecycleViewHolder {
//        binding = FragmentMenuDayPart2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_list, parent, false)
        Log.e(ContentValues.TAG, "onCreateViewHolder")
        return RecycleViewHolder(itemView, itemListener)
    }

    override fun getItemCount(): Int {
        Log.e(ContentValues.TAG, "getItemCount ${menuList.toString()}")
        return menuList.size

    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        Log.e(ContentValues.TAG, "onBindViewHolder")

        holder.bind(menuList[position])

//        holder.string1.text = menuList[position].user.toString()
//        holder.string2.text = menuList[position].data.toString()
//        holder.string3.text = menuList[position].menu.toString()
    }

    class RecycleViewHolder(itemView: View, listener: onItemClickListenerRecyclViewAdapter) : RecyclerView.ViewHolder(itemView) {

        val string1: TextView = itemView.findViewById(R.id.textViewRecyclrString1Mmenu)
        val string2: TextView = itemView.findViewById(R.id.textViewRecyclrString2Mmenu)
        val string3: TextView = itemView.findViewById(R.id.textViewRecyclrString3Mmenu)
        val string4: TextView = itemView.findViewById(R.id.weight)
        val string5: TextView = itemView.findViewById(R.id.id_tab)
init {
    itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
}
        fun bind(data: MenuDay) {
            Log.e(ContentValues.TAG, "bind")
            Log.e(TAG, "Get RecycleViewHolder ${data.user.toString()}")
            Log.e(TAG, "Get RecycleViewHolder ${data.data.toString()}")
            Log.e(TAG, "Get RecycleViewHolder ${data.menu.toString()}")
            string1.text = data.data.toString()
            string2.text = data.user.toString()
            string3.text = data.menu?.joinToString(separator = "/n")
            string4.text = data.weight.toString()
            string5.text = data.id_note.toString()
        }
    }

}