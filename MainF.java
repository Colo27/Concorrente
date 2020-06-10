import java.rmi.RemoteException;

public class MainF {

	public MainF() {
	}
// faccio così per lanciarne due più comodamente
	void myRun() throws RemoteException {
		new Program_F(1);
		new Program_F(2);
	}

	public static void main(String[] args) throws RemoteException {
		new MainF().myRun();
	}
}
