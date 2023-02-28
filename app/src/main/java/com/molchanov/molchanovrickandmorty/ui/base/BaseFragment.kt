package com.molchanov.molchanovrickandmorty.ui.base

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected var _binding: T? = null

    abstract val binding: T
}