package com.example.testcoursework.utils.googleAccount

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

object GoogleAccount
{
    private var instance: GoogleSignInAccount? = null
    fun getLastSignInAccount(context: Context?): GoogleSignInAccount?
    {
        if(instance == null)
        {
            instance = GoogleSignIn.getLastSignedInAccount(context)
        }
        return instance
    }
}
