package Metier;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import Model.Message;

public interface IChat extends Remote{
	
	public void createMessage(String msg,String idS,String date) throws RemoteException;
    public void sendMessage(Message message) throws RemoteException;
    public ArrayList<Message> readMessage() throws RemoteException;
    public void addUser(String user) throws RemoteException;
    public ArrayList<String> getUsers() throws RemoteException;
}
