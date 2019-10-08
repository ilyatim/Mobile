package com.example.testapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String>
{
    val TAG = javaClass.simpleName
    private var mLoader: Loader<String>? = null

    companion object {
        const val LOADER_ID = 1
    }

    override fun onLoaderReset(loader: Loader<String>)
    {
        Log.d(TAG, "onLoaderReset")
    }

    override fun onLoadFinished(loader: Loader<String>, data: String)
    {
        Log.d(TAG, "onLoadFinished")
        textView.text = data

        // Если используется несколько загрузчиков, то удобнее через оператор switch-case
        //        switch (loader.getId()) {
        //            case LOADER_ID:
        //                // Данные загружены и готовы к использованию
        //
        //                break;
        //        }
        // список теперь содержит данные на экране
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<String>
    {
        lateinit var mLoader: Loader<String>
        if(p0 == LOADER_ID)
        {
            mLoader = RandomLoader(this, p1)
            Log.d(TAG, "onCreateLoader")
        }
        return mLoader
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle: Bundle = Bundle()
        bundle.putString(RandomLoader.ARG_WORD, "test")
        mLoader = supportLoaderManager.initLoader(LOADER_ID, bundle, this)
    }
    fun onClick(view: View)
    {
        Log.d(TAG, "startLoad")
        mLoader?.onContentChanged()
    }
}
