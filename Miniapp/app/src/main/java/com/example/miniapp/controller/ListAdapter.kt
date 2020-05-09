package com.example.miniapp.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miniapp.R
import com.example.miniapp.model.Api

class ListAdapter (var mIndicators: List<Api>): RecyclerView.Adapter<ListAdapter.ListHolder>() {



    var mListener: ((String) -> Unit)? = null

    class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView =  itemView.findViewById(R.id.indicator_name) as TextView
        val codigo: TextView = itemView.findViewById(R.id.indicator_codigo) as TextView
        fun setOnClickListener(listener: View.OnClickListener) {
            itemView.setOnClickListener(listener)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent,false)
        val vh = ListHolder(v)
        return vh
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.nombre.text = mIndicators[position].nombre
        holder.codigo.text = mIndicators[position].codigo
        holder.setOnClickListener(View.OnClickListener { mListener?.invoke(mIndicators[position].codigo) })
    }

    override fun getItemCount(): Int {
        return mIndicators.size
    }
    fun setIndicators(list: List<Api>) {
        this.mIndicators = list
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: (String) -> Unit) {
        mListener = listener
    }
}