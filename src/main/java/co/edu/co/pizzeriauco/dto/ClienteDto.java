package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class ClienteDto {

    private UUID id;
    private String nombre;
    private String apellido;
    private String documento;
    private String telefono;

    public ClienteDto() {
        setId(id);
        setNombre(UtilTexto.vacia);
        setApellido(UtilTexto.vacia);
        setDocumento(UtilTexto.vacia);
        setTelefono(UtilTexto.vacia);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(apellido);
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(documento);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(telefono);
    }
}
