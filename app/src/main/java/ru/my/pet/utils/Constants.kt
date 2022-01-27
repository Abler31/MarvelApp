package ru.my.pet.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {
    companion object{
        const val BASE_URL = "https://gateway.marvel.com"
        const val PRIVATE_KEY = Private.PRIVATE_KEY
        const val PUBLIC_KEY = "54a10eaf23aec5923f62018063c118e7"
        val timeSpan = Timestamp(System.currentTimeMillis()).time.toString()
        const val limit = "20"


        fun hash(): String {
            val input = "$timeSpan$PRIVATE_KEY$PUBLIC_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

    }
}