package ru.my.pet.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.my.pet.R
import ru.my.pet.model.Result

class FirstAdapter : PagingDataAdapter<Character, FirstAdapter.CharacterViewHolder>(
    CharacterDiffCallback
) {

    class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view){
        val characterName : TextView = view.findViewById(R.id.tv_name)
        val characterThumbnail : ImageView = view.findViewById(R.id.iv_thumbnail)
        fun bind(data : Character?){
            characterName.text = data?.name

            Glide.with(characterThumbnail)
                .load("${data?.thumbnail?.path}/portrait_medium.jpg")
                .placeholder(R.drawable.placeholder_character)
                .into(characterThumbnail)
        }
    }

    object CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name && oldItem.thumbnail.path == newItem.thumbnail.path
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fragment_first, parent, false)
        return CharacterViewHolder(view)
    }

}