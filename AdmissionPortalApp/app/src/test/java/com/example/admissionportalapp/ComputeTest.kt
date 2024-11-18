package com.example.admissionportalapp

import org.junit.Assert.assertEquals
import org.junit.Test

class ComputeTest {

    @Test
    fun sum() {
        val v=Compute
        assertEquals(10,v.sum(5,5))
    }

    @Test
    fun multiply() {
        val v=Compute
        assertEquals(24,v.multiply(2,2))
    }
}