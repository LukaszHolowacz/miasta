package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private val landmarksMap = mapOf(
        "Gdynia" to listOf(
            Landmark("Skwer Kościuszki", "Opis Skweru Kościuszki", R.drawable.skwer),
            Landmark("Statek 'Dar Pomorza'", "Opis Statku 'Dar Pomorza'", R.drawable.dar_pomorza),
            Landmark("Klif Orłowski", "Opis Klifu Orłowskiego", R.drawable.klif)
        ),
        "Gdansk" to listOf(
            Landmark("Bazylika Mariacka", "Opis Bazyliki Mariackiej", R.drawable.bazylika_mariacka),
            Landmark("Długi Targ", "Opis Długiego Targu", R.drawable.dlugi_targ),
            Landmark("Żuraw", "Opis Żurawia", R.drawable.zuraw)
        ),
        "Sopot" to listOf(
            Landmark("Molo w Sopocie", "Opis Mola", R.drawable.molo),
            Landmark("Krzywy Domek", "Opis Krzywego Domku", R.drawable.krzywy_domek),
            Landmark("Opera Leśna", "Opis Opery Leśnej", R.drawable.opera)
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gdynia_text = findViewById<TextView>(R.id.gdynia)
        val gdansk_text = findViewById<TextView>(R.id.gdansk)
        val sopot_text = findViewById<TextView>(R.id.sopot)

        gdynia_text.setOnClickListener {
            startActivityWithCityName("Gdynia")
        }

        gdansk_text.setOnClickListener {
            startActivityWithCityName("Gdansk")
        }

        sopot_text.setOnClickListener {
            startActivityWithCityName("Sopot")
        }
    }

    private fun startActivityWithCityName(cityName: String) {
        val gson = Gson()
        val landmarks = landmarksMap[cityName]
        val landmarksJson = gson.toJson(landmarks)
        val intent = Intent(this, MainActivity2::class.java).apply {
            putExtra("CITY_NAME", cityName)
            putExtra("LANDMARKS_JSON", landmarksJson)
        }
        startActivity(intent)
    }
}

data class Landmark(val name: String, val description: String, val imageResId: Int)

