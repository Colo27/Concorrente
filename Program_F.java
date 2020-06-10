import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Program_F extends UnicastRemoteObject implements Runnable {
//  dichiarazioni globali
	String Id;
	InterS interf;

	public Program_F(InterS interS, String id) throws RemoteException {
		this.Id = id;
		this.interf = interS;
//		stampa l'ack
		System.out.println(interf);
	}

	public void run() {
//		chiedi il link
		String s;
		while (true) {
			s = getUrl();
			URL url;
			try {
				url = new URL(s);
				String html = LeggiHTML(url);
				interf.aggiornaSito(html, url);
			} catch (MalformedURLException e) {
				System.out.println("URL non valido; ");
//				e.printStackTrace();
			} catch (RemoteException e) {
				System.out.println("Remote Exception; ");
				e.printStackTrace();
			}
		}
	}

	private synchronized String LeggiHTML(URL sito) {
		StringBuilder result = new StringBuilder();
		try {
//			per la copia della pagina web
			HttpURLConnection urlConnection = (HttpURLConnection) sito.openConnection();
			InputStream in = sito.openStream();
//			fa la parte di lettura e scrittura
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
//			stampo sia l'url della pagina selezionata che l'HTML
			System.out.println("L'url è: " + sito + "\nE l'HTML è: " + result.toString());
			urlConnection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();

	}

	private synchronized String getUrl() {
		try {
			System.out.println("Di quale sito vuoi prendere la pagina? ");
			Scanner scan = new Scanner(System.in);
			String s = scan.nextLine();
//  		assegna la stringa a url
			return s;
		} catch (Exception e) {
			return getUrl();
		}
	}

}