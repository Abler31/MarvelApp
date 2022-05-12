package ru.my.pet.characters.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detailed_character.view.*
import ru.my.pet.R
import ru.my.pet.model.CharactersDTO


class CharacterDetailFragment : Fragment() {
    private val viewModel: CharacterDetailViewModel by viewModels()
    private val safeArgs : CharacterDetailFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailed_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.characterByIdLiveData.observe(viewLifecycleOwner){ character ->
            if (character == null) {
                Toast.makeText(
                    requireActivity(),
                    "Unsuccessful network call!",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigateUp()
                return@observe
            }
            bind(character, view)
        }

        val characterId = safeArgs.characterId
        viewModel.fetchCharacter(characterId = characterId)

    }

    private fun bind(character : CharactersDTO, view: View){
        val name = view.tv_detail_name
        val describtion = view.tv_detail_description
        val image = view.iv_character_detail
        name.text = character.data.results[0].name
        if (character.data.results[0].description.isNotEmpty()){
            describtion.text = character.data.results[0].description
        } else {
            describtion.text = "Описание отсутствует"
        }

        Glide.with(image)
            .load("${character.data.results[0].thumbnail.path}/portrait_medium.jpg")
            .placeholder(R.drawable.placeholder_character)
            .into(image)

    }
}