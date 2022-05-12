package ru.my.pet.characters

import ru.my.pet.model.Thumbnail

data class Character(
    val name : String,
    val thumbnail : Thumbnail,
    val id : Int
)
