package com.example.githubusers.feature.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.R
import com.example.githubusers.core.base.BaseFragment
import com.example.githubusers.core.state.LoaderState
import com.example.githubusers.core.util.setGone
import com.example.githubusers.core.util.setVisible
import com.example.githubusers.data.entity.UserFollowersResponseItem
import com.example.githubusers.feature.detail.UserDetailActivity
import kotlinx.android.synthetic.main.fragment_follower.*
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

class FollowerFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: FollowerViewModel

    private var lists = mutableListOf<UserFollowersResponseItem>()

    private val followerAdapter: FollowerAdapter by lazy {
        FollowerAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follower, container, false)
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        handleUserName()
        initObserver()
        initRecyclerView()
    }

    private fun handleUserName() {
        val activity = activity as UserDetailActivity
        val username : String? = activity.getUsername()
        viewModel.getUserFollowers(username!!)
    }

    private fun initRecyclerView() {
        rc_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rc_view.adapter = followerAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[FollowerViewModel::class.java]
    }

    private fun initObserver() {
        viewModel.resultUserFollower.observe(viewLifecycleOwner, Observer {
            handleUserFollower(it)
        })
        viewModel.state.observe(viewLifecycleOwner, Observer {
            handleStateLoading(it)
        })
    }

    private fun handleEmptyFollower(data : List<UserFollowersResponseItem>) {
        if (data.isEmpty()) {
            base_empty_follower.setVisible()
            rc_view.setGone()
        } else {
            base_empty_follower.setGone()
            rc_view.setVisible()
        }
    }

    private fun handleStateLoading(loading: LoaderState) {
        if(loading is LoaderState.ShowLoading) {
            baseLoader.setVisible()
            rc_view.setGone()
        } else {
            baseLoader.setGone()
            rc_view.setVisible()
        }
    }

    private fun handleUserFollower(data: List<UserFollowersResponseItem>) {
        handleEmptyFollower(data)
        lists.clear()
        lists.addAll(data)
        followerAdapter.setItems(data = lists)
    }

}