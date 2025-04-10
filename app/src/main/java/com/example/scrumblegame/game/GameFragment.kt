package com.example.scrumblegame.game

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.scrumblegame.databinding.FragmentGameBinding
import com.example.scrumblegame.di.ProvideViewModel
import com.example.scrumblegame.stats.NavigateToStats

class GameFragment : Fragment() {
    private lateinit var uiState: GameUiState
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GameViewModel

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            uiState = viewModel.handleUserInput(text = s.toString())
            update.invoke()
        }
    }

    private val update: () -> Unit = {
        uiState.update(
            binding.shuffledWordTextView,
            binding.inputView,
            binding.skipButton,
            binding.checkButton,
            binding.nextButton
        )
        uiState.navigate(requireActivity() as NavigateToStats)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            (requireActivity() as ProvideViewModel).makeViewModel(GameViewModel::class.java)

        binding.nextButton.setOnClickListener {
            uiState = viewModel.next()
            update.invoke()
        }

        binding.checkButton.setOnClickListener {
            uiState = viewModel.check(text = binding.inputView.text())
            update.invoke()
        }

        binding.skipButton.setOnClickListener {
            uiState = viewModel.skip()
            update.invoke()
        }

        uiState = viewModel.init(savedInstanceState == null)

        update.invoke()
    }

    override fun onResume() {
        super.onResume()
        binding.inputView.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.inputView.removeTextChangedListener(textWatcher)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}