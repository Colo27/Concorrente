import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class Program_S extends UnicastRemoteObject implements InterS {
	private static final long serialVersionUID = 1L;

//	variabili globali
	private LinkedList<InterC> clients;

	public Program_S() throws RemoteException {
		super();
		clients = new LinkedList();
	}

//	questo è per far andare il server dell'rmi
	public static void main(String[] args) {
		try {
			Registry reg = LocateRegistry.createRegistry(1099);
//			System.setProperty("java.rmi.server.hostname", "192.168.1.138");
			InterS intrS = new Program_S();
			reg.rebind("Program_S", intrS);
			System.out.println("Server bounded in registry");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public synchronized void LeggiHTML(URL sito) {
		try {
//			per la copia della pagina web
			HttpURLConnection urlConnection = (HttpURLConnection) sito.openConnection();
			InputStream in = sito.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder result = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			System.out.println(result.toString());
			urlConnection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void abbonati(InterC ic) throws RemoteException {
		clients.add(ic);
	}

	public synchronized void disabbonati(InterC ic) throws RemoteException {
		clients.remove(ic);
	}

	public synchronized void contattaClient(String s) throws RemoteException {
		for (int j = 0; j < clients.size(); j++) {
			clients.get(j).ricezione(s);
		}
	}
}
