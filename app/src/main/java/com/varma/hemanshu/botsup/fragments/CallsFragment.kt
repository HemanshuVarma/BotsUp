package com.varma.hemanshu.botsup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.varma.hemanshu.botsup.databinding.FragmentCallsBinding

/**
 *  Fragment for Calls Section
 */
class CallsFragment : Fragment() {

    private lateinit var binding: FragmentCallsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCallsBinding.inflate(inflater)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        //Playing animation only when the fragment is active
        binding.callsAnim.playAnimation()
    }

    override fun onPause() {
        super.onPause()

        //Stopping animation only when the fragment is inactive
        binding.callsAnim.pauseAnimation()
    }

    //Instance used by ViewPager to inflate this Fragment
    companion object {
        fun getInstance() = CallsFragment()
    }

}
