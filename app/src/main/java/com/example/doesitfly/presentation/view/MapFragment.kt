package com.example.doesitfly.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.example.doesitfly.R
import com.example.doesitfly.presentation.viewModel.ListFlyingSiteViewModel

class MapFragment : Fragment() {
    private val viewModel by lazy { ViewModelProvider(this)[ListFlyingSiteViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        val animationView = view.findViewById<LottieAnimationView>(R.id.animation_wip)
        animationView.playAnimation()
        animationView.repeatCount = LottieDrawable.INFINITE

        return view
    }

}