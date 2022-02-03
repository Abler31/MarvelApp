package ru.my.pet.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.my.pet.R

class FirstFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    val adapter by lazy { FirstAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        val viewModel by viewModels<CharactersViewModel>()
        recyclerView = view.rv_characters

        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = CharactersLoaderStatesAdapter(adapter),
            footer = CharactersLoaderStatesAdapter(adapter)
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        adapter.addLoadStateListener { state ->
            recyclerView.isVisible = state.refresh is LoadState.NotLoading
            view.progress_bar_first.isVisible = state.refresh is LoadState.Loading
            view.button_retry_first.isVisible = state.refresh is LoadState.Error
        }

        view.button_retry_first.setOnClickListener {
            adapter.retry()
        }
        return view
    }


}