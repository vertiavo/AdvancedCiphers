package com.team

fun main(args: Array<String>) {

    print("Enter polynomial step: ")
    val step = readLine()!!.toInt()

    val generator = PolynomialGenerator(step)
    val polynomial = generator.generate()

    println(polynomial)

}