package com.example.consumerapp.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.consumerapp.R
import com.example.consumerapp.data.UserFavorite
import com.example.consumerapp.databinding.ItemRowFavoriteUserBinding
import com.example.consumerapp.util.load

class MainAdapter(private val mContext: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var items: MutableList<UserFavorite> = mutableListOf()

    fun setItems(items: MutableList<UserFavorite>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemRowFavoriteUserBinding = ItemRowFavoriteUserBinding.bind(itemView)

        fun bind(data: UserFavorite) {
            with(itemView) {
                binding.apply {
                    txtUsername.text = data.username
                    txtRepository.text = data.public_repos
                    txtFollowing.text = data.following
                    txtFollower.text = data.followers
                    txtCompany.text = data.company ?: context.getString(R.string.no_company)
                    txtLocation.text = data.location ?: context.getString(R.string.no_location)
                    ivUser.load(data.avatar_url)
                }
                itemView.setOnClickListener {
                    Toast.makeText(
                        context,
                        context.getString(R.string.user_detail_information),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

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