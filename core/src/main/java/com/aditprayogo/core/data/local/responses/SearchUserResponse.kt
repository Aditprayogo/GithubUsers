package com.aditprayogo.core.data.local.responses


import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("items")
    val userItems: List<UserSearchResponseItem>?,
    @SerializedName("total_count")
    val totalCount: Int?
)