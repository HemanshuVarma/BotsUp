package com.varma.hemanshu.botsup.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.varma.hemanshu.botsup.R
import com.varma.hemanshu.botsup.data.Chat
import timber.log.Timber

/**
 *  A ViewModel, [ChatViewModel] for Chat items.
 */
class ChatViewModel : ViewModel() {

    private val _items = MutableLiveData<List<Chat>>()

    val items: LiveData<List<Chat>> get() = _items

    private val list = mutableListOf<Chat>()

    //Counter for number of items in list
    private var count = 0

    init {
        Timber.i("ViewModel created")
    }

    //Invoked when FAB is clicked. Method which increments the item count by one and adds to list
    fun addItemToList() {
        count++
        for (i in count until count + 1) {
            list.add(Chat(R.drawable.ic_user, "User name $i", "Placeholder message: $i"))
        }
        _items.value = list
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("viewModel cleared")
    }
}