import Autenticacao.Autenticavel

// Em Funcionarios/Funcionario.kt
open class Funcionario(
    val nome: String,
    val salario: Double,
    val cpf: String,
    private var senha: String, // A senha agora é modificável
) : Autenticavel {


    // Método para autenticar, conforme a interface Autenticavel
    override fun autenticar(senha: String): Boolean {
        return this.senha == senha
    }

    // Método para cadastrar a senha
    fun cadastrarSenha(senha: String) {
        this.senha = senha
    }

    // Método para calcular o bônus (pode ser sobrescrito nas subclasses)
    open val bonificacao: Double
        get() = 0.0 // Valor padrão para bonificação, pode ser sobrescrito nas subclasses
}
