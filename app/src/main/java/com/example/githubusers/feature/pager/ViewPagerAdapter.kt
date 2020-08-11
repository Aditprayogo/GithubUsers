package com.example.githubusers.feature.pager

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.githubusers.R
import com.example.githubusers.feature.follower.FollowerFragment
import com.example.githubusers.feature.following.FollowingFragment

class ViewPagerAdapter(
    private val mContext: Context,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.follower_title, R.string.following_title)

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position) {
            0 -> fragment =
                FollowerFragment()
            1 -> fragment =
                FollowingFragment()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int = 2
}