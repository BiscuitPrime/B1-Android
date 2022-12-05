package com.droid.b1.tasklist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    @SerialName("id")
    val id : String,
    @SerialName("content")
    val content : String,
    @SerialName("description")
    val description : String = "Les frigos sont moins chers chez Buts"
)