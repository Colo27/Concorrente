import java.rmi.RemoteException;

public class MainC {

	public MainC() {
	}
	
	void myRun() throws RemoteException {
		new Program_C("Client 1");
		new Program_C("Client 2");
		new Program_C("Client 3");
		new Program_C("Client 4");
		new Program_C("Client 5");
	}

	public static void main(String[] args) throws RemoteException {
		new MainC().myRun();
	}
}
