//+======================================================================
// $Source$
//
// Project:   Tango
//
// Description:  java source code for the Pogo class definition .
//
// $Author$
//
// $Revision$: $
//
// $Log$
// Revision 3.7  2010/06/04 14:12:55  pascal_verdier
// Global command to change startup level added.
//
// Revision 3.6  2009/01/16 14:46:58  pascal_verdier
// Black box management added for host and Server.
// Starter logging display added for host and server.
// Splash screen use ATK one.
//
// Revision 3.5  2005/11/17 12:30:33  pascal_verdier
// Analysed with IntelliJidea.
//
// Revision 3.4  2005/10/17 14:13:35  pascal_verdier
// Replace delete by remove.
//
// Revision 3.3  2004/09/28 07:01:51  pascal_verdier
// Problem on two events server list fixed.
//
// Revision 3.2  2004/03/03 08:31:04  pascal_verdier
// The server restart command has been replaced by a stop and start command in a thread.
// The delete startup level info has been added.
//
// Revision 3.1  2003/06/19 12:57:57  pascal_verdier
// Add a new host option.
// Controlled servers list option.
//
// Revision 3.0  2003/06/04 12:37:52  pascal_verdier
// Main window uses now a Jtree to display hosts.
//
// Revision 2.1  2003/06/04 12:33:12  pascal_verdier
// Main window uses now a Jtree to display hosts.
//
// Revision 2.0  2003/01/16 15:22:35  verdier
// Last ci before CVS usage
//
// Revision 1.6  2002/09/13 08:43:07  verdier
// Use IDL 2 Starter version (polling thread, State from Starter, ...).
// Host info window not modal.
// Host info window resizable for display all servers option.
// And many features.
//
// Revision 1.5  2001/05/30 15:13:29  verdier
// Start/Stop host control added
// Jive statup aded
// and many app_util added...
//
//
// Copyleft 2003 by European Synchrotron Radiation Facility, Grenoble, France
//               All Rights Reversed
//-======================================================================

package admin.astor;
 

/** 
 *	This class is a dialog window to get the controlled server parameters.
 *
 * @author  verdier
 */
 
 

import fr.esrf.TangoApi.DbServInfo;

import javax.swing.*;
import java.awt.*;

//-======================================================================
//-======================================================================
public class PutServerInfoDialog extends javax.swing.JDialog {

	public static final int RET_CANCEL = JOptionPane.CANCEL_OPTION;
	public static final int RET_OK     = JOptionPane.OK_OPTION;

	private DbServInfo	server_info;

