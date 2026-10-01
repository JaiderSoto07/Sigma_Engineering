package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class ProveedorDto {

    private UUID id;
    private String nombreEmpresa;
    private String nit;
    private String contacto;
    //true = se le puede comprar; false = desactivado (retirado), se conserva su historial de compras
    private boolean activo;

    public ProveedorDto() {
        setId(id);
        setNombreEmpresa(UtilTexto.vacia);
        setNit(UtilTexto.vacia);
        setContacto(UtilTexto.vacia);
        //todo proveedor nace activo
        setActivo(true);
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

    public String getNit() {
        return nit;
    }

    //NIT de la empresa (ej. 890904478-6) o cedula si el proveedor es persona natural
    public void setNit(String nit) {
        this.nit = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nit);
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(contacto);
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
