package com.team

fun main(args: Array<String>) {

    print("Enter polynomial: ")
    val coefficient = readLine()!!
    val coefficientArray  = convertToBooleanArray(coefficient)

    val generator = PolynomialGenerator(coefficientArray)
    val polynomial = generator.generate()
    val number = generator.calculateNumber()

    println("Generated polynomial: $polynomial")

    println("Generated number: $number")
}

fun convertToBooleanArray(coefficient: String): BooleanArray {
    val array = BooleanArray(coefficient.length)
    for (i in 0 until coefficient.length) {
        array[i] = coefficient[i].toBoolean()
    }

    return array
}

fun Char.toBoolean() = this != '0'