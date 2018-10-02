package com.jamitlabs.starfacepresentation.ui.message.overview

import com.jamitlabs.starfacepresentation.R
import com.jamitlabs.starfacepresentation.databinding.FragmentMessageBinding
import com.jamitlabs.starfacepresentation.base.BaseFragment

class MessageFragment : BaseFragment<FragmentMessageBinding, MessageViewModel>() {

    override val layoutId = R.layout.fragment_message
    override val viewModelClass = MessageViewModel::class

    override fun onStart() {
        super.onStart()
        viewModel.scrollToBottom()
    }

}
