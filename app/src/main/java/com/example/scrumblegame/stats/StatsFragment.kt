package com.example.scrumblegame.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.scrumblegame.databinding.FragmentStatsBinding
import com.example.scrumblegame.di.ProvideViewModel
import com.example.scrumblegame.game.NavigateToGame
import com.example.scrumblegame.views.stats.StatsUiState

class StatsFragment : Fragment() {

    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: StatsViewModel =
            (requireActivity() as ProvideViewModel).makeViewModel(StatsViewModel::class.java)

        binding.newGameButton.setOnClickListener {
            viewModel.clear()
            (requireActivity() as NavigateToGame).navigateToGame()
        }

        val state: StatsUiState = viewModel.init(isFirstRun = savedInstanceState == null)
        binding.statsTextView.update(state)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}