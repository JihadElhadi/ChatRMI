package Presentation;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimerTask;

import javax.swing.*;

import Metier.IChat;
import Model.Message;

public class Frame extends JFrame implements ActionListener {
	
	Container container = getContentPane();
    String username;
    JLabel label = new JLabel("MESSENGER");
    JLabel userLabel = new JLabel("Users");
    JLabel convLabel = new JLabel("Conversation");
    JTextArea conversation = new JTextArea();
    JTextArea users = new JTextArea();
    JTextPane message = new JTextPane();
    JButton sendButton = new JButton("SEND");
    JButton refreshButton = new JButton("REFRESH");
    IChat chat;
    String all,allUsers;
	
    public Frame(String user) throws MalformedURLException, RemoteException, NotBoundException{
    	setTitle("Messenger");
        setVisible(true);
        setBounds(500,50, 430,620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        this.username = user;
		chat= (IChat)Naming.lookup("rmi://localhost:1090/chat");
		chat.addUser(this.username);
		conversation.setText(this.username+" a rejoint la conversation\n");
		}
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
        label.setFont(new java.awt.Font("Calibri", 1, 46));
        label.setBounds(80, 20, 300, 50);
        userLabel.setFont(new java.awt.Font("Calibri", 1, 21));
        userLabel.setBounds(43, 85, 100, 20);
        convLabel.setFont(new java.awt.Font("Calibri", 1, 21));
        convLabel.setBounds(200, 85, 300, 20);
        conversation.setBounds(130, 110, 280, 380);
        users.setBounds(20, 110,100, 380);
        message.setBounds(200, 500, 170, 40);
        sendButton.setBounds(230, 550, 100, 38);
        sendButton.setFont(new java.awt.Font("Calibri", 1, 14));
        sendButton.setBackground(new Color(30,30,30));
        sendButton.setForeground(Color.white);
        refreshButton.setForeground(Color.white);
        refreshButton.setFont(new java.awt.Font("Calibri", 1, 14));
        refreshButton.setBounds(21, 520, 100, 38);
        refreshButton.setBackground(new Color(30,30,30));
        container.setBackground(new Color(218,218,218));
        users.disable();
        conversation.disable();
        users.setDisabledTextColor(Color.RED);
        conversation.setDisabledTextColor(Color.BLACK);
    }
 
    public void addComponentsToContainer() throws RemoteException {
        container.add(label);
        container.add(conversation);
        container.add(message);
        container.add(sendButton);
        container.add(refreshButton);
        container.add(users);
        container.add(userLabel);
        container.add(convLabel);
    }
 
    public void addActionEvent() {
        sendButton.addActionListener(this);
        refreshButton.addActionListener(this);
    }
    
    public void refresh() {
    	users.setText(allUsers);
		conversation.setText(all);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
    	all="";allUsers="";
    	if (e.getSource() == sendButton) {
			try {
				if(this.message.getText().equals("exit")){
					this.dispose();
				}
				else{
					chat.sendMessage(new Message(this.message.getText(),this.username,(new SimpleDateFormat("HH:mm")).format(new Date())));
				}
				
				
			} catch ( Exception ex) {
				ex.printStackTrace();
			}
    	}
    	
    	try {
			for(Message m : chat.readMessage()){
				all = all+m.getDate()+"  "+m.getID_sender()+" : "+m.getMessage()+"\n";
			}
			for(String u : chat.getUsers()){
				allUsers = allUsers+u+"\n";
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
    	this.refresh();
		
		if (e.getSource() == refreshButton) {
			this.refresh();
		}
        
    }
    
}
