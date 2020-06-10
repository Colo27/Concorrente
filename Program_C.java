import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Program_C extends UnicastRemoteObject implements InterC {
	private static final long serialVersionUID = 1L;

	private InterS stub;
	private String myName;
	Long timer = System.currentTimeMillis();

	public Program_C(String name) throws RemoteException {
		super();
		this.myName = name;
	}

//	questo è per connessione rmi
	public static void main(String[] args) throws RemoteException {
		try {
			Registry registro = LocateRegistry.createRegistry(1099);
			InterS stub = (InterS) registro.lookup("Program_S");
//			new Program_C(stub);
			InterC intrC = new Program_C("Client");
//			registro.rebind("Program_C", intrC);
			System.out.println("Program C " + " si è collegato");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void ricezione(String s) throws RemoteException {
		System.out.println(s);
	}
	
	public void run ()
    {
//		questo è per il timer di disconnessione dell'abbonamento
		while(true)
        {
			try
            {
                Long endTime = System.currentTimeMillis();
                if (endTime - timer > 10000) {
                    Random random =  new Random();
                    int i = random.nextInt(50);
                    if (i%2==0){
                        System.out.println("Il client ha cancellato il suo abbonamento");
                        TimeUnit.SECONDS.sleep(1);
                        stub.disabbonati(this);
                        System.exit(0);
                    }

                    timer = System.currentTimeMillis();
                }


            }
            catch(Exception e)
            {
                System.err.println("problem");
            }
        }
    }

}
