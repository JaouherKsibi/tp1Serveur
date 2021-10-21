package model;

import java.net.InetAddress;

public class Etudiant {
	private String nom;
	private String login;
	private String niveau ;
	private boolean etat ;
	private InetAddress adress;
	private int port ;
	public Etudiant() {
	}
	public Etudiant(String nom, String login, String niveau, boolean etat, InetAddress adress, int port) {
		super();
		this.nom = nom;
		this.login = login;
		this.niveau = niveau;
		this.etat = etat;
		this.adress = adress;
		this.port = port;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public boolean isEtat() {
		return etat;
	}
	public void setEtat(boolean etat) {
		this.etat = etat;
	}
	public InetAddress getAdress() {
		return adress;
	}
	public void setAdress(InetAddress adress) {
		this.adress = adress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", login=" + login + ", niveau=" + niveau + ", etat=" + etat + ", adress="
				+ adress + ", port=" + port + "]";
	}
	


}
