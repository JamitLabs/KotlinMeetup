package com.jamitlabs.starfacepresentation.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.jamitlabs.starfacepresentation.BR
import org.koin.android.viewmodel.ext.android.getViewModelByClass
import kotlin.reflect.KClass

abstract class BaseFragment<Binding : ViewDataBinding, VM : ViewModel> : Fragment() {

    abstract val layoutId: Int
    abstract val viewModelClass: KClass<VM>

    open val variableId: Int = BR.viewModel

    protected lateinit var viewModel: VM
    protected lateinit var binding: Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModelByClass(viewModelClass)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.setVariable(variableId, viewModel)

        return binding.root
    }
}

