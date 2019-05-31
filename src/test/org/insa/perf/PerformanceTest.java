package org.insa.perf;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.shortestpath.ShortestPathSolution;
import org.insa.graph.Graph;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.insa.algo.AbstractSolution;


public class PerformanceTest {
	
	/*@BeforeClass
	public static void initFileTestNodes() throws Exception {
		CreateFileTestNodes("reunion");
	}*/
	
	@Test
	public void MakeTest() throws Exception{
		CreateFileTestNodes("picardie");
		//System.out.println("fichier de test créé");
		//InfoInFile fileT = new InfoInFile("src\\test\\picardie_temps_100_data.txt");
		//System.out.println("info récupérées");
		InfoInFile fileD = new InfoInFile("src\\test\\picardie_distance_100_data.txt");
		/*System.out.println(file.getMapName());
		System.out.println(file.getMode());
		System.out.println(file.getNbNodes());
		System.out.println(file.getListeOrigine().size());*/
		//ArrayList<TempsExeAlgos> resultsT = new ArrayList<TempsExeAlgos>();
		ArrayList<TempsExeAlgos> resultsD = new ArrayList<TempsExeAlgos>();
		for (int i =0; i<100;i++) {
			//resultsT.add(new TempsExeAlgos(fileT.getMapName(),fileT.getMode(),fileT.getListeOrigine().get(i),fileT.getListeDestination().get(i)));
			resultsD.add(new TempsExeAlgos(fileD.getMapName(),fileD.getMode(),fileD.getListeOrigine().get(i),fileD.getListeDestination().get(i)));
			
		}
		
		//CreateFileResults(fileT.getMapName(), fileT.getMode(), resultsT);
		CreateFileResults(fileD.getMapName(), fileD.getMode(), resultsD);
		
	}

	
	
	
	public void CreateFileTestNodes(String mapName) throws Exception {
		 String Delimiter = " ";
		 String newLineSeparator ="\r\n";
		 
		 FileWriter fileDist = new FileWriter("src\\test\\"+mapName+"_distance_100_data.txt");
		 FileWriter fileTime = new FileWriter("src\\test\\"+mapName+"_temps_100_data.txt");
		 
		 fileDist.append(mapName);
		 fileDist.append(newLineSeparator);
		 fileDist.append(/*"mode */String.valueOf(0)/* : dist"*/);
		 fileDist.append(newLineSeparator);
		 
		 fileTime.append(mapName);
		 fileTime.append(newLineSeparator);
		 fileTime.append(/*"mode */String.valueOf(1)/* : time"*/);
		 fileTime.append(newLineSeparator);
		 
		 GraphReader reader = new BinaryGraphReader (new DataInputStream(new BufferedInputStream(new FileInputStream("D:\\INSA\\3A\\"+mapName+".mapgr"))));
		 Graph graph = reader.read();
		 int nbNodes=graph.size();
		 
		 fileDist.append(/*"nombre de Node total : "+*/String.valueOf(nbNodes));
		 fileDist.append(newLineSeparator);
		 fileTime.append(/*"nombre de Node total : "+*/String.valueOf(nbNodes));
		 fileTime.append(newLineSeparator);
		 
		 Random rand = new Random(); 
		 int idAleaOrigine;
		 int idAleaDest;
		 
		 ArcInspector arcInspector = new ArcInspectorFactory().getAllFilters().get(0);
		 
		 int i=0;
		 while (i<100) {
			 idAleaOrigine = rand.nextInt(nbNodes + 1);
			 idAleaDest = rand.nextInt(nbNodes + 1);
			 ShortestPathData data = new ShortestPathData(graph, graph.get(idAleaOrigine),graph.get(idAleaDest), arcInspector);
			 DijkstraAlgorithm DijkstraAlgo = new DijkstraAlgorithm(data);
			 ShortestPathSolution DijkstraSolution = DijkstraAlgo.run();
			 if (DijkstraSolution.getStatus() != AbstractSolution.Status.INFEASIBLE) {
				 fileDist.append(String.valueOf(idAleaOrigine));
				 fileDist.append(Delimiter);
				 fileDist.append(String.valueOf(idAleaDest));
				 fileDist.append(newLineSeparator);
				 fileTime.append(String.valueOf(idAleaOrigine));
				 fileTime.append(Delimiter);
				 fileTime.append(String.valueOf(idAleaDest));
				 fileTime.append(newLineSeparator);
				 i++;
				 
			 }
			 			 
		 }
		 fileDist.flush();
		 fileDist.close();
		 fileTime.flush();
		 fileTime.close();
		 
		 
		
	}

	public void CreateFileResults(String mapName, int mode, ArrayList<TempsExeAlgos> results) throws Exception{
		String Delimiter = ",";
		String newLineSeparator ="\r\n";
		 
		FileWriter fileResults = new FileWriter("src\\test\\"+mapName+"_mode-"+mode+"_resultats_.txt");
		
		fileResults.append(mapName);
		fileResults.append(newLineSeparator);
		fileResults.append(/*"mode */String.valueOf(mode));
		fileResults.append(newLineSeparator);
		for (int i = 0; i<100 ; i++) {
			TempsExeAlgos currentResults = results.get(i); 
			fileResults.append(String.valueOf(currentResults.getOrigine()));
			fileResults.append(Delimiter);
			fileResults.append(String.valueOf(currentResults.getDestination()));
			fileResults.append(Delimiter);
			fileResults.append(String.valueOf(currentResults.getTempsExecutionDijkstra()));
			fileResults.append(Delimiter);
			fileResults.append(String.valueOf(currentResults.getVisiteD()));
			fileResults.append(Delimiter);
			fileResults.append(String.valueOf(currentResults.getMarqueD()));
			fileResults.append(Delimiter);
			fileResults.append(String.valueOf(currentResults.getTempsExecutionAStar()));
			fileResults.append(Delimiter);
			fileResults.append(String.valueOf(currentResults.getVisiteA()));
			fileResults.append(Delimiter);
			fileResults.append(String.valueOf(currentResults.getMarqueA()));
			fileResults.append(Delimiter);
			fileResults.append(String.valueOf(currentResults.getDonneeComp()));
			fileResults.append(newLineSeparator);
		}
		
		
		
		fileResults.flush();
		fileResults.close();
		
		
	}
	
}