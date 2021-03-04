package com.example.githubusers.feature.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubusers.R
import com.example.githubusers.data.entity.UserSearchResponseItem
import com.example.githubusers.feature.detail.UserDetailActivity
import kotlinx.android.synthetic.main.item_row_user.view.*

class MainAdapter(val context: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var items = mutableListOf<UserSearchResponseItem>()
    private lateinit var mainActivity: MainActivity

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: UserSearchResponseItem, activity: MainActivity) {
            with(itemView) {
                Glide.with(context)
                    .load(data.avatarUrl)
                    .apply(RequestOptions().circleCrop())
                    .placeholder(R.drawable.ic_user)
                    .into(iv_user)

                txt_username.text = data.login
            }
            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, UserDetailActivity::class.java).apply {
                        putExtra(UserDetailActivity.USERNAME_KEY, data.login)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                )
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_row_user, viewGroup, false)
        )
    }

    fun setActivity(activity: MainActivity) {
        this.mainActivity = activity
    }

    fun setItems(data: MutableList<UserSearchResponseItem>) {
        this.items = data
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bind(items[position], activity = mainActivity)
    }
}