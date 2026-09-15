package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class ProveedorDto {

    private UUID id;
    private String nombreEmpresa;
    private String contacto;

    public ProveedorDto() {
        setId(id);
        setNombreEmpresa(UtilTexto.vacia);
        setContacto(UtilTexto.vacia);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombreEmpresa);
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(contacto);
    }
}
