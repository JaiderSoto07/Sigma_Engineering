package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class ProductoInternoEntidad {

    private UUID id;
    private String nombre;
    private String perecedero;
    private int vidaUtil;
    private UnidadMedidaEntidad tipoMedida;

    private ProductoInternoEntidad(Builder builder) {
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

    public String getPerecedero() {
        return perecedero;
    }

    public int getVidaUtil() {
        return vidaUtil;
    }

    public UnidadMedidaEntidad getTipoMedida() {
        return tipoMedida;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private String perecedero;
        private int vidaUtil;
        private UnidadMedidaEntidad tipoMedida;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            perecedero = UtilTexto.vacia;
            vidaUtil = 0;
            tipoMedida = new UnidadMedidaEntidad.Builder().build();
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

        public Builder perecedero(String perecedero) {
            this.perecedero =
                    UtilTexto.getUtilTexto()
                            .quitarEspaciosEnBlanco(perecedero);
            return this;
        }

        public Builder vidaUtil(int vidaUtil) {
            this.vidaUtil = vidaUtil;
            return this;
        }

        public Builder tipoMedida(UnidadMedidaEntidad tipoMedida) {
            this.tipoMedida =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            tipoMedida,
                            new UnidadMedidaEntidad.Builder().build()
                    );
            return this;
        }

        public ProductoInternoEntidad build() {
            return new ProductoInternoEntidad(this);
        }
    }
}
