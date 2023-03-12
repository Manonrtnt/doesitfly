package com.example.doesitfly.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.doesitfly.R
import com.example.doesitfly.databinding.FragmentListBinding
import com.example.doesitfly.presentation.view.adapter.ListCardAdapter
import com.example.doesitfly.presentation.viewModel.ListFlyingSiteViewModel
import kotlin.concurrent.thread

class ListFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this)[ListFlyingSiteViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        thread {
            viewModel.loadData()
        }

        viewModel.data.observe(viewLifecycleOwner) {
            val listRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewList)
            listRecyclerView.adapter = ListCardAdapter(viewModel.data.value ?: emptyList())

        }
        viewModel.runInProgress.observe(viewLifecycleOwner) {
            view.findViewById<ProgressBar>(R.id.progressBar).isVisible = it
        }

        val searchView: SearchView = view.findViewById(R.id.searchViewList)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Logique à exécuter lors du changement de texte dans la recherche
                val searchQuery = newText ?: ""

                viewModel.sortDataWithSearchResult(viewModel.data, searchQuery)

                viewModel.filteredData.observe(viewLifecycleOwner) {
                    val listRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewList)
                    listRecyclerView.adapter = ListCardAdapter(viewModel.filteredData.value ?: emptyList())
                }
                return false
            }
        })

        return view
    }
}