	private JButton	unregisterBtn;
	private boolean	unregister = false;
	private Component parent;

//-======================================================================
//-======================================================================
  public PutServerInfoDialog(JDialog parent,boolean modal) {
    super (parent, modal);
	this.parent = parent;
    initComponents ();
	
	//	Initialize ComboBoxe
	//-------------------------------------
	jComboBox1.addItem("None");
	int nb = AstorUtil.getStarterNbStartupLevels();
	for (int i=1 ; i<=nb ; i++)
	{
		String	s = "Level " + i;
		jComboBox1.addItem(s);
	}
    pack ();
  }

//-======================================================================
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the FormEditor.
   */
//-======================================================================
  private void initComponents () {//GEN-BEGIN:initComponents
    buttonPanel = new javax.swing.JPanel ();
    okButton = new javax.swing.JButton ();
    cancelButton = new javax.swing.JButton ();
    jPanel1 = new javax.swing.JPanel ();
    jLabel1 = new javax.swing.JLabel ();
    yesButton = new javax.swing.JRadioButton ();
    noButton = new javax.swing.JRadioButton ();
    jLabel2 = new javax.swing.JLabel ();
    jComboBox1 = new javax.swing.JComboBox ();
    jLabel3 = new javax.swing.JLabel ();
    jLabel5 = new javax.swing.JLabel ();
    jLabel6 = new javax.swing.JLabel ();
    title = new javax.swing.JLabel ();
    addWindowListener (new java.awt.event.WindowAdapter () {
      public void windowClosing (java.awt.event.WindowEvent evt) {
        closeDialog (evt);
      }
    }
    );

    buttonPanel.setLayout (new java.awt.FlowLayout (2, 5, 5));

      okButton.setText ("  OK  ");
      okButton.addActionListener (new java.awt.event.ActionListener () {
        public void actionPerformed (java.awt.event.ActionEvent evt) {
          okButtonActionPerformed (evt);
        }
      }
      );
  
      buttonPanel.add (okButton);
  
      cancelButton.setText ("Cancel");
      cancelButton.addActionListener (new java.awt.event.ActionListener () {
        public void actionPerformed (java.awt.event.ActionEvent evt) {
          cancelButtonActionPerformed (evt);
        }
      }
      );
  
      buttonPanel.add (cancelButton);
  

    getContentPane ().add (buttonPanel, java.awt.BorderLayout.SOUTH);

    jPanel1.setLayout (new java.awt.GridBagLayout ());
    java.awt.GridBagConstraints gridBagConstraints1;

      jLabel1.setText ("Controlled by Astor : ");
      jLabel1.setForeground (java.awt.Color.black);
  
      gridBagConstraints1 = new java.awt.GridBagConstraints ();
      gridBagConstraints1.gridx = 1;
      gridBagConstraints1.gridy = 1;
      gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
      jPanel1.add (jLabel1, gridBagConstraints1);
  
      yesButton.setText ("Yes");
      yesButton.addActionListener (new java.awt.event.ActionListener () {
        public void actionPerformed (java.awt.event.ActionEvent evt) {
          yesnoButtonActionPerformed (evt);
        }
      }
      );
  
      gridBagConstraints1 = new java.awt.GridBagConstraints ();
      gridBagConstraints1.gridx = 2;
      gridBagConstraints1.gridy = 1;
      jPanel1.add (yesButton, gridBagConstraints1);
  
      noButton.setText ("No");
      noButton.addActionListener (new java.awt.event.ActionListener () {
        public void actionPerformed (java.awt.event.ActionEvent evt) {
          yesnoButtonActionPerformed (evt);
        }
      }
      );
  
      gridBagConstraints1 = new java.awt.GridBagConstraints ();
      gridBagConstraints1.gridx = 3;
      gridBagConstraints1.gridy = 1;
      jPanel1.add (noButton, gridBagConstraints1);
  
      jLabel2.setText ("Startup Level : ");
      jLabel2.setForeground (java.awt.Color.black);
  
      gridBagConstraints1 = new java.awt.GridBagConstraints ();
      gridBagConstraints1.gridx = 1;
      gridBagConstraints1.gridy = 3;
      gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
      gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTH;
      jPanel1.add (jLabel2, gridBagConstraints1);
  
  
      gridBagConstraints1 = new java.awt.GridBagConstraints ();
      gridBagConstraints1.gridx = 2;
      gridBagConstraints1.gridy = 3;
      gridBagConstraints1.gridheight = 2;
      gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
      gridBagConstraints1.weightx = 2.0;
      jPanel1.add (jComboBox1, gridBagConstraints1);
  
      jLabel3.setText (" ");
      jLabel3.setForeground (java.awt.Color.black);
      jLabel3.setFont (new java.awt.Font ("Dialog", 1, 10));
  
      gridBagConstraints1 = new java.awt.GridBagConstraints ();
      gridBagConstraints1.gridx = 1;
      gridBagConstraints1.gridy = 2;
      gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
      gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTH;
      jPanel1.add (jLabel3, gridBagConstraints1);
  
      jLabel5.setPreferredSize (new java.awt.Dimension(50, 40));
      jLabel5.setMinimumSize (new java.awt.Dimension(50, 40));
      jLabel5.setText (" ");
      jLabel5.setForeground (java.awt.Color.black);
      jLabel5.setFont (new java.awt.Font ("Dialog", 1, 10));
  
      gridBagConstraints1 = new java.awt.GridBagConstraints ();
      gridBagConstraints1.gridx = 1;
      gridBagConstraints1.gridy = 5;
      gridBagConstraints1.gridheight = 10;
      gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTH;
      gridBagConstraints1.weighty = 10.0;
      jPanel1.add (jLabel5, gridBagConstraints1);
  
      jLabel6.setText (" ");
      jLabel6.setForeground (java.awt.Color.black);
      jLabel6.setFont (new java.awt.Font ("Dialog", 1, 10));
  
      gridBagConstraints1 = new java.awt.GridBagConstraints ();
      gridBagConstraints1.gridx = 1;
      gridBagConstraints1.gridy = 0;
      gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
      gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTH;
      jPanel1.add (jLabel6, gridBagConstraints1);
  


		gridBagConstraints1.gridx = 1;
		gridBagConstraints1.gridy = 7;
		jPanel1.add (new JLabel("  "), gridBagConstraints1);

		unregisterBtn = new JButton("Remove startup level info");	
		unregisterBtn.addActionListener (new java.awt.event.ActionListener () {
			public void actionPerformed (java.awt.event.ActionEvent evt) {
				unregisterBtnActionPerformed (evt);
			}
		});
		gridBagConstraints1.gridx = 1;
		gridBagConstraints1.gridy = 8;
		gridBagConstraints1.gridwidth = 3;
		jPanel1.add (unregisterBtn, gridBagConstraints1);

    getContentPane ().add (jPanel1, java.awt.BorderLayout.CENTER);

    title.setText (" ");
    title.setForeground (java.awt.Color.black);
    title.setFont (new java.awt.Font ("Dialog", 1, 16));



    getContentPane ().add (title, java.awt.BorderLayout.NORTH);

  }//GEN-END:initComponents

