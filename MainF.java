import java.rmi.RemoteException;

public class MainF {

	public MainF() {
	}

	void myRun() throws RemoteException {
		new Program_F(1);
		new Program_F(2);
	}

	public static void main(String[] args) throws RemoteException {
		new MainF().myRun();
	}
}
