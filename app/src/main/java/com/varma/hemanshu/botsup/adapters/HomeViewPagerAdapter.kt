package com.varma.hemanshu.botsup.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.varma.hemanshu.botsup.fragments.CallsFragment
import com.varma.hemanshu.botsup.fragments.CameraFragment
import com.varma.hemanshu.botsup.fragments.ChatFragment
import com.varma.hemanshu.botsup.fragments.StatusFragment

/**
 *  [HomeViewPagerAdapter] for ViewPager to switch thorough all fragments with simple swipe gesture
 *  @param activity To host the Adapter
 *  @param totalItemCount Number of Fragments to inflate
 *  FragmentStateAdapter handles the lifecycle of Fragment along with RecyclerViewAdapter
 *  @author Hemanshu Varma
 */
class HomeViewPagerAdapter(activity: FragmentActivity, private val totalItemCount: Int) :
    FragmentStateAdapter(activity) {

    //Returns count of Fragments
    override fun getItemCount(): Int {
        return totalItemCount
    }

    //Returns the instance of Fragment to be inflated
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CameraFragment.getInstance()
            1 -> ChatFragment.getInstance()
            2 -> StatusFragment.getInstance()
            else -> CallsFragment.getInstance()
        }
    }
}