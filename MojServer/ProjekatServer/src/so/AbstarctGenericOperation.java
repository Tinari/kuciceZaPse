/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import datebase.DatabaseGeneric;
import domen.IDomain;

/**
 *
 * @author Win 7
 */
public abstract class AbstarctGenericOperation {
    
    protected DatabaseGeneric databaseBroker;

    public AbstarctGenericOperation() {
        databaseBroker= new DatabaseGeneric();
    }
    
    public void templateExecute(IDomain iDomain) throws Exception {
        try {
            validate(iDomain);
            try {
                startTransaction();
                execute(iDomain);
                commitTransaction();
            } catch (Exception e) {
                rollbackTransaction();
                throw new Exception("Error: " + e.getMessage());
            }
        } catch (Exception e) {
            throw e;
        }

    }

    protected abstract void validate(IDomain iDomain) throws Exception;

    private void startTransaction() throws Exception {
        databaseBroker.startTransaction();
    }

    protected abstract void execute(IDomain iDomain) throws Exception;

    private void commitTransaction() throws Exception {
        databaseBroker.commitTransaction();
    }

    private void rollbackTransaction() throws Exception {
        databaseBroker.rollbackTransaction();
    }
    
    
    
}
