package com.example.admissionportalapp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UserTest {

    @Test
    fun isEmpty() {
        val v=User
        assertTrue(v.isEmpty("","",""))
    }

    @Test
    fun validateLength() {
        val v=User
        assertTrue(v.validateLength("pas"))
    }


    @Test
    fun confirmPassword() {
        val v=User
        assertTrue(v.confirmPassword("abc","abc"))
    }


    @Test
    fun doesNotExist() {
        val v=User
        val list:MutableList<String> = mutableListOf()
        list.addAll(0, listOf("ram","harshit","kumar"))
        assertTrue(v.doesNotExist("harshit1",list))
    }
}