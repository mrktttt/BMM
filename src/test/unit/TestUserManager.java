package test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import data.Achat;
import data.Entreprise;
import data.Profil;
import data.Vente;
import log.LoggerUtility;
import process.Factory;
import process.UserManager;

/**
 * Unit test of UserManager, for testing the last purchase and sales done by the user.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class TestUserManager {
	private Factory fact;
	private Achat ach;
	private Vente ven;
	private UserManager uM;
	private static Logger logger = LoggerUtility.getLogger(Factory.class,"html");
	private Profil prof = new Profil ("Razafinony","Mario","razafinonymario@gmail.com","07 66 28 63 63","bourse2024");
	
	@Before
	public void prepareAchatVente() {
		fact = new Factory(logger, prof);
		Entreprise tesla = fact.getEnt("Tesla");
		uM = new UserManager(fact.getU(),logger);
		ach = uM.achat(tesla);
		ven = uM.vente(tesla);
	}
	
	@Test
	public void testLastAchat() {
		assertEquals(ach,uM.getLastAchat());
	}
	
	@Test
	public void testLastVente() {
		assertEquals(ven,uM.getLastVente());
	}
}
