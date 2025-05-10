import Autenticacao.Autenticavel

// Em Cliente.kt
class Cliente(
    val nome: String,
    protected var senha: String,
    val cpf: String

) : Autenticavel {
    protected var bloqueado = false
    protected var tentativas = 0

    override fun autenticar(senha: String): Boolean {
        if (bloqueado) {
            println("⚠️ Conta bloqueada. Procure um gerente.")
            return false
        }

        return if (this.senha == senha) {
            tentativas = 0
            true
        } else {
            tentativas++
            println("❌ Senha incorreta. Tentativa $tentativas de 3.")
            if (tentativas >= 3) {
                bloqueado = true
                println("🔒 Conta bloqueada após 3 tentativas.")
            }
            false
        }
    }
    fun estaBloqueado(): Boolean {
        return bloqueado
    }

    fun redefinirSenha(novaSenha: String) {
        senha = novaSenha
        bloqueado = false
        tentativas = 0
        println("✅ Senha redefinida e conta desbloqueada.")
    }

}
