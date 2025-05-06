package Funcionarios

import Cliente
import Funcionario

class Gerente(
    nome: String,
    salario: Double,
    cpf: String,
    senha: String,
    private val agencia: String
) : Funcionario(nome, salario, cpf, senha) {

    override val bonificacao: Double
        get() = getSalario() * 1.2

    fun desbloquearCliente(cliente: Cliente, novaSenha: String) {
        cliente.redefinirSenha(novaSenha)
        println("🔓 Cliente ${cliente.getNome()} foi desbloqueado com nova senha.")
    }


    fun getAgencia() = agencia
}
