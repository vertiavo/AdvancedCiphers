package com.team

class PolynomialGenerator(private val coefficient: BooleanArray) {

    fun generate(): String {
        val builder = StringBuilder()
        val n = coefficient.size - 1

        for (t in 0 until n + 1) {
            val next = coefficient[n - 1] xor coefficient[n]

            for (i in n downTo 1) {
                coefficient[i] = coefficient[i - 1]
            }

            coefficient[0] = next

            if (next)
                builder.append("1")
            else
                builder.append("0")
        }

        return builder.toString()
    }

    fun calculateNumber(): Int {
        /* Need to reverse polynomial to match exercise requirement
         * eg. 1 + x + x^3 instead of x^3 + x + 1
         */
        val reversedArray = coefficient.reversedArray()
        var sum = 0
        for (i in 0 until reversedArray.size) {
            if (reversedArray[i]) {
                sum += (Math.pow(2.0, i.toDouble())).toInt()
            }
        }
        return sum
    }

}