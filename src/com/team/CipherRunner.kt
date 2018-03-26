package com.team

fun main(args: Array<String>) {
    //lfsrRunner()

    ciphertextAutokeyRunner()
}

fun convertToBooleanArray(polynomial: String): BooleanArray {
    val array = BooleanArray(polynomial.length)
    for (i in 0 until polynomial.length) {
        array[i] = polynomial[i].toBoolean()
    }

    return array
}

fun Char.toBoolean() = this != '0'

fun lfsrRunner() {
    print("Enter polynomial: ")
    val polynomial = readLine()!!
    val polynomialArray  = convertToBooleanArray(polynomial)

    val generator = Generator(polynomialArray)
    val result = generator.generate()

    println("\nGenerated polynomial: $result")
}

fun ciphertextAutokeyRunner() {
    print("Enter polynomial: ")
    val polynomial = convertToBooleanArray(readLine()!!)

    print("Enter seed: ")
    val seed = convertToBooleanArray(readLine()!!)

    print("Enter bits: ")
    val fileBits = convertToBooleanArray(readLine()!!)

    val cipher = CiphertextAutokey(polynomial, seed)

    println(cipher.encode(fileBits))
}