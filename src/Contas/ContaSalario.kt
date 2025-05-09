/*
package Contas

import Cliente
import Conta

class ContaSalario(
    cliente: Cliente,
    numero: String,
    agencia: String
) : Conta(cliente, numero, agencia ){

    // Método de saque, se houver alguma lógica diferenciada para a ContaSalario
    override fun sacar(valor: Double): Double {
        if (valor <= consultarSaldo()) {
            return super.efetuarSaque(valor)  // Chama o método de saque da classe pai
        } else {
            println("❌ Saldo insuficiente para o saque.")
            return 0.0
        }
    }

    // Método de depósito, com uma possível validação ou lógica diferente, se necessário
    override fun depositar(valor: Double) {
        if (valor > 0) {
            super.adicionarSaldo(valor)  // Chama o método de adicionar saldo da classe pai
            println("Depósito de R$$valor realizado com sucesso na Conta Salário.")
        } else {
            println("❌ Valor de depósito inválido.")
        }
    }
}*/
