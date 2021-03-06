//+======================================================================
// $Source:  $
//
// Project:   Tango
//
// Description:  Basic Dialog Class to display info
//
// $Author: pascal_verdier $
//
// Copyright (C) :      2004,2005,2006,2007,2008,2009,2009
//						European Synchrotron Radiation Facility
//                      BP 220, Grenoble 38043
//                      FRANCE
//
// This file is part of Tango.
//
// Tango is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// Tango is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with Tango.  If not, see <http://www.gnu.org/licenses/>.
//
// $Revision:  $
//
// $Log:  $
//
//-======================================================================

package admin.astor.tools;


import fr.esrf.Tango.DevFailed;
import fr.esrf.TangoApi.ApiUtil;
import fr.esrf.tangoatk.widget.util.ATKGraphicsUtils;
import fr.esrf.tangoatk.widget.util.ErrorPane;

import javax.swing.*;
import java.awt.*;
import java.util.List;


//===============================================================
/**
 * JDialog Class with a list to select sever to display usage.
 *
 * @author Pascal Verdier
 */
//===============================================================


@SuppressWarnings({"MagicConstant", "Convert2Diamond"})
public class ListDialog extends JDialog {

    private List<String> selection = null;

    //===============================================================

    /**
     * Basic Dialog for Swing Appli
     *
     * @param parent the parent instance
     * @param title  the dialog title
     * @param items  the items to be put in the selection list
     */
    //===============================================================
    public ListDialog(JFrame parent, String title, String[] items) {
        super(parent, true);
        initComponents();
        jList.setListData(items);


        int length = 0;
        for (String item : items)
            if (item.length() > length)
                length = item.length();
        length *= 8;
        int height = items.length * 10;
        if (height > 600)
            height = 600;
        scrollPane.setPreferredSize(new Dimension(length, height));

        if (title != null)
            titleLabel.setText(title);
        pack();
        ATKGraphicsUtils.centerDialog(this);
    }

    //===============================================================
    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    //===============================================================
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel topPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        javax.swing.JPanel bottomPanel = new javax.swing.JPanel();
        javax.swing.JButton okBtn = new javax.swing.JButton();
        javax.swing.JButton cancelBtn = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        jList = new javax.swing.JList<String>();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        titleLabel.setText("List Selection");
        topPanel.add(titleLabel);

        getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);

        okBtn.setText("OK");
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(okBtn);

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(cancelBtn);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);

        jList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(jList);

        getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //===============================================================
    //===============================================================
    @SuppressWarnings({"UnusedParameters"})
    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
        Object  object = jList.getSelectedValue();
        if (object!=null)
            selection = jList.getSelectedValuesList();
        doClose();
    }//GEN-LAST:event_okBtnActionPerformed

    //===============================================================
    //===============================================================
    @SuppressWarnings({"UnusedParameters"})
    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        doClose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    //===============================================================
    //===============================================================
    @SuppressWarnings({"UnusedParameters"})
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose();
    }//GEN-LAST:event_closeDialog

    //===============================================================
    //===============================================================
    private void jListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListMouseClicked
        if (evt.getClickCount() == 2) {
            if (jList.getSelectedValue() != null) {
                selection = jList.getSelectedValuesList();
                doClose();
            }
        }
    }//GEN-LAST:event_jListMouseClicked

    //===============================================================
    /**
     * Closes the dialog
     */
    //===============================================================
    private void doClose() {
        setVisible(false);
        dispose();
    }

    //===============================================================
    //===============================================================
    public List<String> showDialog() {
        setVisible(true);
        return selection;
    }

    //===============================================================
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jList;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
    //===============================================================


    //===============================================================

    /**
     * @param args the command line arguments
     */
    //===============================================================
    public static void main(String args[]) {
        try {
            String[] servers = ApiUtil.get_db_obj().get_server_name_list();
            ListDialog dialog = new ListDialog(null, "Servers", servers);
            List<String> serverNames = dialog.showDialog();
            for (String serverName : serverNames)
                if (serverName != null)
                    System.out.println(serverName);
        } catch (DevFailed e) {
            ErrorPane.showErrorMessage(new Frame(), null, e);
        }
    }

}
