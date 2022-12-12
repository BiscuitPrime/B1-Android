package com.droid.b1.tasklist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    @SerialName("id")
    var id : String,
    @SerialName("content")
    var content : String,
    @SerialName("description")
    var description : String = "Les frigos sont moins chers chez Buts",
) : java.io.Serializable