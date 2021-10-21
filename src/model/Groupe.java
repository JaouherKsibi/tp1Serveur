package model;

import java.util.List;

public class Groupe {
	private String titre;
	private List<Etudiant> listeEtudiant;
	private List<Meassage> listeMessage;
	public Groupe() {
		super();
	}
	public Groupe(String titre, List<Etudiant> listeEtudiant, List<Meassage> listeMessage) {
		super();
		this.titre = titre;
		this.listeEtudiant = listeEtudiant;
		this.listeMessage = listeMessage;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public List<Etudiant> getListeEtudiant() {
		return listeEtudiant;
	}
	public void setListeEtudiant(List<Etudiant> listeEtudiant) {
		this.listeEtudiant = listeEtudiant;
	}
	public List<Meassage> getListeMessage() {
		return listeMessage;
	}
	public void setListeMessage(List<Meassage> listeMessage) {
		this.listeMessage = listeMessage;
	}
	@Override
	public String toString() {
		return "Groupe [titre=" + titre + ", listeEtudiant=" + listeEtudiant + ", listeMessage=" + listeMessage + "]";
	}
	

}
