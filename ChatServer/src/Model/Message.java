package Model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{

	private String message;
	private String ID_sender;
	private String date;
	
	public Message(String message, String iD_sender,String date) {
		super();
		this.message = message;
		this.ID_sender = iD_sender;
		this.date=date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getID_sender() {
		return ID_sender;
	}
	public void setID_sender(String iD_sender) {
		ID_sender = iD_sender;
	}
    
}
