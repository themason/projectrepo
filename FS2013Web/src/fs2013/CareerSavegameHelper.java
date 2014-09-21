package fs2013;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import fs2013.CareerSavegame;


public class CareerSavegameHelper {
	private JAXBContext jc = null;
	private Unmarshaller u;
	private CareerSavegame careerSavegame = null;
	
	public CareerSavegameHelper() throws JAXBException, PropertyException {
		try {
			jc = JAXBContext.newInstance(CareerSavegame.class);
			u = jc.createUnmarshaller();
			
			careerSavegame = (CareerSavegame)u.unmarshal(
					new File("careerSavegame.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public CareerSavegame getCareerSavegame() {
		return careerSavegame;
	}
}
