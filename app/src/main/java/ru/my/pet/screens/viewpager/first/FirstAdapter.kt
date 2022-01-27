package ru.my.pet.screens.viewpager.first

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_fragment_first.view.*
import ru.my.pet.R
import ru.my.pet.model.CharactersDTO
import ru.my.pet.model.Result

class FirstAdapter : RecyclerView.Adapter<FirstAdapter.FirstViewHolder>() {
    var itemsList = emptyList<Result>()

    class FirstViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fragment_first, parent, false)
        return FirstViewHolder(view)
    }

    override fun onBindViewHolder(holder: FirstViewHolder, position: Int) {
        val currentItem = itemsList[position]
        holder.itemView.first_item_name.text = currentItem.name
        Glide.with(holder.itemView.iv_item).load("${currentItem.thumbnail.path}/portrait_medium.jpg").into(holder.itemView.iv_item)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun setList(list: List<Result>){
        itemsList = list
        notifyDataSetChanged()
    }


}