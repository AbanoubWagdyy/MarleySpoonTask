package net.marleyspoon.data.model

import com.contentful.java.cda.CDAEntry

class RecipeDetails(entry: CDAEntry?) {

    var title: String = ""
    var image: String = ""
    var tags: String = ""
    var description: String = ""
    var chefName: String = ""

    init {
        val keys = entry!!.rawFields().keys
        if (keys.contains("title")) {
            title = entry.getField<String>("title")
        }
        if (keys.contains("chef")) {
            chefName = entry.getField<CDAEntry>("chef").getField<String>("name")
        }
        if (keys.contains("description")) {
            description = entry.getField<String>("description")
        }
        if (keys.contains("tags")) {
            val array = entry.getField<ArrayList<CDAEntry>>("tags")
            for (entry in array) {
                tags += entry.getField("name")
                tags += ","
            }
            tags = tags.substring(0, tags.length - 1)
        }
    }
}