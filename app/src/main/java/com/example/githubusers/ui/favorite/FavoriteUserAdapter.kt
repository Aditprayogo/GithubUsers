package com.example.githubusers.ui.favorite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.R
import com.example.githubusers.data.local.db.entity.UserFavorite
import com.example.githubusers.databinding.ItemRowFavoriteUserBinding
import com.example.githubusers.ui.detail.UserDetailActivity

class FavoriteUserAdapter(private val mContext: Context) :
    RecyclerView.Adapter<FavoriteUserAdapter.ViewHolder>() {

    private var items: MutableList<UserFavorite> = mutableListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemRowFavoriteUserBinding = ItemRowFavoriteUserBinding.bind(itemView)

        fun bind(user: UserFavorite) {
            with(itemView) {
                binding.apply {
                    txtUsername.text = user.username
                    txtLocation.text = user.location ?: context.getString(R.string.no_location)
                    txtFollower.text = user.followers.toString()
                    txtFollowing.text = user.following.toString()
                    txtRepository.text = user.publicRepos.toString()
                    txtCompany.text = user.company ?: context.getString(R.string.no_company)
                    binding.ivUser.load(user.avatarUrl)
                }
                itemView.setOnClickListener {
                    context.startActivity(
                        Intent(
                            context,
                            UserDetailActivity::class.java
                        ).apply {
                            putExtra(UserDetailActivity.USERNAME_KEY, user.username)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        })
                }
            }

        }
    }

    fun setItems(items: MutableList<UserFavorite>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_row_favorite_user, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}