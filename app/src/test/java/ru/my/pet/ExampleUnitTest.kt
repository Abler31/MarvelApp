package ru.my.pet

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import ru.my.pet.api.RetrofitInstance
import ru.my.pet.repository.Repository

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getCharacters(){

    }
}