package taxiwebserviquibdo.elienco.com.funlamweb;

/**
 * Created by familia on 11/11/2015.
 */
public class liusuario {

    public String Idclase = null;
    public String Cedula = null;
    public String Nombreclase= null;
    public String Bloque = null;
    public String Aula = null;
    public String Fechainicio = null;


    public liusuario(String idclase,String cedula,String nombreclase,String bloque,String aula,String fechainicio)
    {
        this.Idclase = idclase;
        this.Cedula = cedula;
        this.Nombreclase = nombreclase;
        this.Bloque = bloque;
        this.Aula = aula;
        this.Fechainicio = fechainicio;

    }
    public liusuario()
    {}
}
