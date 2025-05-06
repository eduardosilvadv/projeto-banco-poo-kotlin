package Contas

import Cliente
import Conta

class ContaCorrente(
    cliente: Cliente,
    numero: String,
    agencia: String
) : Conta(cliente, numero, agencia ) {



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
