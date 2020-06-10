import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Program_C extends UnicastRemoteObject implements InterC, Runnable {

	private InterS stub;
	private String myName;
//	timer per scadenza della connessione
	Long timer = System.currentTimeMillis();

	public Program_C(InterS stub, String name) throws RemoteException {
		super();
		this.myName = name;
//			this.stub = (InterS) registro.lookup("Program_S");
		this.stub = stub;
//					attacco l'abbonamento
		this.stub.abbonati(this);
		// Program_C pc = new Program_C(stub, "");
		System.out.println(myName + " si è collegato");
//		run();
	}

	@Override
	public void ricezione(String s, URL url) throws RemoteException {
		System.out.println("l'HTML è: " + s);
		System.out.println("l'URL è: " + url);
	}

	public void run() {
		try {
//		questo è per il timer di disconnessione dell'abbonamento
			while (true) {
//				prendo il tempo che è passato
				Long endTime = System.currentTimeMillis();
				if (endTime - timer > 10000) {
					Random random = new Random();
					int i = random.nextInt(50);
//                  a caso si stacca se la divisione ha 0 di resto
					if (i % 4 == 0) {
						System.out.println("Il client " + myName + " ha cancellato il suo abbonamento");
						TimeUnit.SECONDS.sleep(1);
						stub.disabbonati(this);
						System.exit(0);
					}

					timer = System.currentTimeMillis();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
//			System.err.println("Errore di disconnessione");
		}
	}
}
