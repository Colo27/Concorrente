import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Program_C extends UnicastRemoteObject implements InterC {
	private static final long serialVersionUID = 1L;
	
    public Program_C() throws RemoteException {
		super();
	}
    
    
//	questo è per far andare il server dell'rmi
	public static void main(String[] args) {
		try {
			Registry reg = LocateRegistry.createRegistry(1099);
//			System.setProperty("java.rmi.server.hostname", "192.168.1.138");
			InterC intrC = new Program_C(); 
			reg.rebind("Program_C", intrC);
			System.out.println("Server bounded in registry");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
}
