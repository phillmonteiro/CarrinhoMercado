package phillmonteiro.com.br.carrinhodemercado.validador;

/**
 * Created by philipe.monteiro on 02/12/2016.
 */

public class Validadores {

    public static boolean notNullOrEmpty(String t) {
        return t!=null&&!t.isEmpty();
    }

    public static String validarNull(String s) {
        return s!=null?s:"";
    }
}
