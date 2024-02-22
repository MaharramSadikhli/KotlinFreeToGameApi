package com.example.kotlinfreegamelist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinfreegamelist.R
import com.example.kotlinfreegamelist.databinding.FragmentGameDetailBinding
import com.example.kotlinfreegamelist.util.loadImage
import com.example.kotlinfreegamelist.util.placeHolderProgressBar
import com.example.kotlinfreegamelist.viewmodel.GameDetailViewModel
import com.example.kotlinfreegamelist.viewmodel.GameFeedViewModel


class GameDetailFragment : Fragment() {

    private lateinit var binding: FragmentGameDetailBinding
    private lateinit var viewModel: GameDetailViewModel
    private var gameUuid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGameDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            gameUuid = GameDetailFragmentArgs.fromBundle(it).gameId
        }

        viewModel = ViewModelProvider(this)[GameDetailViewModel::class.java]
        viewModel.getGameDetail(gameUuid)

        observeLiveData()

    }

    private fun observeLiveData() {

        viewModel.gameDetail.observe(viewLifecycleOwner, Observer {
            game ->

            game?.let {
                binding.gameTitle.text = game.title
                binding.gameDescription.text = game.shortDescription
                binding.gameUrl.text = game.gameUrl
                binding.gameGenre.text = game.genre
                binding.gamePlatform.text = game.platform
                binding.gamePublisher.text = game.publisher
                binding.gameDeveloper.text = game.developer
                binding.gameReleaseDate.text = game.releaseDate

                context?.let {
                    binding.imageView.loadImage(game.thumbnail, placeHolderProgressBar(it))
                }
            }
        })

    }

}