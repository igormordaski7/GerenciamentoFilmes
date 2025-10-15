package com.example.gerenciamentofilmes.model

abstract class Validacao {

    companion object {

        private var id = 0

        fun getId(): Int {
            return id++
        }

        fun haCamposEmBranco(titulo: String, diretor: String): Boolean {
            return titulo.isBlank() || diretor.isBlank()
        }
    }
}