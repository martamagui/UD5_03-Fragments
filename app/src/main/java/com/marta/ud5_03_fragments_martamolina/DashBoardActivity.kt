package com.marta.ud5_03_fragments_martamolina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marta.ud5_03_fragments_martamolina.databinding.ActivityDashboardBinding
import com.marta.ud5_03_fragments_martamolina.databinding.ItemUserBinding

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }
}