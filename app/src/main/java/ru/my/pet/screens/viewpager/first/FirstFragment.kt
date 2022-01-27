package ru.my.pet.screens.viewpager.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_first.view.*
import ru.my.pet.R
import ru.my.pet.repository.Repository

class FirstFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FirstAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        recyclerView = view.rv_first
        adapter = FirstAdapter()
        recyclerView.adapter = adapter

        val repository = Repository()
        val viewModelFactory = FirstViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(FirstViewModel::class.java)
        viewModel.getCharacters()
        viewModel.characterList.observe(viewLifecycleOwner, Observer { response ->
            if (response == null){
                Toast.makeText(context, "Проблема с сетью", Toast.LENGTH_SHORT).show()
            }else{
                adapter.setList(response)
            }
        })
        return view
    }
}