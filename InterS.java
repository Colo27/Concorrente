import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterS extends Remote {

	public void LeggiHTML(URL sito) throws RemoteException;

}