	//============================================================
	//============================================================
	private void unregisterBtnActionPerformed (java.awt.event.ActionEvent evt) {
		//	Ask to confirm
		if (unregister=(JOptionPane.showConfirmDialog(parent,
			"Are you sure to want to remove " + 
			server_info.name + " startup info ?",
			"Confirm Dialog",
			JOptionPane.YES_NO_OPTION))==JOptionPane.OK_OPTION)
		{
			doClose (RET_OK);
		}
	}
	//============================================================
	//============================================================
	private void yesnoButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesnoButtonActionPerformed
		String  org = evt.getActionCommand();
		if (org.equals("Yes"))
			updateButtons(true, server_info.startup_level);
		else
			updateButtons(false, 0);
	}//GEN-LAST:event_yesnoButtonActionPerformed

	//============================================================
	//============================================================
	private void cancelButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
		doClose (RET_CANCEL);
	}//GEN-LAST:event_cancelButtonActionPerformed

	//============================================================
	/**
	 *	Closes the dialog
	 */
	//============================================================
	private void okButtonActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
		doClose (RET_OK);
	}//GEN-LAST:event_okButtonActionPerformed

	//============================================================
	/**
	 *	Closes the dialog
	 */
 	//============================================================
	private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
		doClose (RET_CANCEL);
	}//GEN-LAST:event_closeDialog

	//============================================================
	//============================================================
	private void doClose (int retStatus) {
		returnStatus = retStatus;
		setVisible (false);
		dispose ();
	}


	//============================================================
	/**
	 *	Update configuration buttons.
	 */
	//============================================================
	private void updateButtons(boolean ctrl, int level)
	{
		yesButton.setSelected(ctrl);
		noButton.setSelected (!ctrl);
		jComboBox1.setEnabled(ctrl);

		if (ctrl)
		{
			server_info.startup_level = level;
            jComboBox1.setSelectedIndex(level);
			unregisterBtn.setVisible(false);
		}
		else
		{
            jComboBox1.setSelectedIndex(0);
			if (server_info.name.length()>0 && manage_unregister)
				unregisterBtn.setVisible(true);
			else
				unregisterBtn.setVisible(false);
		}
	}
	//============================================================
	/**
	 *	Update configuration buttons and display dialog
	 */
	//============================================================
	public int showDialog(DbServInfo info)
	{
		server_info = info;

		title.setText("  " + info.name + " running on " + info.host + "  ");
		updateButtons(info.controlled, info.startup_level);
		pack();
		setVisible (true);
		return returnStatus;
	}
	//============================================================
	/**
	 *	Update configuration buttons and display dialog
	 */
	//============================================================
	private boolean manage_unregister = true;
	public int showDialog(DbServInfo info, int level)
	{
		server_info = info;
		manage_unregister = false;

		title.setText("  " + "Servers (Level "+level+ ")   running on " + info.host + "  ");
		updateButtons(info.controlled, info.startup_level);
		
		pack();
		setVisible (true);
		return returnStatus;
	}

	//============================================================
	/**
	 *	Get configuration from buttons and return info object.
	 */
	//============================================================
	public DbServInfo getSelection()
	{
		if (unregister)
			return null;
		server_info.controlled  = (yesButton.getSelectedObjects()!=null);
		int	level = jComboBox1.getSelectedIndex();
		
		if (server_info.controlled==false || level==0)
		{
			//	force both
			server_info.controlled = false;
			level = 0;
		}
			
		return new DbServInfo(server_info.name, server_info.host,
					server_info.controlled, level);
	}
	//============================================================
	//============================================================



	//============================================================
        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JPanel buttonPanel;
        private javax.swing.JButton okButton;
        private javax.swing.JButton cancelButton;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JRadioButton yesButton;
        private javax.swing.JRadioButton noButton;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JComboBox jComboBox1;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel title;
        // End of variables declaration//GEN-END:variables
	//============================================================

  private int returnStatus = RET_CANCEL;
}