package com.example.admissionportalapp

object User {
    fun isEmpty(userName:String,password:String,confirmPassword:String):Boolean{
        if(userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
            return true
        return false
    }

    fun validateLength(password:String):Boolean{
        if(password.length==2)
            return true
        return false
    }

    fun confirmPassword(password:String,confirmPassword:String):Boolean{
        if(password.equals(confirmPassword)){
            return true
        }
        return false
    }

    fun doesNotExist(userName:String,list:MutableList<String>):Boolean{
        for( x in list){
            if(userName.equals(x)){
                return false
            }
        }
        return false

    }
}