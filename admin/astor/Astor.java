//+======================================================================
// $Source$
//
// Project:   Tango
//
// Description:  java source code for Astor class definition .
//
// $Author$
//
// $Revision$
//
// $Log$
// Revision 3.69  2011/02/11 10:00:26  pascal_verdier
// Pb with TAC when adding addresses on "All Users" fixed.
// No reference on app_util classes any more.
// Change splash screen image.
//
// Revision 3.68  2011/01/13 13:04:17  pascal_verdier
// Increase release number
//
// Revision 3.67  2011/01/10 12:48:13  pascal_verdier
// TAC is now displayed as database servers.
// StartServersAtStarteup starter class property management added.
// Display access mode in Tango Access panel.
//
// Revision 3.66  2011/01/04 14:33:03  pascal_verdier
// Password added for access control dialog.
// Do not try to subscribe on Starter events if starter device not exported.
//
// Revision 3.65  2010/11/30 09:27:47  pascal_verdier
// For multi servers command, if the command is done throu the starter,
// it is done by a thread and a delay has been added between servers.
//
// Revision 3.64  2010/11/29 13:54:45  pascal_verdier
// Multi servers command added.
// Uptime for servers added.
//
// Revision 3.63  2010/10/08 07:41:27  pascal_verdier
// Minor changes.
//
// Revision 3.62  2010/06/17 08:48:11  pascal_verdier
// Pb on display startup level dialog in case of many devices fixed.
//
// Revision 3.61  2010/06/17 07:41:22  pascal_verdier
// Start new server can take several servers (multiple selection).
//
// Revision 3.60  2010/06/04 14:12:55  pascal_verdier
// Global command to change startup level added.
//
// Revision 3.59  2010/04/08 10:41:47  pascal_verdier
// Minor changes.
//
// Revision 3.58  2010/01/05 13:47:22  pascal_verdier
// Best management of subscribe error window at startup.
//
// Revision 3.57  2009/06/02 15:19:05  pascal_verdier
// Remove serialization between HostStateThread and HostInfoDialogVector.
//
// Revision 3.56  2009/04/06 14:27:43  pascal_verdier
// Using MySqlUtil feature.
//
// Revision 3.55  2009/02/18 09:47:57  pascal_verdier
// Device dependencies (sub-devices) tool added.
//
// Revision 3.54  2009/01/30 09:31:50  pascal_verdier
// Black box management added for database.
// Black box management tool improved.
// Find TANGO object by filter added.
//
// Revision 3.53  2009/01/16 14:46:58  pascal_verdier
// Black box management added for host and Server.
// Starter logging display added for host and server.
// Splash screen use ATK one.
//
// Revision 3.52  2008/11/19 09:59:56  pascal_verdier
// New tests done on Access control.
// Pool Threads management added.
// Size added as preferences.
//
// Revision 3.51  2008/09/12 11:51:23  pascal_verdier
// Minor changes
//
// Revision 3.50  2008/05/26 11:49:12  pascal_verdier
// Host info dialog servers are managed in a jtree.
//
// Revision 3.49  2008/04/07 10:53:36  pascal_verdier
// Branch info modified.
//
// Revision 3.48  2008/03/27 08:10:14  pascal_verdier
// Patching Release-5.0.0
//
// Revision 3.47  2008/03/27 08:07:15  pascal_verdier
// Compatibility with Starter 4.0 and after only !
// Better management of server list.
// Server state MOVING managed.
// Hard kill added on servers.
// New features on polling profiler.
//
// Revision 3.46  2008/03/03 14:55:21  pascal_verdier
// Starter Release_4 management.
//
// Revision 3.45  2007/11/07 09:05:38  pascal_verdier
// Display host info if OSManage DS  is running on host.
// Display host's state on HotInfoDialog.
//
// Revision 3.44  2007/09/21 09:25:18  pascal_verdier
// Refresh Astor properties when TANGO_HOST changed.
//
// Revision 3.43  2007/08/20 14:06:08  pascal_verdier
// ServStatePanel added on HostInfoDialog (Check states option).
//
// Revision 3.42  2007/03/27 08:56:11  pascal_verdier
// Preferences added.
//
// Revision 3.41  2007/03/08 13:44:32  pascal_verdier
// LastCollections property added.
//
// Revision 3.40  2007/01/17 10:11:27  pascal_verdier
// Html helps added.
// Startup error message added in view menu.
//
// Revision 3.39  2007/01/08 08:21:07  pascal_verdier
// Disable Start Server button if Starter is MOVING.
//
// Revision 3.38  2006/09/19 13:04:46  pascal_verdier
// Access control manager added.
//
// Revision 3.37  2006/06/26 12:24:08  pascal_verdier
// Bug fixed in miscellaneous host collection.
//
// Revision 3.36  2006/06/13 13:52:14  pascal_verdier
// During StartAll command, sleep(500) added between 2 hosts.
// MOVING states added for collection.
//
// Revision 3.35  2006/05/17 07:52:56  pascal_verdier
// MOVING state added.
//
// Revision 3.34  2006/04/19 12:06:58  pascal_verdier
// Host info dialog modified to use icons to display server states.
//
// Revision 3.33  2006/04/12 13:50:47  pascal_verdier
// *** empty log message ***
//
// Revision 3.32  2006/01/11 08:46:13  pascal_verdier
// PollingProfiler added.
//
// Revision 3.31  2005/12/08 12:39:27  pascal_verdier
// Add release note display in help menu.
//
// Revision 3.30  2005/12/01 10:00:23  pascal_verdier
// Change TANGO_HOST added (needs TangORB-4.7.7 or later).
//
// Revision 3.29  2005/11/24 12:24:57  pascal_verdier
// DevBrowser utility added.
// MkStarter utility added.
//
// Revision 3.28  2005/11/17 12:30:33  pascal_verdier
// Analysed with IntelliJidea.
//
// Revision 3.27  2005/10/20 13:24:49  pascal_verdier
// Screen position management has been changed.
//
// Revision 3.26  2005/10/17 14:14:24  pascal_verdier
// Search host by name added.
//
// Revision 3.25  2005/09/15 08:26:36  pascal_verdier
// Server architecture display addded.
//
// Revision 3.24  2005/08/30 08:05:25  pascal_verdier
// Management of two TANGO HOST added.
//
// Revision 3.23  2005/06/02 09:02:36  pascal_verdier
// Minor changes.
//
// Revision 3.22  2005/04/25 08:55:35  pascal_verdier
// Start/Stop servers from shell command line added.
//
// Revision 3.21  2005/04/22 09:30:45  pascal_verdier
// Use events management in starter properies dialog added.
//
// Revision 3.20  2005/03/15 10:22:30  pascal_verdier
// Sort servers before creating panel buttons.
//
// Revision 3.19  2005/03/11 14:07:53  pascal_verdier
// Pathes have been modified.
//
// Revision 3.18  2005/02/16 13:41:05  pascal_verdier
// Add controlled servers info in DeviceTree class.
//
// Revision 3.17  2005/02/10 15:38:18  pascal_verdier
// Event subscritions have been serialized.
//
// Revision 3.16  2005/02/03 13:31:58  pascal_verdier
// Display message if subscribe event failed.
// Display hosts using events (Starter/Astor).
//
// Revision 3.15  2005/01/18 08:48:20  pascal_verdier
// Tools menu added.
// Not controlled servers list added.
//
// Revision 3.14  2004/11/23 14:05:56  pascal_verdier
// Minor changes.
//
// Revision 3.13  2004/09/28 07:01:50  pascal_verdier
// Problem on two events server list fixed.
//
// Revision 3.12  2004/07/09 07:21:25  pascal_verdier
// JAR revision and date management added in appli.
//
// Revision 3.11  2004/07/08 11:22:58  pascal_verdier
// First revision able to use events.
//
// Revision 3.10  2004/06/17 09:19:58  pascal_verdier
// Refresh performence problem solved by removing tool tips on JTree.
//
// Revision 3.9  2004/05/04 07:05:27  pascal_verdier
// Bug on notify daemon fixed.
// server reconection transparency added.
//
// Revision 3.8  2004/04/13 12:17:27  pascal_verdier
// DeviceTree class uses the new browsing database commands.
//
// Revision 3.7  2003/11/25 15:56:45  pascal_verdier
// Label on hosts added.
// Notifyd begin to be controled.
//
// Revision 3.6  2003/11/05 10:34:57  pascal_verdier
// Main Panel screen centering.
// Starter multi path added.
// little bugs fixed.
//
// Revision 3.5  2003/10/20 08:55:15  pascal_verdier
// Bug on tree popup menu position fixed.
//
// Revision 3.4  2003/09/08 12:21:36  pascal_verdier
// *** empty log message ***
//
// Revision 3.3  2003/09/08 11:05:28  pascal_verdier
// *** empty log message ***
//
// Revision 3.2  2003/07/22 14:35:20  pascal_verdier
// Minor bugs fixed.
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
//
// Copyleft 2003 by European Synchrotron Radiation Facility, Grenoble, France
//               All Rights Reversed
//-======================================================================

