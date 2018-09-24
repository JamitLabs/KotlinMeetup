package com.jamitlabs.starfacepresentation.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.jamitlabs.starfacepresentation.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private val viewModelFactory = MessageViewModelFactory()
    private lateinit var viewModel: MessageViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MessageViewModel::class.java)

        val mainFragmentBinding = FragmentMessageBinding.inflate(LayoutInflater.from(context), container, false)

        mainFragmentBinding.viewModel = viewModel

        return mainFragmentBinding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.scrollToBottom()
    }
}
