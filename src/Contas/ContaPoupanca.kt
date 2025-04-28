package Contas

import Cliente
import Conta

class ContaPoupanca(
    cliente: Cliente,
    numero: String
) : Conta(cliente, numero) {

    // Sobrescreve o método de saque aplicando uma taxa
    override fun sacar(valor: Double): Double {
        val taxa = 1.02 // 2% de taxa no saque
        val valorComTaxa = valor * taxa
        if (valorComTaxa <= consultarSaldo()) {
            return super.sacar(valorComTaxa)  // Chama o método da classe pai, passando o valor com a taxa
        } else {
            println("❌ Saldo insuficiente para o saque com taxa.")
            return 0.0
        }
    }

    // Sobrescreve o método de depósito (simples)
    override fun depositar(valor: Double) {
        if (valor > 0) {
            adicionarSaldo(valor)  // Chama o método da classe pai para adicionar saldo
            println("Depósito de R$$valor realizado com sucesso na Conta Poupança.")
        } else {
            println("❌ Valor de depósito inválido.")
        }
    }
}
