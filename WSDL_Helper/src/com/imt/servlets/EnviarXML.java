package com.imt.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.imt.funciones.Utiles;
import com.imt.jlex.Yylex;
import com.imt.token.dto.ErrorToken;
import com.imt.token.dto.Yytoken;

@WebServlet("/EnviarXML")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 1,
		maxFileSize = 1024 * 1024 * 5,
		maxRequestSize = 1024 * 1024 * 5
)
public class EnviarXML extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviarXML() {super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String respuestaServidor;
		try {
			InputStream contenidoXML;//Inicializamos la entrada para el Lexer
			
			String tipoDeCarga = Utiles.getValue(request.getPart("typeInput"));//Obteniendo valores del POST
			
			//Validando si se envio un archivo obtenemos "f" de lo contrario se envio texto
			if(tipoDeCarga.contains("f"))
			{
				Part archivoXML = request.getPart("xmlToParse");//Obtenemos el archivo en el POST
				String nombreArchivo = Utiles.getFileName(archivoXML);//Obtenemos el nombre del archivo
				contenidoXML = archivoXML.getInputStream();//Obtenemos el contenido del Archivo
				System.out.println(nombreArchivo);
			}else
			{
				//String textoXML = Utiles.getValue(request.getPart("xmlToParse"));//Obtenemos valor de input
				String textoXML = request.getParameter("xmlToParse");
				System.out.println(textoXML);
				textoXML = URLDecoder.decode(textoXML, "UTF-8");
				System.out.println(textoXML);
				//String textoXML = request.getParameter("xmlEditor");
				contenidoXML = new ByteArrayInputStream(textoXML.getBytes());	
			}
			
			JSONObject jsonP = new JSONObject();//Prepara objeto JSON Parent
			JSONObject jsonT = new JSONObject();//Prepara objeto JSON Token
			JSONObject jsonE = new JSONObject();//Prepara objeto JSON Error
			
			//Metodo para caputarar resultado de lexer
			Yylex yy = new Yylex(contenidoXML);
			Yytoken token;
			int lineasInpresas = 0;
			while((token = yy.yylex()) != null){
				jsonT.put("Linea_"+lineasInpresas, token);
				System.out.println(token);
				lineasInpresas++;
			}
			
			jsonP.put("tokens", jsonT);
			
			respuestaServidor = jsonP.toString();
		} catch (Exception e) {
			respuestaServidor = "ocurrio un error";
			System.err.println(e);
		}
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(respuestaServidor);
	}

}
