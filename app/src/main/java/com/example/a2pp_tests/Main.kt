package com.example.a2pp_tests

import kotlin.math.pow
import kotlin.math.sqrt


fun main() {

    val inputFiltersMap: MutableMap<String, Any>
    val array = mutableListOf<String>()

    for (i in 0 until 6) {
        val inputString = readlnOrNull()
        array.add(inputString!!)
    }
    inputFiltersMap = inputMapFromArray(array)
    val filter: Filters = divideValues(inputFiltersMap)

    val pushsNum = readln().toInt()
    val answers = mutableListOf<String>()
    for (j in 1..pushsNum) {
        val pushParamsNum = readln().toInt()
        var inputDataMap: MutableMap<String, Any>
        var type = ""
        var text = ""
        val arr = mutableListOf<String>()

        for (n in 1..pushParamsNum) {
            val inputString = readlnOrNull()
            if ((n == 1) || (n == 2)) {
                val inputDataPushMap = inputMap(inputString!!)
                inputDataPushMap.forEach { entry ->
                    if (entry.key == "text") {
                        text = entry.value.toString()
                    } else {
                        type = entry.value.toString()
                    }
                }
            } else {
                arr.add(inputString!!)
            }
        }

        inputDataMap = inputMapFromArray(arr)
        val paramsOfPush = divideValues(inputDataMap)

        //сравниваем тип
        if (type == "LocationPush") {
            if (euclideanDistanceAndRadiusCheck(
                    paramsOfPush.xCoord!!,
                    paramsOfPush.yCoord!!,
                    filter.xCoord!!,
                    filter.yCoord!!,
                    paramsOfPush.radius!!
                ) && (paramsOfPush.time!! >= filter.time!!)
            ) {
                println(text)
                answers.add(text)
            }
        } else if (type == "LocationAgePush") {
            if ((paramsOfPush.age!! < filter.age!!) && (euclideanDistanceAndRadiusCheck(
                    paramsOfPush.xCoord!!,
                    paramsOfPush.yCoord!!,
                    filter.xCoord!!,
                    filter.yCoord!!,
                    paramsOfPush.radius!!
                ))
            ) {
                answers.add(text)
                println(text)
            }
        } else if (type == "GenderPush") {
            if (paramsOfPush.gender!! == filter.gender!!) {
                answers.add(text)
                println(text)
            }
        } else if (type == "GenderAgePush") {
            if ((paramsOfPush.gender!! == filter.gender!!) && (paramsOfPush.age!! <= filter.age!!)) {
                answers.add(text)
                println(text)
            }
        } else if (type == "AgeSpecificPush") {
            if ((paramsOfPush.age!! <= filter.age!!) && (paramsOfPush.time!! >= filter.time!!)) {
                answers.add(text)
                println(text)
            }
        } else if (type == "TechPush") {
            if (paramsOfPush.osVersion!! >= filter.osVersion!!) {
                answers.add(text)
                println(text)
            }
        }

    }
    if (answers.size == 0) {
        println("-1")
    }
}

fun inputMap(s: String): MutableMap<String, Any> {
    val inputMap = mutableMapOf<String, Any>()
    s.let {
        val (key, value) = it.split(" ")
        inputMap.put(key, value)
    }
    return inputMap
}

fun inputMapFromArray(array: MutableList<String>): MutableMap<String, Any> {
    val inputMap = mutableMapOf<String, Any>()
    array.forEach { it ->
        it.let {
            val (key, value) = it.split(" ")
            inputMap[key] = value
        }
    }
    return inputMap
}

fun divideValues(inputFiltersMap: MutableMap<String, Any>): Filters {
    var age: Int? = 0
    var osVersion: Int? = 0
    var time: Long? = 0
    var xCoord: Float? = 0f
    var yCoord: Float? = 0f
    var gender: String? = ""
    var radius: Int? = 0
    inputFiltersMap.forEach { entry ->
        when (entry.key) {
            "time" -> {
                time = entry.value.toString().toLong()
            }

            "age" -> {
                age = entry.value.toString().toInt()
            }

            "gender" -> {
                gender = entry.value.toString()
            }

            "os_version" -> {
                osVersion = entry.value.toString().toInt()
            }

            "x_coord" -> {
                xCoord = entry.value.toString().toFloat()
            }

            "y_coord" -> {
                yCoord = entry.value.toString().toFloat()
            }

            "radius" -> {
                radius = entry.value.toString().toInt()
            }
            "expire_date" ->{
                time = entry.value.toString().toLong()
            }
        }
    }
    return Filters(time, age, gender, osVersion, xCoord, yCoord, radius)
}


fun euclideanDistanceAndRadiusCheck(
    x1: Float,
    y1: Float,
    x2: Float,
    y2: Float,
    radius: Int
): Boolean {
    val distance = sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
    return distance <= radius
}
data class Filters(
    var time: Long?,
    var age: Int?,
    var gender: String?,
    var osVersion: Int?,
    var xCoord: Float?,
    var yCoord: Float?,
    var radius: Int?
)

