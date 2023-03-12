package com.example.doesitfly.presentation.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doesitfly.databinding.ListCardDetailBinding
import com.example.doesitfly.domain.FlyingSiteBean

class ListCardAdapter(
    private var flyingSiteList: List<FlyingSiteBean?>?
)
    : RecyclerView.Adapter<ListCardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListCardDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flyingSite = flyingSiteList?.get(position)
        holder.bind(flyingSite)
    }

    override fun getItemCount(): Int {
        return flyingSiteList?.size ?: 0
    }

//    fun updateData(newData: List<FlyingSiteBean?>?) {
//        flyingSiteList = newData
//        notifyDataSetChanged()
//    }

    inner class ViewHolder(private val binding: ListCardDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(flyingSite: FlyingSiteBean?) {
            // Bind data to view
            binding.textViewSiteName.text = flyingSite?.nom ?: ""
            binding.textViewCity.text = ( flyingSite?.ville + " " + flyingSite?.cp)
            binding.textViewWind.text = ("Vent favorable : " + flyingSite?.vent_favo)
        }
    }
}