/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.NacinIzrade;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Win 7
 */
public class TableModelNaciniIzrade extends AbstractTableModel {

    private List<NacinIzrade> naciniIzrade;
    private String[] kolone = new String[]{"ID", "Datum unosa", "Opis", "Kucica za pse", "Radnik"};

    public TableModelNaciniIzrade(List<NacinIzrade> naciniIzrade) {
        this.naciniIzrade = naciniIzrade;
    }

    @Override
    public int getRowCount() {
        return naciniIzrade.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NacinIzrade nacin = naciniIzrade.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return nacin.getNacinId();
            case 1:
                return nacin.getDatum();
            case 2:
                return nacin.getOpis();
            case 3:
                return nacin.getKucicaZaPse();
            case 4:
                return nacin.getRadnik();
            default:
                return "N/A";
        }

    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    

}
