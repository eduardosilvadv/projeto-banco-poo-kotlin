package Contas

import Cliente
import Conta

class ContaPoupanca(
    cliente: Cliente,
    numero: String,
    agencia: String
) : Conta(cliente, numero, agencia ) {

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
