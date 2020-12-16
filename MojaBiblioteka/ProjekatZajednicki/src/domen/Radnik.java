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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Win 7
 */
public class Radnik implements Serializable, IDomain {

    private String jmbg;
    private String ime;
    private String prezime;
    private String pol;
    private Date datumZaposlenja;
    private String status;
    private Mesto mesto;

    /**
     * @return the jmbg
     */
    public String getJmbg() {
        return jmbg;
    }

    /**
     * @param jmbg the jmbg to set
     */
    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    /**
     * @return the ime
     */
    public String getIme() {
        return ime;
    }

    /**
     * @param ime the ime to set
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * @return the prezime
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * @param prezime the prezime to set
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * @return the pol
     */
    public String getPol() {
        return pol;
    }

    /**
     * @param pol the pol to set
     */
    public void setPol(String pol) {
        this.pol = pol;
    }

    /**
     * @return the datumZaposlenja
     */
    public Date getDatumZaposlenja() {
        return datumZaposlenja;
    }

    /**
     * @param datumZaposlenja the datumZaposlenja to set
     */
    public void setDatumZaposlenja(Date datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the mesto
     */
    public Mesto getMesto() {
        return mesto;
    }

    /**
     * @param mesto the mesto to set
     */
    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public Radnik() {
    }

    public Radnik(String jmbg, String ime, String prezime, String pol, Date datumZaposlenja, String status, Mesto mesto) {
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumZaposlenja = datumZaposlenja;
        this.status = status;
        this.mesto = mesto;
    }

    @Override
    public String getTableName() {
        return "radnik";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "jmbg,ime,prezime,pol,datumZaposlenja,status,idMesto";
    }

    @Override
    public String getColumnValuesForInsert() {
        return "'" + jmbg + "','" + ime + "','" + prezime + "','" + pol + "','" + new java.sql.Date(getDatumZaposlenja().getTime())
                + "','" + status + "','" + mesto.getIdMesta() + "'";
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
        return "jmbg";
    }

    @Override
    public String vratiVrednostPrimarnogKljucaString() {
        return String.valueOf(jmbg);
    }

    @Override
    public String postaviVrednosti() {
        return "jmbg='" + jmbg + "', ime='" + ime + "', prezime='" + prezime + "', pol='" + pol + "', datumZaposlenja='"
                + new java.sql.Date(datumZaposlenja.getTime()).toString() + "', status='" + status + "', idMesto='" + mesto.getIdMesta().toString() + "'";
    }

    @Override
    public IDomain vratiObjekat(ResultSet rs) throws Exception {
        return new Radnik(rs.getString("jmbg"), rs.getString("ime"), rs.getString("prezime"), rs.getString("pol"),
                rs.getDate("datumZaposlenja"), rs.getString("status"), new Mesto(rs.getLong("idMesto"), rs.getString("nazivMesta")));
    }

    @Override
    public void postaviPrimarni(PreparedStatement ps) {
        try {
            ps.setString(1, getJmbg());
        } catch (SQLException ex) {
            Logger.getLogger(Radnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiStringZaJOIN() {
        return " INNER JOIN mesto on radnik.idMesto=mesto.idMesto";
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
        return ime + " " + prezime;
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
        return "ime LIKE '%" + ime + "%'" + " or prezime LIKE '%" + prezime + "%'" + " or jmbg LIKE '%" + jmbg+"%'";
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
