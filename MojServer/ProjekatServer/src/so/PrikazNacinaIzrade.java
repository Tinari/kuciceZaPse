/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.IDomain;
import domen.NacinIzrade;

/**
 *
 * @author Win 7
 */
public class PrikazNacinaIzrade extends AbstarctGenericOperation{

    NacinIzrade nacinIzrade;
    @Override
    protected void validate(IDomain iDomain) throws Exception {
         if(iDomain instanceof NacinIzrade){
            NacinIzrade nacinIzrade=(NacinIzrade) iDomain;
        }
        else{
            throw new Exception("Error in parametar");
        }
    }

    @Override
    protected void execute(IDomain iDomain) throws Exception {
        nacinIzrade=(NacinIzrade) databaseBroker.pronadjiPoId(iDomain);
    }

    public NacinIzrade getNacinIzrade() {
        return nacinIzrade;
    }
    
    
}
