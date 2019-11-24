package v1.ajude.comparator;

import v1.ajude.models.Campanha;
import java.util.Comparator;

public class ComparatorCampanhaQuantia implements Comparator {

    @Override
    public int compare(Object obj1, Object obj2) {
        int retorno = 0;
        if(obj1 == null || obj2 == null)
            return 0;

        Campanha campanha1 = (Campanha) obj1;
        Campanha campanha2 = (Campanha) obj2;

        if((campanha1.getMeta() - campanha1.getDoacoes()) > (campanha2.getMeta() - campanha2.getDoacoes())) {
            retorno = 1;
        } else if ((campanha1.getMeta() - campanha1.getDoacoes()) == (campanha2.getMeta() - campanha2.getDoacoes())) {
            retorno = 0;
        } else {
            retorno = -1;
        }
        return retorno;
    }
}
