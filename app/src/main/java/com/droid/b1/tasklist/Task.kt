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
    var description : String = "https://placebear.com/400/400 100",
) : java.io.Serializable {
    fun getUrl() : String {
        return description.split(" ")[0];
    }
    fun setUrl(url : String) {
        description = url + " " + getHunger()
    }
    fun setHunger(hunger : String) {
        description = getUrl() + " " + hunger
    }
    fun getHunger() : String {
        return if (description.split(" ").size > 1) description.split(" ")[1] else "0"
    }
    fun removeHunger(amount : Int) {
        val currentHunger =  Integer.parseInt(getHunger())
        var newHunger = currentHunger - amount
        if (newHunger < 0) newHunger = 0
        this.description = getUrl() + " " + newHunger.toString()
    }
    fun addHunger(amount: Int) {
        val currentHunger =  Integer.parseInt(getHunger())
        var newHunger = currentHunger + amount
        if (newHunger > 100) newHunger = 100
        this.description = getUrl() + " " + (Integer.parseInt(getHunger()) + amount).toString()
    }
}