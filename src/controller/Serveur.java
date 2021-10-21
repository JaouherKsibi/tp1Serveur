package controller;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

import model.Etudiant;
import model.Groupe;
import model.Meassage;

public class Serveur {
	private static List<Groupe> groupe;
	private static List<Etudiant> etudiants;
	private static List<Meassage> messages;
	public static void main(String[] args) {
		groupe=new ArrayList<Groupe>();
		etudiants=new ArrayList<Etudiant>();
		messages=new ArrayList<Meassage>();
		try {
			DatagramSocket socket=new DatagramSocket(3000);
			System.out.println("serveur listening on port number 3000");
			while(true) {
				
				DatagramPacket packet =new DatagramPacket(new byte[1024], 1024);
				socket.receive(packet);
				String msgRecu=new String(packet.getData(),0,packet.getLength());
				//System.out.println(msgRecu);
				if (msgRecu.startsWith("##")) {
					String[] etd=  msgRecu.split("#");
					Serveur.etudiants.add(new Etudiant(etd[3],etd[2],etd[4],true,packet.getAddress(),packet.getPort()));	
				}
				else if (msgRecu.equals("#LIST")) {
					String msg="la liste des (noms) des etudiants connectés est: ";
					for (Etudiant etd : Serveur.etudiants) {
						msg=msg+etd.getNom()+" et ";
					}
					DatagramPacket packet2=new DatagramPacket(msg.getBytes(),msg.length(),packet.getAddress(),packet.getPort());
					socket.send(packet2);
				}
				/******************il faut integrer le sender ********************/
				else if (msgRecu.equals("#HISTO")) {
					Etudiant sender=new Etudiant();
					for (Etudiant etudiant : Serveur.etudiants) {
						if(etudiant.getPort()==packet.getPort()) {
							sender=etudiant;
						}
					}
					String msg="les message Envoyé par "+sender.getNom() +" sont :\n";
					for (Meassage msg1 : Serveur.messages) {
						if (msg1.getSender().getPort()==packet.getPort()) {
							msg=msg+msg1.getMessage()+":à "+msg1.getReceiver().getNom()+" \n";
						}
					}
					
					DatagramPacket packet2=new DatagramPacket(msg.getBytes(),msg.length(),packet.getAddress(),packet.getPort());
					socket.send(packet2);
				}
				else if (msgRecu.equals("#GRPS")) {
					String msg="la liste de groupe:\n";
					for (Groupe gp : Serveur.groupe) {
						msg=msg+gp.getTitre()+"\n";
					}
					
					DatagramPacket packet2=new DatagramPacket(msg.getBytes(),msg.length(),packet.getAddress(),packet.getPort());
					socket.send(packet2);
				}
				/*******************creation de groupe *****************************/
				else if (msgRecu.startsWith("#GRP#")) {
					String [] msg1= msgRecu.split("#");
					List<Etudiant> le=new ArrayList<Etudiant>();
					for (Etudiant etudiant : Serveur.etudiants) {
						if(etudiant.getPort()==packet.getPort()) {
							le.add(etudiant);
						}
					}
					List<Meassage> lm=new ArrayList<Meassage>();
					Serveur.groupe.add(new Groupe(msg1[2],le,lm));
				}
				else if (msgRecu.startsWith("@#")) {
					Etudiant sender=new Etudiant();
					for (Etudiant etudiant : Serveur.etudiants) {
						if(etudiant.getPort()==packet.getPort()) {
							sender=etudiant;
						}
					}
					String [] msg1= msgRecu.split("@#");
					for (Etudiant etd : Serveur.etudiants) {
						if (etd.getLogin().equals(msg1[1])) {
							String msg=sender.getNom()+":"+msg1[2]+"\n";
							DatagramPacket packet2=new DatagramPacket(msg.getBytes(),msg.length(),etd.getAdress(),etd.getPort());
							socket.send(packet2);
							Serveur.messages.add(new Meassage(sender,etd,msg));
						}
					}
				}
				
				else if(msgRecu.startsWith("#>")) {
					String nomGroupe= msgRecu.substring(2) ;
					for (Groupe gp : Serveur.groupe) {
						if(gp.getTitre().equals(nomGroupe)) {
							List<Etudiant> l=gp.getListeEtudiant();
							for (Etudiant etudiant : Serveur.etudiants) {
								if(etudiant.getPort()==packet.getPort()) {
									l.add(etudiant);
								}
							}
							
							gp.setListeEtudiant(l);
						}
					}
				}
				else if(msgRecu.startsWith("#ETDS#")) {
					String nomGroupe= msgRecu.substring(6) ;
					String msgAEnvoyer ="les etudiants du groupe "+nomGroupe+" sont :\n\n " ;
					for (Groupe gp : Serveur.groupe) {
						if(gp.getTitre().equals(nomGroupe)) {
							for (Etudiant etudiant : gp.getListeEtudiant()) {
								msgAEnvoyer=msgAEnvoyer+etudiant.getNom()+" \n\n";
							}
					
						}
					}
					DatagramPacket packet2=new DatagramPacket(msgAEnvoyer.getBytes(),msgAEnvoyer.length(),packet.getAddress(),packet.getPort());
					socket.send(packet2);
					
				}
				else if (msgRecu.startsWith("@>")) {
					Etudiant sender=new Etudiant();
					for (Etudiant etudiant : Serveur.etudiants) {
						if(etudiant.getPort()==packet.getPort()) {
							sender=etudiant;
						}
					}
					String [] ms=msgRecu.split("@>");
					for (Groupe gp : Serveur.groupe) {
						if(gp.getTitre().equals(ms[1])) {
							String msgAEnvoyer=ms[2];
							for (Etudiant et : gp.getListeEtudiant()) {
								if (!(et.getPort()==sender.getPort())) {
									DatagramPacket packet2=new DatagramPacket(msgAEnvoyer.getBytes(),msgAEnvoyer.length(),et.getAdress(),et.getPort());
									socket.send(packet2);
									Serveur.messages.add(new Meassage(sender,et,msgAEnvoyer));
								}
								
							}
						}
					}
					
				}
				
			}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}

}
