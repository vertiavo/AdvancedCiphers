package com.team

class CiphertextAutokey(var polynomial: BooleanArray, var seed: BooleanArray) {

    fun encode(text: BooleanArray): String {
        println("Encoding started")
        val builder = StringBuilder()

        val firstSeedIndex = calculateStartingIndex()

        val n = Math.min(polynomial.size, seed.size)

        for (bit in text) {
            var xorResult = xorPolynomial(n, firstSeedIndex)

            //println(" -> $xorResult xor $bit = ${xorResult xor bit}")
            xorResult = xorResult xor bit

            shiftPolynomial(xorResult)

            if (xorResult) {
                builder.append("1")
            } else {
                builder.append("0")
            }
        }

        println("Encoding finished")

        return builder.toString()
    }

    fun decode(text: BooleanArray): String {
        println("Decoding started")
        val builder = StringBuilder()

        val firstSeedIndex = calculateStartingIndex()

        val n = Math.min(polynomial.size, seed.size)

        for (bit in text) {
            var xorResult = xorPolynomial(n, firstSeedIndex)

            //println(" -> $xorResult xor $bit = ${xorResult xor bit}")
            xorResult = xorResult xor bit

            shiftPolynomial(xorResult)

            if (xorResult) {
                builder.append("1")
            } else {
                builder.append("0")
            }
        }

        println("Decoding finished")
        return builder.toString()
    }

    private fun calculateStartingIndex(): Int {
        val firstSeedIndex = seed.indexOfFirst { element -> element }
        if (firstSeedIndex == -1) {
            throw Exception("Seed should contain ones!")
        }
        return firstSeedIndex
    }

    private fun xorPolynomial(minLength: Int, firstSeedIndex: Int): Boolean {
        var xorResult = polynomial[firstSeedIndex]

        for (i in firstSeedIndex + 1 until minLength) {
            if (seed[i]) {
                //print(" $xorResult xor ${polynomial[i]} = ${xorResult xor polynomial[i]}")
                xorResult = xorResult xor polynomial[i]
            }
        }

        return xorResult
    }

    private fun shiftPolynomial(xorResult: Boolean) {
        for (i in polynomial.size - 1 downTo 1) {
            polynomial[i] = polynomial[i - 1]
        }
        polynomial[0] = xorResult
    }

}