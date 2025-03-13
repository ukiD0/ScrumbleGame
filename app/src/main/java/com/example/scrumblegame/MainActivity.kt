package com.example.scrumblegame

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.scrumblegame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var uiState: GameUiState
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GameViewModel

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            uiState = viewModel.handleUserInput(text = s.toString())
            uiState.update(binding = binding)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as UnscrambleApp).viewModel

        binding.nextButton.setOnClickListener {
            uiState = viewModel.next()
            uiState.update(binding = binding)
        }

        binding.checkButton.setOnClickListener {
            uiState = viewModel.check(text = binding.inputEditText.text.toString())
            uiState.update(binding = binding)
        }

        binding.skipButton.setOnClickListener {
            uiState = viewModel.skip()
            uiState.update(binding = binding)
        }

        uiState = if (savedInstanceState == null)
            viewModel.init()
        else
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                savedInstanceState.getSerializable(KEY, GameUiState::class.java) as GameUiState
            else
                savedInstanceState.getSerializable(KEY) as GameUiState

        uiState.update(binding = binding)
    }

    override fun onResume() {
        super.onResume()
        binding.inputEditText.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.inputEditText.removeTextChangedListener(textWatcher)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY, uiState)
    }

    companion object {
        private const val KEY = "uiState"
    }
}