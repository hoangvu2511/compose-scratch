package com.vunguyenhoang.hapipet

import org.junit.Test
import java.math.BigInteger

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        plusOne(intArrayOf(1, 2, 3))
    }


    fun plusOne(digits: IntArray): IntArray {
        return digits.joinToString(separator = "").toBigInteger().plus(BigInteger.ONE)
            .toString().toCharArray()
            .map { it.toInt() }.toIntArray()
    }

    fun removeElement(nums: IntArray, `val`: Int): Int {

    }
}
















































































