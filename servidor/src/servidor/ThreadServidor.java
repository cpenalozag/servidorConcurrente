package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadServidor extends Thread{
	// atributo socket
	private Socket sktCliente = null;
	private int id;
	private Protocolo p;

	public ThreadServidor(Socket pSocket,int pId) {
		sktCliente = pSocket;
		id = pId;
		p = new Protocolo();
	}

	public void run() {

		System.out.println("Inicio de nuevo thread." + id);
		try {
			PrintWriter escritor = new PrintWriter(sktCliente.getOutputStream(), true);
			BufferedReader lector = new BufferedReader(new InputStreamReader(sktCliente.getInputStream()));
			p.procesar(lector,escritor);
			escritor.close();
			lector.close();
			sktCliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}