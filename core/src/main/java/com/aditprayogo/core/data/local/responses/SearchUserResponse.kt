package com.aditprayogo.core.data.local.responses


import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val userItems: List<UserSearchResponseItem>?,
    @SerializedName("total_count")
    val totalCount: Int?
)