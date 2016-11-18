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

import fr.esrf.TangoApi.DbServInfo;

import javax.swing.*;
import java.awt.*;

/**
 *	This class is a dialog window to get the controlled server parameters.
 *
 * @author verdier
 */
@SuppressWarnings("MagicConstant")
public class PutServerInfoDialog extends javax.swing.JDialog {

    public static final int RET_CANCEL = JOptionPane.CANCEL_OPTION;
    public static final int RET_OK = JOptionPane.OK_OPTION;

    private DbServInfo server_info;

    private JButton unregisterBtn;
    private boolean unregister = false;
    private Component parent;

    //-======================================================================
    //-======================================================================
    public PutServerInfoDialog(JDialog parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        initComponents();

        //	Initialize ComboBox
        jComboBox1.addItem("None");
        int nb = AstorUtil.getStarterNbStartupLevels();
        for (int i = 1; i <= nb; i++) {
            String s = "Level " + i;
            jComboBox1.addItem(s);
        }
        pack();
    }

    //-======================================================================
    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    //-======================================================================
    private void initComponents() {//GEN-BEGIN:initComponents
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton();
        JButton cancelButton = new JButton();
        JPanel jPanel1 = new JPanel();
        JLabel jLabel1 = new JLabel();
        yesButton = new javax.swing.JRadioButton();
        noButton = new javax.swing.JRadioButton();
        JLabel jLabel2 = new JLabel();
        jComboBox1 = new JComboBox<>();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel5 = new JLabel();
        JLabel jLabel6 = new JLabel();
        title = new javax.swing.JLabel();
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        }
        );

        buttonPanel.setLayout(new java.awt.FlowLayout(2, 5, 5));

        okButton.setText("  OK  ");
        okButton.addActionListener(new java.awt.event.ActionListener() {
                                       public void actionPerformed(java.awt.event.ActionEvent evt) {
                                           okButtonActionPerformed(evt);
                                       }
                                   }
        );

        buttonPanel.add(okButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
                                           public void actionPerformed(java.awt.event.ActionEvent evt) {
                                               cancelButtonActionPerformed(evt);
                                           }
                                       }
        );

        buttonPanel.add(cancelButton);


        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;

        jLabel1.setText("Controlled by Astor : ");
        jLabel1.setForeground(java.awt.Color.black);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jLabel1, gridBagConstraints1);

        yesButton.setText("Yes");
        yesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesnoButtonActionPerformed(evt);
            }
        }
        );

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 1;
        jPanel1.add(yesButton, gridBagConstraints1);

        noButton.setText("No");
        noButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesnoButtonActionPerformed(evt);
            }
        }
        );

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 1;
        jPanel1.add(noButton, gridBagConstraints1);

        jLabel2.setText("Startup Level : ");
        jLabel2.setForeground(java.awt.Color.black);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTH;
        jPanel1.add(jLabel2, gridBagConstraints1);


        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.gridheight = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.weightx = 2.0;
        jPanel1.add(jComboBox1, gridBagConstraints1);

        jLabel3.setText(" ");
        jLabel3.setForeground(java.awt.Color.black);
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 10));

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTH;
        jPanel1.add(jLabel3, gridBagConstraints1);

        jLabel5.setPreferredSize(new java.awt.Dimension(50, 40));
        jLabel5.setMinimumSize(new java.awt.Dimension(50, 40));
        jLabel5.setText(" ");
        jLabel5.setForeground(java.awt.Color.black);
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 10));

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 5;
        gridBagConstraints1.gridheight = 10;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints1.weighty = 10.0;
        jPanel1.add(jLabel5, gridBagConstraints1);

        jLabel6.setText(" ");
        jLabel6.setForeground(java.awt.Color.black);
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 10));

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTH;
        jPanel1.add(jLabel6, gridBagConstraints1);


        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 7;
        jPanel1.add(new JLabel("  "), gridBagConstraints1);

        unregisterBtn = new JButton("Remove startup level info");
        unregisterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unregisterBtnActionPerformed(evt);
            }
        });
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 8;
        gridBagConstraints1.gridwidth = 3;
        jPanel1.add(unregisterBtn, gridBagConstraints1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        title.setText(" ");
        title.setForeground(java.awt.Color.black);
        title.setFont(new java.awt.Font("Dialog", 1, 16));


        getContentPane().add(title, java.awt.BorderLayout.NORTH);

    }//GEN-END:initComponents

    //============================================================
    //============================================================
    private void unregisterBtnActionPerformed(@SuppressWarnings("UnusedParameters") java.awt.event.ActionEvent evt) {
        //	Ask to confirm
        if (unregister = (JOptionPane.showConfirmDialog(parent,
                "Are you sure to want to remove " +
                        server_info.name + " startup info ?",
                "Confirm Dialog",
                JOptionPane.YES_NO_OPTION)) == JOptionPane.OK_OPTION) {
            doClose(RET_OK);
        }
    }

    //============================================================
    //============================================================
    private void yesnoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesnoButtonActionPerformed
        String org = evt.getActionCommand();
        if (org.equals("Yes"))
            updateButtons(true, server_info.startup_level);
        else
            updateButtons(false, 0);
    }//GEN-LAST:event_yesnoButtonActionPerformed

    //============================================================
    //============================================================
    private void cancelButtonActionPerformed(@SuppressWarnings("UnusedParameters") java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    //============================================================
    /**
     * Closes the dialog
     */
    //============================================================
    private void okButtonActionPerformed(@SuppressWarnings("UnusedParameters") java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        boolean ctrl = (yesButton.getSelectedObjects() != null);
        int level = jComboBox1.getSelectedIndex();
        if (!ctrl || level == 0) {
            level = 0;
        }

        //  Check if has changed
        if (ctrl!=server_info.controlled ||
            level!=server_info.startup_level) {
            doClose(RET_OK);
        }
        else
            doClose(RET_CANCEL);
    }//GEN-LAST:event_okButtonActionPerformed

    //============================================================
    /**
     * Closes the dialog
     */
    //============================================================
    private void closeDialog(@SuppressWarnings("UnusedParameters") java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    //============================================================
    //============================================================
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }


    //============================================================

    /**
     * Update configuration buttons.
     */
    //============================================================
    private void updateButtons(boolean ctrl, int level) {
        yesButton.setSelected(ctrl);
        noButton.setSelected(!ctrl);
        jComboBox1.setEnabled(ctrl);

        if (ctrl) {
            server_info.startup_level = level;
            jComboBox1.setSelectedIndex(level);
            unregisterBtn.setVisible(false);
        } else {
            jComboBox1.setSelectedIndex(0);
            if (server_info.name.length() > 0 && manage_unregister)
                unregisterBtn.setVisible(true);
            else
                unregisterBtn.setVisible(false);
        }
    }
    //============================================================

    /**
     * Update configuration buttons and display dialog
     */
    //============================================================
    public int showDialog(DbServInfo info) {
        server_info = info;

        title.setText("  " + info.name + " running on " + info.host + "  ");
        updateButtons(info.controlled, info.startup_level);
        pack();
        setVisible(true);
        return returnStatus;
    }
    //============================================================
    /**
     * Update configuration buttons and display dialog
     */
    //============================================================
    private boolean manage_unregister = true;
    public int showDialog(DbServInfo info, int level) {
        server_info = info;
        manage_unregister = false;

        title.setText("  " + "Servers (Level " + level + ")   running on " + info.host + "  ");
        updateButtons(info.controlled, info.startup_level);

        pack();
        setVisible(true);
        return returnStatus;
    }

    //============================================================

    /**
     * Get configuration from buttons and return info object.
     */
    //============================================================
    public DbServInfo getSelection() {
        if (unregister)
            return null;

        boolean ctrl = (yesButton.getSelectedObjects() != null);
        int level = jComboBox1.getSelectedIndex();
        if (!ctrl || level == 0) {
            level = 0;
        }

        return new DbServInfo(server_info.name, server_info.host, ctrl, level);
    }
    //============================================================
    //============================================================


    private javax.swing.JRadioButton yesButton;
    private javax.swing.JRadioButton noButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
    //============================================================

    private int returnStatus = RET_CANCEL;
}