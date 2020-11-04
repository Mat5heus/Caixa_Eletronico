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
		if (mes > 0  && mes <= 12) {
			this.setMes(mes);
			this.defineMesCompleto(mes);
		} else
			this.dataInvalida();
		if(this.isDiaValid(dia, this.diaMax(ano,mes)))
			this.setDia(dia);
		else
			this.dataInvalida();
		this.defineEstacao(dia,mes);
	}

	
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
	
	public void avançarUmDia() {
		this.avançarDias(1);
	}
	
	public void defineEstacao(int dia,int mes) {
		int total = dia + (mes * 100);
		if (total < 320 || total >= 1222)
			this.setEstacao("Inverno");
		else if (total >= 320 && total < 621)
			this.setEstacao("Primavera");
		else if (total >= 621 && total < 923)
			this.setEstacao("Verão");
		else if (total >= 923 && mes < 1222)
			this.setEstacao("Outono");
		else
			this.setEstacao("Indefinido");
	}
	
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
	
	public boolean isDiaValid(int dia, int max) {
		if(dia <= max && dia > 0)
			return true;
		else 
			return false;
	}
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
	
	public void apresentarData(boolean isCompleto) {
		if (isCompleto) {
			System.out.println("Data: "+this.getDia()+" de "+this.getMesCompleto()+
					" de "+this.getAno()+" - Estação: "+this.getEstacao());
		} else {
			System.out.println("Data: "+this.eMenor(this.getDia())+"/"+
					this.eMenor(this.getMes())+"/"+this.getAno());
		}
		
	}
	public String dataPura() {
		return this.getDia()+" de "+this.getMesCompleto()+" de "+this.getAno();
	}
	public void apresentarData() {
		System.out.println("Data: "+this.eMenor(this.getDia())+"/"+
			this.eMenor(this.getMes())+"/"+this.getAno());
	}
	
	public String eMenor(int valor) {
		if (valor < 10)
			return "0"+valor;
		return ""+valor;
	}
	
	public void dataInvalida() {
		System.out.println("Data invalida!");
	}

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
