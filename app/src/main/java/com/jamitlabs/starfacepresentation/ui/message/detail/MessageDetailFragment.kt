package com.jamitlabs.starfacepresentation.ui.message.detail

import com.jamitlabs.starfacepresentation.R
import com.jamitlabs.starfacepresentation.databinding.FragmentMessageBinding
import com.jamitlabs.starfacepresentation.base.BaseFragment
import com.jamitlabs.starfacepresentation.model.Message

class MessageDetailFragment : BaseFragment<FragmentMessageBinding, MessageDetailViewModel>() {

    companion object {
        const val KEY_MESSAGE = "key_message"
    }

    override val layoutId = R.layout.fragment_message_detail
    override val viewModelClass = MessageDetailViewModel::class

    override fun onViewModelInitialised() {
        super.onViewModelInitialised()

        arguments?.getParcelable<Message>(KEY_MESSAGE)?.let { viewModel.displayMessage(it) }
    }
}

