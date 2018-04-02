package com.team

class Generator(private var polynomial: BooleanArray) {

    private val seed = polynomial.clone()

    fun generate(): String {
        println("LFSR started")
        val builder = StringBuilder()
        val length = polynomial.size - 1

        for (i in 0 until length * 2) {
            if (polynomial.last()) {
                builder.append("1")
            } else {
                builder.append("0")
            }

            println("\nStep $i")
            println("Polynomial: ${polynomial.contentToString()}")
            val xorValue = xorPolynomial(polynomial)
            println("xorValue: $xorValue")

            polynomial = shiftPolynomial(polynomial)
            polynomial[0] = xorValue
        }

        println("LFSR finished")
        return builder.toString()
    }

    private fun xorPolynomial(polynomial: BooleanArray): Boolean {
        val lastIndex = seed.lastIndexOf(true)
        var result = polynomial[lastIndex]
        for (i in lastIndex downTo 1) {
            if (seed[i - 1]) {
                println("${polynomial[i - 1]} xor $result = ${polynomial[i - 1] xor result}")
                result = polynomial[i - 1] xor result
            }
        }

        return result
    }

    private fun shiftPolynomial(polynomial: BooleanArray): BooleanArray {
        for (i in polynomial.size - 1 downTo 1) {
            polynomial[i] = polynomial[i - 1]
        }
        return polynomial
    }

}