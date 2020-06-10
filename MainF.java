import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainF {

	public MainF() {
	}
// faccio così per lanciarne due più comodamente
	void myRun() throws RemoteException, Exception {
		Registry registro = LocateRegistry.getRegistry(1099);
		InterS stub = (InterS) registro.lookup("Program_S");
		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(new Program_F(stub, "Program F " + (i+1)));
			t.start();
		}
	}

	public static void main(String[] args) throws Exception {
		new MainF().myRun();
	}
}
