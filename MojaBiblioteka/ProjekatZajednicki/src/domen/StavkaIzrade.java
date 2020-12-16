/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.Date;
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
public class StavkaIzrade implements Serializable, IDomain {

    private int rbStavke;
    private JedinicaMere jedinicaMere;
    private double kolicina;

    private Materijal materijal;
    private NacinIzrade nacinIzrade;

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public JedinicaMere getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(JedinicaMere jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public Materijal getMaterijal() {
        return materijal;
    }

    public void setMaterijal(Materijal materijal) {
        this.materijal = materijal;
    }

    public NacinIzrade getNacinIzrade() {
        return nacinIzrade;
    }

    public void setNacinIzrade(NacinIzrade nacinIzrade) {
        this.nacinIzrade = nacinIzrade;
    }

    public StavkaIzrade() {
    }

    public StavkaIzrade(int rbStavke, JedinicaMere jedinicaMere, double kolicina, Materijal materijal, NacinIzrade nacinIzrade) {
        this.rbStavke = rbStavke;
        this.jedinicaMere = jedinicaMere;
        this.kolicina = kolicina;

        this.materijal = materijal;
        this.nacinIzrade = nacinIzrade;
    }

    @Override
    public String getTableName() {
        return "stavkaizrade";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "rbStavke,nacinId,materijalId,kolicina,jedinicaMere";
    }

    @Override
    public String getColumnValuesForInsert() {
        return rbStavke + ", " + nacinIzrade.getNacinId() + ", " + materijal.getMaterijalId() + ", " + kolicina + ", '" + jedinicaMere + "'";
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
        return "nacinId";
    }

    @Override
    public String vratiVrednostPrimarnogKljucaString() {
        return String.valueOf(nacinIzrade.getNacinId());
    }

    @Override
    public String postaviVrednosti() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IDomain vratiObjekat(ResultSet rs) throws Exception {

        int matId = rs.getInt("materijalId");
        String materNaziv = rs.getString("nazivMaterijala");
        Materijal mat = new Materijal(matId, materNaziv);

        int idTip = rs.getInt("tipId");
        String tipNaziv = rs.getString("nazivTipa");
        TipKuciceZaPse tip = new TipKuciceZaPse(idTip, tipNaziv);

        int idKucicaZaPse = rs.getInt("kucicaZaPseId");
        String kucicaZaPseNaziv = rs.getString("nazivKuciceZaPse");
        KucicaZaPse kucica = new KucicaZaPse(idKucicaZaPse, kucicaZaPseNaziv, tip);

        Long idMesto = rs.getLong("idMesto");
        String nazivMesto = rs.getString("nazivMesta");
        Mesto mesto = new Mesto(idMesto, nazivMesto);

        Radnik rad = new Radnik(rs.getString("jmbg"), rs.getString("ime"), rs.getString("prezime"), rs.getString("pol"),
                rs.getDate("datumZaposlenja"), rs.getString("status"), mesto);
        /*String jmbg = rs.getString("jmbg");
         String ime = rs.getString("ime");
         String prezime = rs.getString("prezime");
         String pol = rs.getString("pol");
         Date datumZap = rs.getDate("datumZaposlenja");*/
        int nacinId2 = rs.getInt("nacinId");
        String opisNacina = rs.getString("opis");
        Date datumNacina = rs.getDate("datum");
        NacinIzrade nacin = new NacinIzrade(nacinId2, opisNacina, datumNacina, rad, kucica);

        int rb = rs.getInt("rbStavke");
        String jedinica = rs.getString("jedinicaMere");
        JedinicaMere mera = JedinicaMere.valueOf(jedinica);
        double kol = rs.getDouble("kolicina");

        StavkaIzrade stavka = new StavkaIzrade(rb, mera, kol, mat, nacin);

        return stavka;

    }

    @Override
    public void postaviPrimarni(PreparedStatement ps) {
        try {
            ps.setInt(1, getRbStavke());
            ps.setInt(2, getNacinIzrade().getNacinId());
        } catch (SQLException ex) {
            Logger.getLogger(StavkaIzrade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiStringZaJOIN() {
        return " INNER JOIN nacinizrade on nacinizrade.nacinId=stavkaizrade.nacinId"
                + " INNER JOIN materijal on stavkaizrade.materijalId=materijal.materijalId"
                + " INNER JOIN kucicazapse on nacinizrade.kucicaZaPseId=kucicazapse.kucicaZaPseId"
                + " INNER JOIN tipkucicezapse on kucicazapse.tipId = tipkucicezapse.tipId"
                + " INNER JOIN radnik on nacinizrade.jmbg = radnik.jmbg"
                + " INNER JOIN mesto on radnik.idMesto=mesto.idMesto";
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
