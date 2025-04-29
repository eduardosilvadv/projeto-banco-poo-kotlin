import Autenticacao.Autenticavel
import Contas.ContaCorrente

// Em Cliente.kt
class Cliente(
    private val nome: String,
    private var senha: String,
    private val cpf: String


) : Autenticavel {
    private var bloqueado = false
    private var tentativas = 0

    private var contaAssociada: Conta? = null

/*    // Método para acessar a conta associada
    fun acessarConta(): Conta? {
        return contaAssociada
    }


    // Método para associar uma conta ao cliente
    fun associarConta(conta: Conta) {
        contaAssociada = conta
    }*/
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

    // Métodos para obter a agência e a conta

    fun getNome(): String = nome
    fun getCpf() = cpf
}
