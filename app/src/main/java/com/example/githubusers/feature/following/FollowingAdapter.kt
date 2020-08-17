package com.example.githubusers.feature.following

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusers.R
import com.example.githubusers.data.entity.UserFollowingResponseItem
import com.example.githubusers.feature.detail.UserDetailActivity
import kotlinx.android.synthetic.main.item_row_user.view.*

class FollowingAdapter(private val mContext: Context) : RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {

    private var items = mutableListOf<UserFollowingResponseItem>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: UserFollowingResponseItem) {
            with(itemView) {
                Glide
                    .with(itemView.context)
                    .load(data.avatarUrl)
                    .placeholder(R.drawable.ic_user)
                    .circleCrop()
                    .into(iv_user)

                txt_username.text = data.login

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, UserDetailActivity::class.java).apply {
                        putExtra(UserDetailActivity.USERNAME_KEY, data.login)
                    }.also {
                        itemView.context.startActivity(it)
                    }
                }
            }
        }
    }

    fun setItems(data : MutableList<UserFollowingResponseItem>) {
        this.items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_row_user, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}