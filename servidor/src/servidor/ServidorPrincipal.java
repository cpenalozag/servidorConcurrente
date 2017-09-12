package servidor;

import java.io.IOException;
import java.net.ServerSocket;

public class ServidorPrincipal {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = null;
		final int PUERTO = 8080;
		boolean continuar = true;
		int i = 0;

		try {
			ss = new ServerSocket(PUERTO);
		} catch (IOException e) {
			System.err.println("No pudo crear socket en el puerto:" + PUERTO);
			System.exit(-1);
		}
		while (continuar) {

			new ThreadServidor(ss.accept(),i).start();
			i++;
		}
		ss.close();
	}
}
