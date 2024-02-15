package com.example.a2pp_tests

import kotlin.math.pow
import kotlin.math.sqrt


fun main() {

    var inputFiltersMap = mutableMapOf<String, Any>()
    var array = mutableListOf<String>()

    for (i in 0 until 6) {
        val inputString = readLine()
        array.add(inputString!!)
    }
    inputFiltersMap = inputMapFromArray(array!!)
    val filter: Filters = divideValues(inputFiltersMap)

    val pushsNum = readLine()!!.toInt()
    var i = 0
    var answers = mutableListOf<String>()
    for (j in 1..pushsNum) {
        val pushParamsNum = readLine()!!.toInt()
        var inputDataMap = mutableMapOf<String, Any>()
        var type = ""
        var text = ""
        var arr = mutableListOf<String>()

        for (n in 1..pushParamsNum) {
            val inputString = readLine()
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
        var paramsOfPush = divideValues(inputDataMap)

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
    var inputMap = mutableMapOf<String, Any>()
    s.let {
        val (key, value) = it.split(" ")
        inputMap.put(key, value)
    }
    return inputMap
}

fun inputMapFromArray(array: MutableList<String>): MutableMap<String, Any> {
    var inputMap = mutableMapOf<String, Any>()
    array.forEach {
        val s = it
        s.let {
            val (key, value) = it.split(" ")
            inputMap.put(key, value)
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
        if (entry.key == "time") {
            time = entry.value.toString().toLong()
        } else if (entry.key == "age") {
            age = entry.value.toString().toInt()
        } else if (entry.key == "gender") {
           gender = entry.value.toString()
        } else if (entry.key == "os_version") {
            osVersion = entry.value.toString().toInt()
        } else if (entry.key == "x_coord") {
            xCoord = entry.value.toString().toFloat()
        } else if (entry.key == "y_coord") {
            yCoord = entry.value.toString().toFloat()
        } else if (entry.key == "radius") {
            radius = entry.value.toString().toInt()
        }
    }
    val values = Filters(time, age, gender, osVersion, xCoord, yCoord, radius)
    return values
}


fun euclideanDistanceAndRadiusCheck(
    x1: Float,
    y1: Float,
    x2: Float,
    y2: Float,
    radius: Int
): Boolean {
    val distance = sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
    if (distance > radius) {
        return false
    } else {
        return true
    }
}


