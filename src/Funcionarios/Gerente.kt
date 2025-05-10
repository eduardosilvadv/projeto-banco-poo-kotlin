package Funcionarios

import Cliente
import Funcionario

class Gerente(
    nome: String,
    salario: Double,
    cpf: String,
    senha: String,
    protected val agencia: String
) : Funcionario(nome, salario, cpf, senha) {

    override val bonificacao: Double
        get() = salario * 1.2

    fun desbloquearCliente(cliente: Cliente, novaSenha: String) {
        cliente.redefinirSenha(novaSenha)
        println("🔓 Cliente ${cliente.nome} foi desbloqueado com nova senha.")
    }

}
