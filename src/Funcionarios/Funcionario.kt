import Autenticacao.Autenticavel

// Em Funcionarios/Funcionario.kt
open class Funcionario(
    private val nome: String,
    private val salario: Double,
    private val cpf: String,
    private var senha: String, // A senha agora é modificável
    val agencia: String
) : Autenticavel {

    // Método para autenticar, conforme a interface Autenticavel
    override fun autenticar(senha: String): Boolean {
        return this.senha == senha
    }

    // Método para cadastrar a senha
    fun cadastrarSenha(senha: String) {
        this.senha = senha
    }

    // Getter para nome
    fun getNome(): String = nome

    // Getter para CPF
    fun getCpf(): String = cpf

    // Getter para salário
    fun getSalario(): Double = salario

    // Método para calcular o bônus (pode ser sobrescrito nas subclasses)
    open val bonificacao: Double
        get() = 0.0 // Valor padrão para bonificação, pode ser sobrescrito nas subclasses
}
