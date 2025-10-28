package com.example.gerenciamentofilmes.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gerenciamentofilmes.model.Validacao
import com.example.gerenciamentofilmes.model.entity.Filme
import com.example.gerenciamentofilmes.model.repository.FilmeRepository

class FilmeViewModel(context: Context) : ViewModel() {

    private val repository = FilmeRepository(context)

    var listaFilmes = mutableStateOf(repository.carregarListaFilmes())
        private set

    fun salvarFilme(titulo: String, diretor: String): String {
        if (Validacao.haCamposEmBranco(titulo, diretor)) {
            return "Preencha todos os campos!"
        }

        val filme = Filme(
            Validacao.getId(),
            titulo,
            diretor
        )

        listaFilmes.value += filme
        repository.salvarListaFilmes(listaFilmes.value)

        return "Filme salvo com sucesso!"
    }

    fun excluirFilme(id: Int) {
        listaFilmes.value = listaFilmes.value.filter { it.id != id }
        repository.salvarListaFilmes(listaFilmes.value)
    }

    fun atualizarFilme(id: Int, titulo: String, diretor: String): String {
        if (Validacao.haCamposEmBranco(titulo, diretor)) {
            return "Ao editar, preencha todos os dados do filme!"
        }

        val filmesAtualizados = listaFilmes.value.map { filme ->
            if (filme.id == id) {
                filme.copy(titulo = titulo, diretor = diretor)
            } else {
                filme
            }
        }

        listaFilmes.value = filmesAtualizados
        repository.salvarListaFilmes(listaFilmes.value)

        return "Filme atualizado!"
    }
}
