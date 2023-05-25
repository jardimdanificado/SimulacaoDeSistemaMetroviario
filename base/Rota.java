package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Parada;
import Metro;

class Rota {
	private String nome;
	private List<parada> paradas = new ArrayList<>();
	private List<metro> veiculos = new ArrayList<>();
	private double passagem = 3.80;

	public Rota(String nome,double passagem, String... paradas) 
	{
		this.nome = nome;
		this.passagem = passagem;
		if(paradas != null)
			for(int i = 0; i < paradas.length;i++)
				this.paradas = paradas.add(new parada(paradas[i]));
	}
	public Rota(String nome,double passagem, parada[] paradas) 
	{
		this.nome = nome;
		this.passagem = passagem;
		if(paradas != null)
			this.paradas = Arrays.asList(paradas);
	}
	public Rota(String nome,double passagem, List<parada> paradas) 
	{
		this.nome = nome;
		this.passagem = passagem;
		if(paradas != null)
			this.paradas = paradas;
	}

	public void addParada(String nome) {
		paradas.add(new parada(nome));
	}

	public void addParada(parada parad) {
		paradas.add(parad);
	}

	public void alterarPreco(double novo) {
		this.passagem = novo;
	}
}