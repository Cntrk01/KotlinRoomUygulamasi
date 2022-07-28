package com.example.kotlinroomdatabaseapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinroomdatabaseapp.R
import com.example.kotlinroomdatabaseapp.data.User
import com.example.kotlinroomdatabaseapp.fragments.ListFragmentDirections
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList= emptyList<User>()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=userList.get(position)
        holder.itemView.customId.text=currentItem.id.toString()
        holder.itemView.userName.text=currentItem.firstName
        holder.itemView.userLastName.text=currentItem.lastName
        holder.itemView.rowAge.text=currentItem.age.toString()
        holder.itemView.rowLayout.setOnClickListener {
            val action=ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun setData(user:List<User>){
        this.userList=user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}