package com.example.myapplication.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VodResponse(
    @SerialName("data")
    val dataList: List<Data>
) {
    @Serializable
    data class Data(
        @SerialName("id")
        val id: Long,
        @SerialName("title")
        val title: String
    )
}