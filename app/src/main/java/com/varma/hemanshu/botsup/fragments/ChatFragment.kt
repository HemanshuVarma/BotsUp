package com.varma.hemanshu.botsup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.varma.hemanshu.botsup.R
import com.varma.hemanshu.botsup.adapters.ChatAdapter
import com.varma.hemanshu.botsup.data.Chat
import com.varma.hemanshu.botsup.databinding.FragmentChatBinding
import timber.log.Timber

/**
 *  Fragment for Chats Section
 */
class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChatBinding.inflate(inflater)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Setting the adapter
        val adapter = ChatAdapter()
        binding.chatList.adapter = adapter
        val items = createDataSet()
        adapter.data = items
        Timber.i("List submitted is $items")

        //Setting click listener on FAB
        binding.newChat.setOnClickListener {
            Toast.makeText(context, getString(R.string.new_chat), Toast.LENGTH_SHORT).show()
        }
    }

    //Instance used by ViewPager to inflate this Fragment
    companion object {
        fun getInstance() = ChatFragment()

        fun createDataSet(): ArrayList<Chat> {
            val list = ArrayList<Chat>()

            //Adding dummy data
            list.add(Chat(R.drawable.profile, "Hemanshu Varma", "Hey There!"))
            list.add(Chat(R.drawable.profile, "Hemanshu Varma", "Hey There!"))
            list.add(Chat(R.drawable.profile, "Hemanshu Varma", "Hey There!"))
            list.add(Chat(R.drawable.profile, "Hemanshu Varma", "Hey There!"))
            list.add(Chat(R.drawable.profile, "Hemanshu Varma", "Hey There!"))
            list.add(Chat(R.drawable.profile, "Hemanshu Varma", "Hey There!"))
            list.add(Chat(R.drawable.profile, "Hemanshu Varma", "Hey There!"))
            list.add(Chat(R.drawable.profile, "Hemanshu Varma", "Hey There!"))
            list.add(Chat(R.drawable.profile, "Hemanshu Varma", "Hey There!"))
            return list
        }
    }

}
