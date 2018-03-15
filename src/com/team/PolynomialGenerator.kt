package com.team

class PolynomialGenerator(private val step: Int) {

    private val defaultMask = booleanArrayOf(false, true, false, false, false, false, true, false, true, true, false)

    fun generate(mask: BooleanArray = defaultMask, tap: Int = 8): String {
        val builder = StringBuilder()
        val n = mask.size // length of register

        // Simulate operation of shift register.
        for (t in 0 until step) {

            // Simulate one shift-register step.
            val next = mask[n - 1] xor mask[tap]  // Compute next bit.

            for (i in n - 1 downTo 1)
                mask[i] = mask[i - 1]                  // Shift one position.

            mask[0] = next                       // Put next bit on right end.

            if (next)
                builder.append("1")
            else
                builder.append("0")
        }

        return builder.toString()
    }

}