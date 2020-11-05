import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String resp;
		int ctd = 0;
		
		Cliente jair = new Cliente("Jair", 9834920, 7938432, new Data(12,06,2005));
		Conta contaJair = new Conta(jair, 98739847, "Mastercard", 1234, new Data());
		
		Cliente janete = new Cliente("Janete", 8983573, 4847538, new Data(17,05,1997));
		Conta contaJanete = new Conta(janete, 8734895, "Visa", 9372, new Data(12,04,2013));
		
		Conta contas[] = {contaJair, contaJanete};
		
		System.out.println("<============== Bem Vindo ao Caixa 24hrs =============>\n");
		
		boolean flag = false;
		
		do {
			int valor;
			
			while(!flag) {
				try {
					System.out.print("Digite o nome da conta: ");
					resp = scan.nextLine();
						
					for(ctd = 0; !resp.equals(contas[ctd].getCliente().getNome()) 
							&& ctd < contas.length; ctd++);
					flag = true;
				} catch(Exception e) {
					System.out.println("Conta não encontrada!");
					flag = false;
				} 
			}	
			
			System.out.println(
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
			
			if(resp.equals("1")) {
				System.out.print("Quanto deseja sacar: ");
				valor = Integer.parseInt(scan.nextLine());
				
				if(contas[ctd].getCartao().validar_senha(contas[ctd].getCliente()))
					contas[ctd].sacar(valor);
			} else if(resp.equals("2")) {
				contas[ctd].consulta();
				
			} else if(resp.equals("3")) {
				System.out.print("Quanto deseja depositar: ");
				valor = Integer.parseInt(scan.nextLine());
				contas[ctd].deposito(valor);
				
			} else if(resp.equals("4")) {
				int i;
				System.out.print("Para quem deseja transferir: ");
				resp = scan.nextLine();
				
				Conta aux[] = contas;
				
				for(i = 0; !resp.equals(aux[i].getCliente().getNome()); i++);
				
				System.out.print("Quanto deseja transferir: ");
				valor = Integer.parseInt(scan.nextLine());
				
				if(contas[ctd].getCartao().validar_senha(contas[ctd].getCliente()))
					contas[ctd].transferencia(aux[i],valor);
			} else if(resp.equals("5")) {
				contas[ctd].informacoesGerais();
			}else if(resp.equals("6")) {
				contas[ctd].setAberta((!contas[ctd].isAberta()? true : false));;
			} else if(resp.equals("7")) {
				System.out.println("\n========= Logout concluído =========\n");
				flag = false;
				
			}
			
		} while (!resp.equals("8"));
		
		scan.close();
		
		System.out.print("\n========= Operação finalizada =========");

	}
	
	
	

}
