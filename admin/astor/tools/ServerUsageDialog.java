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
import fr.esrf.TangoApi.DbServer;
import fr.esrf.tangoatk.widget.util.ATKGraphicsUtils;
import fr.esrf.tangoatk.widget.util.ErrorPane;

import javax.swing.*;
import java.util.ArrayList;


//===============================================================
/**
 *	JDialog Class to display info
 *
 *	@author  Pascal Verdier
 */
//===============================================================


public class ServerUsageDialog extends JDialog {

	private JFrame	parent;

    private ArrayList<TangoClass>   tangoClasses = new ArrayList<TangoClass>();
    private ArrayList<Domain>       deviceDomains = new ArrayList<Domain>();
    private ArrayList<Domain>       serverDomains = new ArrayList<Domain>();
    private int nbServers;
    private String wildcard;

    private static final int    ByDevice = 0;
    private static final int    ByServer = 1;

	//===============================================================
	/**
	 *	Creates new form ServerUsageDialog
     *  @param parent the parent instance
     *  @throws DevFailed if Database commands fail
	 */
	//===============================================================
	public ServerUsageDialog(JFrame parent) throws DevFailed {
		super(parent, true);
		this.parent   = parent;
        initComponents();
        displayServerUsage();

		pack();
 		ATKGraphicsUtils.centerDialog(this);
	}

	//===============================================================
	//===============================================================
    private void displayServerUsage() throws DevFailed {

        //  Get a server list from database for selection
        String[]    servers = ApiUtil.get_db_obj().get_server_name_list();
        ListDialog  dialog =  new ListDialog(parent,
                "Servers in "+ApiUtil.getTangoHost() , servers);
        wildcard = dialog.showDialog();
        if (wildcard==null) {
            doClose();
            return;
        }

        //  Convert to a wildcard and clear previous computation results
        wildcard += "/*";
		titleLabel.setText(wildcard);
        tangoClasses.clear();
        deviceDomains.clear();
        serverDomains.clear();

        //  Get existing server/instances list
        String[]    serverNames = ApiUtil.get_db_obj().get_server_list(wildcard);
        nbServers = serverNames.length;

        //  And foe each one, distribute by class
        for (String serverName : serverNames) {
            fillTangoClasses(serverName);

            //  Sleep a bit to do not overload database
            try { Thread.sleep(20); } catch (InterruptedException e) { /* */ }
        }
        textArea.setText(toString());
    }
	//===============================================================
    /** This method is called from within the constructor to
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
        javax.swing.JButton anotherBtn = new javax.swing.JButton();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JButton cancelBtn = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 18));
        titleLabel.setText("Dialog Title");
        topPanel.add(titleLabel);

        getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);

        anotherBtn.setText("Another Server");
        anotherBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anotherBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(anotherBtn);

        jLabel1.setText("                              ");
        bottomPanel.add(jLabel1);

        cancelBtn.setText("Dismiss");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(cancelBtn);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 500));

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    @SuppressWarnings({"UnusedParameters"})
    private void anotherBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anotherBtnActionPerformed
        try {
            displayServerUsage();
        }
        catch (DevFailed e) {
            ErrorPane.showErrorMessage(parent, null, e);
        }
    }//GEN-LAST:event_anotherBtnActionPerformed

	//===============================================================
	/**
	 *	Closes the dialog
	 */
	//===============================================================
	private void doClose() {
	
		if (parent==null)
			System.exit(0);
		else {
			setVisible(false);
			dispose();
		}
	}
	//===============================================================
	//===============================================================
	public void showDialog() {
		setVisible(true);
	}
	//===============================================================
	//===============================================================

	//===============================================================
	//===============================================================
    private void fillTangoClasses(String serverName)  throws DevFailed {
        DbServer server = new DbServer(serverName);
        String[]    class_dev = server.get_device_class_list();
        boolean doneForServer = false;
        for (int i=0 ; i<class_dev.length ; i+=2) {
            String deviceName = class_dev[i];
            String className  = class_dev[i+1];
            if (!className.equals("DServer")) {
                TangoClass _class = null;
                for (TangoClass tc : tangoClasses)
                    if (tc.name.toLowerCase().equals(className.toLowerCase()))
                        _class = tc;
                if (_class==null) {
                    _class = new TangoClass(className);
                    tangoClasses.add(_class);
                }
                _class.add(deviceName);
                fillDomains(deviceName, ByDevice);

                if (!doneForServer) {
                    fillDomains(deviceName, ByServer);
                    doneForServer = true;
                }
            }
        }
    }
	//===============================================================
	//===============================================================
    private void fillDomains(String deviceName, int target)  throws DevFailed {
        Domain domain = null;
        String domainName = deviceName.substring(0, deviceName.indexOf("/"));

        ArrayList<Domain>   list = (target==ByDevice) ? deviceDomains : serverDomains;

        for (Domain d : list)
            if (d.name.toLowerCase().equals(domainName.toLowerCase()))
                domain = d;
        if (domain==null) {
            domain = new Domain(domainName);
            list.add(domain);
        }
        domain.add(deviceName);
    }
	//===============================================================
	//===============================================================
    public String toString() {
        int  nbDevices = 0 ;
        StringBuffer    sb = new StringBuffer();
        sb.append(wildcard).append("\n");
        sb.append("\n=====================================\n");
        for (TangoClass tangoClass : tangoClasses) {
            sb.append(tangoClass);
            nbDevices += tangoClass.size();
        }
        sb.append("\n=====================================\n");
        for (int i=0 ; i<deviceDomains.size() && i<serverDomains.size() ;i++) {
            sb.append("Domain ").append(deviceDomains.get(i).name).append(":\t");
            sb.append(deviceDomains.get(i).size()).append(" devices\t");
            sb.append(serverDomains.get(i).size()).append(" servers\n");
        }
        sb.append("\n=====================================\n");
        sb.append(nbServers).append("\tservers\n");
        sb.append(nbDevices).append("\tdevices\n");
        return sb.toString();
    }
	//===============================================================
	//===============================================================

	//===============================================================
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea textArea;
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
    			new ServerUsageDialog(null).setVisible(true);
		}
		catch(DevFailed e) {
            ErrorPane.showErrorMessage(new JFrame(), null, e);
		}
	}
	//===============================================================
	//===============================================================




	//===============================================================
	//===============================================================
	class Domain extends ArrayList<String> {
        String name;
	    //===========================================================
        Domain(String name) {
            this.name = name;
        }
	    //===========================================================
        public String toString() {
            StringBuffer    sb = new StringBuffer("Domain " + name + ":\t");
            sb.append(size()).append(" devices\n");
            return sb.toString();
        }
	    //===========================================================
    }
	//===============================================================
	//===============================================================
	class TangoClass extends ArrayList<String> {
        String name;
	    //===========================================================
        TangoClass(String name) {
            this.name = name;
        }
	    //===========================================================
        public String toString() {
            StringBuffer    sb = new StringBuffer("Class " + name + ":\t");
            sb.append(size()).append(" devices\n");
            /*
            for (String deviceName : this) {
                sb.append("  -  ").append(deviceName).append("\n");
            }
            */
            return sb.toString();
        }
	    //===========================================================
    }
	//===============================================================
	//===============================================================
}