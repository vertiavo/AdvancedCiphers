package com.team

class CiphertextAutokey(var polynomial: BooleanArray, var seed: BooleanArray) {

    fun encode(text: BooleanArray): String {
        val builder = StringBuilder()

        val firstSeedIndex = seed.indexOfFirst { element -> element }
        if (firstSeedIndex == -1) {
            throw Exception("Seed should contain ones!")
        }

        val n = Math.min(polynomial.size, seed.size)

        for (bit in text) {
            var xorResult = xorPolynomial(polynomial, n, firstSeedIndex)

            print(" -> $xorResult xor $bit = ${xorResult xor bit}")
            xorResult = xorResult xor bit

            if (xorResult) {
                builder.append("1")
            } else {
                builder.append("0")
            }
            println()
        }

        return builder.toString()
    }

    fun decode(text: BooleanArray): String {
        val builder = StringBuilder()

        return builder.toString()
    }

    private fun xorPolynomial(polynomial: BooleanArray, minLength: Int, firstSeedIndex: Int): Boolean {
        var xorResult = polynomial[firstSeedIndex]

        for (i in firstSeedIndex + 1 until minLength) {
            if (seed[i]) {
                print(" $xorResult xor ${polynomial[i]} = ${xorResult xor polynomial[i]}")
                xorResult = xorResult xor polynomial[i]
            }
        }

        return xorResult
    }

}