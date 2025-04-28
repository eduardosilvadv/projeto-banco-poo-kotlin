package Autenticacao

interface Autenticavel {
    fun autenticar(senha: String): Boolean
}
