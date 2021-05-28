package com.aditPrayogo.githubusers.ui.pager

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.aditPrayogo.githubusers.R
import com.aditPrayogo.githubusers.ui.follower.FollowerFragment
import com.aditPrayogo.githubusers.ui.following.FollowingFragment

class ViewPagerAdapter(
    private val mContext: Context,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.follower_title, R.string.following_title)

    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FollowerFragment()
            1 -> FollowingFragment()
            else -> FollowerFragment()
        }
    }

    override fun getCount(): Int = 2
}