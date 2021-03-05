package com.example.githubusers.feature.following

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
import com.example.githubusers.data.entity.UserFollowingResponseItem
import com.example.githubusers.databinding.FragmentFollowingBinding
import com.example.githubusers.feature.detail.UserDetailActivity
import kotlinx.android.synthetic.main.fragment_following.*
import javax.inject.Inject


class FollowingFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel : FollowingViewModel

    private var lists = mutableListOf<UserFollowingResponseItem>()

    private val followingAdapter: FollowingAdapter by lazy {
        FollowingAdapter(requireContext())
    }

    private val binding : FragmentFollowingBinding by lazy {
        FragmentFollowingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModels()
        handleUserName()
        initObserver()
        initRecyclerView()
    }

    private fun initViewModels() {
        viewModel = ViewModelProvider(this, viewModelFactory)[FollowingViewModel::class.java]
    }

    private fun handleUserName() {
        val activity = activity as UserDetailActivity
        val username : String? = activity.getUsername()
        username?.let { viewModel.getUserFollowing(it) }
    }

    private fun initRecyclerView() {
        binding.rcView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = followingAdapter
        }
    }

    private fun initObserver() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            handleStateLoading(it)
        })
        viewModel.resultUserFollowing.observe(viewLifecycleOwner, Observer {
            handleResultUserFollowing(it)
        })
    }

    private fun handlingEmptyFollowing(data: List<UserFollowingResponseItem>){
        if (data.isEmpty()) {
            binding.baseEmptyFollowing.root.setVisible()
            binding.rcView.setGone()
        } else {
            binding.baseEmptyFollowing.root.setGone()
            binding.rcView.setVisible()
        }
    }

    private fun handleResultUserFollowing(data: List<UserFollowingResponseItem>) {
        handlingEmptyFollowing(data)
        lists.clear()
        lists.addAll(data)
        followingAdapter.setItems(lists)
    }

    private fun handleStateLoading(loading: LoaderState) {
        if (loading is LoaderState.ShowLoading) {
            binding.baseLoader.root.setVisible()
            binding.rcView.setGone()
        } else {
            binding.baseLoader.root.setGone()
            binding.rcView.setVisible()
        }
    }

}