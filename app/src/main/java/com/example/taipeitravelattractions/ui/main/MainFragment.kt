package com.example.taipeitravelattractions.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.taipeitravelattractions.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!  // This property is only valid between onCreateView and onDestroyView
    private val mainViewModel: MainViewModel by viewModels { MainViewModel.Factory }
    private val pagingAdapter = AttractionsAdapter()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        _binding?.let { it.recyclerView.adapter = pagingAdapter }
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.attractions.collectLatest {
                pagingAdapter.submitData(it)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}