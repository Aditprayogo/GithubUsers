package com.example.githubusers.ui.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.core.state.LoaderState
import com.example.githubusers.core.util.setGone
import com.example.githubusers.core.util.setVisible
import com.example.githubusers.data.local.responses.UserFollowersResponseItem
import com.example.githubusers.databinding.FragmentFollowerBinding
import com.example.githubusers.ui.detail.UserDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
class FollowerFragment : Fragment() {

    private val followerViewModel: FollowerViewModel by viewModels()

    private var lists = mutableListOf<UserFollowersResponseItem>()

    private val followerAdapter: FollowerAdapter by lazy {
        FollowerAdapter(requireContext())
    }

    private val binding: FragmentFollowerBinding by lazy {
        FragmentFollowerBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleUserName()
        initObserver()
        initRecyclerView()
    }

    private fun handleUserName() {
        val activity = activity as UserDetailActivity
        val username: String? = activity.getUsername()
        followerViewModel.getUserFollowers(username!!)
    }

    private fun initRecyclerView() {
        binding.rcView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = followerAdapter
        }
    }

    private fun initObserver() {
        with(followerViewModel) {
            state.observe(viewLifecycleOwner, {
                handleStateLoading(it)
            })
            resultUserFollower.observe(viewLifecycleOwner, {
                handleUserFollower(it)
            })
        }
    }

    private fun handleEmptyFollower(data: List<UserFollowersResponseItem>) {
        if (data.isEmpty()) {
            binding.apply {
                baseEmptyFollower.root.setVisible()
                rcView.setGone()
            }
        } else {
            binding.apply {
                baseEmptyFollower.root.setGone()
                rcView.setVisible()
            }
        }
    }

    private fun handleStateLoading(loading: LoaderState) {
        if (loading is LoaderState.ShowLoading) {
            binding.apply {
                baseLoader.root.setVisible()
                rcView.setGone()
            }
        } else {
            binding.apply {
                baseLoader.root.setGone()
                rcView.setVisible()
            }
        }
    }

    private fun handleUserFollower(data: List<UserFollowersResponseItem>) {
        handleEmptyFollower(data)
        lists.clear()
        lists.addAll(data)
        followerAdapter.setItems(data = lists)
    }

}