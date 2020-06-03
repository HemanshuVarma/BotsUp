package com.varma.hemanshu.botsup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.varma.hemanshu.botsup.Constants
import com.varma.hemanshu.botsup.databinding.FragmentCameraBinding
import com.varma.hemanshu.botsup.utils.FirebaseUtils

/**
 *  Fragment for Camera Section
 */
class CameraFragment : Fragment() {

    private lateinit var binding: FragmentCameraBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCameraBinding.inflate(inflater)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.notifyButton.setOnClickListener {
            FirebaseUtils.subscribeTopic(requireContext(), Constants.TOPIC)
        }
    }

    override fun onResume() {
        super.onResume()

        //Playing animation only when the fragment is active
        binding.comingSoonAnim.playAnimation()
    }

    override fun onPause() {
        super.onPause()

        //Stopping animation only when the fragment is inactive
        binding.comingSoonAnim.pauseAnimation()
    }

    //Instance used by ViewPager to inflate this Fragment
    companion object {
        fun getInstance() = CameraFragment()
    }

}