package admin.astor;

/**
 *	This class is the Astor main panel
 *	containing the Jtree used to display hosts.
 *
 * @author  root
 */

import admin.astor.tools.*;
import fr.esrf.Tango.DevFailed;
import fr.esrf.TangoApi.*;
import fr.esrf.TangoDs.Except;
import fr.esrf.tangoatk.widget.util.ATKGraphicsUtils;
import fr.esrf.tangoatk.widget.util.ErrorPane;
import fr.esrf.tangoatk.widget.util.Splash;
import fr.esrf.tangoatk.widget.util.JSmoothProgressBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.Vector;


public class Astor extends JFrame implements AstorDefs
{

	/**
	 *  Initialized by make jar call and used to display title.
	 */
    private static String revNumber =
            "Release 5.4.0  -  Fri Feb 11 11:11:23 CET 2011";
	/**
	 *	JTree object to display control system.
	 */
	public AstorTree	tree = null;
	/**
	 *	JTree state
	 */
	private boolean		expanded = false;

	/**
	 *	JTree Container
	 */
	private JScrollPane	scrollPane;
	/**
	 *	true if Astor is fully built and displayed.
	 */
	static boolean displayed = false;

    public static DevBrowser   dev_browser = null;
    private String tango_host = "";

    private MultiServerCommand  multiServerCommand = null;

