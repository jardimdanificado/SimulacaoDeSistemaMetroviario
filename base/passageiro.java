package base;

import vem;
import parada;
import metro;

class passageiro {
	private parada posicao;
	private vem passe;
	private parada origem;
	private parada destino;
	private metro veiculo;
	private String status = "fora";
	/*
 		status  
   			fora = fora da estação,
	  		esperando = esperando o veiculo
	  		em transito = em transito
	*/
	

	public passageiro(parada estacao, vem passe) {
		this.posicao = posicao;
		this.passe = passe;
	}

	protected void move(parada nova) {
		this.posicao = nova;
	}

	protected void adentrar(parada estacao)
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

	protected void definirRota(parada origem, parada destino)
	{
		if(this.status.equals("fora"))
		{
			this.origem = origem;
			this.destino = destino;
		}
	}

	protected void embarcar(metro veiculo)
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