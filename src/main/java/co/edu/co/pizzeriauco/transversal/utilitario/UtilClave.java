package co.edu.co.pizzeriauco.transversal.utilitario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UtilClave {

    private static final BCryptPasswordEncoder CIFRADOR = new BCryptPasswordEncoder();

    private UtilClave() {
    }

    public static String cifrar(String clave) {
        return CIFRADOR.encode(UtilTexto.obtenerValorDefecto(clave));
    }

    public static boolean coincide(String clave, String claveCifrada) {
        if (UtilTexto.getUtilTexto().esVacia(clave) || UtilTexto.getUtilTexto().esVacia(claveCifrada)) {
            return false;
        }
        return CIFRADOR.matches(clave, claveCifrada);
    }
}
