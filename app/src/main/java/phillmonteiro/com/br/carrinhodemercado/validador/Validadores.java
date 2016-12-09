package phillmonteiro.com.br.carrinhodemercado.validador;

import java.text.NumberFormat;

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

    public static String formatarMoeda(double valor) {NumberFormat nf=NumberFormat.getCurrencyInstance();return nf.format(valor);}
}
