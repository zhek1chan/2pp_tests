package com.example.a2pp_tests


fun main() {
    var inputFiltersMap = mutableMapOf<String, Any>()
    print("Введите ключ и значение через пробел: ")
    for (i in 0..5) {
        val inputString = readLine()
        inputFiltersMap = inputMap(inputString!!)
        println(inputFiltersMap)
    }
    var age = 0
    var osVersion = 0
    var time = 0L
    var xCoord= 0f
    var yCoord= 0f
    var gender = ""

    //надо вынести в отдельную функцию
    inputFiltersMap.forEach { entry ->
        if (entry.key == "time") {
            time = entry.value as Long
        } else if (entry.key == "age") {
            age = entry.value as Int
        } else if (entry.key == "gender") {
            gender = entry.value as String
        } else if (entry.key == "os_version") {
            osVersion = entry.value as Int
        } else if (entry.key == "x_coord") {
            xCoord = entry.value as Float
        } else if (entry.key == "y_coord") {
            yCoord = entry.value as Float
        }
    }

    val pushNum =  readLine()!!.toInt()
    /////////////////////////////////////
    var i = 0
    while(i < pushNum){
        var n = 0
        val pushParamsNum =  readLine()!!.toInt()
        while(n < pushParamsNum){
            //Тут вся фильрация
            var type = ""
            var text = ""

            val inputString = readLine()
            val inputDataPushMap = inputMap(inputString!!)

            inputDataPushMap.forEach { entry ->
                if (entry.key == "text") {
                    text = entry.value as String
                } else {
                    type = entry.value as String
                }
            }
            //сравниваем тип
            if (type == "LocationPush"){

            } else if (type == "LocationAgePush"){

            } else if (type == "GenderPush"){

            } else if (type == "GenderAgePush"){

            } else if (type == "AgeSpecificPush"){

            } else{
                //TechPush
            }
            //дальше сравниваем с фильтром и выводим результат
            n=+1
        }
        i=+1
    }
    ///////////////////////////////////////
    val filter = Filters(
        time,
        age,
        gender,
        osVersion,
        xCoord,
        yCoord
    )
}

fun inputMap(s: String): MutableMap<String, Any> {
    var inputMap = mutableMapOf<String, Any>()
    s.let {
        val (key, value) = it.split(" ")
        println("Ключ: $key, Значение: $value")
        inputMap.put(key, value)
    }
    return inputMap
}
/*lateinit var gender: String
var age: Int = 0
var osVersion: Int = 0
var time: Long = 0
var xCoord: Int = 0
var yCoord: Int = 0
var l: Int = 0
var count: Int = 0
var arr: ArrayList<String> = arrayListOf("", "", "", "", "", "")
while (count < 6) {
    var first = readLine()
    arr.add(count, first.toString())
    l = first!!.length
    if (first.toString().contains("time")) {
        first.toString().drop(5)
        time = (first.toString().toLong())
    } else if (first.toString().contains("gender")) {
        first.toString().drop(7)
        gender = (first.toString())
    } else if (first.toString().contains("age")) {
        first.toString().drop(4)
        age = (first.toString().toInt())
    } else if (first.toString().contains("os_version")) {
        first.toString().drop(11)
        osVersion = (first.toString().toInt())
    } else if (first.toString().contains("x_coord")) {
        first.toString().drop(8)
        xCoord=(first.toString().toInt())
    } else if(first.toString().contains("y_coord")){
        first.toString().drop(8)
        yCoord=(first.toString().toInt())
    }
    count=+1
}
println("$xCoord $yCoord $gender $age $time $osVersion")
}*/

