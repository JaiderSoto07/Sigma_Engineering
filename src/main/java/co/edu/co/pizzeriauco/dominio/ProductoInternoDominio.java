package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class ProductoInternoDominio {

    private UUID id;
    private String nombre;
    private boolean perecedero;
    //no es obligatoria, por eso es Integer y su valor por defecto es 0
    private Integer vidaUtil;
    private UnidadMedidaDominio tipoMedida;

    private ProductoInternoDominio(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.perecedero = builder.perecedero;
        this.vidaUtil = builder.vidaUtil;
        this.tipoMedida = builder.tipoMedida;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isPerecedero() {
        return perecedero;
    }

    public Integer getVidaUtil() {
        return vidaUtil;
    }

    public UnidadMedidaDominio getTipoMedida() {
        return tipoMedida;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private boolean perecedero;
        private Integer vidaUtil;
        private UnidadMedidaDominio tipoMedida;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            perecedero = false;
            vidaUtil = UtilNumero.cero;
            tipoMedida = new UnidadMedidaDominio.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre =
                    UtilTexto.getUtilTexto()
                            .quitarEspaciosEnBlanco(nombre);
            return this;
        }

        public Builder perecedero(boolean perecedero) {
            this.perecedero = perecedero;
            return this;
        }

        public Builder vidaUtil(Integer vidaUtil) {
            this.vidaUtil = UtilNumero.obtenerValorDefecto(vidaUtil, UtilNumero.cero);
            return this;
        }

        public Builder tipoMedida(UnidadMedidaDominio tipoMedida) {
            this.tipoMedida =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            tipoMedida,
                            new UnidadMedidaDominio.Builder().build()
                    );
            return this;
        }

        public ProductoInternoDominio build() {
            return new ProductoInternoDominio(this);
        }
    }
}
