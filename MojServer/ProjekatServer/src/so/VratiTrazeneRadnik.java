/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.IDomain;
import domen.Radnik;
import java.util.List;

/**
 *
 * @author Win 7
 */
public class VratiTrazeneRadnik extends AbstarctGenericOperation{
private List<IDomain> lista;
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
        lista=databaseBroker.vratiTrazene(iDomain);
    }

    public List<IDomain> getLista() {
        return lista;
    }
    
}
