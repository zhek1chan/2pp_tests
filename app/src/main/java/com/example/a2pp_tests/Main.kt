package com.example.a2pp_tests

class Main {
    lateinit var gender: String
    var age: Int = 0
    var osVersion: Int = 0
    var time: Long = 0
    var xCoord: Int = 0
    var yCoord: Int = 0
    var l: Int = 0
    fun main() {
        var count: Int = 0
        var arr: ArrayList<String> = arrayListOf("","","","","","")
        while(count<6) {
            var first = readLine()
            arr.add(count, first.toString())
            l = first!!.length
            if (first.toString().contains("time")){
                first.toString().drop(5)
                time = (first.toString().toLong())
            } else if(first.toString().contains("gender")){
                first.toString().drop(7)
                gender=(first.toString())
            } else if(first.toString().contains("age")){
                first.toString().drop(4)
                age=(first.toString().toInt())
            } else if(first.toString().contains("os_version")){
                first.toString().drop(11)
                osVersion=(first.toString().toInt())
            } else if(first.toString().contains("x_coord")){
                first.toString().drop(8)
                xCoord=(first.toString().toInt())
            } else if(first.toString().contains("y_coord")){
                first.toString().drop(8)
                yCoord=(first.toString().toInt())
            }
            count=+1
        }
        println("$xCoord $yCoord $gender $age $time $osVersion")
    }
}