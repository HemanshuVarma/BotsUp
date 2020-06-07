package com.varma.hemanshu.botsup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.varma.hemanshu.botsup.Constants
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

        //Setting empty view, if true
        if (adapter.data.size == Constants.NO_ITEMS) {
            binding.apply {
                emptyViewContainer.isVisible = true
                chatList.isGone = true
            }
        } else {
            binding.apply {
                emptyViewContainer.isGone = true
                chatList.isVisible = true
            }
        }

        viewModel.items.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
                Timber.i("List submitted is $it")
                binding.apply {
                    chatList.isVisible = true
                    emptyViewContainer.isGone = true
                }
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
