package com.example.company.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val KEY = "key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val shared: SharedPreferences = this.getSharedPreferences("SHARED", Context.MODE_PRIVATE)
        shared.edit().putString(KEY, "").apply()
        save_button.setOnClickListener {
            shared.edit().putString(KEY, editText.text.toString()).apply()
            unsaved_changes_view.text = "All changes saved"
        }
        load_button.setOnClickListener {
            editText.setText(shared.getString(KEY, "").toString())
        }
        clear_button.setOnClickListener {
            editText.setText("")
            stats_view.setText("0")

        }
        var countWords = 0
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                var arrayWords = editText.text.toString().split("[^a-zA-Zа-яА-я0-9_]+".toRegex())
                if (arrayWords.size > 1) {
                    countWords = arrayWords.size - 1
                }
                stats_view.setText("$countWords")
                if (shared.getString(KEY, "").toString() == editText.text.toString()) unsaved_changes_view.text = "All changes saved"
                else unsaved_changes_view.text = "Unsaved changes"

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (shared.getString(KEY, "").toString() == editText.text.toString()) unsaved_changes_view.text = "All changes saved"
                else unsaved_changes_view.text = "Unsaved changes"

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })


        // Write your code here

    }
}
