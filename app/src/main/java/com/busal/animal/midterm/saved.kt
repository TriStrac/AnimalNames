package com.busal.animal.midterm

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class saved(context: Context) {
    private val sharedPref = context
        .getSharedPreferences("GENERAL",Context.MODE_PRIVATE)

    var isSaved: Boolean
        get() = sharedPref.getBoolean("KEY_ISSAVED",true)
        set(value) = sharedPref.edit().putBoolean("KEY_ISSAVED",value).apply()

    // Helper method to convert a list to a Json string
    private fun <T> Gson.toJson(src: T): String {
        return toJson(src)
    }

    // Helper method to convert a Json string to a list
    private inline fun <reified T> Gson.fromJson(json: String): T {
        return fromJson(json, object : TypeToken<T>() {}.type)
    }

    var animalList: List<AnimalClass>
        get() {
            val json: String? = sharedPref.getString("KEY_ANIMAL_LIST", null)
            return if (json != null) {
                Gson().fromJson(json)
            } else {
                emptyList()
            }
        }
        set(value) {
            val gson = Gson()
            val json = gson.toJson(value)
            sharedPref.edit().putString("KEY_ANIMAL_LIST", json).apply()
        }

}