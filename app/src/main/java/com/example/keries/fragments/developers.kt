package com.example.keries.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.keries.databinding.FragmentDevelopersBinding

class developers : Fragment() {
    private lateinit var binding: FragmentDevelopersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDevelopersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swiperefreshd.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swiperefreshd.isRefreshing = false
                playLottieAnimation()
            }, 2000L)
        }

        playLottieAnimation()
    }

    private fun playLottieAnimation() {
        binding.lottieAnimationView.visibility = View.VISIBLE
        binding.lottieAnimationView.playAnimation()

        val delayMillis = 3000L
        Handler(Looper.getMainLooper()).postDelayed({
            binding.lottieAnimationView.cancelAnimation()
            binding.lottieAnimationView.visibility = View.GONE
        }, delayMillis)
    }
}
