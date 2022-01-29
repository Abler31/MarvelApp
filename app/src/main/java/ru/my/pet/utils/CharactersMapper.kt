package ru.my.pet.utils

import ru.my.pet.characters.Character
import ru.my.pet.model.Result

object CharactersMapper{

    fun buildFrom(result : Result): Character{
        return Character(
            name = result.name,
            thumbnail = result.thumbnail
        )
    }
}