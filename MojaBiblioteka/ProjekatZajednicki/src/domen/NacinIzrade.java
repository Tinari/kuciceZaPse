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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Win 7
 */
public class NacinIzrade implements Serializable, IDomain {

    private int nacinId;
    private String opis;
    private Date datum;
    private Radnik radnik;
    private KucicaZaPse kucicaZaPse;
    List<StavkaIzrade> stavakeIzrade;

    public int getNacinId() {
        return nacinId;
    }

    public void setNacinId(int nacinId) {
        this.nacinId = nacinId;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public KucicaZaPse getKucicaZaPse() {
        return kucicaZaPse;
    }

    public void setKucicaZaPse(KucicaZaPse kucicaZaPse) {
        this.kucicaZaPse = kucicaZaPse;
    }

    public List<StavkaIzrade> getStavakeIzrade() {
        return stavakeIzrade;
    }

    public void setStavakeIzrade(List<StavkaIzrade> stavakeIzrade) {
        this.stavakeIzrade = stavakeIzrade;
    }

    public NacinIzrade() {
        stavakeIzrade = new ArrayList<>();
    }

    public NacinIzrade(int nacinId, String opis, Date datum, Radnik radnik, KucicaZaPse kucicaZaPse) {
        this.nacinId = nacinId;
        this.opis = opis;
        this.datum = datum;
        this.radnik = radnik;
        this.kucicaZaPse = kucicaZaPse;

    }

    public NacinIzrade(int nacinId, String opis, Date datum, Radnik radnik, KucicaZaPse kucicaZaPse, List<StavkaIzrade> stavkeIzrade) {
        this.nacinId = nacinId;
        this.opis = opis;
        this.datum = datum;
        this.radnik = radnik;
        this.kucicaZaPse = kucicaZaPse;
        this.stavakeIzrade = stavkeIzrade;
    }

    @Override
    public String getTableName() {
        return "nacinizrade";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "opis,datum,jmbg,kucicaZaPseId";
    }

    @Override
    public String getColumnValuesForInsert() {
        return "'" + opis + "','" + new java.sql.Date(getDatum().getTime()) + "','" + radnik.getJmbg() + "'," + kucicaZaPse.getKucicaZaPseId();
    }

    @Override
    public boolean isIdAutoincrement() {
        return true;
    }

    @Override
    public void setAutoincrementId(Integer id) {
        setNacinId(id);
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "nacinId";
    }

    @Override
    public String vratiVrednostPrimarnogKljucaString() {
        return String.valueOf(nacinId);
    }

    @Override
    public String postaviVrednosti() {
        return "nacinId=" + nacinId + ", opis='" + opis + "', datum='" + new java.sql.Date(datum.getTime()).toString()
                + "', jmbg='" + radnik.getJmbg() + "', kucicaZaPseId=" + kucicaZaPse.getKucicaZaPseId();
    }

    @Override
    public IDomain vratiObjekat(ResultSet rs) throws Exception {
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

        int kucicaZaPseId = rs.getInt("kucicaZaPseId");
        String nazivKuciceZaPse = rs.getString("nazivKuciceZaPse");
        KucicaZaPse kzp = new KucicaZaPse(kucicaZaPseId, nazivKuciceZaPse, tip);
        NacinIzrade nacin = new NacinIzrade(nacinId2, opisNacina, datumNacina, r, kzp);

        return nacin;
    }

    @Override
    public void postaviPrimarni(PreparedStatement ps) {
        try {
            ps.setInt(1, getNacinId());
        } catch (SQLException ex) {
            Logger.getLogger(NacinIzrade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String vratiStringZaJOIN() {
        return " INNER JOIN radnik on nacinizrade.jmbg=radnik.jmbg "
                + "INNER JOIN mesto on radnik.idMesto=mesto.idMesto "
                + "INNER JOIN kucicazapse on kucicazapse.kucicaZaPseId=nacinizrade.kucicaZaPseId "
                + "INNER JOIN tipkucicezapse on kucicazapse.tipId=tipkucicezapse.tipId";
    }

    @Override
    public IDomain vratiSlabeObjekte(ResultSet rs)throws Exception{

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
        java.sql.Date datumNacina = rs.getDate("datum");
        NacinIzrade nacin = new NacinIzrade(nacinId2, opisNacina, datumNacina, rad, kucica);

        int rb = rs.getInt("rbStavke");
        String jedinica = rs.getString("jedinicaMere");
        JedinicaMere mera = JedinicaMere.valueOf(jedinica);
        double kol = rs.getDouble("kolicina");

        StavkaIzrade stavka = new StavkaIzrade(rb, mera, kol, mat, nacin);

        return stavka;
    }

    @Override
    public boolean DaLiImaSlabeObjekte() {
        return true;
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
        final NacinIzrade other = (NacinIzrade) obj;
        if (this.nacinId != other.nacinId) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiNazivSlabogObjekta() {
        return "stavkaizrade";
    }

    @Override
    public String vratiStringZaJOINSlabog() {
      return " INNER JOIN nacinizrade on nacinizrade.nacinId=stavkaizrade.nacinId"
                + " INNER JOIN materijal on stavkaizrade.materijalId=materijal.materijalId"
                + " INNER JOIN kucicazapse on nacinizrade.kucicaZaPseId=kucicazapse.kucicaZaPseId"
                + " INNER JOIN tipkucicezapse on kucicazapse.tipId = tipkucicezapse.tipId"
                + " INNER JOIN radnik on nacinizrade.jmbg = radnik.jmbg"
                + " INNER JOIN mesto on radnik.idMesto=mesto.idMesto";
    }

    @Override
    public List<IDomain> vartiSveSlabe() {
       List<IDomain> lista = new ArrayList<>();
        for (StavkaIzrade stavka : getStavakeIzrade()) {
            lista.add(stavka);
        }
        return lista;
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
