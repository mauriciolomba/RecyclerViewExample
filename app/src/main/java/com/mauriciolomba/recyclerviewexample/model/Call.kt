package com.mauriciolomba.recyclerviewexample.model

class Call(val number: String, val duration: Int, val type: Int, val name: String?) {

    override fun toString(): String {
        return "Number: $number - Duration: $duration - Type: $type - Name: $name"
    }
}