/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Radnik;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Win 7
 */
public class TableModelRadnik extends AbstractTableModel {

    private final List<Radnik> radnici;
    private String[] columnNames = new String[]{"Jmbg", "Ime", "Prezime"};

    public TableModelRadnik(List<Radnik> radnici) {
        this.radnici = radnici;
    }

    public TableModelRadnik() {
        this.radnici =new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return radnici == null ? 0 : radnici.size();

    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Radnik radnik = radnici.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return radnik.getJmbg();
            case 1:
                return radnik.getIme();
            case 2:
                return radnik.getPrezime();
            case 4:
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String datumR = sdf.format(radnik.getDatumZaposlenja());
                return datumR;
            case 3:
                return radnik.getPol();
            case 5:
                return radnik.getStatus();
            case 6:
                return radnik.getMesto().getNazivMesta();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public Radnik vratiRadnika(int red){
        return radnici.get(red);
    }

    public void obrisi(int row) {
        radnici.remove(row);
        fireTableDataChanged();
    }

    public void dodaj(Radnik ulogovaniRanik) {
        radnici.add(ulogovaniRanik);
        fireTableDataChanged();
    }

}
