package com.example.testapplication

import android.content.Context
import android.os.Bundle
import android.support.v4.content.AsyncTaskLoader
import android.util.Log
import java.util.*

class RandomLoader(context: Context, args: Bundle?) : AsyncTaskLoader<String>(context)
{
    val TAG = javaClass.simpleName
    private var mWord: String? = null

    init {
        if(args != null)
        {
            this.mWord = args.getString(ARG_WORD)
        }
    }

    companion object {
        const val ARG_WORD = "word"
        const val RANDOM_STRING_LENGTH = 10
    }
    override fun loadInBackground(): String?
    {
        if(mWord == null)
        {
            return null
        }
        Log.d(TAG, "loadInBackground")
        return generateString(mWord!!)
    }

    override fun forceLoad()
    {
        super.forceLoad()
        Log.d(TAG, "forceLoad")
    }

    override fun onStartLoading()
    {
        super.onStartLoading()
        Log.d(TAG, "onStartLoading")
        forceLoad()
    }

    override fun onStopLoading()
    {
        super.onStopLoading()
        Log.d(TAG, "onStopLoading")
    }

    override fun deliverResult(data: String?)
    {
        super.deliverResult(data)
        Log.d(TAG, "deliverResult")
    }

    private fun generateString(characters: String): String
    {
        val rand = Random()
        val text = CharArray(RANDOM_STRING_LENGTH)
        for (i in 0 until RANDOM_STRING_LENGTH) {
            text[i] = characters[rand.nextInt(characters.length)]
        }
        return String(text)
    }

}
