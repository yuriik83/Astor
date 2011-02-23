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
//
// $Log$
// Revision 3.48  2010/11/29 13:54:45  pascal_verdier
// Multi servers command added.
// Uptime for servers added.
//
// Revision 3.47  2010/10/08 07:41:27  pascal_verdier
// Minor changes.
//
// Revision 3.46  2010/06/17 07:41:22  pascal_verdier
// Start new server can take several servers (multiple selection).
//
// Revision 3.45  2010/04/08 10:41:47  pascal_verdier
// Minor changes.
//
// Revision 3.44  2009/06/02 15:19:05  pascal_verdier
// Remove serialization between HostStateThread and HostInfoDialogVector.
//
// Revision 3.43  2009/04/06 14:27:44  pascal_verdier
// Using MySqlUtil feature.
//
// Revision 3.42  2009/02/18 09:47:57  pascal_verdier
// Device dependencies (sub-devices) tool added.
//
// Revision 3.41  2009/01/30 09:31:50  pascal_verdier
// Black box management added for database.
// Black box management tool improved.
// Find TANGO object by filter added.
//
// Revision 3.40  2009/01/16 14:46:58  pascal_verdier
// Black box management added for host and Server.
// Starter logging display added for host and server.
// Splash screen use ATK one.
//
// Revision 3.39  2008/12/16 15:17:16  pascal_verdier
// Add a scroll pane in HostInfoDialog in case of too big dialog.
//
// Revision 3.38  2008/12/02 08:19:55  pascal_verdier
// Number of controlled server display added.
//
// Revision 3.37  2008/11/19 09:59:56  pascal_verdier
// New tests done on Access control.
// Pool Threads management added.
// Size added as preferences.
//
// Revision 3.36  2008/06/17 11:41:50  pascal_verdier
// Pb on notifd button fixed.
//
// Revision 3.35  2008/06/16 11:50:39  pascal_verdier
// Level trees are now displayed on 2 rows.
//
// Revision 3.34  2008/05/28 13:19:20  pascal_verdier
// Start/Stop All do not ask for not concerned levels.
//
// Revision 3.33  2008/05/26 11:49:12  pascal_verdier
// Host info dialog servers are managed in a jtree.
//
// Revision 3.32  2008/04/07 10:53:35  pascal_verdier
// Branch info modified.
//
// Revision 3.31  2008/03/03 14:55:21  pascal_verdier
// Starter Release_4 management.
//
//
// Copyleft 2007 by European Synchrotron Radiation Facility, Grenoble, France
//               All Rights Reversed
//-======================================================================

package admin.astor;


/**
 *	This class display a dialog with a list of servers running, stopped,
 *	and buttons to start or stop servers.
 *
 * @author  verdier
 * @version $Revision$
 */
import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DevState;
import fr.esrf.TangoApi.ApiUtil;
import fr.esrf.TangoApi.DeviceAttribute;
import fr.esrf.TangoApi.events.ITangoChangeListener;
import fr.esrf.TangoApi.events.TangoChange;
import fr.esrf.TangoApi.events.TangoChangeEvent;
import fr.esrf.TangoApi.events.TangoEventsAdapter;
import fr.esrf.TangoDs.Except;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.tangoatk.widget.util.ATKConstant;
import fr.esrf.tangoatk.widget.util.ATKGraphicsUtils;
import fr.esrf.tangoatk.widget.util.ErrorPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.Vector;

//===============================================================
/**
 *	JDialog Class to display info
 *
 *	@author  Pascal Verdier
 */
//===============================================================


public class HostInfoDialog extends JDialog implements AstorDefs, TangoConst
{
	public  TangoHost		host;
	public	String			name;
	private Astor			astor;
	private Color			bg = null;
	
	private String	attribute = "Servers";

	private	static final int	NO_CHANGE     = 0;
	private	static final int	LIST_CHANGED  = 1;
	private static final int	STATE_CHANGED = 2;
	private static Dimension	preferred_size;

