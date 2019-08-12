package com.example.translator.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.example.translator.R
import com.example.translator.yandexAPI.translatorAPI.TranslatorBackgroundTask
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity()
{
    companion object
    {
        @SuppressLint("StaticFieldLeak")
        lateinit var textView: TextView
        fun setText(string: String?)
        {
            textView.text = string
        }
    }

    //Set context
    var context: Context = this

    var textToBeTranslated: String? = null
    var languagePair: String? = null

    private val REQUEST_CODE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = translatedText_textView

        setListener()
    }



    private fun receiveLanguagePair(): String
    {
        return getLanguage(getTextLangFrom()) + "-" + getLanguage(getTextLangTo())
    }

    private fun getTextLangFrom():String = languageFromWhichTranslate.text.toString()
    private fun getTextLangTo(): String = languageToWhichTranslate.text.toString()
    private fun getTextToTranslate(): String = textForTranslation_editText.text.toString()

    private fun getLanguage(string: String): String?
    {
        when(string)
        {
            "Russian" -> return "ru"
            "English" -> return "en"
            "Greek" -> return "el"
            "Italian" -> return "it"
            "Korean" -> return "ko"
            "Japanese" -> return "ja"
            "French" -> return "fr"
            "Polish" -> return "pl"
            "Ukrainian" -> return "uk"
            "German" -> return "de"
        }
        return null
    }
    //Function for calling executing the Translator Background Task
    private fun translate(textToBeTranslated: String?, languagePair: String?)
    {
        val translatorBackgroundTask = TranslatorBackgroundTask(context)
        translatorBackgroundTask.execute(
            textToBeTranslated,
            languagePair
        )
    }
    private fun setListener()
    {
        //SET LISTENER FOR EDIT TEXT
        textForTranslation_editText.addTextChangedListener(object : TextWatcher
        {

            override fun afterTextChanged(s: Editable)
            {
                if(textForTranslation_editText.text.isEmpty())
                {
                    translatedText_textView.text = null
                }
            }

            override fun beforeTextChanged(s:  CharSequence, start:  Int,
                                           count:  Int, after:  Int) {}

            override fun onTextChanged(s:  CharSequence, start:  Int,
                                       before:  Int, count:  Int) {}
        })

        textForTranslation_editText.onFocusChangeListener = View.OnFocusChangeListener { _, _ ->
            if(textForTranslation_editText.text.toString() == resources.getString(R.string.primaryText))
            {
                textForTranslation_editText.text = null
            }
        }
        textForTranslation_editText.setOnEditorActionListener(object: TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean
            {
                if(actionId == EditorInfo.IME_ACTION_GO)
                {
                    textToBeTranslated = getTextToTranslate()
                    languagePair = receiveLanguagePair()
                    translate(textToBeTranslated, languagePair)
                    return true
                }
                return false
            }
        })

        //SET LISTENER FOR THE TEXT LANGUAGE BUTTON
        languageToWhichTranslate.setOnClickListener {
            val intent: Intent = Intent(this, ListActivity::class.java)
            intent.putExtra("sender", "langOfTheTranslation")
            startActivityForResult(intent, REQUEST_CODE)
        }

        //SET LISTENER FOR THE TRANSLATION LANGUAGE BUTTON
        languageFromWhichTranslate.setOnClickListener {
            val intent: Intent = Intent(this, ListActivity::class.java)
            intent.putExtra("sender", "langOfTheText")
            startActivityForResult(intent, REQUEST_CODE)
        }

        //SET LISTENER FOR BUTTON TO REVERSE LANGUAGE'S
        buttonReverse.setOnClickListener {
            val language: String = languageFromWhichTranslate.text.toString()
            languageFromWhichTranslate.text = languageToWhichTranslate.text
            languageToWhichTranslate.text = language
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        when(requestCode)
        {
            REQUEST_CODE -> {
                if (data != null)
                {
                    if(data.hasExtra("languageOfTheText"))
                    {
                        languageFromWhichTranslate.text = data.getStringExtra("languageOfTheText")
                    }
                    else if(data.hasExtra("languageOfTheTranslation"))
                    {
                        languageToWhichTranslate.text = data.getStringExtra("languageOfTheTranslation")
                    }
                }
            }
        }
    }

}
