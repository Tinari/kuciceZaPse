/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Mesto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Win 7
 */
public class TableModelMesto extends AbstractTableModel {

    private List<Mesto> lista;
    private String[] header = new String[]{"MestoId", "Naziv"};

    public TableModelMesto(List<Mesto> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int i, int j) {
        Mesto mesto = lista.get(i);
        switch (j) {
            case 0:
                return mesto.getIdMesta();
            case 1:
                return mesto.getNazivMesta();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    
}
