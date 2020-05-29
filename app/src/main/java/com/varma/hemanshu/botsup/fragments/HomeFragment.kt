package com.varma.hemanshu.botsup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.varma.hemanshu.botsup.Constants
import com.varma.hemanshu.botsup.adapters.HomeViewPagerAdapter
import com.varma.hemanshu.botsup.databinding.FragmentHomeBinding

/**
 *  Fragment for Home Screen
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Bind and Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeViewPagerAdapter =
            HomeViewPagerAdapter(requireActivity(), Constants.VIEW_PAGER_ITEMS_COUNT)
        binding.homeViewpager.adapter = homeViewPagerAdapter

    }

}