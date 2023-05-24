package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import parada;
import passageiro;

class metro {
	private String placa;
	private rota rota;
	private parada posicao;
	private int capacidade = 1125;// 5 vagões de 225 pessoas
	private List<passageiro> passageiros = new ArrayList<>();

	public metro(String placa, rota rota, int capacidade) {
		this.rota = rota;
		this.posicao = 0;
		this.capacidade = capacidade;
	}

	public String getPlaca() {
		return this.placa;
	}

	public Rota getRota() {
		return this.rota;
	}

	public void setRota(Rota rota) {
		this.rota = rota;
	}

	public void setRota(String... paradas) {
		this.rota = Arrays.asList(paradas);
	}

	public int getPosicao() {
		return this.posicao;
	}

	public int getCapacidade() {
		return this.capacidade;
	}

	public void addPassageiro(passageiro p) {
		this.passageiros.add(p)
	}

	public void rmPassageiro(passageiro p) {
		this.passageiros.remove(p)
	}

	public void move(int tempo) {
		this.posicao += 1;
		if (this.posicao >= (this.rota.size() * 2) - 1)
			this.posicao = 0;
	}
}