package com.example.githubusers.feature.favorite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusers.R
import com.example.githubusers.data.db.entity.UserFavorite
import com.example.githubusers.feature.detail.UserDetailActivity
import kotlinx.android.synthetic.main.item_row_favorite_user.view.*

class FavoriteUserAdapter(val mContext: Context) : RecyclerView.Adapter<FavoriteUserAdapter.ViewHolder>() {

    private var items: MutableList<UserFavorite> = mutableListOf()

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)  {
        fun bind(user : UserFavorite) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(user.avatarUrl)
                    .circleCrop()
                    .into(iv_user)

                txt_username.text = user.username
                txt_follower.text = user.followers.toString()
                txt_following.text = user.following.toString()
                txt_repository.text = user.publicRepos.toString()

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, UserDetailActivity::class.java).apply {
                        putExtra(UserDetailActivity.USERNAME_KEY, user.username)
                        setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }.also {
                        itemView.context.startActivity(it)
                    }
                }

            }
        }
    }

    fun setItems(items: MutableList<UserFavorite>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_row_favorite_user, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}