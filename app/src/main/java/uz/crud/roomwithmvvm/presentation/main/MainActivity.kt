package uz.crud.roomwithmvvm.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.crud.roomwithmvvm.R
import uz.crud.roomwithmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerView, MainFragment())
            .commit()
    }
}