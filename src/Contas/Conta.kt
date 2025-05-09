abstract class Conta(
    var cliente: Cliente, // Cliente associado à conta (privado)
    val numeroConta: String,
    val agencia: String// Número da conta (privado)
) {
    var saldo: Double = 0.0 // Saldo da conta (privado)
        protected set

    // Método para depositar valor na conta
    abstract fun depositar(valor: Double)

    // Método para sacar valor da conta
    open fun sacar(valor: Double): Double {
        if (valor <= saldo) {
            saldo -= valor
            println("Saque de R$$valor realizado com sucesso!")
            return valor
        } else {
            println("❌ Saldo insuficiente para o saque de R$$valor.")
            return 0.0
        }
    }

    // Método para transferir valor para outra conta
    fun transferir(valor: Double, contaDestino: Conta): Boolean {
        if (this.sacar(valor) > 0) {
            contaDestino.depositar(valor)
            println("Transferência de R$$valor realizada com sucesso para a conta ${contaDestino.numeroConta}.")
            return true
        }
        return false
    }

}
