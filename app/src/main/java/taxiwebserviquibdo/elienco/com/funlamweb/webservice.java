package taxiwebserviquibdo.elienco.com.funlamweb;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
public class webservice{

    private static final String NAMESPACE = "http://tempuri.org/";



    private static final String URL="http://190.144.8.138:1345/funlamw.asmx";
    private static final String SOAP_ACTION ="http://tempuri.org/";


    public static SoapObject InvokeObjectMethod(String methodName) throws IOException, Exception
    {
        SoapObject request = new SoapObject(NAMESPACE, methodName);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        transporte.call(SOAP_ACTION + methodName, envelope);
        SoapObject resSoap =(SoapObject)envelope.getResponse();
        return resSoap;
    }

    public static SoapObject InvokeObjectMethod1Parametro(String methodName, String npar1, Object par1) throws IOException, Exception
    {
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        request.addProperty(npar1, par1);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        transporte.call(SOAP_ACTION + methodName, envelope);
        SoapObject resSoap =(SoapObject)envelope.getResponse();
        return resSoap;
    }
    public static SoapObject InvokeObjectMethod2Parametro(String methodName, String npar1, Object par1,String npar2, Object par2) throws IOException, Exception
    {
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        request.addProperty(npar1, par1);
        request.addProperty(npar2, par2);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        transporte.call(SOAP_ACTION + methodName, envelope);
        SoapObject resSoap =(SoapObject)envelope.getResponse();
        return resSoap;
    }


    public static String InvokePrimitiveMethod(String methodName) throws IOException, XmlPullParserException
    {
        SoapObject request = new SoapObject(NAMESPACE, "enviarEmail");
        Object resultado = Llamar(request, methodName, true);
        return resultado.toString();
    }

    public static String InvokePrimitiveMethod1Parametro(String methodName,
                                                         String npar1, Object par1) throws IOException, XmlPullParserException
    {
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        request.addProperty(npar1, par1);
        Object resultado = Llamar(request, methodName, true);
        return resultado.toString();
    }

    public static String InvokePrimitiveMethod2Parametro(String methodName,
                                                         String npar1, Object par1, String npar2, Object par2) throws IOException, XmlPullParserException
    {


        SoapObject request = new SoapObject(NAMESPACE, methodName);
        request.addProperty(npar1, par1);
        request.addProperty(npar2, par2);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        transporte.call(SOAP_ACTION + methodName, envelope);
        SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
        return resultado_xml.toString();
    }

    public static String InvokePrimitiveMethod4Parametro(String methodName,
                                                         String npar1, Object par1, String npar2, Object par2,
                                                         String npar3, Object par3, String npar4, Object par4) throws IOException, XmlPullParserException
    {
        try
        {
            SoapObject request = new SoapObject(NAMESPACE, methodName);
            request.addProperty(npar1, par1);
            request.addProperty(npar2, par2);
            request.addProperty(npar3, par3);
            request.addProperty(npar4, par4);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL);

            transporte.call(SOAP_ACTION + methodName, envelope);
            SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
            return resultado_xml.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    public static String InvokePrimitiveMethod8Parametro(String methodName,
                                                         String npar1, Object par1,
                                                         String npar2, Object par2,
                                                         String npar3, Object par3,
                                                         String npar4, Object par4,
                                                         String npar5, Object par5,
                                                         String npar6, Object par6,
                                                         String npar7, Object par7,
                                                         String npar8, Object par8) throws IOException, XmlPullParserException
    {
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        request.addProperty(npar1, par1);
        request.addProperty(npar2, par2);
        request.addProperty(npar3, par3);
        request.addProperty(npar4, par4);
        request.addProperty(npar5, par5);
        request.addProperty(npar6, par6);
        request.addProperty(npar7, par7);
        request.addProperty(npar8, par8);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        transporte.call(SOAP_ACTION + methodName, envelope);
        SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
        return resultado_xml.toString();
    }

    public static String InvokePrimitiveMethod9Parametro(String methodName,
                                                         String npar1, Object par1,
                                                         String npar2, Object par2,
                                                         String npar3, Object par3,
                                                         String npar4, Object par4,
                                                         String npar5, Object par5,
                                                         String npar6, Object par6,
                                                         String npar7, Object par7,
                                                         String npar8, Object par8,
                                                         String npar9, Object par9) throws IOException, XmlPullParserException
    {
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        request.addProperty(npar1, par1);
        request.addProperty(npar2, par2);
        request.addProperty(npar3, par3);
        request.addProperty(npar4, par4);
        request.addProperty(npar5, par5);
        request.addProperty(npar6, par6);
        request.addProperty(npar7, par7);
        request.addProperty(npar8, par8);
        request.addProperty(npar9, par9);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        transporte.call(SOAP_ACTION + methodName, envelope);
        SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
        return resultado_xml.toString();
    }

    public static String InvokePrimitiveMethod11Parametro(String methodName,
                                                          String npar1, Object par1,
                                                          String npar2, Object par2,
                                                          String npar3, Object par3,
                                                          String npar4, Object par4,
                                                          String npar5, Object par5,
                                                          String npar6, Object par6,
                                                          String npar7, Object par7,
                                                          String npar8, Object par8,
                                                          String npar9, Object par9,
                                                          String npar10, Object par10,
                                                          String npar11, Object par11) throws IOException, XmlPullParserException
    {
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        request.addProperty(npar1, par1);
        request.addProperty(npar2, par2);
        request.addProperty(npar3, par3);
        request.addProperty(npar4, par4);
        request.addProperty(npar5, par5);
        request.addProperty(npar6, par6);
        request.addProperty(npar7, par7);
        request.addProperty(npar8, par8);
        request.addProperty(npar9, par9);
        request.addProperty(npar10, par10);
        request.addProperty(npar11, par11);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        transporte.call(SOAP_ACTION + methodName, envelope);
        SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
        return resultado_xml.toString();
    }

    private static Object Llamar(SoapObject request, String methodName, boolean primitive) throws IOException, XmlPullParserException
    {

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        transporte.call(SOAP_ACTION + methodName, envelope);
        if(primitive)
        {
            SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
            if(resultado_xml == null)
                return "";
            return resultado_xml.toString();
        }
        else
        {
            return null;
        }
    }
}
