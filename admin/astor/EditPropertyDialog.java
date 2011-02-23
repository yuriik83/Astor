//+======================================================================
// $Source$
//
// Project:   Tango
//
// Description:  Basic Dialog Class to display info
//
// $Author$
//
// $Revision$
// $Log$
// Revision 3.3  2005/11/24 12:24:57  pascal_verdier
// DevBrowser utility added.
// MkStarter utility added.
//
// Revision 3.2  2005/11/17 12:30:33  pascal_verdier
// Analysed with IntelliJidea.
//
// Revision 3.1  2005/10/14 14:28:28  pascal_verdier
// Edit property added to server achitecture tree.
//
//
//
// Copyleft 2005 by European Synchrotron Radiation Facility, Grenoble, France
//               All Rights Reversed
//-======================================================================

package admin.astor;

import javax.swing.*;
import java.awt.*;


//===============================================================
/**
 *	Class Description: Basic Dialog Class to display info
 *
 *	@author  root
 */
//===============================================================


public class EditPropertyDialog extends JDialog {
	private ServArchitectureDialog.TgProperty	retVal = null;
	private ServArchitectureDialog.TgProperty	prop;
	private String	initial_value;
    //===============================================================
    /**
     *	Creates new form EditPropertyDialog
     */
    //===============================================================
    public EditPropertyDialog(JDialog parent, ServArchitectureDialog.TgProperty prop)
    {
        super(parent, true);
        this.prop   = prop;
        initComponents();

        String	title = prop.src + ": " + prop.objname + "/" + prop.name;
        titleLabel.setText(title);

        descriptionTxt.setText(prop.desc);
        defaultValTxt.setText(
            ServArchitectureDialog.OneLine2multiLine(prop.def_value));
        dbValTxt.setText(
            ServArchitectureDialog.OneLine2multiLine(prop.db_value));
        initial_value = dbValTxt.getText();
        manageOkBtn();
        pack();
        AstorUtil.centerDialog(this, parent);
    }
    //===============================================================
    /**
     *	Creates new form EditPropertyDialog
     */
    //===============================================================
    public EditPropertyDialog(JFrame parent, ServArchitectureDialog.TgProperty prop)
    {
        super(parent, true);
        this.prop   = prop;
        initComponents();

        String	title = prop.src + ": " + prop.objname + "/" + prop.name;
        titleLabel.setText(title);

        descriptionTxt.setText(prop.desc);
        defaultValTxt.setText(
            ServArchitectureDialog.OneLine2multiLine(prop.def_value));
        dbValTxt.setText(
            ServArchitectureDialog.OneLine2multiLine(prop.db_value));
        initial_value = dbValTxt.getText();
        manageOkBtn();
        pack();
        AstorUtil.centerDialog(this, parent);
    }

	//===============================================================
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
	//===============================================================
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        okBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTextField12 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        defaultValTxt = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        descriptionTxt = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        dbValTxt = new javax.swing.JTextArea();

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

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Dialog", 1, 14));
        jTextField12.setText("  ");
        jTextField12.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(jTextField12, gridBagConstraints);

        jLabel2.setText("Default Value : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(jLabel2, gridBagConstraints);

        defaultValTxt.setBackground(new java.awt.Color(204, 204, 204));
        defaultValTxt.setColumns(40);
        defaultValTxt.setEditable(false);
        defaultValTxt.setBorder(new javax.swing.border.EtchedBorder());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(defaultValTxt, gridBagConstraints);

        jLabel1.setText("  ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        jPanel4.add(jLabel1, gridBagConstraints);

        jLabel11.setText("  ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 19;
        jPanel4.add(jLabel11, gridBagConstraints);

        jLabel3.setText("Database Value : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(jLabel3, gridBagConstraints);

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 18));
        titleLabel.setText("Dialog Title");
        jPanel2.add(titleLabel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel4.add(jPanel2, gridBagConstraints);

        descriptionTxt.setBackground(new java.awt.Color(204, 204, 204));
        descriptionTxt.setEditable(false);
        descriptionTxt.setFont(new java.awt.Font("Dialog", 1, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel4.add(descriptionTxt, gridBagConstraints);

        jPanel5.add(jPanel4);

        jPanel3.add(jPanel5, java.awt.BorderLayout.NORTH);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(180, 100));
        dbValTxt.setColumns(40);
        dbValTxt.setBorder(new javax.swing.border.EtchedBorder());
        dbValTxt.setPreferredSize(null);
        dbValTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dbValTxtKeyReleased(evt);
            }
        });
        dbValTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dbValTxtMouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(dbValTxt);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

	//===============================================================
	//===============================================================
	private void dbValTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dbValTxtMouseClicked
		manageOkBtn();
	}//GEN-LAST:event_dbValTxtMouseClicked
	//===============================================================
	//===============================================================
	private void dbValTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dbValTxtKeyReleased
		manageOkBtn();
	}//GEN-LAST:event_dbValTxtKeyReleased

	//===============================================================
	//===============================================================
	private void manageOkBtn()
	{
		//	Set OK btn enabled only if value has changed.
		String	db_val = dbValTxt.getText();
		boolean	b = ! db_val.equals(initial_value);
		okBtn.setEnabled(b);
	}
	//===============================================================
	//===============================================================
	private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed

		String	db_val = dbValTxt.getText().trim();
		retVal = prop;
		if (db_val.length()>0)
			retVal.db_value = ServArchitectureDialog.multiLine2OneLine(db_val);
		else
			retVal.db_value =null;

		doClose();
	}//GEN-LAST:event_okBtnActionPerformed

	//===============================================================
	//===============================================================
	private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
		doClose();
	}//GEN-LAST:event_cancelBtnActionPerformed

	//===============================================================
	/**
	 *	Closes the dialog
	 */
	//===============================================================
	private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
		doClose();
	}//GEN-LAST:event_closeDialog

	//===============================================================
	/**
	 *	Closes the dialog
	 */
	//===============================================================
	private void doClose()
	{
		setVisible(false);
		dispose();
	}
	//===============================================================
	//===============================================================
	public ServArchitectureDialog.TgProperty showDialog()
	{
		setVisible(true);
		return retVal;
	}

	//===============================================================
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextArea dbValTxt;
    private javax.swing.JTextArea defaultValTxt;
    private javax.swing.JTextArea descriptionTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JButton okBtn;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
	//===============================================================
}