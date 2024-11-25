package com.example.exploringfragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.exploringfragments.databinding.FragmentABinding

class FragmentA : Fragment() {

    private lateinit var binding: FragmentABinding
    private val TAG = FragmentA::class.java.simpleName

    private val viewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.addText("$TAG onAttach")
        Log.d(TAG, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        viewModel.addText("$TAG onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentABinding.inflate(inflater)
        viewModel.addText("$TAG onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        viewModel.addText("$TAG onViewCreated")
        binding.pageTitle = TAG

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
        viewModel.addText("$TAG onActivityCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG, "onViewStateRestored")
        viewModel.addText("$TAG onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
        viewModel.addText("$TAG onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        viewModel.addText("$TAG onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        viewModel.addText("$TAG onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        viewModel.addText("$TAG onSaveInstanceState")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
        viewModel.addText("$TAG onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
        viewModel.addText("$TAG onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
        viewModel.addText("$TAG onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
        viewModel.addText("$TAG onDetach")
    }
}