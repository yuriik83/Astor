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

import fr.esrf.Tango.DevFailed;
import fr.esrf.TangoApi.DbDatum;
import fr.esrf.TangoApi.DeviceProxy;
import fr.esrf.tangoatk.widget.util.ErrorPane;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *	This class is the Astor dialog
 *	to create a new Starter server in TANGO database
 *	and add a new host in AstorTree.
 *
 * @author verdier
 */
@SuppressWarnings("MagicConstant")
public class NewStarterDialog extends JDialog implements AstorDefs {

    private Astor parent;
    private TangoHost[] hosts;
    private TangoHost host;
    private List<String> collections;
    private int retVal = JOptionPane.CANCEL_OPTION;
    private boolean creating;

    //======================================================================
    //======================================================================
    private boolean getUseEvents() {
        //	Check if use events
        //	Do not use host field because it could
        //	have been reset if notifd is stopped !!!!!
        boolean ue = false;
        try {
            DbDatum data = host.get_property("UseEvents");
            if (!data.is_empty())
                ue = data.extractBoolean();
        } catch (Exception e) { /* */ }
        return ue;
    }

    //======================================================================
    /*
	 *	Creates new NewStarterDialog for editing properties
	 */
    //======================================================================
    public NewStarterDialog(Astor parent, TangoHost host,
                            List<String> collectionList, TangoHost[] hosts, boolean creating) {
        super(parent, true);
        this.parent = parent;
        this.hosts = hosts;
        this.creating = creating;
        //	Take Off Database (first one)
        this.collections = new ArrayList<>();
        for (int i = 1; i < collectionList.size(); i++)
            this.collections.add(collectionList.get(i));

        initComponents();

        //	Init text fields with host object fields
        this.host = host;
        if (host != null) {
            String hostname = host.getName();
            String[] ds_path = host.getPath();
            for (String path : ds_path)
                pathText.append(path + "\n");

            usageText.setText(host.usage);
            familyText.setText(host.getFamily());
            hostText.setText(hostname);

            useEventsBtn.setSelected(getUseEvents());
            if (creating) {
                familyText.setText(host.getFamily());
                hostText.setText(hostname);
                hostText.select(0, hostname.length());
            } else {
                titleLbl.setText("Change property for " +
                        hostname + " starter in database");
                jLabel2.setText("  ");
                hostText.setVisible(false);
                jLabel4.setVisible(false);
                familyText.setVisible(false);
                familyBtn.setVisible(false);
                createBtn.setText("Apply");
            }
        }
        pack();
        AstorUtil.centerDialog(this, parent);
    }

