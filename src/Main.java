/*
 * Program name: Caixa Eletronico 
 * 
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		boolean flag = false; 					
		String resp;
		int ctd = 0;
		
		Cliente jair = new Cliente("Jair", 9834920, 7938432, new Data(4,10,2002));
		Conta contaJair = new Conta(jair, 98739847, "Mastercard", 1234, new Data());
		
		Cliente janete = new Cliente("Janete", 8983573, 4847538, new Data(17,05,1997));
		Conta contaJanete = new Conta(janete, 8734895, "Visa", 9372, new Data(12,04,2013));
		
		Conta contas[] = {contaJair, contaJanete}; //Aguarda os dados das contas para busca
		
		System.out.println("<============== Bem Vindo ao Caixa 24hrs =============>\n");
		
		do {
			int valor;
			
			while(!flag) { //Permite multiplas chances de login
				try {
					System.out.print("Digite o nome da conta: ");
					resp = scan.nextLine();
					
					//Realiza a busca da conta pelo nome do usuario
					for(ctd = 0; !resp.equals(contas[ctd].getCliente().getNome()) 
							&& ctd < contas.length; ctd++);
					flag = true; //Confirma que conta foi encontrada
					
				// Lança uma exception caso o usuario não seja encontrado no array	
				} catch(Exception e) {
					System.out.println("Conta não encontrada!");
					flag = false; // Cancela a confirmação acaso a exception seja acionada
				} 
			}	
			
			System.out.println( // Exibe o menu principal
					"\n================ Menu ================\n"+
					"1) Saque\n"+
					"2) Saldo \n"+
					"3) Deposito\n"+
					"4) Transferência\n"+
					"5) Informações gerais\n"+
					"6) "+(!contas[ctd].isAberta()? "Ativar\n" : "Desativar\n")+
					"7) Sair da conta\n"+
					"8) Finalizar operação\n"
					);
			
			System.out.print("Digite a opção desejada: ");
			resp = scan.nextLine();
			
			// Ifs realizam a checagem da opção de escolhida
			if(resp.equals("1")) {
				System.out.print("Quanto deseja sacar: ");
				
				// Passa o valor digitado de String para inteiro
				valor = Integer.parseInt(scan.nextLine()); 
				
				if(valor < 0) valor = valor * (-1); // Garante que o valor seja positivo
				
				// Pede e valida senha do cartão
				if(contas[ctd].getCartao().validar_senha(contas[ctd].getCliente())) 
					contas[ctd].sacar(valor);
				
			} else if(resp.equals("2")) {
				contas[ctd].consulta();
				
			} else if(resp.equals("3")) {
				System.out.print("Quanto deseja depositar: ");
				
				// Passa o valor digitado de String para inteiro
				valor = Integer.parseInt(scan.nextLine()); 
				
				if(valor < 0) valor = valor * (-1); // Garante que o valor seja positivo
				
				contas[ctd].deposito(valor); // Realiza a operação solicitada
				
			} else if(resp.equals("4")) {
				int i;
				
				System.out.print("Para quem deseja transferir: ");
				resp = scan.nextLine();
			
				Conta aux[] = contas;  
				
				// Realiza outra busca no vetor pelo segundo usuario (alvo da trans.)
				for(i = 0; !resp.equals(aux[i].getCliente().getNome()); i++);
				
				System.out.print("Quanto deseja transferir: ");
				valor = Integer.parseInt(scan.nextLine());
				
				if(valor < 0) valor = valor * (-1); // Garante que o valor seja positivo
				
				if(contas[ctd].getCartao().validar_senha(contas[ctd].getCliente()))
					contas[ctd].transferencia(aux[i],valor); // transfere o valor para conta alvo
				
			} else if(resp.equals("5")) {
				contas[ctd].informacoesGerais();
				
			}else if(resp.equals("6")) {
				if(contas[ctd].getCartao().validar_senha(contas[ctd].getCliente()))
					
					// Ativa ou desativa a conta
					contas[ctd].setAberta((!contas[ctd].isAberta()? true : false)); 
					
			} else if(resp.equals("7")) {
				flag = false; // Realiza o logout do usuario
				System.out.println("\n========= Logout concluído =========\n");
				
			}
			
		} while (!resp.equals("8"));
		
		scan.close(); // Fecha o Scanner
		
		// Exibe a ultima mensagem e encerra o programa
		System.out.print("\n========= Operação finalizada ========="); 
		

	}
}
