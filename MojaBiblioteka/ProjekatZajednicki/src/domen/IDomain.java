/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Win 7
 */
public interface IDomain {

    public String getTableName();

    public String getColumnNamesForInsert();

    public String getColumnValuesForInsert();

    public boolean isIdAutoincrement();

    public void setAutoincrementId(Integer id);

    public String vratiPrimarniKljuc();

    public String vratiVrednostPrimarnogKljucaString();

    public String postaviVrednosti();

    public IDomain vratiObjekat(ResultSet rs) throws Exception;

    public void postaviPrimarni(PreparedStatement ps);

    public String vratiStringZaJOIN();

    public IDomain vratiSlabeObjekte(ResultSet rs) throws Exception;

    public List<IDomain> vartiSveSlabe();

    public boolean DaLiImaSlabeObjekte();

    public String vratiNazivSlabogObjekta();

    public String vratiStringZaJOINSlabog();

    public String vratiUslov();

    public String getVeza();

    public String getJoinVeze();

    public IDomain vratiObjekteVeze(ResultSet rs) throws Exception;

}
