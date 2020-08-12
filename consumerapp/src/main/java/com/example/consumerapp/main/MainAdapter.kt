package com.example.consumerapp.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.consumerapp.R
import com.example.consumerapp.data.UserFavorite
import kotlinx.android.synthetic.main.item_row_favorite_user.view.*

class MainAdapter(val mContext: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var items: MutableList<UserFavorite> = mutableListOf()

    fun setItems(items: MutableList<UserFavorite>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: UserFavorite) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(data.avatar_url)
                    .circleCrop()
                    .into(iv_user)

                txt_username.text = data.username
                txt_repository.text = data.public_repos
                txt_following.text = data.following
                txt_follower.text = data.followers
                txt_company.text = data.company ?: context.getString(R.string.no_company)
                txt_location.text = data.location ?: context.getString(R.string.no_location)

                itemView.setOnClickListener {
                    Toast.makeText(context, context.getString(R.string.user_detail_information), Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_row_favorite_user, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}