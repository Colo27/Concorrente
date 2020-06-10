import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterC extends Remote {
	void ricezione(String s) throws RemoteException;

}
