package test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import data.Entreprise;
import data.Profil;
import log.LoggerUtility;
import process.Factory;

/**
 * Unit test of Factory, the changes of the rate are tested.
 * 
 * @author RAZAFINONY TREUIL OUAMMOUR
 */
public class TestFactory {
	private Factory fact;
	private static Logger logger = LoggerUtility.getLogger(Factory.class,"html");
	private Profil prof = new Profil ("Razafinony","Mario","razafinonymario@gmail.com","07 66 28 63 63","bourse2024");
	
	@Before
	public void createFactory() {
		fact = new Factory(logger, prof);
	}
	
	@Test
	public void testChangTauxEntreprise() {
		Entreprise tesla = fact.getEnt("Tesla");
		fact.changTauxEntreprise(tesla, 5);
		assertEquals(5,fact.getEntsManag().get(tesla).getTaux());
	}
	
	@Test
	public void testChangTauxDom() {
		Entreprise tesla = fact.getEnt("Tesla");
		String dom = tesla.getDomaine();
		fact.changTauxDomaine(dom, 4);
		assertEquals(4,fact.getEntsManag().get(tesla).getTaux());
	}

}
