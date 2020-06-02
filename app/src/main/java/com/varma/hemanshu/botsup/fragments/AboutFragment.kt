package com.varma.hemanshu.botsup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.varma.hemanshu.botsup.BuildConfig
import com.varma.hemanshu.botsup.R
import kotlinx.android.synthetic.main.fragment_about.*

/**
 *  Fragment for About Section
 */
class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Setting App version at runtime
        about_version_text_view.text =
            resources.getString(R.string.app_version, BuildConfig.VERSION_NAME)
    }

}
