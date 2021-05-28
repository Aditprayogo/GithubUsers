package com.aditPrayogo.githubusers.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditPrayogo.githubusers.R
import com.aditPrayogo.githubusers.databinding.ItemRowUserBinding
import com.aditPrayogo.githubusers.ui.detail.UserDetailActivity
import com.aditprayogo.core.domain.model.UserSearchItem
import com.aditprayogo.core.utils.viewUtils.load

class MainAdapter(val context: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var items = mutableListOf<UserSearchItem>()
    private lateinit var mainActivity: MainActivity

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemRowUserBinding = ItemRowUserBinding.bind(itemView)

        fun bind(data: UserSearchItem) {
            binding.apply {
                ivUser.load(data.avatarUrl)
                binding.txtUsername.text = data.login
            }
            with(itemView) {
                setOnClickListener {
                    context.startActivity(
                        Intent(context, UserDetailActivity::class.java).apply {
                            putExtra(UserDetailActivity.USERNAME_KEY, data.login)
                        }
                    )
                }
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

    fun setItems(data: MutableList<UserSearchItem>) {
        this.items = data
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}