package com.example.taipeitravelattractions.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
    private var pagingAdapter = AttractionsAdapter()

    companion object {
        fun newInstance() = MainFragment()
    }

    // TODO: handle offline, offline->online should automatically refresh RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        _binding?.let { b ->
            b.recyclerView.adapter = pagingAdapter
            b.swipeRefreshLayout.setOnRefreshListener {
                b.swipeRefreshLayout.isRefreshing = false
                Log.d("MainFragment", "${pagingAdapter.itemCount}")
                if (pagingAdapter.itemCount == 0) {
                    Log.d("MainFragment", "Reloading data...")
                    mainViewModel.getAttractions()  // reload data
                    // recollect data to RecyclerView
                    viewLifecycleOwner.lifecycleScope.launch {
                        mainViewModel.attractions.collectLatest {
                            pagingAdapter.submitData(it)
                        }
                    }
                }
            }
        }
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