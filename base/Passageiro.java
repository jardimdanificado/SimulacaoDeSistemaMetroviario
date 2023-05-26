package base;

import base.VEM;
import base.Parada;
import base.Metro;

public class Passageiro {
	private base.Parada posicao;
	public base.VEM passe;
	private base.Parada origem;
	private base.Parada destino;
	private base.Metro veiculo;
	private String status = "fora";
	/*
 		status  
   			fora = fora da estação,
	  		esperando = esperando o veiculo
	  		em transito = em transito
	*/
	

	public Passageiro(base.Parada estacao, base.VEM passe) {
		this.posicao = posicao;
		this.passe = passe;
	}
	
	public Passageiro(base.Parada estacao, String nome, String tipo) {
		this.posicao = posicao;
		this.passe = new base.VEM(nome,tipo);
	}

	protected void move(base.Parada nova) {
		this.posicao = nova;
	}

	protected void adentrar(base.Parada estacao)
	{
		if(this.status.equals("esperando") && this.passe.getSaldo() > estacao.veiculo.rota.passagem)
		{
			this.posicao = estacao;
			this.status = "esperando";
			this.passe.debito(estacao.veiculo.rota.passagem);
		}
		
	}

	protected void recarregar(double valor)
	{
		if(this.status.equals("esperando") || this.status.equals("fora"))
			this.passe.recarga(valor);
	}

	protected void definirRota(base.Parada origem, base.Parada destino)
	{
		if(this.status.equals("fora"))
		{
			this.origem = origem;
			this.destino = destino;
		}
	}

	protected void embarcar(base.Metro veiculo)
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