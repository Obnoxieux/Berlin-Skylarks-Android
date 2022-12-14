package de.davidbattefeld.berlinskylarks.classes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.gson.GsonBuilder
import java.lang.reflect.Type
import java.net.URL

abstract class ContentLoader(application: Application) : AndroidViewModel(application) {
    open var url = ""

    inline fun <reified T> parseResponse(json: String, typeToken: Type): T {
        val gson = GsonBuilder().create()
        return gson.fromJson<T>(json, typeToken)
    }

    suspend fun fetchJSONData(): String {
        return URL(url).readText()
    }
}