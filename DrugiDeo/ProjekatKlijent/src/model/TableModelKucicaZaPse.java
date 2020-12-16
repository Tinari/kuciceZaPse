/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import domen.KucicaZaPse;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Win 7
 */
public class TableModelKucicaZaPse extends AbstractTableModel {

    private List<KucicaZaPse> lista;
    private String[] kolone = new String[]{"ID", "Naziv kucice za pse", "Tip kucice za pse"};

    public TableModelKucicaZaPse(List<KucicaZaPse> lista) {
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
        KucicaZaPse kzp = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kzp.getKucicaZaPseId();
            case 1:
                return kzp.getNazivKuciceZaPse();
            case 2:
                return kzp.getTipKuciceZaPse();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public KucicaZaPse vrati(int red){
        return lista.get(red);
    }

    public void obrisi(int selektovani) {
        lista.remove(selektovani);
        fireTableDataChanged();
    }

}
