package com.varma.hemanshu.botsup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.varma.hemanshu.botsup.R
import kotlinx.android.synthetic.main.fragment_camera.*
import timber.log.Timber

/**
 *  Fragment for Camera Section
 */
class CameraFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onResume() {
        super.onResume()

        //Playing animation only when the fragment is active
        coming_soon_anim.playAnimation()
        Timber.i("Animation Resumed")
    }

    override fun onPause() {
        super.onPause()

        //Stopping animation only when the fragment is inactive
        coming_soon_anim.pauseAnimation()
        Timber.i("Animation Paused")
    }

    //Instance used by ViewPager to inflate this Fragment
    companion object {
        fun getInstance() = CameraFragment()
    }

}
