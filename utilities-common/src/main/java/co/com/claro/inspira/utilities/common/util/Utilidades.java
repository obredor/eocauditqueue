// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.util;

import org.apache.logging.log4j.LogManager;
import java.text.MessageFormat;
import java.net.URI;
import javax.xml.soap.SOAPException;
import java.util.Iterator;
import javax.xml.soap.Name;
import java.util.List;
import java.lang.reflect.Field;
import javax.xml.soap.SOAPElement;
import java.io.OutputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import org.apache.commons.lang3.ClassUtils;
import java.sql.Timestamp;
import org.apache.logging.log4j.Logger;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPFactory;

public class Utilidades
{
    private SOAPFactory soapFactory;
    private SOAPBodyElement purchaseLineItems;
    private static Logger logger;
    
    public static boolean validarCampoNulo(final String valor) {
        return valor == null || valor.trim().isEmpty();
    }
    
    public static String obtenerTraceabilityId(final String valor) {
        return validarCampoNulo(valor) ? String.valueOf(new Timestamp(System.currentTimeMillis()).getTime()) : valor;
    }
    
    public static boolean validarCampoNumerico(final String valor) {
        try {
            Long.parseLong(valor);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    public Utilidades(final SOAPFactory soapFactory, final SOAPBodyElement purchaseLineItems) {
        this.soapFactory = soapFactory;
        this.purchaseLineItems = purchaseLineItems;
    }
    
    public static boolean validarPrimitivoWrapper(final Object valor) throws Exception {
        try {
            return ClassUtils.isPrimitiveOrWrapper((Class)valor.getClass()) || valor instanceof String;
        }
        catch (Exception e) {
            Utilidades.logger.error("Error en Utilidades validarPrimitivoWrapper ", (Throwable)e);
            throw new Exception(e.getMessage());
        }
    }
    
    public static String consumirServicioHttpConnection(final String message, final String accion, final String endPoint, final StringBuilder responseCode) throws MalformedURLException, IOException, Exception {
        try {
            Utilidades.logger.info("Inicio consumirServicioHttpConnection --- endpoint " + endPoint);
            final ByteArrayOutputStream bout = new ByteArrayOutputStream();
            final URL url = new URL(endPoint);
            final URLConnection connection = url.openConnection();
            HttpURLConnection httpConn = null;
            httpConn = (HttpURLConnection)connection;
            final byte[] buffer = message.getBytes();
            bout.write(buffer);
            final byte[] b = bout.toByteArray();
            httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            httpConn.setRequestProperty("SOAPAction", accion);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            final OutputStream out = httpConn.getOutputStream();
            out.write(b);
            out.close();
            responseCode.append(String.valueOf(httpConn.getResponseCode()));
            final StringBuilder outputString = new StringBuilder();
            final InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), "UTF-8");
            final BufferedReader in = new BufferedReader(isr);
            String responseString;
            while ((responseString = in.readLine()) != null) {
                outputString.append(responseString);
            }
            return outputString.toString();
        }
        catch (MalformedURLException e) {
            Utilidades.logger.error("Error en Utilidades consumirServicioHttpConnection - MalformedURLException", (Throwable)e);
            throw new MalformedURLException(e.getMessage());
        }
        catch (IOException e2) {
            Utilidades.logger.error("Error en Utilidades consumirServicioHttpConnection - IOException ", (Throwable)e2);
            throw new IOException(e2.getMessage());
        }
        catch (Exception e3) {
            Utilidades.logger.error("Error en Utilidades consumirServicioHttpConnection - Exception ", (Throwable)e3);
            throw new Exception(e3.getMessage());
        }
    }
    
    public void generarValoresRequestXml(final Object request, SOAPElement paramList, final String... argumentos) throws IllegalAccessException, SOAPException, IOException, InstantiationException, NoSuchMethodException, Exception {
        Field[] parametrosRequest;
        if (request instanceof Field) {
            parametrosRequest = ((Field)request).getType().getDeclaredFields();
        }
        else {
            parametrosRequest = request.getClass().getDeclaredFields();
        }
        for (final Field parametroRequest : parametrosRequest) {
            parametroRequest.setAccessible(true);
            final String clave = parametroRequest.getName();
            final Object value = parametroRequest.get(request);
            if (value != null) {
                Name bodyName2;
                if (argumentos.length > 0) {
                    bodyName2 = this.soapFactory.createName(clave, argumentos[0], argumentos[1]);
                }
                else {
                    bodyName2 = this.soapFactory.createName(clave);
                }
                if (value instanceof List) {
                    final List<Object> campos = (List<Object>)value;
                    for (final Object parametroInterno : campos) {
                        if (!validarPrimitivoWrapper(parametroInterno)) {
                            paramList = this.purchaseLineItems.addChildElement(bodyName2);
                        }
                        this.generarValoresRequestXml(parametroInterno, paramList, new String[0]);
                    }
                }
                else if (!validarPrimitivoWrapper(value)) {
                    if (argumentos.length > 0) {
                        bodyName2 = this.soapFactory.createName(clave, argumentos[0], argumentos[1]);
                    }
                    else {
                        bodyName2 = this.soapFactory.createName(clave);
                    }
                    bodyName2 = this.soapFactory.createName(clave);
                    paramList = this.purchaseLineItems.addChildElement(bodyName2);
                    this.generarValoresRequestXml(value, paramList, new String[0]);
                }
                else {
                    Name childName;
                    if (argumentos.length > 0) {
                        childName = this.soapFactory.createName(clave, argumentos[0], argumentos[1]);
                    }
                    else {
                        childName = this.soapFactory.createName(clave);
                    }
                    final SOAPElement order = paramList.addChildElement(childName);
                    order.addTextNode(value.toString());
                }
            }
        }
    }
    
    public static String generarUrl(final Object json, final String endpoint, final Logger logger) throws Exception {
        try {
            final URI message = new URI("http", null, null, json.toString(), null);
            return MessageFormat.format(endpoint, message.toString().substring(6, message.toString().length()));
        }
        catch (Exception e) {
            logger.error("Error de Exception (generarUrl)", (Throwable)e);
            throw new Exception(e.getMessage());
        }
    }
    
    static {
        Utilidades.logger = LogManager.getLogger();
    }
}
