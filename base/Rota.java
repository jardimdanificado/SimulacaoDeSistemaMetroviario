package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import base.Parada;
import base.Metro;

public class Rota {
	public String nome;
	public List<base.Parada> paradas = new ArrayList<>();
	private List<base.Metro> veiculos = new ArrayList<>();
	public double passagem = 3.80;

	public Rota(String nome,double passagem, String... paradas) 
	{
		this.nome = nome;
		this.passagem = passagem;
		if(paradas != null)
			for(int i = 0; i < paradas.length;i++)
				this.paradas.add(new base.Parada(paradas[i]));
	}
	public Rota(String nome,double passagem, base.Parada[] paradas) 
	{
		this.nome = nome;
		this.passagem = passagem;
		if(paradas != null)
			this.paradas = Arrays.asList(paradas);
	}
	public Rota(String nome,double passagem, List<base.Parada> paradas) 
	{
		this.nome = nome;
		this.passagem = passagem;
		if(paradas != null)
			this.paradas = paradas;
	}

	public void addParada(String nome) {
		paradas.add(new Parada(nome));
	}

	public void addParada(base.Parada parad) {
		paradas.add(parad);
	}

	public void alterarPreco(double novo) {
		this.passagem = novo;
	}
}