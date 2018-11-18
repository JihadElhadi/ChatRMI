package Presentation;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.*;

import Metier.IChat;

public class Login extends JFrame implements ActionListener  {

	Container container = getContentPane();
    JLabel label = new JLabel("USERNAME");
    JTextField username = new JTextField();
    JButton loginButton = new JButton("JOIN");
    String user;
    public Frame frame;
    IChat chat;

    public Login() throws MalformedURLException, RemoteException, NotBoundException{
    	setTitle("Messenger");
        setVisible(true);
        setBounds(500,200, 400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    	chat= (IChat)Naming.lookup("rmi://localhost:1090/chat");
    }
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
        label.setFont(new java.awt.Font("Calibri", 1, 46));
        label.setBounds(80, 20, 300, 50);
        username.setBounds(50, 90, 180, 30);
        loginButton.setBounds(250, 90, 90, 29);
        loginButton.setFont(new java.awt.Font("Calibri", 1, 14));
        loginButton.setBackground(new Color(30,30,30));
        loginButton.setForeground(Color.white);
        container.setBackground(new Color(218,218,218));
    }
 
    public void addComponentsToContainer() {
        container.add(label);
        container.add(username);
        container.add(loginButton);
    }
 
    public void addActionEvent() {
        loginButton.addActionListener(this);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
        	boolean test = true;
            try {
				for(String u : chat.getUsers()){
					if(username.getText().equals(u)) {
					    test = false; 
					    break;
					}
				}
				
				if(test == false) {
				    JOptionPane.showMessageDialog(this,"Username already used","WARNING",
			                   JOptionPane.WARNING_MESSAGE);
				}
				else {
				    this.user = username.getText();
					this.dispose();
		            try {
						frame = new Frame(this.user);
						
					} catch (MalformedURLException | RemoteException | NotBoundException e1) {
						e1.printStackTrace();
					}
				}
				
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
            
            
        }
    }

}
