package Funcionarios

import Cliente
import Funcionario

class Gerente(
    nome: String,
    salario: Double,
    cpf: String,
    senha: String,
    agencia: String
) : Funcionario(nome, salario, cpf, senha,agencia) {

    override val bonificacao: Double
        get() = getSalario() * 0.2

    fun desbloquearCliente(cliente: Cliente, novaSenha: String) {
        cliente.redefinirSenha(novaSenha)
        println("🔓 Cliente ${cliente.getNome()} foi desbloqueado com nova senha.")
    }


    fun getAgenciaGerente() = agencia
}
