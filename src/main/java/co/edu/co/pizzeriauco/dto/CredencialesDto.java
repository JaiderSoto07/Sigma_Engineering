package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

public class CredencialesDto {

    private String usuario;
    private String clave;

    public CredencialesDto() {
        setUsuario(UtilTexto.vacia);
        setClave(UtilTexto.vacia);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(usuario);
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = UtilTexto.obtenerValorDefecto(clave);
    }
}
