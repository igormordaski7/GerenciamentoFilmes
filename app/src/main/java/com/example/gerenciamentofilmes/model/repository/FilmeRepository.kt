package com.example.gerenciamentofilmes.model.repository

import android.content.Context
import com.example.gerenciamentofilmes.model.entity.Filme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FilmeRepository(private val context: Context) {

    private val sharedPrefs = context.getSharedPreferences("filmes_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun salvarListaFilmes(lista: List<Filme>) {
        val json = gson.toJson(lista)
        sharedPrefs.edit().putString("lista_filmes", json).apply()
    }

    fun carregarListaFilmes(): List<Filme> {
        val json = sharedPrefs.getString("lista_filmes", null)
        if (json != null) {
            val type = object : TypeToken<List<Filme>>() {}.type
            return gson.fromJson(json, type)
        }
        return emptyList()
    }
}