	private JScrollPane			scrollPane = null;
	private LevelTree[]			trees = null;
	private JPanel				levelsPanel = null;
	private JPanel[]			treePanels;
	private JPanel				notifdPanel;
	private ServerPopupMenu		notifd_menu;
	//===============================================================
	/**
	 *	Creates new form HostInfoDialog
     * @param parent the Astor parent instance
     * @param host   host to display info
     */
	//===============================================================
	public HostInfoDialog(Astor parent, TangoHost host)
	{
		super(parent, false);
		this.astor  = parent;
		this.host   = host;
		this.name   = host.getName();
		initComponents();
		setTitle(host + "  Control");
		displayAllBtn.setSelected(true);
		preferred_size = AstorUtil.getHostDialogPreferredSize();
		scrollPane = new JScrollPane();

		new UpdateThread().start();

		bg = titlePanel.getBackground();
		titleLabel.setText("Controlled Servers on " + name);
		notifd_menu = new ServerPopupMenu(astor, this, host, ServerPopupMenu.NOTIFD);

		pack();
 		ATKGraphicsUtils.centerDialog(this);
	}
	//===============================================================
	//===============================================================
	public Astor getAstorObject()
	{
		return astor;
	}
	//===============================================================
	//===============================================================
	public void displayHostInfoDialog(String hostname)
	{
		astor.tree.displayHostInfoDialog(hostname);
	}
	//===============================================================
	//===============================================================
	void setDialogPreferredSize(Dimension d)
	{
		preferred_size = d;
		packTheDialog();
	}
	//===============================================================
	//===============================================================
	void updatePanel()
	{
		//	Build panel
		if (trees==null)
		{
			int	nb_levels = AstorUtil.getStarterNbStartupLevels();
			nb_levels++; //	for not controlled

			levelsPanel = new JPanel();
        	levelsPanel.setLayout(new GridBagLayout());
        	centerPanel.add(levelsPanel, java.awt.BorderLayout.CENTER);

			//	First time build notifd label
			if (host.check_notifd)
			{
				notifdPanel = new JPanel();
				host.notifd_label = new JLabel("Event Notify Daemon");
            	host.notifd_label.setFont(new Font("Dialog", 1, 12));
				GridBagConstraints gbc = new GridBagConstraints ();
				gbc.gridx  = 0;
				gbc.gridy  = 0;
				gbc.gridwidth = nb_levels;
				gbc.fill  = GridBagConstraints.HORIZONTAL;
				notifdPanel.add (host.notifd_label, gbc);
				levelsPanel.add (notifdPanel, gbc);

				//	Add Action listener
				host.notifd_label.addMouseListener (new java.awt.event.MouseAdapter () {
					public void mouseClicked (java.awt.event.MouseEvent evt) {
						serverBtnMouseClicked (evt);
					}
				});
			}
			treePanels = new JPanel[nb_levels];
			trees = new LevelTree[nb_levels];

			for (int i=0 ; i<nb_levels ; i++)
			{
				trees[i] = new LevelTree(astor, this, host, i);
				treePanels[i] = new JPanel();
				treePanels[i].add(trees[i]);
			}
		}
		else
			for (LevelTree tree : trees)
				tree.checkUpdate();

		int	nb = 0;
		for (int i=1 ; i<trees.length ; i++)
			nb += trees[i].getNbServers();
		titleLabel.setText("" + nb + " Controlled Servers on " + name);

		checkActiveLevels();
		updateHostState();
		packTheDialog();
	}
	//===============================================================
	/**
	 * Pack the window and check if it needs scroll bars or not.
	 * If very big, needs scroll bars.
	 * But if not so big, do not needs to have a good resize.
	 */
	//===============================================================
	void packTheDialog()
	{
		if (!isVisible())
			return;

		pack();
		int	width  = levelsPanel.getWidth();
		int	height = levelsPanel.getHeight();
		//System.out.println(width + ", " + height);

		if (width>preferred_size.width || height>preferred_size.height)
		{
			//	set bar width (cannot get, not active yet)
			int	bw = 20;
			//	Put the tree panel in a scroll pane
			centerPanel.remove(levelsPanel);
			Dimension	d = new Dimension(preferred_size);
			//	Check to resize only in one way
			if (width<preferred_size.width-bw)		d.width = width+bw;
			if (height<preferred_size.height-bw)	d.height = height+bw;
			scrollPane.setPreferredSize(d);
			scrollPane.add(levelsPanel);
			scrollPane.setViewportView(levelsPanel);
			centerPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
		}
		else
		{
			//	Put the tree panel directly in center panel.
			centerPanel.remove(scrollPane);
			scrollPane.remove(levelsPanel);
			centerPanel.add(levelsPanel, java.awt.BorderLayout.CENTER);
		}
		pack();
	}
	//===============================================================
	//===============================================================
	private void checkActiveLevels()
	{
		levelsPanel.removeAll();

		//	Check how many levels are active
		Vector<JPanel>	v = new Vector<JPanel>();
		for (int i=1 ; i<trees.length ; i++)
			if (trees[i].getNbServers()>0)
				v.add(treePanels[i]);
		if (trees[LEVEL_NOT_CTRL].getNbServers()>0)
			v.add(treePanels[LEVEL_NOT_CTRL]);

		//	Compute horizontal size and dispach
		int	x = 0;
		int	y = 1;
		int	x_size = v.size()/2-1;
		if (x_size<2) x_size = 2;

		for (int i=0 ; i<v.size() ; i++)
		{
			JPanel	panel = v.get(i);
			GridBagConstraints gbc = new GridBagConstraints ();
			gbc.gridx  = x++;
			gbc.gridy  = y;
			gbc.insets = new Insets(5, 10, 0, 0);
			gbc.fill   = GridBagConstraints.HORIZONTAL;
			gbc.fill   = GridBagConstraints.VERTICAL;
			gbc.anchor = GridBagConstraints.WEST;
			levelsPanel.add (panel, gbc);
			if(i==x_size)
			{
				x = 0;
				y+=2;
			}
		}
		//	Re-add notifd panel
		if (host.check_notifd)
		{
			GridBagConstraints gbc = new GridBagConstraints ();
			gbc.gridx  = 0;
			gbc.gridy  = 0;
			gbc.gridwidth = x_size+1;
			gbc.fill  = GridBagConstraints.HORIZONTAL;
			levelsPanel.add (notifdPanel, gbc);
		}
	}
	//===============================================================
	//===============================================================
	Color getBackgroundColor()
	{
		//	under Win32 the color is from button
		//	From dialog it is another one (Why ?)
		return startNewBtn.getBackground();
	}
	//===============================================================
	//===============================================================
	void updateHostState()
	{
		//	Manage Stater state
		if (host.state==moving)
		{
			String	str_state = ApiUtil.stateName(DevState.MOVING);
			titlePanel.setBackground(ATKConstant.getColor4State(str_state));
		}
		else
		if (host.state==alarm)
		{
			String	str_state = ApiUtil.stateName(DevState.ALARM);
			titlePanel.setBackground(ATKConstant.getColor4State(str_state));
		}
		else
			titlePanel.setBackground(bg);

		//	Update  notifd state
		if (host.check_notifd)
			host.notifd_label.setIcon(AstorUtil.state_icons[host.notifyd_state]);
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
        btnPanel = new javax.swing.JPanel();
        cancelBtn = new javax.swing.JButton();
        centerPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        startNewBtn = new javax.swing.JButton();
        StartAllBtn = new javax.swing.JButton();
        stopAllBtn = new javax.swing.JButton();
        displayAllBtn = new javax.swing.JRadioButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        cancelBtn.setText("Dismiss");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        btnPanel.add(cancelBtn);

        getContentPane().add(btnPanel, java.awt.BorderLayout.SOUTH);

        centerPanel.setLayout(new java.awt.BorderLayout());

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 18));
        titleLabel.setText("Dialog Title");
        titlePanel.add(titleLabel);

        centerPanel.add(titlePanel, java.awt.BorderLayout.NORTH);

        getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);

        startNewBtn.setText("Start New");
        startNewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startNewBtnActionPerformed(evt);
            }
        });

        jPanel1.add(startNewBtn);

        StartAllBtn.setText("Start All");
        StartAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartAllBtnActionPerformed(evt);
            }
        });

        jPanel1.add(StartAllBtn);

        stopAllBtn.setText("Stop All");
        stopAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopAllBtnActionPerformed(evt);
            }
        });

        jPanel1.add(stopAllBtn);

        displayAllBtn.setText("Display All");
        displayAllBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        displayAllBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        displayAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayAllBtnActionPerformed(evt);
            }
        });

        jPanel1.add(displayAllBtn);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	//======================================================
	/**
	 *	Manage event on clicked mouse on PogoTree object.
     *  @param evt the mouse event
     */
	//======================================================
	private void serverBtnMouseClicked (java.awt.event.MouseEvent evt)
	{
		notifd_menu.showMenu(evt);
	}
	//===============================================================
	//===============================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void displayAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayAllBtnActionPerformed

		boolean b = (displayAllBtn.getSelectedObjects()!=null);
		if (trees!=null) {
			for (LevelTree tree : trees)
				if (b) {
					if (tree.getLevelRow() != LEVEL_NOT_CTRL)
						tree.expandTree();
				}
				else
					tree.collapseTree();
		}
    }//GEN-LAST:event_displayAllBtnActionPerformed

	//===============================================================
	//===============================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void stopAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopAllBtnActionPerformed

		//	Check levels used by servers
		Vector<Integer>	used = new Vector<Integer>();
		for (int i=trees.length-1 ; i>=0 ; i--) {
			int	level = trees[i].getLevelRow();
			if (level!=LEVEL_NOT_CTRL &&	//	is controlled
				trees[i].getNbServers()>0 &&
				trees[i].hasRunningServer())
					used.add(level);
		}
		//	And stop them
		new ServerCmdThread(this, host, StopAllServers, used).start();

    }//GEN-LAST:event_stopAllBtnActionPerformed

 	//===============================================================
	//===============================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void StartAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartAllBtnActionPerformed

		//	Check levels used by servers
		Vector<Integer>	used = new Vector<Integer>();
		 for (LevelTree tree : trees) {
			 int level = tree.getLevelRow();
			 if (level != LEVEL_NOT_CTRL &&	//	is controlled
					 tree.getNbServers() > 0 &&
					 tree.getState() != DevState.ON)
				 used.add(level);
		 }
		//	And start them
		new ServerCmdThread(this, host, StartAllServers, used).start();

    }//GEN-LAST:event_StartAllBtnActionPerformed

	//===============================================================
	//===============================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void startNewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startNewBtnActionPerformed

 		//	Get Servername
		ListDialog	jlist = new ListDialog(this);
		//	Search Btn position to set dialog location
		Point	p   = getLocationOnScreen();
		p.translate(50,50);
		jlist.setLocation(p);
		jlist.showDialog();
		Vector<String>	servnames = jlist.getSelectedItems();
        if (servnames==null)
            return;
		for (String servname : servnames) {
            if (servname!=null) {
                try  {
                    //	OK to start, do it.
                    //------------------------------------------------------------
                    host.registerServer(servname);
                    host.startOneServer(servname);

                    //	OK to start get the Startup control params.
                    //--------------------------------------------------
                    TangoServer	ts = new TangoServer(servname, DevState.OFF);
                    ts.startupLevel(this, host.getName(), p);
                }
                catch (DevFailed e) {
                    ErrorPane.showErrorMessage(astor, null, e);
                }
            }
        }
	}//GEN-LAST:event_startNewBtnActionPerformed

	
	//===============================================================
	//===============================================================
    @SuppressWarnings({"UnusedDeclaration"})
	private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
		doClose();
	}//GEN-LAST:event_cancelBtnActionPerformed

	//===============================================================
	//===============================================================
    @SuppressWarnings({"UnusedDeclaration"})
	private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
		doClose();
	}//GEN-LAST:event_closeDialog

	//===============================================================
	/**
	 *	Closes the dialog
	 */
	//===============================================================
	void doClose()
	{
		if (astor.getWidth()==0)	//	in test
			System.exit(0);
		setVisible(false);
		dispose();
	}
	//===============================================================
	//===============================================================
	void fireNewTreeSelection(LevelTree tree)
	{
		for (LevelTree tree1 : trees)
			if (tree1 != tree)
				tree1.clearSelection();
	}
	//===============================================================
	//===============================================================
    void stopLevel(int level)
	{
		Vector<Integer>	v = new Vector<Integer>();
		v.add(level);
		new ServerCmdThread(this, host, StopAllServers, v).start();
	}
	//===============================================================
	//===============================================================
    void startLevel(int level)
	{
        Vector<Integer>	v = new Vector<Integer>();
		v.add(level);
		new ServerCmdThread(this, host, StartAllServers, v).start();
	}
	//=============================================================
	//=============================================================
	private void manageServersAttribute(DeviceAttribute	att)
	{
		//	
		Vector<Server>	servers = new Vector<Server>();
		try {
			if (!att.hasFailed()) {
				String[]	list = att.extractStringArray();
				for (String item : list)
					servers.add(new Server(item));
			}
		}
		catch(DevFailed e) {
			System.err.println(name);
			Except.print_exception(e);
		}

		//	Check if something has changed
		switch(updateHost(servers)) {
		case LIST_CHANGED:
			updatePanel();
			break;
		case NO_CHANGE:
			return;
		}
		for (LevelTree tree : trees)
			tree.repaint();
	}
	//=============================================================
	/*
	 *	Update TangoHost objects and check what has changed.
	 */
	//=============================================================
	public int updateHost(Vector<Server> new_servers)
	{
		boolean	state_changed = false;
		boolean	list_changed = false;

		//	check if new one
		for (Server new_serv : new_servers)
		{
			TangoServer server = host.getServer(new_serv.name);
			if (server == null)
			{
				//	create it
				try
				{
					server = new TangoServer(new_serv.name, new_serv.state);
				}
				catch (DevFailed e)
				{
					System.err.println(name);
					Except.print_exception(e);
				}
				host.addServer(server);
				list_changed = true;
			}

			if (server!=null)
			{
				//	Check state
				if (new_serv.state != server.getState())
				{
					server.setState(new_serv.state);
					state_changed = true;
				}
				//	Check control
				if (new_serv.controlled != server.controlled |
						new_serv.level != server.startup_level)
				{
					server.controlled = new_serv.controlled;
					server.startup_level = new_serv.level;
					list_changed = true;
				}
			}
		}

		//	Check if some have been removed
		for (int i=0 ; i<host.nbServers() ; i++) {
			TangoServer	server   = host.getServer(i);
			boolean	found = false;
			for (int j=0 ; !found && j<new_servers.size() ; j++) {
				Server	new_serv = new_servers.get(j);
				found = (new_serv.name.equals(server.getName()));
			}
			if (!found) {
				host.removeServer(server.getName());
				list_changed = true;
			}
		}

		if(list_changed)
			return LIST_CHANGED;
		else
		if(state_changed)
			return STATE_CHANGED;
		else
			return NO_CHANGE;
	}
	//=============================================================
	//=============================================================
	private DevState string2state(String str)
	{
		for (int i=0 ; i<Tango_DevStateName.length ; i++)
			if (str.equals(Tango_DevStateName[i]))
				return DevState.from_int(i);
		return DevState.UNKNOWN;
	}
	//=============================================================
	//=============================================================
	class Server
	{
		String		name;
		DevState	state;
		boolean		controlled = false;
		int			level = 0;

		//=========================================================
		public Server(String line)
		{
			//	Parse line
        	StringTokenizer stk = new StringTokenizer(line);
			Vector<String>	v = new Vector<String>();
		    while (stk.hasMoreTokens())
        		v.add(stk.nextToken());
			if (v.size()>0)
				this.name = (String)v.get(0);

			if (v.size()>1)
				this.state = string2state((String)v.get(1));

			if (v.size()>2)
				this.controlled = ( (v.get(2)).equals("1") );

			if (v.size()>3) {
				String	s = v.get(3);
				try {
					this.level = Integer.parseInt(s);
				} catch(NumberFormatException e){ /* */}
			}
		}
		//=========================================================
		public String toString()
		{
			return name + " -> " + ApiUtil.stateName(state) + "	- " +
					((controlled)? "" : "not ") + "Controlled 	level " + level;
		}
		//=========================================================
	}

	//===============================================================
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton StartAllBtn;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JRadioButton displayAllBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton startNewBtn;
    private javax.swing.JButton stopAllBtn;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
	//===============================================================


	//=========================================================
	//=========================================================
	void setSelection(String servname)
	{
		if (trees != null)
			for (LevelTree tree : trees)
			{
				TangoServer server = tree.getServer(servname);
				if (server != null)
				{
					tree.expandTree();
					tree.setSelection(server);
				}
				else
					tree.resetSelection();
			}
	}
	//=========================================================
	//=========================================================
	void updateData()
	{
		updatePanel();
	}
	//=========================================================
	//=========================================================




















	//===============================================================
	/**
	 * A thread to read and update server lists
	 */
	//===============================================================
	class UpdateThread extends Thread
	{
		private int 	readInfoPeriod = 1000;
		private boolean	stop_it = false;
		//===========================================================
		public UpdateThread()
		{
		}
		//===========================================================
		public void run()
		{
			if (host.use_events)
				subscribeChangeEvent();

			
			//	Manage polling on synchronous calls
			while (!stop_it)
			{
				long		t0 = System.currentTimeMillis();

				if (!host.use_events)
				{
					if (host.do_polling)
					{
						//System.out.println("Reading " + host.get_name() +
						//		"  on events: " + host.use_events);
						manageSynchronous();
					}
				}
				wait_next_loop(t0);
			}
		}
		//=============================================================
		/**
		 *	Compute time to sleep before next loop
         * @param t0  time reference to compute time to wait
         */
		//=============================================================
		public synchronized void wait_next_loop(long t0)
		{
			try
			{
				long	t1 = System.currentTimeMillis();
				long	time_to_sleep = readInfoPeriod - (t1-t0);

				if (time_to_sleep<=0)
					time_to_sleep = 100;
				wait(time_to_sleep);
			} 
			catch(InterruptedException e) { System.out.println(e); }
		}
		//=============================================================
		/**
		 *	Read servers list attributes in synchronous mode.
		 */
		//=============================================================
		private void manageSynchronous()
		{
			try
			{
				DeviceAttribute	att = host.read_attribute(attribute);
				manageServersAttribute(att);
			}
			catch(DevFailed e) {
				// Except.print_exception(e);
			}
		}
	}




	private static String[]		filters = new String[0];
	private	ServerEventListener	server_listener = null;
	//======================================================================
	//======================================================================
	private void subscribeChangeEvent()
	{
		try
		{
			if (host.supplier==null)
				host.supplier = new TangoEventsAdapter(host);
		}
		catch(DevFailed e)
		{
			host.use_events = false;
			//	Display exception 
			System.err.println("subscribeChangeServerEvent() for " +
							host.get_name() + " FAILED !");
			fr.esrf.TangoDs.Except.print_exception(e);
			return;
		}
		catch(Exception e)
		{
			host.use_events = false;
			//	Display exception 
			System.err.println("subscribeChangeServerEvent() for " +
							host.get_name() + " FAILED !");
			System.err.println(e);
			return;
		}

		try
		{
			//	add listener for double_event and server_event
			server_listener = new ServerEventListener();

			host.supplier.addTangoChangeListener(
						server_listener, attribute, filters);
			//if (AstorUtil.getDebug())
				System.out.println("subscribeChangeServerEvent() for " +
					host.get_name() + "/" + attribute + " OK!");
		}
		catch(DevFailed e)
		{
			//	Display exception
			host.use_events = false;
			System.err.println("subscribeChangeServerEvent() for " +
					host.get_name() + " FAILED !");
			fr.esrf.TangoDs.Except.print_exception(e);
		}
		catch(Exception e)
		{
			//	Display exception 
			host.use_events = false;
			System.err.println("subscribeChangeServerEvent() for " +
							host.get_name() + " FAILED !");
			System.err.println(e);
		}
	}
	//======================================================================
	//======================================================================
	public void unsubscribeServersEvent()
	{
		if (host.supplier!=null && server_listener!=null)
			try
			{
			    host.supplier.removeTangoChangeListener(server_listener, attribute);
  			    System.out.println("unsubscribe event for " + host.getName() + "/" + attribute);
			}
			catch(DevFailed e)
			{
				System.err.println("Failed to unsubscribe event for " + attribute);
				fr.esrf.TangoDs.Except.print_exception(e);
			}
	}

	//=========================================================================
	/**
	 *	Server event listener
	 */
	//=========================================================================
	class ServerEventListener implements ITangoChangeListener
	{
		//=====================================================================
		//=====================================================================
    	public void change(TangoChangeEvent event)
		{
			TangoChange	tc = (TangoChange)event.getSource();
			String		devname = tc.getEventSupplier().get_name();

			try
			{
			 	DeviceAttribute	att = event.getValue();
				manageServersAttribute(att);
				//if (AstorUtil.getDebug())
				//	System.out.println(devname + "/" + att.getName() + " changed " + " : ");
	    	}
			catch (DevFailed e)
			{
				System.out.println(name);
				if (e.errors[0].reason.equals("API_EventTimeout"))
				{
					System.err.println("HostStataThread.ServerEventListener" +
										devname + " : API_EventTimeout");
					//fr.esrf.TangoDs.Except.print_exception(e);
				}
				else
					fr.esrf.TangoDs.Except.print_exception(e);
			}
        	catch (Exception e)
        	{
				System.err.println(name);
				System.err.println("AstorEvent." + devname);
 				System.err.println(e);
            	System.err.println("HostStateThread.ServerEventListener : could not extract data!");
        	}
		}
	}
    //======================================================
    //======================================================
      class Blink
      {
          private JComponent  obj;
          private int     cnt;
          private Timer   timer;
          private Color   color;
          private long    t0 = 0;
          private int     duration;
          private String  text = null;

        //======================================================
        /**
         * Constructor for blicking object
         *
         * @param obj JComponent to start blinking
         */
        //======================================================
        Blink(JComponent obj)
        {
            this.obj = obj;
            cnt      = 0;
            color    = obj.getBackground();
        }
       //======================================================
       /**
        * Constructor for blicking object
        *
        * @param obj      JComponent to start blinking
        * @param duration Duration to blink in second
        */
        //======================================================
       Blink(JComponent obj, int duration)
       {
           this(obj);
           this.duration = duration;
           if (obj instanceof JLabel)
               text = ((JLabel)obj).getText();
           else
               color = obj.getBackground();
           t0 = System.currentTimeMillis();
        }
        //======================================================
        //======================================================
        @SuppressWarnings({"UnusedDeclaration"})
        void blinkPerformer(ActionEvent evt)
        {
            cnt++;
            if (cnt%2==0) {
                if (obj instanceof JLabel)
                    ((JLabel)obj).setText("-> " + text);
                else
                    obj.setBackground(color);
            }
            else  {
                if (obj instanceof JLabel)
                    ((JLabel)obj).setText(text);
                else
                   obj.setBackground(Color.lightGray);
            }
            //    Check if terminated.
            if (duration!=0)  {
                long  t1 = System.currentTimeMillis();
                if (t1-t0 >= duration*1000)  {
                    timer.stop();
                    if (obj instanceof JLabel)
                        ((JLabel)obj).setText(text);
                    else
                        obj.setBackground(color);
                }
            }
        }
        //======================================================
        //======================================================
        private void start()
        {
            //	Fire a timer every once in a while make blink the button
            ActionListener taskPerformer = new ActionListener() {
               public void actionPerformed(ActionEvent evt) {
                   blinkPerformer(evt);
               }
            };
            timer = new Timer(200, taskPerformer);
            timer.start();
        }
    }
}