package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

import java.util.UUID;

public class UsuarioDto {

    private UUID id;
    private String usuario;
    private String rol;

    public UsuarioDto() {
        setId(UtilId.VALOR_DEFECTO);
        setUsuario(UtilTexto.vacia);
        setRol(UtilTexto.vacia);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(usuario);
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = UtilTexto.getUtilTexto().primeraLetraMayuscula(rol);
    }
}
