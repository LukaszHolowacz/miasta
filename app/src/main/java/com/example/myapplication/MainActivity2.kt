package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        findViewById<Button>(R.id.back).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Pobranie JSON jako String z Intent
        val landmarksJson = intent.getStringExtra("LANDMARKS_JSON")

        // Deserializacja JSON do listy obiektów Landmark
        val gson = Gson()
        val type = object : TypeToken<List<Landmark>>() {}.type
        val landmarksList: List<Landmark> = gson.fromJson(landmarksJson, type)

        // Ustawienie adaptera z listą zabytków
        recyclerView.adapter = MyAdapter(landmarksList)
    }
}


class MyAdapter(private val landmarksList: List<Landmark>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // ViewHolder wewnętrznej klasy, która przechowuje widoki elementów listy
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.ivItemPhoto)
        val nameTextView: TextView = view.findViewById(R.id.tvItemName)
        val descriptionTextView: TextView = view.findViewById(R.id.tvDescription)
    }

    // Tworzy nowe widoki (wywoływane przez menedżera układów)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Utwórz nowy widok
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(itemView)
    }

    // Zastępuje zawartość widoku (wywoływane przez menedżera układów)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - pobierz element z twojego zbioru danych w tej pozycji
        // - zastąp zawartość widoku tymi elementami
        val landmark = landmarksList[position]
        holder.imageView.setImageResource(landmark.imageResId)
        holder.nameTextView.text = landmark.name
        holder.descriptionTextView.text = landmark.description
    }

    // Zwróć rozmiar twojego zbioru danych (wywoływane przez menedżera układów)
    override fun getItemCount() = landmarksList.size
}

