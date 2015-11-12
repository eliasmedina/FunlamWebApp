package taxiwebserviquibdo.elienco.com.funlamweb;

public class lihistorial{

    public String Idclase = null;
    public String Cedula = null;
    public String Nombreclase= null;
    public String Bloque = null;
    public String Aula = null;
    public String Fechainicio = null;
    public String Fechafinal = null;
    public String Hora = null;



    public lihistorial(String idclase,String cedula,String nombreclase,String bloque,String aula,String fechainicio,String fechafinal,String hora)
    {
        this.Idclase = idclase;
        this.Cedula = cedula;
        this.Nombreclase = nombreclase;
        this.Bloque = bloque;
        this.Aula = aula;
        this.Fechainicio = fechainicio;
        this.Fechafinal = fechafinal;
        this.Hora = hora;
    }
    public lihistorial()
    {}
}
