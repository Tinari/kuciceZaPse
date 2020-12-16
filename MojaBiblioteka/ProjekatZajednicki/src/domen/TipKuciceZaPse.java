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
public class TipKuciceZaPse implements Serializable,IDomain{
    
    private int tipId;
    private String nazivTipa;

    public int getTipId() {
        return tipId;
    }

    public void setTipId(int tipId) {
        this.tipId = tipId;
    }

    public String getNazivTipa() {
        return nazivTipa;
    }

    public void setNazivTipa(String nazivTipa) {
        this.nazivTipa = nazivTipa;
    }

    public TipKuciceZaPse() {
    }

    public TipKuciceZaPse(int tipId, String nazivTipa) {
        this.tipId = tipId;
        this.nazivTipa = nazivTipa;
    }

    @Override
    public String getTableName() {
     return "tipkucicezapse";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "tipId, nazivTipa";
    }

    @Override
    public String getColumnValuesForInsert() {
         return "'" + nazivTipa + "'";
    }

    @Override
    public boolean isIdAutoincrement() {
        return true;
    }

    @Override
    public void setAutoincrementId(Integer id) {
        setTipId(id);
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "tipId";
    }

    @Override
    public String vratiVrednostPrimarnogKljucaString() {
         return String.valueOf(tipId);
    }

    @Override
    public String postaviVrednosti() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IDomain vratiObjekat(ResultSet rs) throws Exception {
       return new TipKuciceZaPse(rs.getInt("tipId"), rs.getString("nazivTipa"));
    }

    @Override
    public void postaviPrimarni(PreparedStatement ps) {
        try {
            ps.setInt(1, tipId);
        } catch (SQLException ex) {
            Logger.getLogger(TipKuciceZaPse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiStringZaJOIN() {
        return "";
    }

    @Override
    public IDomain vratiSlabeObjekte(ResultSet rs) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean DaLiImaSlabeObjekte() {
       return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipKuciceZaPse other = (TipKuciceZaPse) obj;
        if (this.tipId != other.tipId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivTipa;
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
