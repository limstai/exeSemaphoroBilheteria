package view;

import java.util.concurrent.Semaphore;
import controller.bilheteria;

public class PrincipalBilheteria {

	public static void main(String[] args) {
		final int compra = 1;
		Semaphore semaforo = new Semaphore(compra);
		for (int id = 1; id <= 300; id++) {
			Thread thread = new bilheteria(id, semaforo);
			thread.start();
		}

	}

}
