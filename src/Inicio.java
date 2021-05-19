
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
 


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;

/**
 * @author jmcneil
 * (c) copyright Software Pulse 2020
 *
 */
public class Inicio {
 
    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        Inicio testHelloAge = new Inicio();
        testHelloAge.getCitiesByCountry();
    }
    
    
    
    
    public void getCitiesByCountry() throws MalformedURLException, IOException {
    	 
        //Code to make a webservice HTTP request
        String responseString = "";
        String outputString = "";
        String wsURL = "https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx";// Endpoint of the webservice to be consumed
        URL url = new URL(wsURL);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection)connection;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        String xmlInput = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
        		+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
        		+ "  <soap:Body>\r\n"
        		+ "    <TipoCambioFechaInicial xmlns=\"http://www.banguat.gob.gt/variables/ws/\">\r\n"
        		+ "      <fechainit>15/04/2021</fechainit>\r\n"
        		+ "    </TipoCambioFechaInicial>\r\n"
        		+ "  </soap:Body>\r\n"
        		+ "</soap:Envelope>"; //entire SOAP Request

        byte[] buffer = new byte[xmlInput.length()];
        buffer = xmlInput.getBytes();
        bout.write(buffer);
        byte[] b = bout.toByteArray();
        //String SOAPAction = "TipoCambioFechaInicial"; // SOAP action of the webservice to be consumed
        // Set the appropriate HTTP parameters.
        httpConn.setRequestProperty("Content-Length",
        String.valueOf(b.length));
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        //httpConn.setRequestProperty("SOAPAction", SOAPAction);
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        OutputStream out = httpConn.getOutputStream();
        //Write the content of the request to the outputstream of the HTTP Connection.
        out.write(b);
        out.close();
        //Ready with sending the request.
         
        //Read the response.
        InputStreamReader isr = null;
        if (httpConn.getResponseCode() == 200) {
          isr = new InputStreamReader(httpConn.getInputStream());
        } else {
          isr = new InputStreamReader(httpConn.getErrorStream());
        }
        
        BufferedReader in = new BufferedReader(isr);
         
        //Write the SOAP message response to a String.
        while ((responseString = in.readLine()) != null) {
            outputString = outputString + responseString;
        }
        
        System.out.println("pruebas: "+outputString);
        //Parse the String output to a org.w3c.dom.Document and be able to reach every node with the org.w3c.dom API.
        Document document = parseXmlFile2(outputString);
        NodeList nodeLst = document.getElementsByTagName("TipoCambioFechaInicial"); // TagName of the element to be retrieved
        //String elementValue = nodeLst.item(0).getTextContent();
        System.out.println(nodeLst);
        
        //return elementValue;
    }

    public Document parseXmlFile2(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    
    public void metodo() throws IOException {
    	URL oURL;
		try {
			oURL = new URL("https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx?WSDL");
			HttpURLConnection con = (HttpURLConnection) oURL.openConnection();
			con.setRequestMethod("POST");
			
			con.setRequestProperty("Content-type", "text/xml; charset=utf-8");
			con.setRequestProperty("SOAPAction", 
			      "https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx?op=TipoCambioFechaIniciald");
			con.setDoOutput(true);
			con.setDoInput(true);
			   
			   String reqXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
		        		+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
		        		+ "  <soap:Body>\r\n"
		        		+ "    <TipoCambioFechaInicial xmlns=\"http://www.banguat.gob.gt/variables/ws/\">\r\n"
		        		+ "      <fechainit>15/04/2021</fechainit>\r\n"
		        		+ "    </TipoCambioFechaInicial>\r\n"
		        		+ "  </soap:Body>\r\n"
		        		+ "</soap:Envelope>";
			   
			   OutputStream reqStream = con.getOutputStream();
			   reqStream.write(reqXML.getBytes());
			   
			   InputStream resStream = con.getInputStream();
			   byte[] byteBuf = new byte[10240];
			   int len = resStream.read(byteBuf);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
   
     
    public void geHelloAge() {
        //String wsURL = "https://10.16.0.250:9980/wsTSEservicios/wsDefuncion.asmx";
        String wsURL = "https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx?WSDL";
    	URL url = null;
        URLConnection connection = null;
        HttpURLConnection httpConn = null;
        String responseString = null;
        String outputString="java.security.policy";
        OutputStream out = null;
        InputStreamReader isr = null;
        BufferedReader in = null;
        
        String xmlInput= "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
        		+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
        		+ "  <soap:Body>\r\n"
        		+ "    <TipoCambioFechaInicial xmlns=\"http://www.banguat.gob.gt/variables/ws/\">\r\n"
        		+ "      <fechainit>15/04/2021</fechainit>\r\n"
        		+ "    </TipoCambioFechaInicial>\r\n"
        		+ "  </soap:Body>\r\n"
        		+ "</soap:Envelope>";
         
        try
        {
        	System.setProperty(outputString, xmlInput);
        	
            url = new URL(wsURL);
            connection = url.openConnection();
            //connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            //connection.addRequestProperty("User-Agent", "Mozilla");
            httpConn = (HttpURLConnection) connection;
 
            byte[] buffer = new byte[xmlInput.length()];
            buffer = xmlInput.getBytes();
 
            String SOAPAction = "";
            // Set the appropriate HTTP parameters.
             httpConn.setRequestProperty("Content-Length", String
                     .valueOf(buffer.length));
            httpConn.setRequestProperty("Content-Type",
                    "text/xml; charset=utf-8");
             
             
            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            out = httpConn.getOutputStream();
            out.write(buffer);
            out.close();
             
            // Read the response and write it to standard out.
            isr = new InputStreamReader(httpConn.getInputStream());
            in = new BufferedReader(isr);
             
            while ((responseString = in.readLine()) != null) 
            {
                outputString = outputString + responseString;
            }
            System.out.println(outputString);
            System.out.println("");
             
            // Get the response from the web service call
            Document document = parseXmlFile(outputString);
             
            NodeList nodeLst = document.getElementsByTagName("ns2:sayhelloResponse");
            String webServiceResponse = nodeLst.item(0).getTextContent();
            System.out.println("The response from the web service call is : " + webServiceResponse);
              
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
     
    private Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
             InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
}