	static long	t0;
	//======================================================================
    /**
	 *	Creates new form Astor
     * @throws DevFailed in case of database connection failed
	 */
	//======================================================================
	public Astor() throws DevFailed
	{
		t0 = System.currentTimeMillis();

		initComponents();
		AstorUtil.getInstance().initIcons();
		customizeMenu();

		setTitle("TANGO Manager - " + revNumber);
		buildTree();
		ImageIcon icon = new ImageIcon(
			getClass().getResource(img_path+"tango_icon.jpg"));
		setIconImage(icon.getImage());

		bottomPanel.setVisible(AstorUtil.getCtrlBtn());
		centerWindow();

        try {
            tango_host = ApiUtil.get_db_obj().get_tango_host();
        }
        catch(DevFailed e){ /* do nothing */ }
	}
//===========================================================
/**
 *	Move the window to the center of the screen
 */
//===========================================================
	public void centerWindow() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrsize = toolkit.getScreenSize();
		Dimension appsize = getSize();
		Point	p = new Point();
		p.x = (scrsize.width  - appsize.width)/2;
		p.y = (scrsize.height - appsize.height)/2;
		setLocation(p);
	}
	//======================================================================
	//======================================================================
	private void buildTree() throws DevFailed
	{
		//	Build Splash Screen
		String	title = "Astor (TANGO Manager)";
		int end = revNumber.indexOf("-");
		if (end>0)
			title += " - " + revNumber.substring(0, end).trim();

		//	Create a splash window.
		JSmoothProgressBar myBar = new JSmoothProgressBar();
		myBar.setStringPainted(true);
		myBar.setBackground(Color.lightGray);
		myBar.setProgressBarColors(Color.gray, Color.gray, Color.gray);

		ImageIcon icon = new ImageIcon(
			getClass().getResource(img_path+"TangoCollaboration.jpg"));
		Splash	splash = new Splash(icon, Color.black, myBar);
		splash.setTitle(title);
		splash.setMessage("Starting....");
		splash.setVisible(true);

		try {
			//	Stop threads if already started (updating tree)
			if (tree!=null) {
				tree.stopThreads();
				scrollPane.remove(tree);
				remove(scrollPane);
			}

 	 		//	Build tree and start threads to update tree
			tree = new AstorTree(this, true, splash);
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(AstorUtil.getPreferredSize());
			scrollPane.setViewportView (tree);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			expanded = false;
			pack();

            //  Access control management
            manageAccessControlMenu(tree.isAccessControlled());
		}
		catch (DevFailed e) {
			splash.setVisible(false);
			throw e;
		}
	}
	//======================================================================
	//======================================================================
	Dimension getTreeSize()
	{
		//return scrollPane.getSize();
		return scrollPane.getPreferredSize();
	}
	//======================================================================
	//======================================================================
	void setTreeSize(Dimension d)
	{
		scrollPane.setPreferredSize(d);
		pack();
	}
	//======================================================================
	//======================================================================
	private void customizeMenu()
	{
		//	File menu
		fileMenu.setMnemonic ('F');
		exitBtn.setMnemonic ('E');
		exitBtn.setAccelerator(KeyStroke.getKeyStroke('Q', Event.CTRL_MASK));

        ctrlPreferenceBtn.setMnemonic ('P');
		ctrlPreferenceBtn.setAccelerator(KeyStroke.getKeyStroke('P', Event.CTRL_MASK));
		String	s = System.getProperty("NO_PREF");
		if (s!=null && s.toLowerCase().equals("true"))
			ctrlPreferenceBtn.setEnabled(false);

        changeTgHostBtn.setMnemonic ('T');
		changeTgHostBtn.setAccelerator(KeyStroke.getKeyStroke('T', Event.CTRL_MASK));

        //	View menu
		viewMenu.setMnemonic ('V');
		newBranchBtn.setMnemonic ('N');
		newBranchBtn.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));

		refreshBtn.setMnemonic ('U');
		refreshBtn.setAccelerator(KeyStroke.getKeyStroke('U', Event.CTRL_MASK));

		deviceBrowserBtn.setMnemonic ('B');
		deviceBrowserBtn.setAccelerator(KeyStroke.getKeyStroke('B', Event.CTRL_MASK));

		expandBtn.setMnemonic ('E');
		expandBtn.setAccelerator(KeyStroke.getKeyStroke('E', Event.CTRL_MASK));

		//	Search menu
		toolsMenu.setMnemonic ('T');
		multiServersCmdItem.setAccelerator(KeyStroke.getKeyStroke('M', Event.CTRL_MASK));
		jiveMenuItem.setAccelerator(KeyStroke.getKeyStroke('J',        Event.CTRL_MASK));
		logviewerMenuItem.setAccelerator(KeyStroke.getKeyStroke('L',   Event.CTRL_MASK));

		//	Command menu
		cmdMenu.setMnemonic ('C');

		newHostBtn.setAccelerator(KeyStroke.getKeyStroke('H', Event.CTRL_MASK));

        nb_def_tools = toolsMenu.getItemCount();
        buildToolsItems();
        buildAdditionnalHelps();

        expandBtn.setVisible(false);
    }


    //======================================================================
    //======================================================================
    private void manageAccessControlMenu(boolean isAccessControlled)
    {
        if (isAccessControlled)  {
            accessControlBtn.setMnemonic ('A');
            accessControlBtn.setAccelerator(KeyStroke.getKeyStroke('A', Event.CTRL_MASK));
            accessControlBtn.setVisible(true);
            System.out.println("AccessControl is active");
        }
        else {   //  Service does not exist !
            accessControlBtn.setVisible(false);
        }
    }
    //======================================================================
    //======================================================================
    private String[]  htmlHelps = null;
    private void buildAdditionnalHelps()
    {
        htmlHelps = AstorUtil.getHtmlHelps();
        if (htmlHelps==null)    return;
        if (htmlHelps.length==0)    return;

        helpMenu.add(new JSeparator());

        for (int i=0 ; i<htmlHelps.length/2 ; i++)
        {
            JMenuItem	mi = new JMenuItem();
            mi.setText(htmlHelps[2*i]);
            mi.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    htmlHelpsItemActionPerformed(evt);
                }
            });
            helpMenu.add(mi);
        }
    }

    //======================================================================
    //======================================================================
    private void htmlHelpsItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        String  str = evt.getActionCommand();
        String  cmd = null;
        for (int i=0 ; i<htmlHelps.length ; i++)
            if (str.equals(htmlHelps[i]))
                if (i<htmlHelps.length-1)
                    cmd = htmlHelps[i+1];
        if (cmd==null)
            Utils.popupError(this, "No command found for item  \'" + str +"\'");
        System.out.println(cmd);

		AstorUtil.showInHtmBrowser(cmd);
    }
    //======================================================================
   /**
    * Remove Optional tools if any and add new ones.
    */
    //======================================================================
    private void buildToolsItems()
    {
        //  Remove items if any
        for (int i=toolsMenu.getItemCount() ; i>nb_def_tools ; i--)
        {
            JMenuItem   mi = toolsMenu.getItem(i-1);
            mi.removeActionListener(tools_al.get(i-nb_def_tools-1));
            toolsMenu.remove(i-1);
        }
        app_tools.clear();
        tools_al.clear();

		//	Add JMenuItem for tools
		String[]	str_tools = AstorUtil.getTools();
		if (str_tools!=null)
		{
			for (int i=0 ; i<str_tools.length ; i+=2)
			{
				OneTool	t = new OneTool(str_tools[i], str_tools[i+1]);
				app_tools.add(t);
				JMenuItem	mi = new JMenuItem();
				mi.setText(t.name);
                ActionListener  al;
				mi.addActionListener(al=new ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						toolsItemActionPerformed(evt);
					}
                });
                tools_al.add(al);
				toolsMenu.add(mi);
			}
		}
	}

    private int     nb_def_tools = 1;
	private Vector<OneTool>         app_tools = new Vector<OneTool>();
    private Vector<ActionListener>  tools_al  = new Vector<ActionListener>();
	//======================================================================
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
	//======================================================================
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bottomPanel = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        allHostsControledBtn = new javax.swing.JToggleButton();
        stopHostsControledBtn = new javax.swing.JToggleButton();
        javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        changeTgHostBtn = new javax.swing.JMenuItem();
        ctrlPreferenceBtn = new javax.swing.JMenuItem();
        exitBtn = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        deviceBrowserBtn = new javax.swing.JMenuItem();
        refreshBtn = new javax.swing.JMenuItem();
        expandBtn = new javax.swing.JMenuItem();
        javax.swing.JMenuItem startupErrorBtn = new javax.swing.JMenuItem();
        cmdMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem startServersBtn = new javax.swing.JMenuItem();
        javax.swing.JMenuItem stopServersBtn = new javax.swing.JMenuItem();
        newHostBtn = new javax.swing.JMenuItem();
        newBranchBtn = new javax.swing.JMenuItem();
        toolsMenu = new javax.swing.JMenu();
        javax.swing.JMenu findMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem findHostItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem findObjectByFilterItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem tangoStatBtn = new javax.swing.JMenuItem();
        multiServersCmdItem = new javax.swing.JMenuItem();
        jiveMenuItem = new javax.swing.JMenuItem();
        accessControlBtn = new javax.swing.JMenuItem();
        logviewerMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        principleBtn = new javax.swing.JMenuItem();
        distributionBtn = new javax.swing.JMenuItem();
        stateIconsBtn = new javax.swing.JMenuItem();
        tangorbBtn = new javax.swing.JMenuItem();
        starterEventsItem = new javax.swing.JMenuItem();
        starterNoEventsItem = new javax.swing.JMenuItem();
        releaseNoteBtn = new javax.swing.JMenuItem();
        aboutBtn = new javax.swing.JMenuItem();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jLabel1.setText("Control  :   ");
        bottomPanel.add(jLabel1);

        allHostsControledBtn.setFont(new java.awt.Font("Dialog", 1, 10));
        allHostsControledBtn.setText("  All  hosts  ");
        allHostsControledBtn.setToolTipText("Start Control on All Hosts");
        allHostsControledBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        allHostsControledBtn.setMargin(new java.awt.Insets(2, 2, 2, 2));
        allHostsControledBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allHostsControledBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(allHostsControledBtn);

        stopHostsControledBtn.setFont(new java.awt.Font("Dialog", 1, 10));
        stopHostsControledBtn.setText("    None   ");
        stopHostsControledBtn.setToolTipText("Stop Control on All Hosts");
        stopHostsControledBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        stopHostsControledBtn.setMargin(new java.awt.Insets(2, 2, 2, 2));
        stopHostsControledBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopHostsControledBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(stopHostsControledBtn);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);

        fileMenu.setText("File");

        changeTgHostBtn.setText("Change Tango Host");
        changeTgHostBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeTgHostBtnActionPerformed(evt);
            }
        });
        fileMenu.add(changeTgHostBtn);

        ctrlPreferenceBtn.setText("Ctrl System Preferences");
        ctrlPreferenceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctrlPreferenceBtnActionPerformed(evt);
            }
        });
        fileMenu.add(ctrlPreferenceBtn);

        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        fileMenu.add(exitBtn);

        jMenuBar1.add(fileMenu);

        viewMenu.setText("View");

        deviceBrowserBtn.setText("Event Manager");
        deviceBrowserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deviceBrowserBtnActionPerformed(evt);
            }
        });
        viewMenu.add(deviceBrowserBtn);

        refreshBtn.setText("Update Tree from database");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });
        viewMenu.add(refreshBtn);

        expandBtn.setText("Expand Tree");
        expandBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expandBtnActionPerformed(evt);
            }
        });
        viewMenu.add(expandBtn);

        startupErrorBtn.setText("Startup Errors");
        startupErrorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startupErrorBtnActionPerformed(evt);
            }
        });
        viewMenu.add(startupErrorBtn);

        jMenuBar1.add(viewMenu);

        cmdMenu.setText("Command");

        startServersBtn.setText("Start All Controlled Servers");
        startServersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServersBtnActionPerformed(evt);
            }
        });
        cmdMenu.add(startServersBtn);

        stopServersBtn.setText("Stop All Controlled Servers");
        stopServersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopServersBtnActionPerformed(evt);
            }
        });
        cmdMenu.add(stopServersBtn);

        newHostBtn.setText("Add a New Host");
        newHostBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newHostBtnActionPerformed(evt);
            }
        });
        cmdMenu.add(newHostBtn);

        newBranchBtn.setText("Add a New Branch");
        newBranchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBranchBtnActionPerformed(evt);
            }
        });
        cmdMenu.add(newBranchBtn);

        jMenuBar1.add(cmdMenu);

        toolsMenu.setText("Tools");

        findMenu.setText("Find");

        findHostItem.setText("Host by Name");
        findHostItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findHostItemActionPerformed(evt);
            }
        });
        findMenu.add(findHostItem);

        findObjectByFilterItem.setText("Device/Server/Class by Filter");
        findObjectByFilterItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findObjectByFilterItemActionPerformed(evt);
            }
        });
        findMenu.add(findObjectByFilterItem);

        toolsMenu.add(findMenu);

        tangoStatBtn.setText("Ctrl System Info");
        tangoStatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tangoStatBtnActionPerformed(evt);
            }
        });
        toolsMenu.add(tangoStatBtn);

        multiServersCmdItem.setText("Multi Servers Command");
        multiServersCmdItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiServersCmdItemActionPerformed(evt);
            }
        });
        toolsMenu.add(multiServersCmdItem);

        jiveMenuItem.setText("Jive");
        jiveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jiveMenuItemActionPerformed(evt);
            }
        });
        toolsMenu.add(jiveMenuItem);

        accessControlBtn.setText("Access Control");
        accessControlBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accessControlBtnActionPerformed(evt);
            }
        });
        toolsMenu.add(accessControlBtn);

        logviewerMenuItem.setText("LogViewer");
        logviewerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logviewerMenuItemActionPerformed(evt);
            }
        });
        toolsMenu.add(logviewerMenuItem);

        jMenuBar1.add(toolsMenu);

        helpMenu.setText("Help");

        principleBtn.setText("Principle");
        principleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });
        helpMenu.add(principleBtn);

        distributionBtn.setText("Servers Distribution");
        distributionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });
        helpMenu.add(distributionBtn);

        stateIconsBtn.setText("State Icons");
        stateIconsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });
        helpMenu.add(stateIconsBtn);

        tangorbBtn.setText("TangORB revision");
        tangorbBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });
        helpMenu.add(tangorbBtn);

        starterEventsItem.setText("Hosts Controlled on Events");
        starterEventsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });
        helpMenu.add(starterEventsItem);

        starterNoEventsItem.setText("Hosts Controlled on Polling");
        starterNoEventsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });
        helpMenu.add(starterNoEventsItem);

        releaseNoteBtn.setText("Release Note");
        releaseNoteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });
        helpMenu.add(releaseNoteBtn);

        aboutBtn.setText("About");
        aboutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });
        helpMenu.add(aboutBtn);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //======================================================================
    //======================================================================
    @SuppressWarnings({"UnusedDeclaration"})
	private void ctrlPreferenceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctrlPreferenceBtnActionPerformed
		new PreferenceDialog(this).setVisible(true);

    }//GEN-LAST:event_ctrlPreferenceBtnActionPerformed

    //======================================================================
    //======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
    private void startupErrorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startupErrorBtnActionPerformed
        
		if (tree.subscribeErrWindow==null)
			Utils.popupMessage(this, "No error at startup.");
		else
			tree.subscribeErrWindow.setVisible(true);
    }//GEN-LAST:event_startupErrorBtnActionPerformed

    //======================================================================
    //======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
    private void accessControlBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accessControlBtnActionPerformed
        tree.startTACpanel();
    }//GEN-LAST:event_accessControlBtnActionPerformed

    //======================================================================
    //======================================================================
    private Selector    tango_host_selector = null;
	@SuppressWarnings({"UnusedDeclaration"})
    private void changeTgHostBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeTgHostBtnActionPerformed
        try{
            String      tmp_tgh;
            String[]    known_tgh;
            if (tango_host_selector==null)
            {
                known_tgh = AstorUtil.getKnownTangoHosts();
                tango_host_selector = new Selector(this, "Tango Host  (e.g.  hal:2001)", known_tgh, tango_host);
                ATKGraphicsUtils.centerDialog(tango_host_selector);
            }
            tmp_tgh = tango_host_selector.showDialog();

            if (tmp_tgh==null || tmp_tgh.length()==0)
                return;

            //	Check if connection OK
            String[]	tgh_arr = tmp_tgh.split(":");
            if (tgh_arr.length!=2)
            {
                Utils.popupError(this, "Input syntax error\n" + tmp_tgh + "\n is not a valid TANGO_HOST");
                return;
            }
            ApiUtil.get_db_obj(tgh_arr[0], tgh_arr[1]);

            if (tango_host.equals(tmp_tgh))
                return;

            tango_host = tmp_tgh;
            //	Close all host info dialogs
            tree.hostDialogs.close();
            tree.hostDialogs.clear();
            if (dev_browser!=null)
            {
                dev_browser.setVisible(false);
                dev_browser = null;
            }
            //  Change the Tango host and rebuid tree
            AstorUtil.setTangoHost(tango_host);
            ApiUtil.change_db_obj(tgh_arr[0], tgh_arr[1]);
			AstorUtil.readAstorProperties();//	could have changed with new Tango Host

			//	Method has been deprecated
			//fr.esrf.TangoApi.events.EventConsumer.create().updateDatabaseObject();

            buildTree();

            //  Re-build tools MenuItems
            buildToolsItems();
        }
        catch(DevFailed e) {
            ErrorPane.showErrorMessage(this,
				"Cannot change TANGO_HOST", e);
        }
    }//GEN-LAST:event_changeTgHostBtnActionPerformed

	//======================================================================
	//======================================================================
	private fr.esrf.logviewer.Main	logviewer = null;
	@SuppressWarnings({"UnusedDeclaration"})
	private void logviewerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logviewerMenuItemActionPerformed
		if (logviewer==null)
			logviewer = new fr.esrf.logviewer.Main(this);
		logviewer.setVisible(true);
		logviewer.toFront();
	}//GEN-LAST:event_logviewerMenuItemActionPerformed

	//======================================================================
	//======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
	private void jiveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jiveMenuItemActionPerformed
		tree.displayJiveAppli();
	}//GEN-LAST:event_jiveMenuItemActionPerformed

	//======================================================================
	//======================================================================
	static private String	searched_host = "";
	@SuppressWarnings({"UnusedDeclaration"})
	private void findHostItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findHostItemActionPerformed

		//	Ask for host's name
		String	hostname = (String) JOptionPane.showInputDialog(this,
												"Host Name ?",
												"Input Dialog",
												JOptionPane.INFORMATION_MESSAGE,
												null, null, searched_host);
        /*
        String hostname = InputDialog.getInput(this, "Host name ?", tree.getHostList());
        */
		//	if host has been typed,
		//	select it on tree and open control panel
		if (hostname!=null)
		{
			tree.setSelectionPath(hostname);
			tree.displayHostInfo();
			searched_host = hostname;
		}

	}//GEN-LAST:event_findHostItemActionPerformed
	//======================================================================
	//======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
	private void tangoStatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tangoStatBtnActionPerformed

		//	Ask to confirm
		if (JOptionPane.showConfirmDialog(this,
				"The System Information needs to browse Database\n"+
				"        and it could take a long time !\n\n" +
				"Start it any way ?",
				"Confirm Dialog",
				JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION)
					new DeviceTreeDialog(this);
	}//GEN-LAST:event_tangoStatBtnActionPerformed

	//======================================================================
	/**
	 *	Start tools found in Astor/Tools property
     * @param evt mouse event
	 */
	//======================================================================
	@SuppressWarnings({"ConstantConditions"})
    private void toolsItemActionPerformed(java.awt.event.ActionEvent evt) {
 		String  name = evt.getActionCommand();
        for (OneTool app : app_tools) {
            if (app.name.equals(name)) {
                try {
                    System.out.println("Starting " + app.classname);

                    //	Check if tool is already instancied.
                    if (app.jframe == null) {
                        //	Retrieve class name
                        Class cl = Class.forName(app.classname);

                        //	And build object
                        Class[] param = new Class[1];
                        param[0] = JFrame.class;
                        Constructor contructor = cl.getConstructor(param);

                        // ----------------- Java 5 -----------------
                        JFrame jf = (JFrame) contructor.newInstance(this);
                        //JFrame	jf = (JFrame)contructor.newInstance(new Object[] { this });
                        app.setJFrame(jf);
                    }
                    app.jframe.setVisible(true);
                }
                catch (Exception e) {
                    ErrorPane.showErrorMessage(this, null, e);
                }
            }
        }
	}
	//======================================================================
	//======================================================================
	void removeHost(String hostname)
	{
		if (JOptionPane.showConfirmDialog(this,
				"Are you sure to want to remove " + hostname,
				"Confirm Dialog",
				JOptionPane.YES_NO_OPTION)!=JOptionPane.OK_OPTION)
			return;

		String	servname = "Starter/" + hostname;
		String	devadmin = "dserver/" + servname;
		String	devname  = starterDeviceHeader + hostname;

		//	Ask to confirm
		try
		{
			//	Remove properties
			DeviceProxy	dev = new 	DeviceProxy(devname);
			dev.delete_property("StarteDsPath");
            dev.delete_property(collec_property);
            dev.delete_property(usage_property);
            dev.delete_property("UseEvents");

			//	Remove devices and server
			Database	db = ApiUtil.get_db_obj(AstorUtil.getTangoHost());
            db.delete_server(servname);
			db.delete_device(devname);
			db.delete_device(devadmin);

			JOptionPane.showMessageDialog(this,
								hostname + " has been removed !",
								"Command Done",
								JOptionPane.INFORMATION_MESSAGE);
		}
		catch (DevFailed e)
		{
            ErrorPane.showErrorMessage(this,
				"Cannot remove host", e);
		}
		try
		{
			buildTree();
		}
		catch (DevFailed e)
		{
            ErrorPane.showErrorMessage(this, null, e);
		}
	}
	//======================================================================
	//======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
	private void newHostBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newHostBtnActionPerformed
		addNewHost(null);
	}//GEN-LAST:event_newHostBtnActionPerformed
	//======================================================================
	//======================================================================
	void addNewHost(TangoHost h)
	{
		NewStarterDialog	dialog
			= new NewStarterDialog(this, h, tree.getCollectionList(), tree.hosts, true);
		dialog.setVisible(true);
		if (dialog.getValue()==JOptionPane.OK_OPTION)
		{
			try
			{
				buildTree();
			}
			catch (DevFailed e)
			{
        		ErrorPane.showErrorMessage(this, null, e);
			}
		}
	}
	//======================================================================
	//======================================================================
	void editHostProperties(TangoHost h)
	{
		NewStarterDialog	dialog
			= new NewStarterDialog(this, h, tree.getCollectionList(), tree.hosts, false);
		dialog.setVisible(true);
	}
	//======================================================================
	//======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
	private void stopServersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopServersBtnActionPerformed
		new ServerCmdThread(this, tree.hosts, StopAllServers).start();
	}//GEN-LAST:event_stopServersBtnActionPerformed

	//======================================================================
	//======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
	private void startServersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startServersBtnActionPerformed
		new ServerCmdThread(this, tree.hosts, StartAllServers).start();
	}//GEN-LAST:event_startServersBtnActionPerformed

	//======================================================================
	//======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
	private void newBranchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBranchBtnActionPerformed

		String	branch_name = (String)JOptionPane.showInputDialog(this,
								"New Branch Name",
								"Input Dialog",
								JOptionPane.INFORMATION_MESSAGE,
								null, null, "");
		if (branch_name!=null)
		{
			tree.addBranch(branch_name);
		}
	}//GEN-LAST:event_newBranchBtnActionPerformed

	//======================================================================
	//======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
	private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
 		String  item = evt.getActionCommand();

		if (item.equals(principleBtn.getText()))
			Utils.popupMessage(this, "", img_path + "principle.gif");
		else
		if (item.equals(distributionBtn.getText()))
			new HostsScanThread(this, tree.hosts).start();
		else
		if (item.equals(stateIconsBtn.getText()))
			Utils.popupMessage(this, "", img_path + "astor_state_icons.jpg");
		else
		if (item.equals(releaseNoteBtn.getText()))
		{
            new PopupHtml(this).show(ReleaseNote.str);
        }
		else
		if (item.equals(tangorbBtn.getText()))
		{
			TangORBversion	tangorb;
			try {
				tangorb = new TangORBversion();
			}
			catch(Exception e) {
                ErrorPane.showErrorMessage(this,
                    "Cannot check TangORB revision", e);
                return;
            }
			String	message = tangorb.jarfile + ":\n\n" + tangorb;
			PopupText txt = new PopupText(this, true);
			txt.setFont(new java.awt.Font("Courier", 1, 14));
			AstorUtil.centerDialog(txt, this);
			txt.show(message);
		}
		else
		if (item.equals(aboutBtn.getText()))
		{
			String	message =
				"           Astor  (Tango Manager) \n\n"+
				"This programme is used to control, start and stop\n"+
				"           the TANGO device servers. \n\n" +
				revNumber +
				"\n\n"+
				"Pascal Verdier - Software Engineering Group - ESRF";
			Utils.popupMessage(this, message, img_path + "tango_icon.jpg");
		}
		else
		if (item.equals(starterEventsItem.getText()))
		{
			Vector<String>	v = new Vector<String>();
			for (TangoHost host : tree.hosts)
				if (host.use_events)
					v.add(host.getName());

			String		title;
			String[]	hostnames = null;
			if (v.size()==0)
				title = "There is no host controlled on events !";
			else
			if (v.size()==tree.hosts.length)
				title = "All hosts are controlled on events !";
			else
			{
				title = "On " + tree.hosts.length + " hosts,\n" +
						v.size() +"  are controlled on events :";
				hostnames = new String[v.size()];
				for (int i=0 ; i<v.size() ; i++)
					hostnames[i] = v.get(i);
			}
			if (hostnames==null)
				Utils.popupMessage(this, title);
			else
				new PopupText(this, true).show(title, hostnames, 300, 400);
		}
		else
		if (item.equals(starterNoEventsItem.getText()))
		{
			Vector<String>	v = new Vector<String>();
			for (TangoHost host : tree.hosts)
				if (!host.use_events)
					v.add(host.getName());

			String		title;
			String[]	hostnames = null;
			if (v.size()==0)
				title = "There is no host controlled on polling !";
			else
			if (v.size()==tree.hosts.length)
				title = "All hosts are controlled on polling !";
			else
			{
				title = "On " + tree.hosts.length + " hosts,\n" +
						v.size() +"  are controlled on polling :";
				hostnames = new String[v.size()];
				for (int i=0 ; i<v.size() ; i++)
					hostnames[i] = v.get(i);
			}
			if (hostnames==null)
				Utils.popupMessage(this, title);
			else
				new PopupText(this, true).show(title, hostnames, 300, 400);
		}
		else
			Utils.popupMessage(this, "Not implemented yet !");
	}//GEN-LAST:event_helpActionPerformed

	//======================================================================
	//======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
	private void stopHostsControledBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopHostsControledBtnActionPerformed
		if (tree!=null)
		{
			for (TangoHost host : tree.hosts)
			{
				host.do_polling = false;
				tree.hostDialogs.close(host);
			}
			stopHostsControledBtn.setSelected(false);
		}
	}//GEN-LAST:event_stopHostsControledBtnActionPerformed

	//======================================================================
	//======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
	private void allHostsControledBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allHostsControledBtnActionPerformed
		for (TangoHost host : tree.hosts)
			host.do_polling = true;
		allHostsControledBtn.setSelected(false);
	}//GEN-LAST:event_allHostsControledBtnActionPerformed

	//======================================================================
	//======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
	private void expandBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expandBtnActionPerformed
		expanded = !expanded;
		tree.expand(expanded);
		if (expanded)
			expandBtn.setText("Collapse Tree");
		else
			expandBtn.setText("Expand Tree");
	}//GEN-LAST:event_expandBtnActionPerformed
	//======================================================================
	//======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
	private void deviceBrowserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deviceBrowserBtnActionPerformed
		if (dev_browser==null)
			dev_browser = new DevBrowser(this);
		dev_browser.setVisible(true);
	}//GEN-LAST:event_deviceBrowserBtnActionPerformed
	//======================================================================
	//======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
		try {
			buildTree();
		}
		catch (DevFailed e) {
            ErrorPane.showErrorMessage(this, null, e);
		}
    }//GEN-LAST:event_refreshBtnActionPerformed

	//======================================================================
	//======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
		doExit();
    }//GEN-LAST:event_exitBtnActionPerformed

	//======================================================================
	//======================================================================
	@SuppressWarnings({"UnusedDeclaration"})
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
		doExit();
	}//GEN-LAST:event_exitForm
	//======================================================================
	//======================================================================
	private WideSearchDialog	wide_search_dlg = null;
	@SuppressWarnings({"UnusedDeclaration"})
	private void findObjectByFilterItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findObjectByFilterItemActionPerformed

		if (wide_search_dlg==null)
			wide_search_dlg = new WideSearchDialog(this);
		wide_search_dlg.setVisible(true);

	}//GEN-LAST:event_findObjectByFilterItemActionPerformed

    //======================================================================
    //======================================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void multiServersCmdItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiServersCmdItemActionPerformed
        try {
            if (multiServerCommand==null) {
                multiServerCommand = new MultiServerCommand(this);
            }
            multiServerCommand.setVisible(true);
        }
        catch(DevFailed e) {
            ErrorPane.showErrorMessage(this, null, e);
        }
    }//GEN-LAST:event_multiServersCmdItemActionPerformed
	//======================================================================
	//======================================================================
	@SuppressWarnings({"ConstantConditions"})
    private void stopThreads()
	{
        System.out.println("Astor exiting....");
        //	Stop all host controled
        if (tree!=null && tree.hosts!=null)  {
			for (TangoHost host : tree.hosts) {
				//	Display a little timer during unsubscribe
				host.stopThread();
				if (host.use_events) {
					try { Thread.sleep(20); }
					catch (Exception e) {/* Do nothing */}
				}
			}
        }
        System.out.println(" ");
	}
	//======================================================================
	//======================================================================
	public void doExit()
	{
        if (dev_browser!=null && dev_browser.isVisible())
            setVisible(false);
        else
        {
            setVisible(false);
			stopThreads();
            System.exit(0);
        }
	}

	//======================================================================
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutBtn;
    private javax.swing.JMenuItem accessControlBtn;
    private javax.swing.JToggleButton allHostsControledBtn;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JMenuItem changeTgHostBtn;
    private javax.swing.JMenu cmdMenu;
    private javax.swing.JMenuItem ctrlPreferenceBtn;
    private javax.swing.JMenuItem deviceBrowserBtn;
    private javax.swing.JMenuItem distributionBtn;
    private javax.swing.JMenuItem exitBtn;
    private javax.swing.JMenuItem expandBtn;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem jiveMenuItem;
    private javax.swing.JMenuItem logviewerMenuItem;
    private javax.swing.JMenuItem multiServersCmdItem;
    private javax.swing.JMenuItem newBranchBtn;
    private javax.swing.JMenuItem newHostBtn;
    private javax.swing.JMenuItem principleBtn;
    private javax.swing.JMenuItem refreshBtn;
    private javax.swing.JMenuItem releaseNoteBtn;
    private javax.swing.JMenuItem starterEventsItem;
    private javax.swing.JMenuItem starterNoEventsItem;
    private javax.swing.JMenuItem stateIconsBtn;
    private javax.swing.JToggleButton stopHostsControledBtn;
    private javax.swing.JMenuItem tangorbBtn;
    private javax.swing.JMenu toolsMenu;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
	//======================================================================

	//======================================================================
	/**
	 * @param args the command line arguments
	 */
	//======================================================================
    public static void main(String args[]) {

		//	Check if line command
		if (args.length>0)
		{
			try
			{
				new AstorCmdLine(args);
			}
			catch(DevFailed e)
			{
				Except.print_exception(e);
			}
			catch(Exception e)
			{
				System.out.println(e);
				e.printStackTrace();
			}
			System.exit(0);
		}
		//	Else start application


		long	t0 = System.currentTimeMillis();
		//	First time, Open a simple Tango window
		TangoWindow	tw = new TangoWindow("ASTOR  Tango Manager");
		//noinspection ErrorNotRethrown
		try
		{
			Astor	astor = new Astor();
			astor.setVisible(true);
			Astor.displayed = true;
		}
		catch(DevFailed e)
		{
			System.out.println(e);
			tw.setVisible(false);
			if (e.errors[0].desc.indexOf("Controlled access service defined in Db but unreachable")>0)
				e.errors[0].desc = "Controlled access service defined in Db but unreachable\n" +
						"Astor cannot be configured from database !";
			
			ErrorPane.showErrorMessage(new JFrame(), null, e);
			System.exit(-1);
		}
		catch(java.lang.InternalError e)
		{
			System.out.println(e);
		}
		 catch(java.awt.HeadlessException e)
		{
			System.out.println(e);
		}
		long	t1 = System.currentTimeMillis();
		System.out.println("Build  GUI :" + (t1-t0) + " ms");
	}








    //===============================================================
    /**
     *	A thread class to execute a hosts scan
     */
    //===============================================================
    class HostsScanThread extends Thread
    {
        private JFrame		parent;
		private TangoHost[]	hosts;
        //===============================================================
        HostsScanThread(JFrame parent, TangoHost[] hosts)
        {
            this.parent = parent;
			this.hosts  = hosts;
		}
       //===============================================================
        public void run()
        {
			String[][]	list = new String[hosts.length][];
            String  message = "Scanning hosts...";
            Monitor monitor = new Monitor(parent, message);
			double	ratio = 0.01;
            monitor.setProgressValue(ratio, "Starting...");
			try { sleep(500); } catch(InterruptedException e) { /** */}
			try
			{
				int	nb_serv = 0;
				for (int i=0 ; i<hosts.length ; i++)
				{
					ratio = (1+1.0*i)/hosts.length;
                	monitor.setProgressValue(ratio,
							"Reading " + hosts[i].getName());

					String[]	servers = hosts[i].getServerAttribute();
					
					list[i] = new String[2];
					list[i][0] = hosts[i].getName();
					list[i][1] = ""+servers.length;
					nb_serv += servers.length;
				}
				
				//	Format results
				String	title = nb_serv + " servers   on " +
								hosts.length + " hosts";
				String[] cols = new String[] { "Names", "Nb Servers" };
				
				PopupTable	table = new PopupTable(parent, title, cols, list);
				table.setColumnWidth(new int[] { 200, 100 });
				table.setVisible(true);
			}
			catch(DevFailed e)
			{
            	ErrorPane.showErrorMessage(parent, "", e);
			}
		}
	}
}