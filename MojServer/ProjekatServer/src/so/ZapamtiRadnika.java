/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.IDomain;
import domen.Radnik;

/**
 *
 * @author Win 7
 */
public class ZapamtiRadnika extends AbstarctGenericOperation{

    Radnik radnik;
    @Override
    protected void validate(IDomain iDomain) throws Exception {
        if(iDomain instanceof Radnik){
            Radnik radnik=(Radnik) iDomain;
        }
        else{
            throw new Exception("Error in parametar");
        }
    }

    @Override
    protected void execute(IDomain iDomain) throws Exception {
        radnik=(Radnik) databaseBroker.sacuvaj(iDomain);
    }

    public Radnik getRadnik() {
        return radnik;
    }
    
}
