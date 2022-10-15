package com.example.bitfit_pt2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView

class AddEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_entry)

        fun closeKeyboard() {
            val view = this.currentFocus
            if(view != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }

        var diaryTitleEmpty: Boolean
        var diaryEntryEmpty: Boolean
        val diaryTitleEntry: TextView = findViewById(R.id.DiaryTitleEntry)
        val diaryEntry: TextView = findViewById(R.id.DiaryEntry)
        val saveButton: Button = findViewById(R.id.saveButton)

        class InputTextWatcher: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                diaryTitleEmpty = diaryTitleEntry.text.toString().isEmpty()
                diaryEntryEmpty = diaryEntry.text.toString().isEmpty()
                saveButton.isEnabled = !diaryTitleEmpty && !diaryEntryEmpty
            }

        }

        diaryTitleEntry.addTextChangedListener(InputTextWatcher())
        diaryEntry.addTextChangedListener(InputTextWatcher())
    }
}