package com.marta.ud5_03_fragments_martamolina

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.marta.ud5_03_fragments_martamolina.model.User

class App : Application() {
    val userList: List<User> = listOf()
}
val AppCompatActivity.app: App
    get() = this.application as App