package com.example.githubusers.feature.widget

import android.app.Application
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.example.githubusers.R
import com.example.githubusers.core.util.Coroutine
import com.example.githubusers.core.util.ImageTask
import com.example.githubusers.data.db.dao.UserFavoriteDao
import com.example.githubusers.data.db.entity.UserFavorite
import javax.inject.Inject

@Suppress("DEPRECATION")
class StackRemoteViewsFactory (
    val app: Application
) : RemoteViewsService.RemoteViewsFactory {

    @Inject
    lateinit var userFavoriteDao: UserFavoriteDao

    private var items : MutableList<Bitmap> = ArrayList()
    private var userLists: List<UserFavorite> = ArrayList()

    override fun onCreate(){
    }

    override fun onDataSetChanged() {
        Coroutine.main {
            userLists = userFavoriteDao.fetchAllUsers()
            convertToBitmap(userLists)
        }
    }

    override fun hasStableIds(): Boolean = false

    override fun getViewAt(position: Int): RemoteViews {
        val remoteViews = RemoteViews(app.packageName, R.layout.item_user_widget)
        remoteViews.setImageViewBitmap(R.id.iv_image_view, items[position])

        val bundle = Bundle()
        bundle.putInt(UserWidget.EXTRA_ITEM, position)

        val intent = Intent()
        intent.putExtras(bundle)

        remoteViews.setOnClickFillInIntent(R.id.iv_image_view, intent)
        return remoteViews
    }

    override fun getCount(): Int = items.size

    override fun getViewTypeCount(): Int = 1

    override fun getLoadingView(): RemoteViews? = null

    override fun getItemId(p0: Int): Long = 0

    override fun onDestroy() {
    }

    private fun convertToBitmap(data: List<UserFavorite>) {
        items.removeAll(items)
        items.clear()

        for (item in data) {
            items.add(ImageTask().execute(item.avatarUrl).get())
        }
    }
}