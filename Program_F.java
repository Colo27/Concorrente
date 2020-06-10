import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Program_F extends Thread {
//  dichiarazioni globali
	int Id;
	InterS interf;

	public Program_F(int id) {
		Id = id;
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();
			InterS interf = (InterS) registry.lookup("Program_S");
//			stampa l'ack
			System.out.println(interf);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		start();
	}

	public void run() {
//		chiedi il link
		InterS interf = null;
		try {
			Registry registry = LocateRegistry.getRegistry();
			interf = (InterS) registry.lookup("Program_S");

		} catch (Exception e) {
			System.out.println("ERROR " + e.getStackTrace());
		}

		String s;
		while (true) {
			s = getUrl();
			URL url;
			try {
				url = new URL(s);
				interf.LeggiHTML(url);
			} catch (MalformedURLException | RemoteException e) {
				System.out.println("URL no valido; " );
			}
		}
	}

	public String getUrl() {
		try {
			System.out.println("Di quale sito vuoi prendere la pagina? " );
			Scanner scan = new Scanner(System.in);
			String s = scan.nextLine();
//  		assegna la stringa a url
			return s;
		} catch (Exception e) {
			return getUrl();
		}
	}

}