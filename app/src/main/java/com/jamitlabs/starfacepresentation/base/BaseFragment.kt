package com.jamitlabs.starfacepresentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jamitlabs.starfacepresentation.BR
import com.jamitlabs.starfacepresentation.util.navigateTo
import com.jamitlabs.starfacepresentation.util.snack
import org.koin.android.viewmodel.ext.android.getViewModelByClass
import timber.log.Timber
import kotlin.reflect.KClass

abstract class BaseFragment<Binding : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    abstract val layoutId: Int
    abstract val viewModelClass: KClass<VM>

    open val variableId: Int = BR.viewModel

    protected lateinit var viewModel: VM
    protected lateinit var binding: Binding

    open fun onViewModelInitialised() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModelByClass(viewModelClass)
        onViewModelInitialised()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.setVariable(variableId, viewModel)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.events.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {

                Timber.i(it.toString())

                when(it) {
                    is ShowSnackbar -> (activity?.currentFocus ?: binding.root).snack(it)
                    is NavigateTo -> navigateTo(it)
                }
            }
        })
    }
}

