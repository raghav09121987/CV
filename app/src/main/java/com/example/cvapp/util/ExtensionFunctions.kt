package com.example.cvapp.util

fun String.stringToList(): String? {
    var stringArray = this.split(". ",".")
    var convertedString: String? = ""
    stringArray.forEach { i ->
        if (i.isNotEmpty()) {
            convertedString = "$convertedString$i.\n\n"
        }
    }
    return convertedString
}