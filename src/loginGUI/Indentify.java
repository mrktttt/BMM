package loginGUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import data.Profil;

public class Indentify {
	private String mail;
	private String prenom;
	private String nom;
	private String numero;
	private Profil prof;
	
    public Indentify(){}
    
    public boolean createFich(String mail, String firstName, String lastName, String phone, String password){
        if (isProfileHere(mail)) {
            return false;
        }else {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/comptes/" + mail + ".txt"));
            out.write(password);
            out.newLine();
            out.write(mail);
            out.newLine();
            this.mail=mail;
            out.write(firstName);
            out.newLine();
            prenom=firstName;
            out.write(lastName);
            out.newLine();
            nom=lastName;
            out.write(phone);
            out.newLine();
            numero=phone;
            out.close(); 
            prof = new Profil(lastName, firstName, mail, phone, password);
                               
            /*                                
            PrintWriter outWriter = new PrintWriter(new BufferedWriter(new FileWriter("./compte.txt")));
            outWriter.println(email.getText() + ";" + new String(newPassword.getPassword()));
            outWriter.close();
            */
            return true;
        }catch(IOException ex) {
            ex.printStackTrace();
        }
        return false;
        }
    }

    public String getMail() {
		return mail;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}

	public String getNumero() {
		return numero;
	}
	
	public Profil getProf() {
		return prof;
	}

	//Vérifie si le profil est bien présent (adresse mail)
    public boolean isProfileHere(String identifiant) {
        File f = new File("src/comptes/" + identifiant + ".txt");
        if(f.exists()){
            return true;
        }
        return false;
    }

    //Si adresse mail & mdp ok
    public Boolean connectionCheck(String mail, String mdp) throws IOException 
    {
        if (!isProfileHere(mail)) {
            //mail non présent
            return false;
        }

        BufferedReader conditionReader = new BufferedReader(new FileReader("src/comptes/" + mail + ".txt"));
        if ((conditionReader.readLine().startsWith(mdp)) && ((conditionReader.readLine().startsWith(mail)))) {
                conditionReader.close();
                return true;
        }
        conditionReader.close();
        return false;
    }
}
