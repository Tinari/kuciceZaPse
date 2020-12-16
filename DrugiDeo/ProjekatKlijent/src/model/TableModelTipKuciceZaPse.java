/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.TipKuciceZaPse;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Win 7
 */
public class TableModelTipKuciceZaPse extends AbstractTableModel {

    List<TipKuciceZaPse> lista;
    String[] kolone = new String[]{"ID", "Tip kucice za pse"};

    public TableModelTipKuciceZaPse(List<TipKuciceZaPse> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TipKuciceZaPse tip = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tip.getTipId();
            case 1:
                return tip.getNazivTipa();
            default:
                return "N/A";
        }
    }

}
