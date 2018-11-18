package Client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Presentation.Login;
//https://github.com/JihadElhadi/ChatRMI

public class Demo{

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException{
		Login log = new Login();
	}

}
