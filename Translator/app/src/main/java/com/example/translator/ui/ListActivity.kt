package com.example.translator.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.translator.R
import com.example.translator.util.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity()
{
    var senderLeft: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val arguments: Bundle? = intent.extras
        when(arguments?.get("sender").toString()) {
            "langOfTheTranslation" -> {
                senderLeft = false
                titleText.text = "Язык текста"
            }
            "langOfTheText" -> {
                senderLeft = true
                titleText.text = "Язык перевода"
            }
        }

        backButton.setOnClickListener {
            finish()
        }
        val adapter = RecyclerViewAdapter(resources.getStringArray(R.array.languages))
        adapter.setOnClickListener(object: RecyclerViewAdapter.ClickListener {
            override fun onItemClick(string: String) {
                val intent: Intent = Intent()
                if(senderLeft) {
                    intent.putExtra("languageOfTheText", string)
                } else {
                    intent.putExtra("languageOfTheTranslation", string)
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        })
        listOfLanguages.adapter = adapter
    }
}
