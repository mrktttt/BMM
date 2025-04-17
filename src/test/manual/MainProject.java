package test.manual;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.text.DefaultStyledDocument;

import org.apache.log4j.Logger;

import data.Entreprise;
import data.Profil;
import gui.elements.MainFrame;
import log.LoggerUtility;
import loginGUI.InterfaceDemo;
import process.ChartManager;
import process.Factory;
import supervisorGUI.MainFrameS;

/**
 * This class start all of the simulation
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class MainProject {
	
	private static HashMap<String,Factory> factorys = new HashMap<String,Factory>();
	private static HashMap<String,DefaultStyledDocument> news = new HashMap<String,DefaultStyledDocument>();
	private static HashMap<Entreprise,ChartManager> hm = new HashMap<Entreprise,ChartManager>();
	private static HashMap<String,HashMap<Entreprise,ChartManager>> hmap = new HashMap<String,HashMap<Entreprise,ChartManager>>();
	
	private static Logger logger = LoggerUtility.getLogger(Factory.class,"html");
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		
		File fileF = new File("src/compte.ser");
		File fileH = new File("src/graph.ser");
		File fileN = new File("src/news.ser");
		if(fileF.exists()) {
			factorys = readFact();
		}
		if(fileH.exists()) {
			hmap = readHM();
		}
		if(fileN.exists()) {
			news = readNews();
		}
		
		InterfaceDemo id = new InterfaceDemo();
		Thread th = new Thread(id);
		th.start();
		
		try {
			th.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ArrayList<Profil> profils = id.getConnexion().getAccount().getProfils();
		Iterator<Profil> iterator = profils.iterator();
		while(iterator.hasNext()) {
			Profil p = iterator.next();
			Factory fact = new Factory(logger,p);
			factorys.put(p.getMail(), fact);
		}
		
		writeFact();
		
		int nb = id.getNb();
		//Factory fact = new Factory(logger);
		
		String email = id.getConnexion().getEmail();
		
		if(nb == 0) {
			//MainFrame mf = new MainFrame(fact);
			//if user already exist
			
			Factory fact = factorys.get(email);
			fact.setLogger(logger);
			MainFrame mf = new MainFrame(fact);
			if(hmap.containsKey(fact.getU().getProf().getMail())) {
				hm = hmap.get(fact.getU().getProf().getMail());
				Iterator<Entreprise> it = fact.getEnts().iterator();
				HashMap<Entreprise,ChartManager> hm1 = new HashMap<Entreprise,ChartManager>();
				while (it.hasNext()) {
					Entreprise e = it.next();
					Set<Entreprise> key = hm.keySet();
					Iterator<Entreprise> itKey = key.iterator();
					while (itKey.hasNext()) {
						Entreprise eKey = itKey.next();
						if (e.getNom().equals(eKey.getNom())) {
							hm1.put(e,hm.get(eKey));
						}
					}
				}
				hm = hm1;
				mf.getG().setEnts(hm);
			} else {
				hm = mf.getG().getEnts();
				hmap.put(fact.getU().getProf().getMail(), hm);
			}
			
			if(news.containsKey(fact.getU().getProf().getMail())) {
				DefaultStyledDocument text = news.get(fact.getU().getProf().getMail());
				mf.getN().setDocument(text);
			} else {
				news.put(fact.getU().getProf().getMail(),mf.getN().getDocument());
			}
			
			
			
			////////
		
			Thread t = new Thread(mf);
			t.start();
		
			try {
				t.join();
				fact = mf.getFact();
				hm = mf.getG().getEnts();
				hmap.put(fact.getU().getProf().getMail(), hm);
				factorys.put(fact.getU().getProf().getMail(),fact);
				news.put(fact.getU().getProf().getMail(),mf.getN().getDocument());
				writeNews();
				writeFact();
				writeHM();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		
		
		} else if (nb == 1){
		
			//if user already exist
			
			
			Factory fact = factorys.get(email);
			fact.setLogger(logger);
			MainFrameS mfs = new MainFrameS(fact);
			if(hmap.containsKey(fact.getU().getProf().getMail())) {
				hm = hmap.get(fact.getU().getProf().getMail());
				Iterator<Entreprise> it = fact.getEnts().iterator();
				HashMap<Entreprise,ChartManager> hm1 = new HashMap<Entreprise,ChartManager>();
				while (it.hasNext()) {
					Entreprise e = it.next();
					Set<Entreprise> key = hm.keySet();
					Iterator<Entreprise> itKey = key.iterator();
					while (itKey.hasNext()) {
						Entreprise eKey = itKey.next();
						if (e.getNom().equals(eKey.getNom())) {
							hm1.put(e,hm.get(eKey));
						}
					}
				}
				hm = hm1;
				mfs.getG().setEnts(hm);
			} else {
				hm = mfs.getG().getEnts();
				hmap.put(fact.getU().getProf().getMail(), hm);
			}
					
			if(news.containsKey(fact.getU().getProf().getMail())) {
				DefaultStyledDocument text = news.get(fact.getU().getProf().getMail());
				mfs.getN().setDocument(text);
			} else {
				news.put(fact.getU().getProf().getMail(),mfs.getN().getDocument());
			}
					
					
			
			////////
				
			Thread t = new Thread(mfs);
			t.start();
				
			try {
				t.join();
				fact = mfs.getFact();
				hm = mfs.getG().getEnts();
				hmap.put(fact.getU().getProf().getMail(), hm);
				factorys.put(fact.getU().getProf().getMail(),fact);
				news.put(fact.getU().getProf().getMail(),mfs.getN().getDocument());
				writeNews();
				writeFact();
				writeHM();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<String,Factory> readFact() throws FileNotFoundException, IOException {
		HashMap<String,Factory> hm = new HashMap<String,Factory>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/compte.ser"));
			hm = (HashMap<String,Factory>)(ois.readObject());
			ois.close();
			return hm;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return hm;
	}
	
	public static void writeFact() throws FileNotFoundException, IOException {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/compte.ser"));
			oos.writeObject(factorys);
			oos.close();
		} catch (NotSerializableException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static HashMap<String,HashMap<Entreprise,ChartManager>> readHM() throws FileNotFoundException, IOException {
		HashMap<String,HashMap<Entreprise,ChartManager>> map = new HashMap<String,HashMap<Entreprise,ChartManager>>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/graph.ser"));
			map = (HashMap<String,HashMap<Entreprise,ChartManager>>)(ois.readObject());
			ois.close();
			return map;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return map;
	}
	
	public static void writeHM() throws FileNotFoundException, IOException {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/graph.ser"));
			oos.writeObject(hmap);
			oos.close();
		} catch (NotSerializableException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static HashMap<String,DefaultStyledDocument> readNews() throws FileNotFoundException, IOException {
		HashMap<String,DefaultStyledDocument> hm = new HashMap<String,DefaultStyledDocument>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/news.ser"));
			hm = (HashMap<String,DefaultStyledDocument>)(ois.readObject());
			ois.close();
			return hm;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return hm;
	}
	
	public static void writeNews() throws FileNotFoundException, IOException {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/news.ser"));
			oos.writeObject(news);
			oos.close();
		} catch (NotSerializableException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
}
