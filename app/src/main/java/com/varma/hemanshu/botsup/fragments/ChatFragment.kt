package com.varma.hemanshu.botsup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.varma.hemanshu.botsup.adapters.ChatAdapter
import com.varma.hemanshu.botsup.databinding.FragmentChatBinding
import com.varma.hemanshu.botsup.viewmodels.ChatViewModel
import timber.log.Timber

/**
 *  Fragment for Chats Section
 */
class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChatBinding.inflate(inflater)

        //Coupling viewModel
        binding.chatViewModel = viewModel

        //Setting the adapter
        val adapter = ChatAdapter()
        binding.chatList.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
                Timber.i("List submitted is $it")
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    //Instance used by ViewPager to inflate this Fragment
    companion object {
        fun getInstance() = ChatFragment()
    }

}
