package com.marta.ud5_03_fragments_martamolina

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.marta.ud5_03_fragments_martamolina.model.User

class App : Application() {
    val userList: MutableList<User> = mutableListOf()
}

val AppCompatActivity.app: App
    get() = this.application as App

val Fragment.userList
    get() = (activity?.application as? App)?.userList