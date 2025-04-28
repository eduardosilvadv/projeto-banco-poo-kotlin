import Contas.ContaCorrente
import Funcionarios.Diretor
import Funcionarios.Gerente

// Em main.kt
fun main() {
    val usuarios = mutableListOf<Any>()
    var usuarioLogado: Any? = null

    fun limparTela() {
        print("\u001b[H\u001b[2J")
        System.out.flush()
    }


    // Criando Gerente e Diretor com senha cadastrada
    val gerente = Gerente("Ricardo", 5000.0, "123456789", "Agência 001", "1").apply {
        cadastrarSenha("123")
    }

    val diretor = Diretor("Carlos", 8000.0, "789456123", "123", "").apply {
        cadastrarSenha("999")
    }

    // Criando o cliente
    val cliente = Cliente("Lais", "123", "456", "senhaCliente", "00011122233")
    val contaCliente = ContaCorrente(cliente, "001")
    cliente.associarConta(contaCliente)

    usuarios.addAll(listOf(gerente, diretor, cliente, contaCliente))

    while (true) {
        limparTela()
        println("\n=== MENU BANCO ===")

        if (usuarioLogado == null) {
            limparTela()
            println("1. Login como Cliente")
            println("2. Login como Funcionário")
            println("0. Sair")
            when (readln()) {
                "1" -> {
                    limparTela()
                    // Login Cliente
                    println("Olá, coloque seus dados abaixo!! ")
                    print("Digite a agência: ")
                    val agencia = readln()
                    print("Digite a conta: ")
                    val conta = readln()
                    print("Digite a senha: ")
                    val senha = readln()

                    val usuario = usuarios.find {
                        it is Cliente && it.getAgencia() == agencia && it.getConta() == conta
                    }

                    when (usuario) {

                        is Cliente -> {
                            limparTela()
                            if (usuario.autenticar(senha)) {
                                usuarioLogado = usuario
                                println("✅ Login como ${usuario.getNome()} (Cliente)")
                                println("Saldo: R$ ${usuario.acessarConta()?.consultarSaldo()}")
                            } else {
                                println("❌ Falha na autenticação.")
                            }
                        }
                        else -> println("❌ Cliente não encontrado.")
                    }
                }
                "2" -> {
                    // Login Funcionário (Gerente ou Diretor)
                    print("Digite o CPF: ")
                    val cpf = readln()
                    print("Digite a senha: ")
                    val senha = readln()

                    val usuario = usuarios.find {
                        (it is Gerente && it.getCpf() == cpf) || (it is Diretor && it.getCpf() == cpf)
                    }

                    when (usuario) {
                        is Funcionario -> {
                            if (usuario.autenticar(senha)) {
                                usuarioLogado = usuario
                                println("✅ Login como ${usuario.getNome()} (${usuario::class.simpleName})")
                            } else {
                                println("❌ Falha na autenticação.")
                            }
                        }
                        else -> println("❌ Funcionário não encontrado.")
                    }
                }
                "0" -> return
                else -> println("❌ Opção inválida.")
            }
        } else {
            when (usuarioLogado) {
                is Cliente -> {
                    println("1. Consultar saldo\n2. Sacar\n3. Depositar\n4. Logout\n0. Sair")
                    when (readln()) {
                        "1" -> {
                            println("Saldo: R$ ${usuarioLogado.acessarConta()?.consultarSaldo()}")
                        }
                        "2" -> {
                            print("Digite o valor para saque: R$ ")
                            val valor = readln().toDouble()
                            usuarioLogado.acessarConta()?.sacar(valor)
                        }
                        "3" -> {
                            print("Digite o valor para depósito: R$ ")
                            val valor = readln().toDouble()
                            usuarioLogado.acessarConta()?.depositar(valor)
                        }
                        "4" -> usuarioLogado = null.also { println("🔓 Logout realizado.") }
                        "0" -> return
                        else -> println("❌ Opção inválida.")
                    }
                }
                is Gerente -> {
                    println("1. Desbloquear cliente\n2. Logout\n0. Sair")
                    when (readln()) {
                        "1" -> {
                            println("=== Desbloquear cliente ===")
                            print("Agência do cliente: ")
                            val agenciaCliente = readln()
                            print("Número da conta do cliente: ")
                            val contaCliente = readln()
                            print("CPF do cliente: ")
                            val cpfCliente = readln()

                            val clienteAlvo = usuarios.find {
                                it is Cliente &&
                                        it.getAgencia() == agenciaCliente &&
                                        it.getConta() == contaCliente &&
                                        it is Cliente && (it as Cliente).getCpf() == cpfCliente
                            }

                            if (clienteAlvo is Cliente && clienteAlvo.estaBloqueado()) {
                                print("Digite nova senha para o cliente: ")
                                val novaSenha = readln()
                                (usuarioLogado as Gerente).desbloquearCliente(clienteAlvo, novaSenha)
                            } else {
                                println("❌ Cliente não encontrado ou não está bloqueado.")
                            }
                        }

                        "2" -> usuarioLogado = null.also { println("🔓 Logout realizado.") }
                        "0" -> return
                    }
                }
                else -> usuarioLogado = null
            }
        }
    }
}
