package model;

public class Meassage {
	private Etudiant sender;
	private Etudiant receiver ;
	private String message;
	public Meassage() {}
	public Meassage(Etudiant sender, Etudiant receiver, String message) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
	}
	public Etudiant getSender() {
		return sender;
	}
	public void setSender(Etudiant sender) {
		this.sender = sender;
	}
	public Etudiant getReceiver() {
		return receiver;
	}
	public void setReceiver(Etudiant receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Meassage [sender=" + sender + ", receiver=" + receiver + ", message=" + message + "]";
	}

}
