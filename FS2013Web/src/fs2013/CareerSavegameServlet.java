package fs2013;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@WebServlet(value="/savegame")
public class CareerSavegameServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JAXBContext jc = null;
	private Unmarshaller u;
	private Marshaller m;
	private CareerSavegame careerSavegame = null;
	private ObjectFactory objectFactory = new ObjectFactory();
	private Map<String, String> careerSavegameProperties;
	
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		URL saveGameFile = getServletContext().getResource("/WEB-INF/upload/careerSavegame.xml");
		URL schemaFile = getServletContext().getResource("/WEB-INF/careerSavegame.xsd");
		
		if (careerSavegame == null) {
			try {
				jc = JAXBContext.newInstance(CareerSavegame.class);
				u = jc.createUnmarshaller();
				
				careerSavegame = (CareerSavegame)u.unmarshal(saveGameFile);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		
		String doThis = req.getParameter("doThis");
		
		// if it is the first time, initialize the save game details
		if (doThis == null) {
			careerSavegameProperties = new HashMap<String, String>();
			careerSavegameProperties.put("money", careerSavegame.getMoney().toString());
			careerSavegameProperties.put("loan", careerSavegame.getLoan().toString());
			
			session.setAttribute("careerSavegameProperties.list", careerSavegameProperties);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
			rd.forward(req, res);
		} else if (doThis.equals("upload")) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(req, res);
		} else if (doThis.equals("update")) {
			String propertyName = req.getParameter("property");
			String propertyValue = req.getParameter("propertyValue");
			
			careerSavegameProperties.put(propertyName, propertyValue);
			
			session.setAttribute("careerSavegameProperties.list", careerSavegameProperties);
			
			careerSavegame.setMoney(new BigInteger(careerSavegameProperties.get("money")));
			careerSavegame.setLoan(new BigInteger(careerSavegameProperties.get("loan")));
			
			ByteArrayOutputStream careerSavegameXmlOutput = new ByteArrayOutputStream();
			
			if (m == null) {
				try {
					m = jc.createMarshaller();
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaFile.toString());
				} catch (JAXBException e) {
					e.printStackTrace();
				}
			}
			try {
				m.marshal(careerSavegame, careerSavegameXmlOutput);
				outputCareerSavegame(careerSavegameXmlOutput.toString());
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			session.setAttribute("careerSavegame.xml", careerSavegameXmlOutput.toString());
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
			rd.forward(req, res);
		}
	}
	
	private void outputCareerSavegame(String careerSavegame) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(getServletContext().getRealPath(
					"/WEB-INF/careerSavegame.xml"
					)));
			fw.write(careerSavegame);
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
