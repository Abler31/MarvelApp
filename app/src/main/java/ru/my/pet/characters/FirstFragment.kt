package ru.my.pet.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
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
        recyclerView = view.rv_first

        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = CharactersLoaderStatesAdapter(adapter),
            footer = CharactersLoaderStatesAdapter(adapter)
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
        return view
    }


}