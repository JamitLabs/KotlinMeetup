package com.jamitlabs.starfacepresentation.ui.message

import android.os.Bundle
import androidx.lifecycle.Observer
import com.jamitlabs.starfacepresentation.R
import com.jamitlabs.starfacepresentation.databinding.FragmentMessageBinding
import com.jamitlabs.starfacepresentation.ui.common.BaseFragment
import com.jamitlabs.starfacepresentation.util.snackError

class MessageFragment : BaseFragment<FragmentMessageBinding, MessageViewModel>() {

    override val layoutId = R.layout.fragment_message
    override val viewModelClass = MessageViewModel::class

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.events.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                when (it) {
                    is ShowError -> binding.root.snackError(it.message)
                }
            }
        })
    }
}
