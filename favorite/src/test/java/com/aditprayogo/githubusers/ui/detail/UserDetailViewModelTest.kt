package com.aditprayogo.githubusers.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aditPrayogo.githubusers.ui.detail.UserDetailViewModel
import com.aditprayogo.core.domain.model.UserDetail
import com.aditprayogo.core.domain.usecase.UserUseCase
import com.aditprayogo.core.utils.state.LoaderState
import com.aditprayogo.core.utils.state.ResultState
import com.aditprayogo.githubusers.utils.getAwaitOrValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.assertj.core.api.Assertions.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UserDetailViewModelTest {

    private lateinit var viewModel: UserDetailViewModel

    @Mock
    private lateinit var mockUserUseCase: UserUseCase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = UserDetailViewModel(mockUserUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getUserDetailFromApi`() = runBlockingTest {
        // Given
        val username = "octocat"
        val userDetail =  UserDetail(
            username = "johndoe",
            name = "John Doe",
            avatarUrl = "https://avatars.githubusercontent.com/u/1234567?v=4",
            followingUrl = "https://api.github.com/users/johndoe/following{/other_user}",
            bio = "Software Engineer",
            company = "Acme Inc.",
            publicRepos = 10,
            followersUrl = "https://api.github.com/users/johndoe/followers",
            followers = 20,
            following = 30,
            location = "New York City"
        )
        val flow = flow {
            emit(ResultState.Success(userDetail))
        }
        given(mockUserUseCase.getUserDetailFromApi(username)).willReturn(flow)

        // When
        viewModel.getUserDetailFromApi(username)

        // Then
        val resultUserDetail = viewModel.resultUserDetail.getAwaitOrValue()
        val state = viewModel.state.getAwaitOrValue()
        assertThat(resultUserDetail).isEqualTo(userDetail)
        assertThat(state).isInstanceOf(LoaderState.HideLoading::class.java)
    }
}