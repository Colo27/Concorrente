import java.net.URL;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterS extends Remote {

	public void aggiornaSito(String sito, URL url) throws RemoteException;

	public void abbonati(InterC ic) throws RemoteException;

	public void disabbonati(InterC ic) throws RemoteException;

	public void contattaClient(String s, URL url) throws RemoteException;

}