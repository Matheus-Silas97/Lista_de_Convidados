package com.matheussilas.listadeconvidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matheussilas.listadeconvidados.R
import com.matheussilas.listadeconvidados.service.model.GuestModel
import com.matheussilas.listadeconvidados.view.listener.GuestListener
import com.matheussilas.listadeconvidados.view.viewholder.GuestViewHolder

class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    private var mGuestList: List<GuestModel> = arrayListOf()
    private lateinit var mListener: GuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent, false)
        return GuestViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(mGuestList[position])
    }

    override fun getItemCount(): Int {
        return mGuestList.count()
    }

    fun updateGuests(list: List<GuestModel>) {
        mGuestList = list
       notifyDataSetChanged()
    }

    fun attachListener(listener: GuestListener){
        mListener = listener
    }

}