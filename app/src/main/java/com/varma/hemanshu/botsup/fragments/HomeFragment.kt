package com.varma.hemanshu.botsup.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.varma.hemanshu.botsup.Constants
import com.varma.hemanshu.botsup.R
import com.varma.hemanshu.botsup.adapters.HomeViewPagerAdapter
import com.varma.hemanshu.botsup.databinding.FragmentHomeBinding
import timber.log.Timber

/**
 *  Fragment for Home Screen
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    //Callback when page changes
    private val homePageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            //Add implementation as per page change using position
            Timber.i("Position is $position")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Bind and Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)

        //Setting Overflow menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Reference to ViewPager
        val homeViewPagerAdapter =
            HomeViewPagerAdapter(requireActivity(), Constants.VIEW_PAGER_ITEMS_COUNT)
        binding.homeViewpager.apply {

            //Setting up adapter
            adapter = homeViewPagerAdapter

            //Setting Chat screen as Default screen
            currentItem = Constants.DEFAULT_PAGE

            //Attaching page change callback
            registerOnPageChangeCallback(homePageChangeCallback)

            //Not saving state of ViewPager Fragment
            isSaveEnabled = false

            //Binding ViewPager with TabLayout to set names/icon
            TabLayoutMediator(binding.homeTabLayout, binding.homeViewpager) { tab, position ->
                tab.text = resources.getStringArray(R.array.homepage_screen_names)[position]
            }.attach()
        }
    }

    //Inflating overflow menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_chat, menu)
    }

    //Handle when overflow item is clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()

        //Removing pager callback when fragment is destroyed
        binding.homeViewpager.unregisterOnPageChangeCallback(homePageChangeCallback)
    }
}