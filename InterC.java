import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterC extends Remote {
	void ricezione(String s, URL url) throws RemoteException;

}
