package ru.my.pet.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.my.pet.R
import ru.my.pet.databinding.LoadStateViewItemBinding

class CharactersLoaderStatesAdapter(
    private val adapter: PagingAdapter
) : LoadStateAdapter<CharactersLoaderStatesAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ItemViewHolder {
        return ItemViewHolder(
            LoadStateViewItemBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.load_state_view_item, parent, false)
            )
        ) { adapter.retry() }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class ItemViewHolder(
        private val binding: LoadStateViewItemBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }
        fun bind(loadState: LoadState){
            if (loadState is LoadState.Error){
                binding.errorMsg.text = loadState.error.localizedMessage
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
            binding.errorMsg.isVisible = loadState is LoadState.Error

        }
    }

}