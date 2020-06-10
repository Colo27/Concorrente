import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MainF {

	public MainF() {
	}
// faccio cos� per lanciarne due pi� comodamente
	void myRun() throws RemoteException, Exception {
		Scanner scan = new Scanner(System.in);
		Registry registro = LocateRegistry.getRegistry(1099);
		InterS stub = (InterS) registro.lookup("Program_S");
		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(new Program_F(stub, "Program F " + (i+1), scan));
			t.start();
		}
	}

	public static void main(String[] args) throws Exception {
		new MainF().myRun();
	}
}
