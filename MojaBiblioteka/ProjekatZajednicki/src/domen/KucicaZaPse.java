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
public class KucicaZaPse implements Serializable, IDomain {

    private int kucicaZaPseId;
    private String nazivKuciceZaPse;
    private TipKuciceZaPse tipKuciceZaPse;

    public int getKucicaZaPseId() {
        return kucicaZaPseId;
    }

    public void setKucicaZaPseId(int kucicaZaPseId) {
        this.kucicaZaPseId = kucicaZaPseId;
    }

    public String getNazivKuciceZaPse() {
        return nazivKuciceZaPse;
    }

    public void setNazivKuciceZaPse(String nazivKuciceZaPse) {
        this.nazivKuciceZaPse = nazivKuciceZaPse;
    }

    public TipKuciceZaPse getTipKuciceZaPse() {
        return tipKuciceZaPse;
    }

    public void setTipKuciceZaPse(TipKuciceZaPse tipKuciceZaPse) {
        this.tipKuciceZaPse = tipKuciceZaPse;
    }

    public KucicaZaPse(int kucicaZaPseId, String nazivKuciceZaPse, TipKuciceZaPse tipKuciceZaPse) {
        this.kucicaZaPseId = kucicaZaPseId;
        this.nazivKuciceZaPse = nazivKuciceZaPse;
        this.tipKuciceZaPse = tipKuciceZaPse;
    }

    public KucicaZaPse() {
    }

    public KucicaZaPse(String nazivKuciceZaPse, TipKuciceZaPse tipKuciceZaPse) {
        this.nazivKuciceZaPse = nazivKuciceZaPse;
        this.tipKuciceZaPse = tipKuciceZaPse;
    }

    @Override
    public String getTableName() {
        return "kucicazapse";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "nazivKuciceZaPse,tipId";
    }

    @Override
    public String getColumnValuesForInsert() {
        return "'" + nazivKuciceZaPse + "'," + tipKuciceZaPse.getTipId();
    }

    @Override
    public boolean isIdAutoincrement() {
        return true;
    }

    @Override
    public void setAutoincrementId(Integer id) {
        setKucicaZaPseId(id);
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kucicaZaPseId";
    }

    @Override
    public String vratiVrednostPrimarnogKljucaString() {
        return String.valueOf(kucicaZaPseId);
    }

    @Override
    public String postaviVrednosti() {
        return "kucicaZaPseId=" + kucicaZaPseId + ", nazivKuciceZaPse='" + nazivKuciceZaPse + ""
                + "', tipId=" + tipKuciceZaPse.getTipId();
    }

    @Override
    public IDomain vratiObjekat(ResultSet rs) throws Exception {
        return new KucicaZaPse(rs.getInt("kucicaZaPseId"), rs.getString("nazivKuciceZaPse"),
                new TipKuciceZaPse(rs.getInt("tipId"), rs.getString("nazivTipa")));
    }

    @Override
    public void postaviPrimarni(PreparedStatement ps) {
        try {
            ps.setInt(1, getKucicaZaPseId());
        } catch (SQLException ex) {
            Logger.getLogger(KucicaZaPse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiStringZaJOIN() {
        return " INNER JOIN tipkucicezapse on kucicazapse.tipId=tipkucicezapse.tipId";
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
        return nazivKuciceZaPse;
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
        return "nacinizrade";
    }

    @Override
    public String getJoinVeze() {
        return " INNER JOIN radnik on nacinizrade.jmbg=radnik.jmbg "
                + "INNER JOIN mesto on radnik.idMesto=mesto.idMesto "
                + "INNER JOIN kucicazapse on kucicazapse.kucicaZaPseId=nacinizrade.kucicaZaPseId "
                + "INNER JOIN tipkucicezapse on kucicazapse.tipId=tipkucicezapse.tipId";
    }

    @Override
    public IDomain vratiObjekteVeze(ResultSet rs) throws Exception {
        int nacinId2 = rs.getInt("nacinId");
        String opisNacina = rs.getString("opis");
        java.sql.Date datumNacina = rs.getDate("datum");

        Long idMesto = rs.getLong("idMesto");
        String nazivMesta = rs.getString("nazivMesta");
        Mesto mesto = new Mesto(idMesto, nazivMesta);
        String jmbg = rs.getString("jmbg");
        String ime = rs.getString("ime");
        String prezime = rs.getString("prezime");
        String pol = rs.getString("pol");
        java.sql.Date datumZaposlenja = rs.getDate("datumZaposlenja");
        String status = rs.getString("status");

        Radnik r = new Radnik(jmbg, ime, prezime, pol, datumZaposlenja, status, mesto);
        int tipId = rs.getInt("tipId");
        String nazivTipa = rs.getString("nazivTipa");
        TipKuciceZaPse tip = new TipKuciceZaPse(tipId, nazivTipa);

        int kucicaZaPseId2 = rs.getInt("kucicaZaPseId");
        String nazivKuciceZaPse2 = rs.getString("nazivKuciceZaPse");
        KucicaZaPse kzp = new KucicaZaPse(kucicaZaPseId2, nazivKuciceZaPse2, tip);
        NacinIzrade nacin = new NacinIzrade(nacinId2, opisNacina, datumNacina, r, kzp);

        return nacin;
    }

}
