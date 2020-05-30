package com.varma.hemanshu.botsup.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.varma.hemanshu.botsup.Constants
import com.varma.hemanshu.botsup.adapters.HomeViewPagerAdapter
import com.varma.hemanshu.botsup.databinding.FragmentHomeBinding

/**
 *  Fragment for Home Screen
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    //Callback when page changes
    private val homePageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            //Add implementation as per page change using position
            Log.i("HomeFragment", "Position is $position")
        }
    }


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

        //Setting up adapter with chats section as start position and attaching page change listener
        val homeViewPagerAdapter =
            HomeViewPagerAdapter(requireActivity(), Constants.VIEW_PAGER_ITEMS_COUNT)
        binding.homeViewpager.apply {
            adapter = homeViewPagerAdapter
            currentItem = Constants.DEFAULT_PAGE
            registerOnPageChangeCallback(homePageChangeCallback)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        //Removing pager callback when fragment is destroyed
        binding.homeViewpager.unregisterOnPageChangeCallback(homePageChangeCallback)
    }
}