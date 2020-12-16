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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Win 7
 */
public class Mesto implements Serializable, IDomain {

    private Long idMesta;
    private String nazivMesta;

    public Long getIdMesta() {
        return idMesta;
    }

    public void setIdMesta(Long idMesta) {
        this.idMesta = idMesta;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    public void setNazivMesta(String nazivMesta) {
        this.nazivMesta = nazivMesta;
    }

    public Mesto() {
    }

    public Mesto(Long idMesta, String nazivMesta) {
        this.idMesta = idMesta;
        this.nazivMesta = nazivMesta;
    }

    @Override
    public String toString() {
        return nazivMesta;
    }

    @Override
    public String getTableName() {
        return "mesto";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "idMesto,nazivMesta";
    }

    @Override
    public String getColumnValuesForInsert() {
        return "'" + idMesta + "'," + nazivMesta + "'";
    }

    @Override
    public boolean isIdAutoincrement() {
        return false;
    }

    @Override
    public void setAutoincrementId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idMesto";
    }

    @Override
    public String vratiVrednostPrimarnogKljucaString() {
        return String.valueOf(getIdMesta());
    }

    @Override
    public String postaviVrednosti() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IDomain vratiObjekat(ResultSet rs) throws Exception {
        return new Mesto(rs.getLong("idMesto"), rs.getString("nazivMesta"));
    }

    @Override
    public void postaviPrimarni(PreparedStatement ps) {
        try {
            ps.setLong(1, getIdMesta());
        } catch (SQLException ex) {
            Logger.getLogger(Mesto.class.getName()).log(Level.SEVERE, null, ex);
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
        int hash = 7;
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
        final Mesto other = (Mesto) obj;
        if (!Objects.equals(this.idMesta, other.idMesta)) {
            return false;
        }
        return true;
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
