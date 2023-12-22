package com.example.keries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.keries.R
import androidx.viewpager2.widget.ViewPager2
import com.example.keries.adapter.ViewPagerAdapter
import com.example.keries.databinding.FragmentHomeBinding



class Home : Fragment() ,ExploreClickListener{
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewPager: ViewPager2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onExploreClick() {
        // Handle the explore click event, e.g., switch to hf2
        viewPager.setCurrentItem(1, true) // Assuming hf2 is at position 1 in your adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.view_pager)
        val fragmentClasses = listOf(hf1::class.java, hf2::class.java)
        viewPager.adapter = ViewPagerAdapter(this, fragmentClasses,this)
        // To change the direction of swipe to vertical
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

    }
}
