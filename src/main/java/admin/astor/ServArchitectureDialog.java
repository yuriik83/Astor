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

import admin.astor.tango_release.TangoServerRelease;
import admin.astor.tools.Utils;
import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DevInfo;
import fr.esrf.TangoApi.*;
import fr.esrf.TangoDs.Except;
import fr.esrf.tangoatk.widget.util.ErrorPane;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Class Description: Basic Dialog Class to display info
 *
 * @author root
 */
@SuppressWarnings({"MagicConstant", "WeakerAccess"})
public class ServArchitectureDialog extends JDialog {

    private String serverName;
    private boolean fromApplication = true;
    private boolean modified = false;
    private TgServer server;

    private ServerInfoTree tree;
    public static final boolean EXPAND_NOT_FULL = false;
    public static final boolean EXPAND_FULL = true;

    //===============================================================
    /*
     *	Creates new form ServArchitectureDialog
     */
    //===============================================================
    public ServArchitectureDialog(JFrame parent, String serverName) throws DevFailed {
        super(parent, false);
        this.serverName = serverName;
        initComponents();

        initOwnComponent(parent);
    }

    //===============================================================
    /*
     *	Creates new form ServArchitectureDialog
     */
    //===============================================================
    public ServArchitectureDialog(JDialog parent, DeviceProxy dev) throws DevFailed {
        super(parent, false);
        this.serverName = dev.get_name().substring("dserver/".length());
        initComponents();

        initOwnComponent(parent);
    }

    //===============================================================
    //===============================================================
    private void initOwnComponent(Component parent) throws DevFailed {
        //	build tree to show the result
        tree = new ServerInfoTree(this);
        treeScrollPane.setViewportView(tree);
        treeScrollPane.setPreferredSize(new Dimension(350, 450));
        textScrollPane.setPreferredSize(new Dimension(350, 180));
        titleLabel.setText(serverName + "  Information");

        //	Check if from an appli or from an empty JDialog
        if (parent == null)
            fromApplication = false;

        jive.MultiLineToolTipUI.initialize();
        pack();
        tree.expandTree(EXPAND_NOT_FULL);
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

        javax.swing.JPanel titlePanel = new javax.swing.JPanel();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        zmqButton = new javax.swing.JRadioButton();
        javax.swing.JPanel scrollPanel = new javax.swing.JPanel();
        treeScrollPane = new javax.swing.JScrollPane();
        javax.swing.JPanel bottomPanel = new javax.swing.JPanel();
        expandBtn = new javax.swing.JRadioButton();
        infoBtn = new javax.swing.JRadioButton();
        javax.swing.JButton cancelBtn = new javax.swing.JButton();
        textScrollPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        titlePanel.setLayout(new java.awt.BorderLayout());

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        titleLabel.setText("Dialog Title");
        jPanel1.add(titleLabel);

        titlePanel.add(jPanel1, java.awt.BorderLayout.CENTER);

        zmqButton.setText("ZMQ event system compatible");
        zmqButton.setEnabled(false);
        titlePanel.add(zmqButton, java.awt.BorderLayout.SOUTH);

        getContentPane().add(titlePanel, java.awt.BorderLayout.NORTH);

        scrollPanel.setLayout(new java.awt.BorderLayout());
        scrollPanel.add(treeScrollPane, java.awt.BorderLayout.CENTER);

        getContentPane().add(scrollPanel, java.awt.BorderLayout.CENTER);

        expandBtn.setText("Expand tree");
        expandBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expandBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(expandBtn);

        infoBtn.setSelected(true);
        infoBtn.setText("Info");
        infoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(infoBtn);

        cancelBtn.setText("Dismiss");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(cancelBtn);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);

        textScrollPane.setPreferredSize(new java.awt.Dimension(200, 170));

        textArea.setEditable(false);
        textScrollPane.setViewportView(textArea);

        getContentPane().add(textScrollPane, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //===============================================================
    //===============================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void infoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoBtnActionPerformed

        boolean b = (infoBtn.getSelectedObjects() != null);
        textScrollPane.setVisible(b);
        pack();
    }//GEN-LAST:event_infoBtnActionPerformed

