package com.example.doesitfly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.doesitfly.adapter.ListCardAdapter
import com.example.doesitfly.model.FlyingSiteBean
import com.example.doesitfly.viewModel.ListFlyingSiteViewModel

class ListFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this)[ListFlyingSiteViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        viewModel.loadData()

        val listRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewList)
        listRecyclerView.adapter = ListCardAdapter(viewModel.data.value ?: emptyList())
        return view
    }

}