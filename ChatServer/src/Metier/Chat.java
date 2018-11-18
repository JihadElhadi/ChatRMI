package Metier;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Model.Message;

public class Chat extends UnicastRemoteObject implements IChat, Serializable {

    ArrayList<Message> messages = new ArrayList<>();
    ArrayList<String> users = new ArrayList<>();

    public Chat() throws RemoteException {
        super();    
    }
    
    public void createMessage(String msg,String idS,String date) throws RemoteException {
    	Message message = new Message(msg,idS,date);
    }

    @Override
    public synchronized void sendMessage(Message msg) throws RemoteException {
        messages.add(msg);
    }

    @Override
    public synchronized ArrayList<Message> readMessage() throws RemoteException {
        return messages;
    }
    
    @Override
    public void addUser(String user) throws RemoteException {
        users.add(user);
    }
    
    @Override
    public synchronized ArrayList<String> getUsers() throws RemoteException {
        return users;
    }
    

}
