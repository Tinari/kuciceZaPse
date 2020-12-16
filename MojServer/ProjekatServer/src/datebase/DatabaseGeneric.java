/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datebase;

import domen.IDomain;
import domen.Mesto;
import domen.Radnik;
import domen.StavkaIzrade;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Win 7
 */
public class DatabaseGeneric {

    public List<IDomain> vratiSve(IDomain domenskiObjekat) throws Exception {

        List<IDomain> lista = new ArrayList<>();

        String upit = "SELECT * FROM " + domenskiObjekat.getTableName() + domenskiObjekat.vratiStringZaJOIN();
        System.out.println(upit);
        Connection connection = DatabaseConneection.getIstance().getConnection();
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        while (rs.next()) {
            lista.add(domenskiObjekat.vratiObjekat(rs));
            System.out.println(domenskiObjekat.vratiObjekat(rs));
        }
        rs.close();
        stat.close();
        return lista;
    }

    public Radnik logovanje(Radnik radnik) throws Exception {
        Radnik r = null;
        String upit = "SELECT * FROM radnik r join mesto m on r.idMesto=m.idMesto WHERE r.jmbg='" + radnik.getJmbg() + "'";

        Connection connection = DatabaseConneection.getIstance().getConnection();
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        if (rs.next()) {
            String ime = rs.getString("r.ime");
            String prezime = rs.getString("r.prezime");
            String pol = rs.getString("r.pol");
            Date datumZaposlenja = rs.getDate("r.datumZaposlenja");
            String status = rs.getString("r.status");
            Long idMesta = rs.getLong("m.idMesto");
            String nazivMesta = rs.getString("m.nazivMesta");

            Mesto m = new Mesto(idMesta, nazivMesta);
            r = new Radnik(radnik.getJmbg(), ime, prezime, pol, datumZaposlenja, status, m);

        }
        rs.close();
        stat.close();
        return r;
    }

    public void startTransaction() throws Exception {
        DatabaseConneection.getIstance().getConnection().setAutoCommit(false);
    }

    public void commitTransaction() throws Exception {
        DatabaseConneection.getIstance().getConnection().commit();
    }

    public void rollbackTransaction() throws Exception {
        DatabaseConneection.getIstance().getConnection().rollback();
    }

