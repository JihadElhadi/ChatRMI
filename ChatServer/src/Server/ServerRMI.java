package Server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Metier.Chat;
import Metier.IChat;

public class ServerRMI {
	
	public static void main(String[] args) throws  RemoteException, MalformedURLException, AlreadyBoundException {
        try {
            System.out.println("Starting Server");
            LocateRegistry.createRegistry(1090);
            IChat chat = new Chat();
            Naming.rebind("rmi://localhost:1090/chat", chat); 
            System.out.println("Server Started");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } 
    }

}
