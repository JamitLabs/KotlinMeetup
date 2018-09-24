package com.jamitlabs.starfacepresentation.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jamitlabs.starfacepresentation.databinding.FragmentMessageBinding
import com.jamitlabs.starfacepresentation.util.snackError

class MessageFragment : Fragment() {

    private val viewModelFactory = MessageViewModelFactory()
    private lateinit var viewModel: MessageViewModel
    private lateinit var binding: FragmentMessageBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageBinding.inflate(LayoutInflater.from(context), container, false)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MessageViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.events.observe(this, Observer {
            it.getContentIfNotHandled().let {
                when (it) {
                    is ShowError -> binding.root.snackError(it.message)
                }
            }
        })

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.scrollToBottom()
    }
}