    public IDomain sacuvaj(IDomain iDomain) throws Exception {
        Connection connection = DatabaseConneection.getIstance().getConnection();
        Statement stat = null;
        Statement stat2 = null;

        try {
            StringBuilder sb = new StringBuilder(); 
            sb.append("INSERT INTO ")
                    .append(iDomain.getTableName())
                    .append("(")
                    .append(iDomain.getColumnNamesForInsert())
                    .append(")")
                    .append(" VALUES ")
                    .append("(")
                    .append(iDomain.getColumnValuesForInsert())
                    .append(")");
            System.out.println(iDomain.getColumnNamesForInsert());
            System.out.println(iDomain.getColumnValuesForInsert());
            stat = connection.createStatement();
            stat.executeUpdate(sb.toString(), Statement.RETURN_GENERATED_KEYS);

            if (iDomain.isIdAutoincrement()) {
                ResultSet rs = stat.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    iDomain.setAutoincrementId(id);
                    System.out.println(id);
                    if (iDomain.DaLiImaSlabeObjekte()) {
                        for (IDomain iDomain2 : iDomain.vartiSveSlabe()) {
                            StringBuilder sb1 = new StringBuilder();
                            sb1.append("INSERT INTO ")
                                    .append(iDomain2.getTableName())
                                    .append("(")
                                    .append(iDomain2.getColumnNamesForInsert())
                                    .append(")")
                                    .append(" VALUES ")
                                    .append("(")
                                    .append(iDomain2.getColumnValuesForInsert())
                                    .append(")");

                            String query1 = sb1.toString();
                            System.out.println(query1);
                            stat2 = connection.createStatement();
                            stat2.executeUpdate(query1);
                        }
                        stat2.close();
                        connection.commit();
                    }
                }
            }
            return iDomain;
        } catch (Exception e) {
            connection.rollback();
            throw new Exception("Save bill error. " + e.getMessage());
        } finally {
            if (stat != null) {
                stat.close();
            }
        }

    }

    public IDomain pronadjiPoId(IDomain iDomain) throws Exception {

        Connection connection = DatabaseConneection.getIstance().getConnection();
        String query = "SELECT * FROM " + iDomain.getTableName() + iDomain.vratiStringZaJOIN() + " WHERE " + iDomain.vratiPrimarniKljuc() + "=?";
        PreparedStatement ps = connection.prepareStatement(query);

        iDomain.postaviPrimarni(ps);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            iDomain = iDomain.vratiObjekat(rs);
            rs.close();
            ps.close();
            return iDomain;
        }
        throw new Exception("Ne postoji");

    }

    public boolean obrisi(IDomain iDomain) throws Exception {
        Connection connection = DatabaseConneection.getIstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ")
                .append(iDomain.getTableName())
                .append(" WHERE ")
                .append(iDomain.vratiPrimarniKljuc())
                .append("=")
                .append(iDomain.vratiVrednostPrimarnogKljucaString());

        String query = sb.toString();
        System.out.println("Query: " + query);
        Statement s = connection.createStatement();
        s.executeUpdate(query);
        s.close();
        return true;
    }

    public void azuriraj(IDomain iDomain) throws Exception {
        Connection connection = DatabaseConneection.getIstance().getConnection();
        connection.setAutoCommit(false);
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ")
                .append(iDomain.getTableName())
                .append(" SET ")
                .append(iDomain.postaviVrednosti())
                .append(" WHERE ")
                .append(iDomain.vratiPrimarniKljuc())
                .append("=")
                .append(iDomain.vratiVrednostPrimarnogKljucaString());

        String query = sb.toString();
        System.out.println("Query: " + query);
        Statement s = connection.createStatement();
        s.executeUpdate(query);
        if (iDomain.DaLiImaSlabeObjekte()) {
            System.out.println("Ima slabe");
            obrisi(iDomain.vartiSveSlabe().get(0));
            for (IDomain slab : iDomain.vartiSveSlabe()) {
                System.out.println(slab);
                sacuvaj(slab);
            }
        }
        connection.commit();
    }

    public List<IDomain> vratiStavke(IDomain iDomain) throws Exception {

        List<IDomain> lista = new ArrayList<>();

        Connection connection = DatabaseConneection.getIstance().getConnection();
        String upit = "SELECT * FROM " + iDomain.vratiNazivSlabogObjekta() + iDomain.vratiStringZaJOINSlabog() + " WHERE " + iDomain.vratiNazivSlabogObjekta() + "." + iDomain.vratiPrimarniKljuc() + "=" + iDomain.vratiVrednostPrimarnogKljucaString();
        //String upit = "SELECT * FROM " + domenskiObjekat.getTableName()+domenskiObjekat.vratiStringZaJOIN();
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit);

//        iDomain.postaviPrimarni(ps);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(iDomain.vratiSlabeObjekte(rs));
            //System.out.println(domenskiObjekat.vratiObjekat(rs));
        }
        rs.close();

        return lista;
    }

    public List<IDomain> vratiTrazene(IDomain iDomain) throws Exception {
        List<IDomain> lista = new ArrayList<>();

        Connection connection = DatabaseConneection.getIstance().getConnection();
        String upit = "SELECT * FROM " + iDomain.getTableName() + iDomain.vratiStringZaJOIN() + " WHERE " + iDomain.vratiUslov();
        //String upit = "SELECT * FROM " + domenskiObjekat.getTableName()+domenskiObjekat.vratiStringZaJOIN();
        System.out.println(upit);
        //PreparedStatement ps = connection.prepareStatement(upit);

//        iDomain.postaviPrimarni(ps);
        //ResultSet rs = ps.executeQuery();
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        while (rs.next()) {
            lista.add(iDomain.vratiObjekat(rs));
            System.out.println(iDomain.vratiObjekat(rs));
        }
        rs.close();

        return lista;
    }

    public List<IDomain> vratiTrazeneVeze(IDomain iDomain) throws Exception {
        List<IDomain> lista = new ArrayList<>();

        Connection connection = DatabaseConneection.getIstance().getConnection();
        String upit = "SELECT * FROM " + iDomain.getVeza() + iDomain.getJoinVeze() + " WHERE " + iDomain.getVeza() + "." + iDomain.vratiPrimarniKljuc() + "=" + iDomain.vratiVrednostPrimarnogKljucaString();
        System.out.println(upit);

        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(upit);
        while (rs.next()) {
            lista.add(iDomain.vratiObjekteVeze(rs));
            System.out.println(iDomain.vratiObjekat(rs));
        }
        rs.close();

        return lista;
    }

}
