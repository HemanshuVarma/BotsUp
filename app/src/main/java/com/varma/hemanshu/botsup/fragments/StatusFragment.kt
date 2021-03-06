package com.varma.hemanshu.botsup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.varma.hemanshu.botsup.R

/**
 *  Fragment for Status Section
 */
class StatusFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false)
    }

    //Instance used by ViewPager to inflate this Fragment
    companion object {
        fun getInstance() = StatusFragment()
    }

}
