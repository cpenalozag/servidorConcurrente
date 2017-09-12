package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {
	private static final String ip = "157.253.201.149";
	static boolean ejecutar = true;
	static Socket sock = null;
	static PrintWriter escritor = null;
	static BufferedReader lector = null;
	public static void main (String[] args) throws IOException{
		try {
			sock = new Socket(ip, 8080);
			escritor = new PrintWriter(sock.getOutputStream(), true);
			lector = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			System.exit(1);
		}
		BufferedReader stdIn = new BufferedReader(
				new InputStreamReader(System.in));
		String fromServer;
		String fromUser;
		while (ejecutar) {

			System.out.print("Escriba el mensaje para enviar:");
			fromUser = stdIn.readLine();
			if (fromUser != null && !fromUser.equals("-1")) {
				System.out.println("Cliente: " + fromUser);
				if (fromUser.equalsIgnoreCase("OK"))
					ejecutar = false;
				escritor.println(fromUser);
			}
			if ((fromServer = lector.readLine()) != null) {
				System.out.println("Servidor: " + fromServer);
			}
		}
		escritor.close();
		lector.close();
		// cierre el socket y la entrada est�ndar
	}
}