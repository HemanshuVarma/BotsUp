package com.varma.hemanshu.botsup.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikhaellopez.circularimageview.CircularImageView
import com.varma.hemanshu.botsup.R
import com.varma.hemanshu.botsup.data.Chat

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    var data = listOf<Chat>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val chatProfile: CircularImageView = itemView.findViewById(R.id.chat_profile)
        private val chatName: TextView = itemView.findViewById(R.id.chat_name)
        private val chatMessage: TextView = itemView.findViewById(R.id.chat_message)

        fun bind(item: Chat) {

            val profile = item.profile
            chatProfile.setImageResource(profile)
            chatName.text = item.name
            chatMessage.text = item.message
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.chat_item_layout, parent, false)
                return ViewHolder(view)
            }
        }
    }
}