package com.team

import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.BitSet


val OUTPUT_FILENAME = "output"

fun main(args: Array<String>) {
//    lfsrRunner()

    ciphertextAutokeyRunner()
}

fun lfsrRunner() {
    print("Enter polynomial: ")
    val polynomial = readLine()!!
    val polynomialArray = stringToBooleanArray(polynomial)

    val generator = Generator(polynomialArray)
    val result = generator.generate()

    println("\nGenerated polynomial: $result")
}

fun ciphertextAutokeyRunner() {
    print("Enter polynomial: ")
    val polynomial = stringToBooleanArray(readLine()!!)

    print("Enter seed: ")
    val seed = stringToBooleanArray(readLine()!!)

    print("Enter input filename: ")
    val fileName = readLine()
    val fileBits = Files.readAllBytes(Paths.get(fileName))

    print("Enter output filename: ")
    val outputFileName = readLine()

    val cipher = CiphertextAutokey(polynomial, seed)

    // Encoding
//    val encoded = cipher.encode(bytesToBooleanArray(fileBits))
//    writeToFile(encoded, outputFileName)

    // Decoding
    val decoded = cipher.decode(bytesToBooleanArray(fileBits))
    writeToFile(decoded, outputFileName)
}

fun writeToFile(text: String, filename: String? = OUTPUT_FILENAME) {
    FileOutputStream(filename).use { fos ->
        fos.write(text.toByteArray())
    }
}

fun stringToBooleanArray(polynomial: String): BooleanArray {
    val array = BooleanArray(polynomial.length)
    for (i in 0 until polynomial.length) {
        array[i] = polynomial[i].toBoolean()
    }

    return array
}

fun bytesToBooleanArray(bytes: ByteArray): BooleanArray {
    val bits = BitSet.valueOf(bytes)
    val bools = BooleanArray(bytes.size * 8)
    var i = bits.nextSetBit(0)
    while (i != -1) {
        bools[i] = true
        i = bits.nextSetBit(i + 1)
    }
    return bools
}

fun Char.toBoolean() = this != '0'
