package com.example.kotlinfreegamelist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinfreegamelist.R
import com.example.kotlinfreegamelist.adapter.GameAdapter
import com.example.kotlinfreegamelist.databinding.FragmentGameFeedBinding
import com.example.kotlinfreegamelist.viewmodel.GameFeedViewModel

class GameFeedFragment : Fragment() {

    private lateinit var binding: FragmentGameFeedBinding
    private lateinit var viewModel: GameFeedViewModel
    private lateinit var gameAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGameFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[GameFeedViewModel::class.java]
        viewModel.refreshData()

        gameAdapter = GameAdapter(arrayListOf())
        binding.recyclerView.adapter = gameAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)


        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.recyclerView.visibility = View.GONE
            binding.errorText.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.refreshDataFromAPI()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    private fun observeLiveData() {

        viewModel.games.observe(viewLifecycleOwner, Observer {
            games ->

            games?.let {
                binding.recyclerView.visibility = View.VISIBLE
                gameAdapter.updateGameList(games)
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            loading ->

            loading?.let {
                if (it) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                } else {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
            }

        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            error ->

            error?.let {
                if (it) {
                    binding.errorText.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    binding.errorText.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
            }
        })

    }


}