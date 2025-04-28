package Contas

import Cliente
import Conta

class ContaCorrente(
    cliente: Cliente,
    numero: String
) : Conta(cliente, numero) {

    // Sobrescreve o método de saque
    override fun sacar(valor: Double): Double {
        if (valor <= consultarSaldo()) {
            return super.efetuarSaque(valor)  // Chama o método de saque da classe pai
        } else {
            println("❌ Saldo insuficiente para o saque.")
            return 0.0
        }
    }

    // Sobrescreve o método de depósito
    override fun depositar(valor: Double) {
        if (valor > 0) {
            super.adicionarSaldo(valor)  // Chama o método de adicionar saldo da classe pai
            println("Depósito de R$$valor realizado com sucesso na Conta Corrente.")
        } else {
            println("❌ Valor de depósito inválido.")
        }
    }
}
