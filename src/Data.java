import java.util.Calendar;
public class Data {
	private int dia, mes, ano;
	private String mesCompleto, estacao;
	
	public Data() {
		Calendar cal = Calendar.getInstance();
		this.setDia(cal.get(Calendar.DATE));
		this.setMes(cal.get(Calendar.MONTH));
		this.setAno(cal.get(Calendar.YEAR));
		this.defineEstacao(this.getDia(),this.getMes());
		this.defineMesCompleto(this.getMes());
	}
	
	public Data(int dia, int mes, int ano) {
		this.setAno(ano);	
		if (mes > 0  && mes <= 12) { //verifica se é um mes valido
			this.setMes(mes);
			this.defineMesCompleto(mes);
		} else
			this.dataInvalida(); // informa o usuario caso nao seja
		if(this.isDiaValid(dia, this.diaMax(ano,mes))) // verifica se o dia é valido
			this.setDia(dia);
		else
			this.dataInvalida(); // informa o usuario caso nao seja
		this.defineEstacao(dia,mes); // seta a estacão para a data
	}

	//defina e salva o mes atual
	public void defineMesCompleto(int mes) {
		switch(mes) {
		case 1:
			this.setMesCompleto("janeiro");
			break;
		case 2:
			this.setMesCompleto("fevereiro");
			break;
		case 3:
			this.setMesCompleto("março");
			break;
		case 4:
			this.setMesCompleto("abril");
			break;
		case 5:
			this.setMesCompleto("maio");
			break;
		case 6:
			this.setMesCompleto("junho");
			break;
		case 7:
			this.setMesCompleto("julho");
			break;
		case 8:
			this.setMesCompleto("agosto");
			break;
		case 9:
			this.setMesCompleto("setembro");
			break;
		case 10:
			this.setMesCompleto("outubro");
			break;
		case 11:
			this.setMesCompleto("novembro");
			break;
		default:
			this.setMesCompleto("dezembro");
		}
	}
	
	// Avanca um dia no calendario
	public void avançarUmDia() {
		this.avançarDias(1);
	}
	
	// Gera uma "pontuação" para cada dia do mes e insere 
	public void defineEstacao(int dia,int mes) {
		int total = dia + (mes * 100);
		if (total < 320 || total >= 1222)
			this.setEstacao("Inverno");
		else if (total < 621)
			this.setEstacao("Primavera");
		else if (total < 923)
			this.setEstacao("Verão");
		else if (mes < 1222)
			this.setEstacao("Outono");
		else
			this.setEstacao("Indefinido");
	}
	
	// Avança uma determinada quantidade de dias
	public void avançarDias(int dias) {
		
		while(! this.isDiaValid( this.getDia()+dias, this.diaMax(this.getAno(),this.getMes()) ) ) {
			dias -= (this.diaMax(this.getAno(),this.getMes()) - this.getDia()) + 1;
			this.setMes(this.getMes()+1);
			this.setDia(1);
			if (this.getMes() > 12) {
				this.setMes(1);
				this.setAno(this.getAno()+1);
			}
		} 
		this.setDia(this.getDia()+dias);
		this.defineMesCompleto(this.getMes());
		this.defineEstacao(this.getDia(), this.getMes());
	}
	
	// Verifica se o dia está dentro dos limites do mes
	public boolean isDiaValid(int dia, int max) {
		if(dia <= max && dia > 0)
			return true;
		else 
			return false;
	}
	
	// Retorna o maximo de dias de um determinado mes no ano
	public int diaMax(int ano,int mes) {
		if ((mes % 2 == 0 && mes <= 6 && mes != 2)||(mes % 2 != 0 && mes >= 9)) 
			return 30; 
		else if ((mes % 2 == 0 && mes >= 8) || (mes % 2 != 0 && mes <= 7 )) 
			return 31;
		else if (ano % 4 == 0)
			return 29;
		else 
			return 28;
	}
	
	// Exibe a data com a identificação antes
	public void apresentarData(boolean isCompleto) {
		if (isCompleto) {
			System.out.println("Data: "+this.getDia()+" de "+this.getMesCompleto()+
					" de "+this.getAno()+" - Estação: "+this.getEstacao());
		} else {
			System.out.println("Data: "+this.eMenor(this.getDia())+"/"+
					this.eMenor(this.getMes())+"/"+this.getAno());
		}
		
	}
	
	// Exibe a data sem a identificação
	public String dataCompleta() {
		return this.getDia()+" de "+this.getMesCompleto()+" de "+this.getAno();
	}
	public String dataSimples() {
		return this.eMenor(this.getDia())+"/"+this.eMenor(this.getMes())+"/"+this.getAno();
	}
	
	// Acresenta um 0 caso o valor < 10
	public String eMenor(int valor) {
		if (valor < 10)
			return "0"+valor;
		return ""+valor;
	}
	
	// Em caso de erro
	public void dataInvalida() {
		System.out.println("Data invalida!");
	}
	
	//-----------------------------//
	//----- Getters & Setters -----//
	//-----------------------------//

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getMesCompleto() {
		return mesCompleto;
	}

	public void setMesCompleto(String mesCompleto) {
		this.mesCompleto = mesCompleto;
	}

	public String getEstacao() {
		return estacao;
	}

	public void setEstacao(String estacao) {
		this.estacao = estacao;
	}
	
}
