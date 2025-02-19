package com.example.scrumblegame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.scrumblegame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: GameViewModel = GameViewModel()

        binding.skipButton.setOnClickListener {
            val uiState: GameUiState = viewModel.skip()
            uiState.update(binding = binding)
        }
        binding.checkButton.setOnClickListener {
            val uiState: GameUiState = viewModel.check(text = binding.inputEditText.text.toString())
            uiState.update(binding = binding)
        }
        binding.nextButton.setOnClickListener {
            val uiState: GameUiState = viewModel.next()
            uiState.update(binding = binding)
        }
        binding.inputEditText.addTextChangedListener {
            val uiState: GameUiState = viewModel.handleUserInput(text = it.toString())
            uiState.update(binding = binding)
        }
    }
}