/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.IDomain;
import domen.KucicaZaPse;

/**
 *
 * @author Win 7
 */
public class ZapamtiKucicuZaPse extends AbstarctGenericOperation{

    KucicaZaPse kucica;
    @Override
    protected void validate(IDomain iDomain) throws Exception {
        if(iDomain instanceof KucicaZaPse){
            KucicaZaPse kucica = (KucicaZaPse) iDomain;
        }
        else{
            throw new Exception("Error in parametar");
        }
    }

    @Override
    protected void execute(IDomain iDomain) throws Exception {
        kucica = (KucicaZaPse) databaseBroker.sacuvaj(iDomain);
    }

    public KucicaZaPse getKucica() {
        return kucica;
    }
    
}
