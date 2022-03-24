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
				System.out.println("Seu login: " + id + "\nN�o foi efetuado, ocorreu um erro e n�o poder� comprar");

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
						"Seu tempo de entrada na sess�o: " + id + " foi excedido e assim n�o poder� efetuar a compra");
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
			System.out.println("N�mero de ingressos disponiv�is: " + ingressos);
		} else
			System.out.println("O n�mero de ingressos solicitado � maior que o estoque");

	}

}
