package controller;

import java.util.concurrent.Semaphore;

public class bilheteria extends Thread {
	static int ingressos = 100;
	private int id;
	private Semaphore semaforo;
	private int time;
	private int ningressos;

	public bilheteria(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		if (login()) {
			if (compra()) {
				try {
					semaforo.acquire();
					validacao();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
			}
		}
	}

	public boolean login() {
		time = (int) (Math.random() * 1951 + 50);
		try {
			if (time > 1000) {
				System.out.println("Seu login: " + id + "\nNão foi efetuado, ocorreu um erro e não poderá comprar");

			}
			sleep(time);
			return true;

		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean compra() {
		time = (int) (Math.random() * 2001 + 1000);
		try {
			if (time > 2500) {
				System.out.println(
						"Seu tempo de entrada na sessão: " + id + " foi excedido e assim não poderá efetuar a compra");
				return false;
			}
			sleep(time);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void validacao() {
		ningressos = (int) ((Math.random() * 4) + 1);
		if (ingressos >= ningressos) {
			System.out.println(id + " Comprou: " + ningressos + " - ingressos");
			ingressos -= ningressos;
			System.out.println("Número de ingressos disponivéis: " + ingressos);
		} else
			System.out.println("O número de ingressos solicitado é maior que o estoque");

	}

}
