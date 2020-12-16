/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.JedinicaMere;
import domen.Materijal;
import domen.NacinIzrade;
import domen.StavkaIzrade;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Win 7
 */
public class TableModelNacinIzrade extends AbstractTableModel {

    NacinIzrade nacinIzrade;
    String[] kolone = new String[]{"RBR", "Materijal", "Kolicina", "Jedinica mere"};

    public NacinIzrade getNacinIzrade() {
        return nacinIzrade;
    }

    public void setNacinIzrade(NacinIzrade nacinIzrade) {
        this.nacinIzrade = nacinIzrade;
    }

    public String[] getKolone() {
        return kolone;
    }

    public void setKolone(String[] kolone) {
        this.kolone = kolone;
    }

    public TableModelNacinIzrade(NacinIzrade nacinIzrade) {
        this.nacinIzrade = nacinIzrade;
    }

    @Override
    public int getRowCount() {
        return nacinIzrade.getStavakeIzrade().size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaIzrade stavka = nacinIzrade.getStavakeIzrade().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getRbStavke();
            case 1:
                return stavka.getMaterijal() != null ? stavka.getMaterijal().getNazivMaterijala() : "-";
            case 2:
                return stavka.getKolicina();
            case 3:
                return stavka.getJedinicaMere() != null ? stavka.getJedinicaMere().toString() : "-";
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StavkaIzrade stavka = nacinIzrade.getStavakeIzrade().get(rowIndex);
        switch (columnIndex) {
            case 1:
                stavka.setMaterijal((Materijal) aValue);
                break;
            case 2:
                stavka.setKolicina(Double.parseDouble(aValue.toString()));
                break;
            case 3:
                stavka.setJedinicaMere(JedinicaMere.valueOf(aValue.toString()));
                break;
        }
    }

    public void dodajNovuStavku() {

        List<StavkaIzrade> stavke = nacinIzrade.getStavakeIzrade();
        if (stavke.isEmpty()
                || (stavke.get(stavke.size() - 1).getMaterijal() != null && stavke.get(stavke.size() - 1).getJedinicaMere() != null
                && stavke.get(stavke.size() - 1).getKolicina() > 0)) {
            StavkaIzrade stavka = new StavkaIzrade();
            stavka.setRbStavke(nacinIzrade.getStavakeIzrade().size() + 1);

            nacinIzrade.getStavakeIzrade().add(stavka);

            fireTableDataChanged();
        }

    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void obrisiStavku(int selektovani) {
        if (nacinIzrade != null && selektovani >= 0 && nacinIzrade.getStavakeIzrade().size() > selektovani) {
            nacinIzrade.getStavakeIzrade().remove(selektovani);
            srediStavke();
            fireTableDataChanged();
        }
    }

    private void srediStavke() {
        List<StavkaIzrade> stavke = nacinIzrade.getStavakeIzrade();
        for (int i = 0; i < stavke.size(); i++) {
            stavke.get(i).setRbStavke(i + 1);
        }
    }

    public void postavIdStavkama() {

        for (StavkaIzrade stavka : nacinIzrade.getStavakeIzrade()) {
            stavka.setNacinIzrade(nacinIzrade);

        }
    }

    public void dodajNovuStavku(StavkaIzrade stavaka) {
        List<StavkaIzrade> stavke = nacinIzrade.getStavakeIzrade();
        if (stavke.isEmpty()
                || (stavke.get(stavke.size() - 1).getMaterijal() != null && stavke.get(stavke.size() - 1).getJedinicaMere() != null
                && stavke.get(stavke.size() - 1).getKolicina() > 0)) {
            //StavkaIzrade stavka = new StavkaIzrade();
            stavaka.setRbStavke(nacinIzrade.getStavakeIzrade().size() + 1);

            nacinIzrade.getStavakeIzrade().add(stavaka);

            fireTableDataChanged();
        }
        //nacinIzrade.getStavakeIzrade().add(stavaka);
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex != 0) {
            return true;
        }
        return false;
    }

}
