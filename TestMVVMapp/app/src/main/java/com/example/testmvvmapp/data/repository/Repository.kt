package com.example.testmvvmapp.data.repository

/*
class Repository(private val name: String, private  val owner: String, private val number: Int, private val isOwner: Boolean)
{
    fun getName(): String = name
    fun getOwner(): String = owner
    fun getNumber(): Int = number
    fun getIsOwner(): Boolean = isOwner
}*/
data class Repository(val repositoryName: String, val repositoryOwner: String, val numberOfStars: Int, val hasIssues: Boolean)
