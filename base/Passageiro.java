package base;

import VEM;
import Parada;
import Metro;

class Passageiro {
	private Parada posicao;
	private VEM passe;
	private Parada origem;
	private Parada destino;
	private Metro veiculo;
	private String status = "fora";
	/*
 		status  
   			fora = fora da estação,
	  		esperando = esperando o veiculo
	  		em transito = em transito
	*/
	

	public Passageiro(Parada estacao, VEM passe) {
		this.posicao = posicao;
		this.passe = passe;
	}

	protected void move(Parada nova) {
		this.posicao = nova;
	}

	protected void adentrar(Parada estacao)
	{
		if(this.status.equals("esperando") && this.passe.saldo > estacao.veiculos[0].passagem)
		{
			this.posicao = estacao;
			this.status = "esperando";
			this.passe.debito(estacao.veiculos[0].passagem);
		}
		
	}

	protected void recarregar(double valor)
	{
		if(this.status.equals("esperando") || this.status.equals("fora"))
			this.passe.recarga(valor)
	}

	protected void definirRota(Parada origem, Parada destino)
	{
		if(this.status.equals("fora"))
		{
			this.origem = origem;
			this.destino = destino;
		}
	}

	protected void embarcar(Metro veiculo)
	{
		if(veiculo.posicao == this.posicao && this.status.equals("esperando"))
		{
			this.veiculo = veiculo;
			this.status = "em transito";
		}
	}

	protected void desembarcar()
	{
		if(this.status.equals("em transito"))
		{
			if(this.origem != veiculo.posicao)
				this.status = "esperando";
			else 
				this.status = "fora";
			this.posicao = veiculo.posicao;
		}
	}
}