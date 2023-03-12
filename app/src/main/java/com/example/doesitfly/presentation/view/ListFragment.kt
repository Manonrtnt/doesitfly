package com.example.doesitfly.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.doesitfly.R
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

        // Load data from ViewModel
        thread {
            viewModel.loadData()
        }

        // Set up RecyclerView adapter with the data from ViewModel
        viewModel.data.observe(viewLifecycleOwner) { data ->
            val listRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewList)
            listRecyclerView.adapter = ListCardAdapter(data ?: emptyList())
        }

        // Show progress bar if data is being loaded
        viewModel.runInProgress.observe(viewLifecycleOwner) { inProgress ->
            view.findViewById<ProgressBar>(R.id.progressBar).isVisible = inProgress
        }

        // Set up search functionality with the data from ViewModel
        val searchView: SearchView = view.findViewById(R.id.searchViewList)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Get the search query text and sort the data accordingly
                val searchQuery = newText ?: ""
                viewModel.sortDataWithSearchResult(viewModel.data, searchQuery)

                // Update the RecyclerView adapter with filtered data
                viewModel.filteredData.observe(viewLifecycleOwner) { filteredData ->
                    val listRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewList)
                    listRecyclerView.adapter = ListCardAdapter(filteredData ?: emptyList())
                }
                return false
            }
        })

        val messageInformation = view.findViewById<TextView>(R.id.textViewMessageInformation)
        val listRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewList)

        // Error message
        viewModel.errorMessage.observe(viewLifecycleOwner){ errorMessage ->
            if (errorMessage != "") {
                messageInformation.text = errorMessage
                messageInformation.visibility = View.VISIBLE
                listRecyclerView.visibility = View.GONE
            } else {
                messageInformation.visibility = View.GONE
                listRecyclerView.visibility = View.VISIBLE
            }
        }

        // No data for search constraint
        viewModel.noDataMessage.observe(viewLifecycleOwner){ noDataMessage ->
            if (noDataMessage != ""){
                messageInformation.text = noDataMessage
                messageInformation.visibility = View.VISIBLE
                listRecyclerView.visibility = View.GONE
            } else {
                messageInformation.visibility = View.GONE
                listRecyclerView.visibility = View.VISIBLE
            }
        }

        return view
    }
}