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

public class Program_S extends UnicastRemoteObject implements InterS {
	private static final long serialVersionUID = 1L;

//	variabili globali

	public Program_S() throws RemoteException {
		super();
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
}
