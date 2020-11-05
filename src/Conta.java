public class Conta implements funcionalidades_conta {
	private int saldo, cheque_especial, codigo;
	private boolean aberta;
	private Cliente cliente;
	private Cartao cartao;
	private Data data;
	
	public Conta(Cliente cliente, int codigo, String bandeira, int senha, Data data) {
		this.setCliente(cliente);
		this.setCodigo(codigo);
		this.setCartao(new Cartao(codigo, bandeira, senha));
		this.setSaldo(0);
		this.setCheque_especial(300);
		this.setData(data);
		this.setAberta(true);
	}
	
	public void deposito(int valor) {
		if(this.aberta) {
			// verifica se há o valor devolvido cabe no limite de 300 reais
			if(this.getCheque_especial() + valor <= 300) { 
				this.setCheque_especial(this.getCheque_especial() + valor);
				
			// se não, verifica se há saldo devedor e preenche o que falta
			} else if (this.getCheque_especial() <= 300) { 
				valor -= 300 - this.getCheque_especial();
				if(valor > 0) {
					this.setCheque_especial(300);
					this.setSaldo(this.getSaldo() + valor);
				}
			// caso o usuario não deva nada o saldo atual é somado ao valor do deposito
			} else 
				this.setSaldo(this.getSaldo() + valor);
			
			Conta.mensagens(this.getCliente(), 3); // confirma deposito bem sucedido
			
		} else {
			Conta.mensagens(this.cliente, 9);
			Conta.mensagens(this.cliente, 7);
		}
	}
	
	// Guarda as mensagem do programa
	public static void mensagens(Cliente cliente, int numero, int valor) {
		System.out.print("Conta de "+cliente.getNome()+": ");
		switch(numero) {
		case 1:
			System.out.println("Saldo "+valor);
			break;
		case 2:
			System.out.println("Cheque especial "+valor+" de 300");
			break;
		default:
			System.out.println("Mensagem não encontrada!");
		}
	}
	
	// Guarda as mensagem do programa
	public static void mensagens(Cliente cliente,Cliente clienteAlvo, int numero) {
		System.out.print("Conta de "+cliente.getNome()+": ");
		switch(numero) {
		case 1:
			System.out.println("A Conta de "+clienteAlvo.getNome()+" está desativada");
			break;
		default:
			System.out.println("Mensagem não encontrada!");
		}
	}
	
	// Guarda as mensagem do programa
	public static void mensagens(Cliente cliente, int numero) {
		System.out.print("Conta de "+cliente.getNome()+": ");
		switch(numero) {
		case 1:
			System.out.println("Saldo insuficiente");
			break;
		case 2:
			System.out.println("Transferencia cancelada!");
			break;
		case 3:
			System.out.println("Deposito realizado!");
			break;
		case 4:
			System.out.println("Saque cancelado!");
			break;
		case 5:
			System.out.println("Senha incorreta");
			break;
		case 6:
			System.out.println("Cartão bloqueado!");
			break;
		case 7:
			System.out.println("Operação cancelada");
			break;
		case 8:
			System.out.println("Saque realizado!");
			break;
		case 9:
			System.out.println("Conta não ativa!");
			break;
		default:
			System.out.println("Código de mensagem invalido!");
		}
	}
	
	public int sacar(int valor) {
		if(this.aberta) {
			int aux = valor;
			
			// Verifica se há saldo suficiente na conta para saque
			if(this.getSaldo() + this.getCheque_especial() >= valor) {
				valor = valor - this.getSaldo();
				
				// Caso o valor > saldo, o valor é retidado do cheque especial
				if(valor > 0) {
					this.setSaldo(0);
					this.setCheque_especial(this.getCheque_especial() - valor);
				} else 
					// Caso haja saldo para o saque o valor é descontado do saldo
					this.setSaldo(this.getSaldo() - aux);
				Conta.mensagens(this.getCliente(), 8);
				return aux;
			} else {
				// Informa o usuario que o saldo não é suficiente
				Conta.mensagens(this.getCliente(),1);
				return 0;
			}
		} else {
			Conta.mensagens(this.cliente, 9);
			Conta.mensagens(this.cliente, 7);
		}
		return 0;
		
	}
	
	public void transferencia(Conta conta, int valor) {
		
		//verifica se a conta do cliente alvo esta ativada e finaliza a operação caso não esteja
		if(!conta.isAberta()) {
			Conta.mensagens(this.cliente, conta.cliente, 1);
			Conta.mensagens(this.cliente, 2);
			return;
		}
		
		// Verifica se a conta do cliente esta ativada e realiza a operação caso haja saldo
		if(this.aberta) {
			if (this.sacar(valor) != 0)
				conta.deposito(valor);
			else
				// Informa que a operação foi cancelada
				Conta.mensagens(this.getCliente(),2);
		} else {
			Conta.mensagens(this.cliente, 9);
			Conta.mensagens(this.cliente, 7);
		}
	}
	
	public void consulta() { //Exibe o saldo total disponível
		if(this.aberta) {
			Conta.mensagens(this.getCliente(), 1, this.getSaldo());
			Conta.mensagens(this.getCliente(), 2, this.getCheque_especial());
		} else {
			Conta.mensagens(this.cliente, 9);
			Conta.mensagens(this.cliente, 7);
		}
	}

	public void informacoesGerais() {
		int valor;
		String saldo_devedor = "";
		
		// Calcula a diferença e informa ao usuario o saldo devedor
		if(this.cheque_especial < 300) { 
			valor = 300 - this.getCheque_especial();
		    saldo_devedor = "(devendo "+valor+")";
		}
		
		System.out.println( // Fornece principais informações sobre a conta
				"========= Informações ===========\n"+
				"Cliente: "+this.getCliente().getNome()+"\n"+
				"Idade do cliente: "+this.getCliente().idade()+"\n"+
				"Conta:"+this.getCodigo()+"\n"+
				"Data de criação: "+this.getData().dataSimples()+"\n"+
				"Status da conta: "+(this.isAberta() ? "Ativada" : "Desativada")+"\n"+
				"Saldo: "+this.getSaldo()+"\n"+
				"Cheque Especial: "+this.getCheque_especial()+" "+saldo_devedor
				);
	}
	
    //-----------------------------//
	//----- Getters & Setters -----//
	//-----------------------------//
	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public int getCheque_especial() {
		return cheque_especial;
	}

	public void setCheque_especial(int cheque_especial) {
		this.cheque_especial = cheque_especial;
	}

	public boolean isAberta() {
		return aberta;
	}

	public void setAberta(boolean aberta) {
		this.aberta = aberta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	};
}
