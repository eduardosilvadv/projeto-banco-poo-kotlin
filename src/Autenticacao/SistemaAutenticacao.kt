package Autenticacao

object SistemaAutenticacao {
    fun login(usuario: Autenticavel, senha: String): Boolean {
        return usuario.autenticar(senha)
    }
}

