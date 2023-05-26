package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import base.Parada;
import base.Passageiro;
import base.Rota;

public class Metro {
	private String placa;
	public base.Rota rota;
	protected base.Parada posicao;
	private int capacidade = 1125;// 5 vagões de 225 pessoas
	private List<base.Passageiro> passageiros = new ArrayList<>();

	public Metro(String placa, base.Rota rota, int capacidade) {
		this.rota = rota;
		this.posicao = rota.paradas.get(0);
		this.capacidade = capacidade;
	}

	public String getPlaca() {
		return this.placa;
	}

	public base.Rota getRota() {
		return this.rota;
	}

	public void setRota(base.Rota rota) {
		this.rota = rota;
	}

	public void setRota(String... paradas) {
		List<base.Parada> p = new ArrayList<>();
		for(int i = 0;i < paradas.length; i++)
			p.add(paradas[i]);
		this.rota = Arrays.asList(p);
	}

	public Parada getPosicao() {
		return this.posicao;
	}

	public int getCapacidade() {
		return this.capacidade;
	}

	public void addPassageiro(base.Passageiro p) {
		this.passageiros.add(p);
	}

	public void rmPassageiro(base.Passageiro p) {
		this.passageiros.remove(p);
	}

	public void move(int tempo) {
		//precisa reescrever
	}
}