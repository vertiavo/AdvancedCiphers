package com.team

fun main(args: Array<String>) {

    print("Enter polynomial: ")
    val polynomial = readLine()!!
    val polynomialArray  = convertToBooleanArray(polynomial)

    val generator = Generator(polynomialArray)
    val result = generator.generate()

    println("\nGenerated polynomial: $result")
}

fun convertToBooleanArray(polynomial: String): BooleanArray {
    val array = BooleanArray(polynomial.length)
    for (i in 0 until polynomial.length) {
        array[i] = polynomial[i].toBoolean()
    }

    return array
}

fun Char.toBoolean() = this != '0'