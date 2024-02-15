package com.example.a2pp_tests

import kotlin.math.pow
import kotlin.math.sqrt


fun main() {

    var inputFiltersMap = mutableMapOf<String, Any>()
    for (i in 0 until 6) {
        val inputString = readLine()
        inputFiltersMap = inputMap(inputString!!)
    }

    val filter: Filters = divideValues(inputFiltersMap)

    val pushsNum = readLine()!!.toInt()
    var i = 0
    var answers: Array<String> = arrayOf()
    for (j in 1..pushsNum) {
        val pushParamsNum = readLine()!!.toInt()
        println("Nums $pushParamsNum")
        var inputDataMap = mutableMapOf<String, Any>()
        var type = ""
        var text = ""
        for (n in 1..pushParamsNum) {
            println(n)
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
                inputDataMap = inputMap(inputString!!)
            }
        }
        val paramsOfPush = divideValues(inputDataMap)
        //сравниваем тип
        if (type == "LocationPush") {
            if (euclideanDistanceAndRadiusCheck(
                    paramsOfPush.xCoord!!,
                    paramsOfPush.yCoord!!,
                    filter.xCoord!!,
                    filter.yCoord!!,
                    paramsOfPush.radius!!
                )
            ) {
                println(text)
                answers.plus(text)
            }
        } else if (type == "LocationAgePush") {
            if ((paramsOfPush.gender!! == filter.gender!!) && (euclideanDistanceAndRadiusCheck(
                    paramsOfPush.xCoord!!,
                    paramsOfPush.yCoord!!,
                    filter.xCoord!!,
                    filter.yCoord!!,
                    paramsOfPush.radius!!
                ))
            ) {
                println(text)
                answers.plus(text)
            }
        } else if (type == "GenderPush") {
            if (paramsOfPush.gender!! == filter.gender!!) {
                println(text)
                answers.plus(text)
            }
        } else if (type == "GenderAgePush") {
            if ((paramsOfPush.gender!! == filter.gender!!) && (paramsOfPush.age!! < filter.age!!)) {
                println(text)
                answers.plus(text)
            }
        } else if (type == "AgeSpecificPush") {
            if ((paramsOfPush.age!! < filter.age!!) && (paramsOfPush.time!! < filter.time!!)) {
                println(text)
                answers.plus(text)
            }
        } else if (type == "TechPush") {
            if (paramsOfPush.osVersion!! < filter.osVersion!!) {
                println(text)
                answers.plus(text)
            }
        }

    }
    //println("Result")
    println(answers.size)
    if (answers.size == 0) {
        println("-1")
    } else {
        answers.forEach {
            println(it)

        }
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

