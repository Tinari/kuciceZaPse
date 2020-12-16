/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Win 7
 */
public class Materijal implements Serializable, IDomain {

    private int materijalId;
    private String nazivMaterijala;

    /**
     * @return the materijalId
     */
    public int getMaterijalId() {
        return materijalId;
    }

    /**
     * @param materijalId the materijalId to set
     */
    public void setMaterijalId(int materijalId) {
        this.materijalId = materijalId;
    }

    /**
     * @return the nazivMaterijala
     */
    public String getNazivMaterijala() {
        return nazivMaterijala;
    }

    /**
     * @param nazivMaterijala the nazivMaterijala to set
     */
    public void setNazivMaterijala(String nazivMaterijala) {
        this.nazivMaterijala = nazivMaterijala;
    }

    public Materijal() {
    }

    public Materijal(int materijalId, String nazivMaterijala) {
        this.materijalId = materijalId;
        this.nazivMaterijala = nazivMaterijala;
    }

    @Override
    public String getTableName() {
        return "materijal";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "materijalId,nazivMaterijala";
    }

    @Override
    public String getColumnValuesForInsert() {
        return "'" + nazivMaterijala + "'";
    }

    @Override
    public boolean isIdAutoincrement() {
        return true;
    }

    @Override
    public void setAutoincrementId(Integer id) {
        setMaterijalId(id);
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "materijalId";
    }

    @Override
    public String vratiVrednostPrimarnogKljucaString() {
         return String.valueOf(materijalId);
    }

    @Override
    public String postaviVrednosti() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IDomain vratiObjekat(ResultSet rs) throws Exception {
       return new Materijal(rs.getInt("materijalId"), rs.getString("nazivMaterijala"));
    }

    @Override
    public void postaviPrimarni(PreparedStatement ps) {
        try {
            ps.setInt(1, getMaterijalId());
        } catch (SQLException ex) {
            Logger.getLogger(Materijal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiStringZaJOIN() {
        return "";
    }

    @Override
    public IDomain vratiSlabeObjekte(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean DaLiImaSlabeObjekte() {
        return false;
    }

    @Override
    public String toString() {
        return nazivMaterijala;
    }

    @Override
    public String vratiNazivSlabogObjekta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiStringZaJOINSlabog() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IDomain> vartiSveSlabe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiUslov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getVeza() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getJoinVeze() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IDomain vratiObjekteVeze(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
