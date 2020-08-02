package com.example.githubusers.feature.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusers.R
import com.example.githubusers.data.entity.UserSearchResponseItem
import kotlinx.android.synthetic.main.item_row_user.view.*

class MainAdapter(val context: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var items = mutableListOf<UserSearchResponseItem>()
    private lateinit var activity: MainActivity

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(data: UserSearchResponseItem) {
            with(itemView) {
                Glide.with(context)
                    .load(data.avatarUrl).into(iv_user)

                txt_username.text = data.login
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_user, viewGroup, false))
    }

    fun setItems(data: MutableList<UserSearchResponseItem>) {
        this.items = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}