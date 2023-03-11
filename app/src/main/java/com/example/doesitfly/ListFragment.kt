package com.example.doesitfly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.doesitfly.adapter.ListCardAdapter
import com.example.doesitfly.model.FlyingSiteBean

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val tempList : List<FlyingSiteBean> = emptyList()

        val listRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewList)
        listRecyclerView.adapter = ListCardAdapter(tempList)
        return view
    }

}