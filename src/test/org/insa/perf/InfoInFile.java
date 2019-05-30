package org.insa.perf;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/* Permet de récupérer les informations d'un fichier .txt : */
/* Nom de la carte, liste des origines, liste des destinations */
public class InfoInFile {

	private String map ;
	private int mode;
	private int nbNodes;
	private ArrayList<Integer> Origines;
	private ArrayList<Integer> Destinations;

	public InfoInFile(String file) {
		this.Origines = new ArrayList<Integer>();
		this.Destinations = new ArrayList<Integer>();
		this.mode=-1;
		this.nbNodes=-1;
		this.Read(file);
	}

	public void Read (String fileName) {
		try {
			Scanner scan = new Scanner(new File(fileName));
			
			map = scan.nextLine();
			
			mode=scan.nextInt();
			nbNodes=scan.nextInt();
			
			for (int i =0; i<100;i++) {
				Origines.add(scan.nextInt());
				Destinations.add(scan.nextInt());
			}
					
			
			
			scan.close();
		}
		catch(Exception e) {
				System.out.println(e.getMessage());
		}
	}

	public String getMapName() {
		return this.map;
	}

	public ArrayList<Integer> getListeOrigine(){
		return this.Origines;
	}

	public ArrayList<Integer> getListeDestination(){
		return this.Destinations;
	}
	
	public int getMode(){
		return this.mode;
	}
	
	public int getNbNodes(){
		return this.nbNodes;
	}
}