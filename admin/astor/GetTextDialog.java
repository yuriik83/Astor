//+======================================================================
// $Source:  $
//
// Project:   Tango
//
// Description:  java source code for Tango manager tool..
//
// $Author$
//
// Copyright (C) :      2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,
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
// $Revision$
//
//-======================================================================


package admin.astor;

import fr.esrf.tangoatk.widget.util.ATKGraphicsUtils;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 * Class Description: Basic Dialog Class to display info
 *
 * @author Pascal Verdier
 */
public class GetTextDialog extends JDialog {
    private int retVal = JOptionPane.OK_OPTION;

    //===============================================================
    /*
     * Creates new form GetTextDialog
     */
    //===============================================================
    public GetTextDialog(JDialog parent, String title, String tip, String[] lines) {
        super(parent, true);
        initComponents();
        List<String> list = new ArrayList<>();
        if (lines!=null)
            Collections.addAll(list,lines);

        initialize(title, tip, list);
    }
    //===============================================================
    /*
     * Creates new form GetTextDialog
     */
    //===============================================================
    public GetTextDialog(JFrame parent, String title, String tip, List<String> lines) {
        super(parent, true);
        initComponents();

        initialize(title, tip, lines);
    }

    //===============================================================
    //===============================================================
    private void initialize(String title, String tip, List<String> lines) {
        //	Set title and help
        titleLabel.setText(title);
        textArea.setToolTipText(tip);

        //	Set default text
        if (lines != null) {
            String text = "";
            for (String line : lines)
                text += line.trim() + "\n";
            textArea.setText(text);
        }
        //	Set Size and Position
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

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JButton okBtn = new javax.swing.JButton();
        javax.swing.JButton cancelBtn = new javax.swing.JButton();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        okBtn.setText("OK");
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });
        jPanel1.add(okBtn);

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        jPanel1.add(cancelBtn);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        titleLabel.setText("Dialog Title");
        jPanel2.add(titleLabel);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 300));

        textArea.setColumns(60);
        textArea.setFont(new java.awt.Font("Courier", 1, 14)); // NOI18N
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //===============================================================
    //===============================================================
    @SuppressWarnings("UnusedParameters")
    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
        retVal = JOptionPane.OK_OPTION;
        doClose();
    }//GEN-LAST:event_okBtnActionPerformed

    //===============================================================
    //===============================================================
    @SuppressWarnings("UnusedParameters")
    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        retVal = JOptionPane.CANCEL_OPTION;
        doClose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    //===============================================================
    //===============================================================
    @SuppressWarnings("UnusedParameters")
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        retVal = JOptionPane.CANCEL_OPTION;
        doClose();
    }//GEN-LAST:event_closeDialog

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
    public String getText() {
        return textArea.getText().trim();
    }

    //===============================================================
    //===============================================================
    public List<String> getTextLines() {
        String str = getText();
        List<String> tokens = new ArrayList<>();
        StringTokenizer stk = new StringTokenizer(str, "\n");
        while (stk.hasMoreTokens())
            tokens.add(stk.nextToken());
        return tokens;
    }
    //===============================================================
    //===============================================================
    public String[] getTextLinesAsArray() {
        List<String> list = getTextLines();
        String[]    array = new String[list.size()];
        for (int i=0 ; i<list.size() ; i++)
            array[i] = list.get(i);
        return array;
    }

    //===============================================================
    //===============================================================
    public int showDialog() {
        setVisible(true);
        return retVal;
    }

    //===============================================================
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea textArea;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
    //===============================================================
}