    //===============================================================
    //===============================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void expandBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expandBtnActionPerformed

        if (expandBtn.getSelectedObjects() != null)
            tree.expandTree(EXPAND_FULL);
        else
            tree.expandTree(EXPAND_NOT_FULL);

    }//GEN-LAST:event_expandBtnActionPerformed

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
     * Closes the dialog
     */
    //===============================================================
    private void doClose() {
        //	If modified -->  propose to restart devices
        if (modified) {
            if (JOptionPane.showConfirmDialog(this,
                    "Some properties have been modified !\n\n" +
                            "Do you want a restart devices ?",
                    "Dialog",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                try {
                    new DeviceProxy("dserver/" + serverName).command_inout("init");
                } catch (DevFailed e) {
                    ErrorPane.showErrorMessage(this, null, e);
                }
            }
        }
        setVisible(false);
        dispose();
        if (!fromApplication)
            System.exit(0);
    }

    //===============================================================
    //===============================================================
    public void showDialog() {
        setVisible(true);
    }

    //===============================================================
    //===============================================================
    static private String separator = ", ";

    static public String multiLine2OneLine(String str) {
        if (str == null) return null;
        //	Take of '\n'
        int idx;
        while ((idx = str.indexOf('\n')) >= 0)
            str = str.substring(0, idx) + separator +
                    str.substring(idx + 1);
        return str;
    }

    //===============================================================
    //===============================================================
    static public String OneLine2multiLine(String str) {
        if (str == null) return null;
        //	replace ", " by '\n'
        int idx;
        while ((idx = str.indexOf(separator)) >= 0)
            str = str.substring(0, idx) + "\n" +
                    str.substring(idx + separator.length());
        return str;
    }

    //===============================================================
    //===============================================================
    static public String[] string2array(String str) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer stk = new StringTokenizer(str, separator);
        while (stk.hasMoreTokens())
            tokens.add(stk.nextToken());
        return tokens.toArray(new String[tokens.size()]);
    }

    //===============================================================
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton expandBtn;
    private javax.swing.JRadioButton infoBtn;
    private javax.swing.JTextArea textArea;
    private javax.swing.JScrollPane textScrollPane;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JScrollPane treeScrollPane;
    private javax.swing.JRadioButton zmqButton;
    // End of variables declaration//GEN-END:variables
    //===============================================================


    //===============================================================
    /**
     * JTree Class
     */
    //===============================================================
    private class ServerInfoTree extends JTree {
        private Component parent;
        private DeviceProxy deviceProxy = null;
        private String[] deviceList = null;
        private DefaultTreeModel treeModel;
        private DefaultMutableTreeNode root;

        //===============================================================
        //===============================================================
        private ServerInfoTree(Component parent) throws DevFailed {
            super();
            this.parent = parent;
            initComponent();
        }

        //===============================================================
        //===============================================================
        private void expandTree(boolean expand) {
            expandTree(root, expand);
        }
        //===============================================================
        /**
         * Expend tree from node (re-entring method)
         *
         * @param node   origin to start expanding.
         * @param expand true if must be expanded.
         */
        //===============================================================
        private void expandTree(DefaultMutableTreeNode node, boolean expand) {
            int nb = node.getChildCount();
            for (int i = 0; i < nb; i++) {
                DefaultMutableTreeNode child =
                        (DefaultMutableTreeNode) node.getChildAt(i);

                Object obj = child.getUserObject();
                //	Check if all must be expanded
                if (expand || (obj instanceof TgProperty)) {
                    TreePath path = new TreePath(child.getPath());
                    expandPath(path);
                    expandTree(child, expand);
                }

                //	Check if something must be collapsed
                if (!expand && !(obj instanceof TgProperty)) {
                    TreePath path = new TreePath(child.getPath());
                    collapsePath(path);
                }
            }
        }

        //===============================================================
        //===============================================================
        private void initComponent() throws DevFailed {
            //	Create the nodes (root is the server).
            server = new TgServer(serverName);
            root = new DefaultMutableTreeNode(server);

            createNodes(root);
            //	Create the tree that allows one selection at a time.
            getSelectionModel().setSelectionMode
                    (TreeSelectionModel.SINGLE_TREE_SELECTION);

            //	Create Tree and Tree model
            treeModel = new DefaultTreeModel(root);
            setModel(treeModel);

            //	Enable tool tips.
            ToolTipManager.sharedInstance().registerComponent(this);

            //	Set the icon for leaf nodes.
            setCellRenderer(new TangoRenderer());
            //	Add Action listener
            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    treeMouseClicked(evt);
                }
            });

            zmqButton.setSelected(isZmqCompatible());
        }

        //===============================================================
        /*
         *	Create the server tree
         */
        //===============================================================
        private void createNodes(DefaultMutableTreeNode root) throws DevFailed {
            if (deviceProxy == null)
                deviceProxy = new DeviceProxy("dserver/" + serverName);

            TgClass[] classes = getClasses();
            server.nbDevices = 0;
            for (TgClass _class : classes) {
                //	Display class part
                DefaultMutableTreeNode classNode = new DefaultMutableTreeNode(_class);
                root.add(classNode);

                for (int p = 0; p < _class.properties.length; p++) {
                    TgProperty prop = _class.properties[p];
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(prop);
                    classNode.add(node);
                    node.add(new DefaultMutableTreeNode(prop.getValue()));
                }

                //	Display device part
                TgDevice[] devices = getDevices(_class.name);
                _class.nbDevices = devices.length;
                server.nbDevices += _class.nbDevices;

                //	Build for each device
                for (TgDevice device : devices) {
                    DefaultMutableTreeNode deviceNode = new DefaultMutableTreeNode(device);
                    classNode.add(deviceNode);
                    for (TgProperty property : device.properties) {
                        DefaultMutableTreeNode node = new DefaultMutableTreeNode(property);
                        deviceNode.add(node);
                        node.add(new DefaultMutableTreeNode(property.getValue()));
                    }
                }
                //  Get (in devive.info() the tag name for this class.
                if (devices.length > 0)
                    _class.setTagName(devices[0].getTagName());
            }
        }

        //===============================================================
        //===============================================================
        private TgDevice[] getDevices(String classname) throws DevFailed {
            if (deviceList == null) {
                DeviceData argOut = deviceProxy.command_inout("QueryDevice");
                deviceList = argOut.extractStringArray();
            }
            //	get only the device name for specified class
            List<String> deviceNames = new ArrayList<>();
            String str = classname + "::";
            for (String item : deviceList)
                if (item.startsWith(str))
                    deviceNames.add(item.substring(str.length()));

            //	Build properties for each device
            TgProperty[] dev_prop = getProperties(classname, "Dev");
            TgDevice[] devices = new TgDevice[deviceNames.size()];
            int i=0;
            for (String deviceName : deviceNames)
                devices[i++] = new TgDevice(deviceName, dev_prop);
            return devices;
        }

        //===============================================================
        //===============================================================
        private TgClass[] getClasses() throws DevFailed {
            //	Get the class list
            DeviceData argout = deviceProxy.command_inout("QueryClass");
            String[] classnames = argout.extractStringArray();

            //	Build properties for each class
            TgClass[] classes = new TgClass[classnames.length];
            for (int i = 0; i < classnames.length; i++) {
                TgProperty[] prop = getProperties(classnames[i], "Class");
                classes[i] = new TgClass(classnames[i], prop);
            }
            return classes;
        }

        //===============================================================
        //===============================================================
        private TgProperty[] getProperties(String classname, String source) throws DevFailed {
            DeviceData argin = new DeviceData();
            argin.insert(classname);
            String cmd = "QueryWizard" + source + "Property";
            DeviceData argout = deviceProxy.command_inout(cmd, argin);
            String[] str = argout.extractStringArray();
            TgProperty[] prop = new TgProperty[str.length / 3];
            for (int i = 0, n = 0; i < str.length; n++, i += 3)
                prop[n] = new TgProperty(classname, source, str[i], str[i + 1], str[i + 2]);

            return prop;
        }

        //======================================================
        //======================================================
        private boolean isZmqCompatible() {
            try {
                deviceProxy.command_query("ZmqEventSubscriptionChange");
                return true;
            }
            catch (DevFailed e) {
                return false;
            }
        }
        //======================================================
        /*
         *	Manage event on clicked mouse on PogoTree object.
         */
        //======================================================
        private void treeMouseClicked(java.awt.event.MouseEvent evt) {
            //	Check if click is on a node
            if (getRowForLocation(evt.getX(), evt.getY()) < 1)
                return;

            int mask = evt.getModifiers();
            //	Do something only if double click
            //-------------------------------------
            if (evt.getClickCount() == 2) {
                //	Check if btn1
                //------------------
                if ((mask & MouseEvent.BUTTON1_MASK) != 0) {
                    //	Check if on a property value
                    DefaultMutableTreeNode node =
                            (DefaultMutableTreeNode) getLastSelectedPathComponent();
                    Object o = node.getUserObject();
                    if (node.isLeaf() && o instanceof String) {
                        editProperty(node);
                    }
                }
            }
        }

        //===============================================================
        //===============================================================
        private void editProperty(DefaultMutableTreeNode node) {
            DefaultMutableTreeNode prop_node =
                    (DefaultMutableTreeNode) node.getParent();
            TgProperty property = (TgProperty) prop_node.getUserObject();
            EditPropertyDialog dialog;
            if (parent instanceof JDialog)
                dialog = new EditPropertyDialog((JDialog) parent, property);
            else
                dialog = new EditPropertyDialog((JFrame) parent, property);
            if ((property = dialog.showDialog()) != null) {
                //	Get the Class or device parent
                DefaultMutableTreeNode tg_node =
                        (DefaultMutableTreeNode) prop_node.getParent();
                Object o = tg_node.getUserObject();
                try {
                    if (o instanceof TgClass) {
                        TgClass _class = (TgClass) o;
                        _class.put_property(property);
                        //	Re-create node to resize.
                        replaceNode(node, property.getValue());
                        modified = true;
                    } else if (o instanceof TgDevice) {
                        TgDevice dev = (TgDevice) o;
                        dev.put_property(property);
                        //	Re-create node to resize.
                        replaceNode(node, property.getValue());
                        modified = true;
                    } else
                        System.out.println("object " + o + "  not implemented !");
                } catch (DevFailed e) {
                    ErrorPane.showErrorMessage(this, null, e);
                }
            }
        }

        //===============================================================
        //===============================================================
        private void replaceNode(DefaultMutableTreeNode node, String str) {
            //	Get parent node and node position.
            DefaultMutableTreeNode parent_node =
                    (DefaultMutableTreeNode) node.getParent();
            int pos = 0;
            for (int i = 0; i < parent_node.getChildCount(); i++)
                if (parent_node.getChildAt(i).equals(node))
                    pos = i;

            //	Build ne node and insert
            DefaultMutableTreeNode new_node = new DefaultMutableTreeNode(str);
            treeModel.insertNodeInto(new_node, parent_node, pos);

            //	Remove old one
            treeModel.removeNodeFromParent(node);
        }
        //===============================================================
        //===============================================================
    }


    //===============================================================
    /**
     * Class to define TANGO Server object
     */
    //===============================================================
    private class TgServer {
        String name;
        String desc;
        int nbDevices = 0;

        //===============================================================
        private TgServer(String name) {
            this.name = name;
            this.desc = "";

            try {
                String admin = "dserver/" + name;
                DeviceInfo info = new DbDevice(admin).get_info();
                desc = info.toString();
                textArea.setText(desc + "\n\n"+ new TangoServerRelease(name).toStringFull());
            } catch (DevFailed e) { /* Nothing to do **/}
        }

        //===============================================================
        public String toString() {
            return name;
        }
    }
    //===============================================================

    /**
     * Class to define TANGO Device object
     */
    //===============================================================
    private class TgDevice extends DeviceProxy {
        String name;
        TgProperty[] properties;

        private TgDevice(String name, TgProperty[] properties) throws DevFailed {
            super(name);
            this.name = name;

            //	Copy the default properties
            this.properties = new TgProperty[properties.length];
            for (int i = 0; i < properties.length; i++) {
                this.properties[i] = new TgProperty(name,
                        properties[i].src,
                        properties[i].name,
                        properties[i].desc,
                        properties[i].def_value);
                //	And Check the database
                try {
                    DbDatum data = get_property(properties[i].name);
                    if (!data.is_empty())
                        this.properties[i].setDbValue(data.extractStringArray());
                } catch (DevFailed e) {
                    Except.print_exception(e);
                }
            }
        }

        //===============================================================
        //===============================================================
        private void put_property(TgProperty prop) throws DevFailed {
            if (prop.db_value == null) {
                //	remove property in database
                delete_property(prop.name);
            } else {
                String[] value = string2array(prop.db_value);
                DbDatum[] data = {new DbDatum(prop.name)};
                data[0].insert(value);
                put_property(data);
            }
        }

        //===============================================================
        //===============================================================
        private String getTagName() {
            String tagName = "";
            try {
                DevInfo info = info();
                String servinfo = info.doc_url;
                String tag = "CVS Tag = ";
                int start = servinfo.indexOf(tag);
                if (start > 0) {
                    start += tag.length();
                    int end = servinfo.indexOf('\n', start);
                    if (end > start)
                        tagName = servinfo.substring(start, end);
                }
            } catch (DevFailed e) { /* Nothing to do */}
            return tagName;
        }

        //===============================================================
        //===============================================================
        public String toString() {
            return "Device: " + name;
        }
    }
    //===============================================================
    //===============================================================


    //===============================================================
    /**
     * Class to define TANGO Class Object
     */
    //===============================================================
    private class TgClass extends DbClass {
        String name;
        String desc;
        String tagName = null;
        TgProperty[] properties;
        int nbDevices = 0;

        //===============================================================
        private TgClass(String name, TgProperty[] properties) throws DevFailed {
            super(name);
            this.name = name;
            this.desc = "No Description Found in Database";
            try {
                //	Try to get description
                DbDatum data = get_property("Description");
                if (!data.is_empty()) {
                    String[] array = data.extractStringArray();
                    this.desc = "";
                    for (String line : array)
                        this.desc += line + "\n";
                }

                //	Copy the default properties
                this.properties = new TgProperty[properties.length];
                for (int i = 0; i < properties.length; i++) {
                    this.properties[i] = new TgProperty(name,
                            properties[i].src,
                            properties[i].name,
                            properties[i].desc,
                            properties[i].def_value);
                    //	And Check the database
                    DbDatum d = get_property(properties[i].name);
                    if (!d.is_empty())
                        this.properties[i].setDbValue(d.extractStringArray());
                }
            } catch (DevFailed e) { /* Do nothing */ }
        }

        //===============================================================
        //===============================================================
        private void put_property(TgProperty prop) throws DevFailed {
            if (prop.db_value == null) {
                //	remove property in database
                delete_property(prop.name);
            } else {
                String[] value = string2array(prop.db_value);
                DbDatum[] data = {new DbDatum(prop.name)};
                data[0].insert(value);
                put_property(data);
            }
        }

        //===============================================================
        //===============================================================
        private void setTagName(String tagName) {
            this.tagName = tagName;
        }

        //===============================================================
        //===============================================================
        public String toString() {
            String label = "Class: ";
            if (tagName != null) {
                if (tagName.startsWith(name))
                    label += tagName;
                else
                    label += name;
            } else
                label += name;
            return label;
        }
    }

    //===============================================================
    /**
     * Class to define TANGO property object
     */
    //===============================================================
    class TgProperty {
        String objectName;
        String src;
        String name;
        String desc;
        String def_value;
        String db_value = null;
        //===============================================================
        private TgProperty(String objectName, String src, String name, String desc, String def_value) {
            this.objectName = objectName;
            this.src = src;
            this.name = name;
            this.desc = desc;
            this.def_value = "";

            this.def_value = ServArchitectureDialog.multiLine2OneLine(def_value);
        }
        //===============================================================
        private void setDbValue(String[] values) {
            db_value = "";
            for (int i = 0; i < values.length; i++) {
                db_value += values[i];
                if (i < (values.length - 1))
                    db_value += ", ";
            }
        }
        //===============================================================
        private String getValue() {
            if (db_value == null)
                return def_value;
            else
                return db_value;
        }
        //===============================================================
        public String toString() {
            return name;
        }
    }


    //===============================================================
    /**
     * Renderer Class
     */
    //===============================================================
    private class TangoRenderer extends DefaultTreeCellRenderer {
        private ImageIcon root_icon;
        private ImageIcon class_icon;
        private ImageIcon prop_icon;
        private ImageIcon leaf_icon;
        private Font[] fonts;

        private final int TITLE = 0;
        private final int CLASS = 1;
        private final int DEVICE = 2;
        private final int PROP_NAME = 3;
        private final int PROP_DESC = 4;

        //===============================================================
        //===============================================================
        private TangoRenderer() {
            root_icon = Utils.getInstance().getIcon("TransparentTango.gif", 0.15);
            class_icon = Utils.getInstance().getIcon("class.gif");
            prop_icon = Utils.getInstance().getIcon("attleaf.gif");
            leaf_icon = Utils.getInstance().getIcon("uleaf.gif");

            fonts = new Font[PROP_DESC + 1];
            fonts[TITLE] = new Font("helvetica", Font.BOLD, 18);
            fonts[CLASS] = new Font("helvetica", Font.BOLD, 16);
            fonts[DEVICE] = new Font("helvetica", Font.BOLD, 12);
            fonts[PROP_NAME] = new Font("helvetica", Font.PLAIN, 12);
            fonts[PROP_DESC] = new Font("helvetica", Font.PLAIN, 10);
        }

        //===============================================================
        //===============================================================
        public Component getTreeCellRendererComponent(
                JTree tree,
                Object obj,
                boolean sel,
                boolean expanded,
                boolean leaf,
                int row,
                boolean hasFocus) {

            super.getTreeCellRendererComponent(
                    tree, obj, sel,
                    expanded, leaf, row,
                    hasFocus);
            String tip = null;
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) obj;
            Object user_obj = node.getUserObject();
            if (row == 0) {
                //	ROOT
                setIcon(root_icon);
                setFont(fonts[TITLE]);
                tip = ((TgServer) user_obj).desc;
                tip += "\n\n" + ((TgServer) user_obj).nbDevices + "  devices";
            } else {
                if (user_obj instanceof TgClass) {
                    setIcon(class_icon);
                    setFont(fonts[CLASS]);
                    tip = ((TgClass) user_obj).desc;
                    tip += "\n" + ((TgClass) user_obj).nbDevices + "  devices";
                } else if (user_obj instanceof TgDevice) {
                    setIcon(class_icon);
                    setFont(fonts[DEVICE]);
                } else if (user_obj instanceof TgProperty) {
                    //	Property name
                    setIcon(prop_icon);
                    setFont(fonts[PROP_NAME]);
                    tip = ((TgProperty) user_obj).desc;
                } else if (user_obj instanceof String) {
                    //	Property desc and value
                    setIcon(leaf_icon);
                    setFont(fonts[PROP_DESC]);
                }
            }
            setToolTipText(tip);
            return this;
        }
    }
   //===============================================================
    //===============================================================


    //===============================================================
    /**
     * @param args the command line arguments
     */
    //===============================================================
    public static void main(String args[]) {
        String serverName = "VacGaugeServer/sr_c27-ip";
        if (args.length > 0)
            serverName = args[0];
        try {
            new ServArchitectureDialog(null, serverName).setVisible(true);
        } catch (DevFailed e) {
            ErrorPane.showErrorMessage(new javax.swing.JDialog(), null, e);
            System.exit(0);
        }

    }
}
