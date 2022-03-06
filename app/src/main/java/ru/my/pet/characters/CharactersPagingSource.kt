package ru.my.pet.characters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.my.pet.model.CharactersDTO
import ru.my.pet.network.NetworkLayer
import ru.my.pet.model.Result
import ru.my.pet.network.SimpleResponse
import ru.my.pet.utils.CharactersMapper
import ru.my.pet.utils.Constants

class CharactersPagingSource(
    private val repository : CharactersRepository,
    private val userSearch : String,
) : PagingSource<Int, Character>() {

//При пустом поисковом запросе выводим всех персонажей,
// Либо ищем персонажей начинающихся с букв в поисковом запросе
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val offset = params.key ?: 0
        val pageSize = params.loadSize.coerceAtMost(Constants.limit.toInt())
        val charactersRequest : SimpleResponse<CharactersDTO>
        if (userSearch.isEmpty()){
            charactersRequest = NetworkLayer.apiClient.getCharacters(offset, "name")
        }else{
            charactersRequest = NetworkLayer.apiClient.getCharactersByName(offset, userSearch, "name")
        }

        charactersRequest.exception?.let {
            return LoadResult.Error(it)
        }
        val characters = charactersRequest.body.data.results.map { CharactersMapper.buildFrom(it) }
        return LoadResult.Page(
            data = characters,
            prevKey = if (offset == 0) null else offset - 20,
            nextKey = if (characters.size < pageSize) null else offset + 20
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(20) ?: anchorPage?.nextKey?.minus(20)
        }
    }
}