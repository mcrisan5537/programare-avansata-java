import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {

    public String handleInput(String string) throws RemoteException;
    public String getInput() throws RemoteException;

}
