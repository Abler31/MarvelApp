package ru.my.pet.characters

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.my.pet.R
import ru.my.pet.viewpager.ViewPagerFragmentDirections

class FirstFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { PagingAdapter() }
    private val viewModel by viewModels<CharactersViewModel>()
    private var currentText = ""
    private val handler = Handler(Looper.getMainLooper())
    //флаг прямой или обратной сортировки
    private var isAZButton = true
    //передача поискового вопроса
    private val searchRunnable = Runnable {
        viewModel.submitQuery(currentText)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        //recycler view with error/loading implementation
        recyclerView = view.rv_characters

        //обработка состояния при загрузке следующей части списка

        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = CharactersLoaderStatesAdapter(adapter),
            footer = CharactersLoaderStatesAdapter(adapter)
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        //обработка нажатия на элемент списка
        adapter.setOnItemClickListener(object: PagingAdapter.onItemClickListener{
            override fun onItemClick(id: Int?) {

                if (id != null) {
                    val directions = ViewPagerFragmentDirections.actionViewPagerFragmentToDetailedCharacter(id)
                    findNavController().navigate(directions)
                } else {
                    Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
                }

            }
        })

        //реагирование на состояние изначальной загрузки списка появлением необходимых элементов
        adapter.addLoadStateListener { state ->
            recyclerView.isVisible = state.refresh is LoadState.NotLoading
            view.shimmer_first.isVisible = state.refresh is LoadState.Loading
            view.button_retry_first.isVisible = state.refresh is LoadState.Error
        }

        view.button_retry_first.setOnClickListener {
            adapter.retry()
        }

        //побуквенно собираем поисковый запрос
        view.et_search.doAfterTextChanged {
            currentText = it?.toString() ?: ""

            handler.removeCallbacks(searchRunnable)
            handler.postDelayed(searchRunnable, 500L)
        }

        //вызов кастомного диалога снизу при нажатии на кнопку сортировки
        view.button_sort.setOnClickListener {
            val bottomSheetDialog = context?.let { it1 ->
                BottomSheetDialog(
                    it1, R.style.BottomSheetDialogTheme
                )
            }
            val bottomSheetView = LayoutInflater.from(context).inflate(
                R.layout.bottom_sheet_sorting_layout,
                view.findViewById(R.id.bottomSheet) as LinearLayout?
            )
            // TODO: Реализация сортировки
            //выбор сортировки в диалоге
            /*bottomSheetDialog?.findViewById<RadioGroup>(R.id.radio_group_sorting)?.clearCheck()
            bottomSheetDialog?.findViewById<RadioGroup>(R.id.radio_group_sorting)?.setOnCheckedChangeListener { radioGroup, i ->
                when(i) {
                    R.id.radio_AZ -> isAZButton = true
                    R.id.radio_ZA -> isAZButton = false
                }

            }*/
            /*val buttonId = bottomSheetDialog?.findViewById<RadioGroup>(R.id.radio_group_sorting)?.checkedRadioButtonId
            when(buttonId){
                R.id.radio_AZ -> isAZButton = true
                R.id.radio_ZA -> isAZButton  = false
            }*/

            bottomSheetDialog?.setContentView(bottomSheetView)
            bottomSheetDialog?.show()
        }
        //viewModel.radioButton(isAZButton)


        return view
    }

}