    //======================================================================

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    //======================================================================
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        javax.swing.JPanel topPanel = new javax.swing.JPanel();
        titleLbl = new javax.swing.JLabel();
        javax.swing.JPanel centerPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        hostText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        familyText = new javax.swing.JTextField();
        javax.swing.JButton pathBtn = new javax.swing.JButton();
        familyBtn = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        pathText = new javax.swing.JTextArea();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        usageText = new javax.swing.JTextField();
        useEventsBtn = new javax.swing.JRadioButton();
        javax.swing.JPanel bottomPanel = new javax.swing.JPanel();
        createBtn = new javax.swing.JButton();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JButton cancelBtn = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.BorderLayout());

        titleLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        titleLbl.setText("Create a Starter in Database For a New Host");
        topPanel.add(titleLbl);

        getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);

        centerPanel.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Host name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        centerPanel.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Device Servers PATH :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        centerPanel.add(jLabel5, gridBagConstraints);

        hostText.setColumns(20);
        hostText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostTextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        centerPanel.add(hostText, gridBagConstraints);

        jLabel2.setText("Host Family:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        centerPanel.add(jLabel2, gridBagConstraints);

        familyText.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        centerPanel.add(familyText, gridBagConstraints);

        pathBtn.setText("...");
        pathBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        pathBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        centerPanel.add(pathBtn, gridBagConstraints);

        familyBtn.setText("...");
        familyBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        familyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                familyBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        centerPanel.add(familyBtn, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 180));
        jScrollPane1.setViewportView(pathText);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        centerPanel.add(jScrollPane1, gridBagConstraints);

        jLabel8.setText("Host Usage:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        centerPanel.add(jLabel8, gridBagConstraints);

        usageText.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        centerPanel.add(usageText, gridBagConstraints);

        useEventsBtn.setText("Manage notifd");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        centerPanel.add(useEventsBtn, gridBagConstraints);

        getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);

        createBtn.setText("Create");
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(createBtn);

        jLabel1.setText("          ");
        bottomPanel.add(jLabel1);

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(cancelBtn);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //======================================================================
    //======================================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void hostTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostTextActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_hostTextActionPerformed

    //======================================================================
    //======================================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void familyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_familyBtnActionPerformed

        //	Create the list for host family
        PropListDialog list = new PropListDialog(parent, collections);
        list.showDialog();

        String family = list.getSelectedItem();
        if (family != null)
            familyText.setText(family);
    }//GEN-LAST:event_familyBtnActionPerformed

    //======================================================================
    //======================================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void pathBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathBtnActionPerformed

        /* Not used any more
         *  Path seen from Astor (e.g: y:\tango\bin)
         *  could be different than from Starter on crate (e.g: /opt/bin)
         *
        if (chooser==null) {
            chooser = new JFileChooser(AstorUtil.getStarterPathHome());
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }
        if (chooser.showDialog(this, "Add Path")==JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file!=null)
                if (file.isDirectory()) {
                    String path = pathText.getText().trim() + "\n" + file;
                    pathText.setText(path);
                }
        }
        */

        //	Create the list for Start DS Path
        new PropListDialog(parent, pathText, hosts).showDialog();
    }//GEN-LAST:event_pathBtnActionPerformed

    //======================================================================
    //======================================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        retVal = JOptionPane.CANCEL_OPTION;
        doClose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    //======================================================================
    //======================================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBtnActionPerformed
        String hostname = hostText.getText();
        String usage = usageText.getText();
        String family = familyText.getText();
        String str_ds_path = pathText.getText().trim();
        if (hostname.length() == 0 || str_ds_path.length() == 0) {
            JOptionPane.showMessageDialog(parent,
                    "Fill the fields before creation !",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            String[] ds_path = AstorUtil.string2StringArray(str_ds_path);
            boolean use_events = (useEventsBtn.getSelectedObjects() != null);
            MkStarter starter = new MkStarter(hostname, ds_path, use_events);
            if (creating)
                starter.create();
            starter.setProperties();

            //	Set usage and family properties
            starter.setAdditionalProperties(AstorDefs.usage_property, usage, creating);
            starter.setAdditionalProperties(AstorDefs.collec_property, family,creating);

            //	And if not creating set usage to Host object
            if (host != null && !creating)
                if (!usage.equals(host.usage)) {
                    host.usage = usage;
                    //	Re-create node to resize.
                    Astor astor = parent;
                    astor.tree.changeHostNode(host);
                }

            String message;
            if (creating)
                message =
                        "A Starter server has been created in TANGO database.\n\n" +
                                "You can now start it on " + hostname + " machine.";
            else {
                message = "The property has been modified in database";
                try {
                    String deviceName = AstorUtil.getStarterDeviceHeader() + hostname;
                    new DeviceProxy(deviceName).command_inout("Init");
                    message += "\nand the device has been re-initialized.";
                } catch (DevFailed e) {
                    //	may be it is stopped
                }
            }
            JOptionPane.showMessageDialog(parent,
                    message, "Command Done", JOptionPane.INFORMATION_MESSAGE);

        } catch (DevFailed e) {
            ErrorPane.showErrorMessage(parent, null, e);
            return;
        }
        retVal = JOptionPane.OK_OPTION;
        doClose();
    }//GEN-LAST:event_createBtnActionPerformed

    //======================================================================
    //======================================================================
    int getValue() {
        return retVal;
    }

    //======================================================================
    //======================================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        retVal = JOptionPane.CANCEL_OPTION;
        doClose();
    }//GEN-LAST:event_closeDialog

    //======================================================================
    //======================================================================
    private void doClose() {
        setVisible(false);
        dispose();
    }
    //======================================================================
    //======================================================================


    //======================================================================
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createBtn;
    private javax.swing.JButton familyBtn;
    private javax.swing.JTextField familyText;
    private javax.swing.JTextField hostText;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextArea pathText;
    private javax.swing.JLabel titleLbl;
    private javax.swing.JTextField usageText;
    private javax.swing.JRadioButton useEventsBtn;
    // End of variables declaration//GEN-END:variables
    //======================================================================

}
