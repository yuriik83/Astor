//+======================================================================
// $Source$
//
// Project:   Tango
//
// Description:  java source code for the Pogo class definition .
//
// $Author$
//
// $Revision$
//
// $Log$
// Revision 3.19  2005/09/15 08:26:36  pascal_verdier
// Server architecture display addded.
//
// Revision 3.18  2005/08/30 08:05:25  pascal_verdier
// Management of two TANGO HOST added.
//
// Revision 3.17  2005/08/02 12:01:51  pascal_verdier
// Minor changes.
//
// Revision 3.16  2005/06/02 09:02:36  pascal_verdier
// Minor changes.
//
// Revision 3.15  2005/04/25 08:55:36  pascal_verdier
// Start/Stop servers from shell command line added.
//
// Revision 3.14  2005/03/15 10:22:30  pascal_verdier
// Sort servers before creating panel buttons.
//
// Revision 3.13  2005/01/18 08:48:20  pascal_verdier
// Tools menu added.
// Not controlled servers list added.
//
// Revision 3.12  2004/11/29 11:43:56  pascal_verdier
// OsIsUnix method modified.
//
//
// Copyleft 2003 by European Synchrotron Radiation Facility, Grenoble, France
//               All Rights Reversed
//-======================================================================


package admin.astor;
 
//import admin.astor.*;


/** 
 *	This class group many info and methods used By Astor.
 *
 * @author  verdier
 * @Revision 
 */


import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import fr.esrf.Tango.*;
import fr.esrf.TangoDs.*;
import fr.esrf.TangoApi.*;

public class AstorUtil implements AstorDefs {

	private static DbClass	_class = null;
	private	static AstorUtil	instance = null;

	//	Variables will be setted by properties
	//---------------------------------------------
	private static short	readInfoPeriod  = 5;
	private static short	nbStartupLevels	= 5;
	private static String	rloginCmd 	    = null;
	private static String	rloginUser	    = null;
	private	static String[]	tools           = null;
	private static String	serverHelpURL	= "http://www.esrf.fr/computing/cs/tango/";
	private static String	appliHelpURL	= "http://www.esrf.fr/computing/cs/tango/";
	private static boolean	res_read        = false;
	private static boolean	debug           = false;
	private static boolean	no_ctrl_btn     = false;
	MyCompare		compare;
	//===============================================================
	//===============================================================
	private AstorUtil()
	{
		compare = new MyCompare();
	}

	//===============================================================
	//===============================================================
	public static AstorUtil getInstance()
	{
		if (instance==null)
			instance = new AstorUtil();
		return instance;
	}
	//===============================================================
	//===============================================================
	static boolean getCtrlBtn()
	{
		if (no_ctrl_btn==false)
			readAstorRes();
		return !no_ctrl_btn;
	}
	//===============================================================
	//===============================================================
	static boolean getDebug()
	{
		if (res_read==false)
			readAstorRes();
		return debug;
	}
	//===============================================================
	//===============================================================
	public static String getRloginCmd()
	{
		readAstorRes();
		return rloginCmd;
	}
	//===============================================================
	//===============================================================
	public static String getRloginUser()
	{
		readAstorRes();
		return rloginUser;
	}
	//===============================================================
	//===============================================================
	public static String[] getTools()
	{
		if (res_read==false)
			readAstorRes();
		return tools;
	}
	//===============================================================
	//===============================================================
	static void readAstorRes()
	{
		String[]	argout = null;

		try
		{
			//	Get database instance
			//----------------------------------
			Database	dbase = ApiUtil.get_db_obj();
			//	get Astor Property
			//----------------------------------
			String[]	propnames = {
							"Debug",
							"RloginCmd",
							"RloginUser",
							"Tools",
							"NoCtrlButton"
						};
			DbDatum[]	datum = dbase.get_property("Astor", propnames);
			int	i = -1;
			if (datum[++i].is_empty()==false)
				debug = datum[i].extractBoolean();
			if (datum[++i].is_empty()==false)
				rloginCmd = datum[i].extractString();
			if (datum[++i].is_empty()==false)
				rloginUser = datum[i].extractString();
			if (datum[++i].is_empty()==false)
				tools = datum[i].extractStringArray();
			if (datum[++i].is_empty()==false)
				no_ctrl_btn = datum[i].extractBoolean();
			res_read = true;
		}
		catch(DevFailed e) {}
	}
	//===============================================================
	//===============================================================
	String[] getCollectionList(TangoHost[] hosts)
	{
		boolean	null_exists = false;
		Vector	vect = new Vector();
		for (int i=0 ; i<hosts.length ; i++)
		{
			boolean found = false;
			for (int j=0 ; j<vect.size() && !found ; j++)
				if (hosts[i].collection==null)
				{
					//System.out.println(hosts[i]);
					null_exists = true;
				}
				else
					found = (hosts[i].collection.equals((String)vect.elementAt(j)));

			//	If not already exists add it
			if (!found && hosts[i].collection!=null)
				vect.add(hosts[i].collection);
		}
		if (null_exists)
			vect.add("Miscellaneous");

		//	Sort for alphabetical order
		Collections.sort(vect, compare);
		
		//	Add database as first element
		vect.add(0, "Tango Database");

		//	Copy vector to string array
		String[]	list = new String[vect.size()];
		for (int i=0 ; i<vect.size() ; i++)
			list[i] = (String)vect.elementAt(i);
		return list;
	}
	//===============================================================
	//===============================================================
	public TangoHost[] getTangoHostList() throws DevFailed
	{
		//	Get hosts list from database
		//----------------------------------
		String []	hostnames = getHostControlledList();

		//	And create TangoHost array object
		//----------------------------------------
		TangoHost[]	hosts = new TangoHost[hostnames.length];
		for(int i=0, j=0 ; i<hostnames.length ; i++, j++)
		{
			//System.out.println(i + ":	" + hostnames[i]);
			hosts[j] = new TangoHost(hostnames[i]);
		}
		return hosts;
	}
	//===============================================================
	/**
	 *	Get the devices controlled by Starter DS
	 *	and return the hosts list.
	 */
	//===============================================================
	private String[] getHostControlledList() throws DevFailed
	{
		//	Get database instance and read host list
		//-------------------------------------------------
		Database	dbase = ApiUtil.get_db_obj();
		String[]	devices = dbase.get_device_member("tango/admin/*");
		return devices;
	}
	//===============================================================
	//===============================================================
	public static String getTangoHost()
	{
		return System.getProperty("TANGO_HOST");
	}
	//===============================================================
	//===============================================================
	public static void setTangoHost(String tango_host)
	{
		Properties props = System.getProperties();
		props.put("TANGO_HOST", tango_host);
		System.setProperties(props);
		_class = null;
	}
	//===============================================================
	//===============================================================
	public static String getTangoPort() throws DevFailed
	{
		String	strport;
		if ((strport=System.getProperty("TANGO_HOST"))==null)
			Except.throw_exception("TANGO_HOST_NOT_DEFINED",
									"TANGO_HOST Not Defined !",
									"AstorUtil.getTangoPort()");
		int	i;
		if ((i=strport.indexOf(":"))<0)
			Except.throw_exception("TANGO_HOST_NOT_DEFINED",
									"TANGO_HOST Incorect Definition !",
									"AstorUtil.getTangoPort()");
		return strport.substring(i+1);
	}
	//===============================================================
	//===============================================================
	public static short getStarterReadPeriod()
	{
		if (_class==null)
		{
			getStarterClassProperties();
		}
		return readInfoPeriod;
	}
	//===============================================================
	//===============================================================
	public static short getStarterNbStartupLevels()
	{
		if (_class==null)
		{
			getStarterClassProperties();
		}
		return nbStartupLevels;
	}
	//===============================================================
	//===============================================================
	public static String getStarterHelpURL()
	{
		if (_class==null)
		{
			getStarterClassProperties();
		}
		return serverHelpURL;
	}
	//===============================================================
	//===============================================================
	public static String getAppliHelpURL()
	{
		if (_class==null)
		{
			getStarterClassProperties();
		}
		return appliHelpURL;
	}
	//===============================================================
	//===============================================================
	private static void getStarterClassProperties()
	{
		try
		{
			_class = new DbClass("Starter");

			String[]	propnames = {
								"NbStartupLevels",
								"ReadInfoDbPeriod",
								"doc_url",
								"appli_doc_url"
								};
			DbDatum[]	properties = _class.get_property(propnames);
			if (properties[0].is_empty()==false)
				nbStartupLevels  = properties[0].extractShort();
			if (properties[1].is_empty()==false)
				readInfoPeriod   = properties[1].extractShort();
			readInfoPeriod  *= 1000;	//	sec -> ms

			if (properties[2].is_empty()==false)
				serverHelpURL = properties[2].extractString();

			if (properties[3].is_empty()==false)
				appliHelpURL = properties[3].extractString();
		}
		catch(DevFailed e)
		{
			 Except.print_exception(e);
		}
		if (getDebug())
		{
			System.out.println("NbStartupLevels:  " + nbStartupLevels);
			System.out.println("ReadInfoDbPeriod: " + readInfoPeriod);
			System.out.println("server doc_url:   " + serverHelpURL);
			System.out.println("appli_doc_url:    " + appliHelpURL);
		}
	}
	//===============================================================
	//===============================================================
	static String[] getServerClassProperties(String classname)
	{
		String[] result = new String[3];
		try
		{
			DbClass	dbclass  = new DbClass(classname);
			String[] propnames = {  "ProjectTitle", 
									"Description", 
									"doc_url" };
			String[]	desc;
			DbDatum[]	prop = dbclass.get_property(propnames);
			if (prop[0].is_empty()==false)
				result[0] = prop[0].extractString();
			if (prop[1].is_empty()==false)
			{
				//	Get description as string array and convert to string
				desc = prop[1].extractStringArray();
				result[1] = "";
				for (int i=0 ; i<desc.length ; i++)
				{
					result[1] += desc[i];
					if (i<desc.length-1)
						result[1] += "\n";
				}
			}
			if (prop[2].is_empty())
				result[2] = DocLocationUnknown;
			else
				result[2] = prop[2].extractString();
		}
		catch(DevFailed e)
		{
			result[0] = result[1] = result[2] = null;
		}
		return result;
	}

	//======================================================
	//======================================================
	static public void centerDialog(JDialog dialog, JFrame parent)
	{
		Point	p = parent.getLocationOnScreen();
		p.x += ((parent.getWidth() - dialog.getWidth())  / 2);
		p.y += ((parent.getHeight() - dialog.getHeight())  / 2);
		dialog.setLocation(p);
	}
	//======================================================
	//======================================================
	static public void centerDialog(JDialog dialog, JDialog parent)
	{
		Point	p = parent.getLocationOnScreen();
		p.x += ((parent.getWidth() - dialog.getWidth())  / 2);
		p.y += ((parent.getHeight() - dialog.getHeight())  / 2);
		dialog.setLocation(p);
	}
	//======================================================
	//======================================================
	static public void rightShiftDialog(JDialog dialog, JFrame parent)
	{
		Point	p = parent.getLocationOnScreen();
		p.x += parent.getWidth();
		p.y += ((parent.getHeight() - dialog.getHeight())  / 2);
		dialog.setLocation(p);
	}
	//======================================================
	//======================================================
	static String[] string2StringArray(String str)
	{
		int	idx = 0;
		Vector	v = new Vector();
		while ((idx=str.indexOf("\n"))>0)
		{
			v.add(str.substring(0, idx));
			str = str.substring(idx+1);
			idx = 0;
		}
		v.add(str);
		String[]	result = new String[v.size()];
		for (int i=0 ; i<v.size() ; i++)
			result[i] = (String) v.elementAt(i);
		return result;
	}
	//===============================================================
	/**
	 *	Execute a shell command and throw exception if command failed.
	 *
	 *	@param cmd	shell command to be executed.
	 */
	//===============================================================
	public static String executeShellCmd(String cmd) throws Exception
	{
		Process proc = Runtime.getRuntime().exec(cmd);

		// get command's output stream and
		// put a buffered reader input stream on it.
		//-------------------------------------------
		InputStream istr = proc.getInputStream();
		BufferedReader br =
                new BufferedReader(new InputStreamReader(istr));
		StringBuffer	sb =new StringBuffer("");

		// read output lines from command
		//-------------------------------------------
		String str;
		while ((str = br.readLine()) != null)
		{
			//System.out.println(str);
			sb.append(str+"\n");
		}

		// wait for end of command
		//---------------------------------------
		proc.waitFor();

		// check its exit value
		//------------------------
		int retVal;
		if ((retVal=proc.exitValue()) != 0)
		{
			//	An error occured try to read it
			InputStream errstr = proc.getErrorStream();
			br = new BufferedReader(new InputStreamReader(errstr));
			while ((str = br.readLine()) != null)
			{
				System.out.println(str);
				sb.append(str+"\n");
			}
			Except.throw_exception("SHELL_CMD_FAILED",
				"the shell command\n" + cmd + "\nreturns : " + retVal
				+ " !\n\n"+ sb.toString(),
				"AstorUtil.executeShellCmd()");
		}
		return sb.toString();
	}
	//===============================================================
	//===============================================================
	static private boolean	_osIsUnix = true;
	static private boolean	_osIsUnixTested = false;
	static public boolean osIsUnix()
	{
		if (_osIsUnixTested==false)
		{
			try
			{
				String	os = System.getProperty("os.name");
				//System.out.println("Running under " + os);
				_osIsUnix = (os.toLowerCase().startsWith("windows")==false);
			}
			catch(Exception e)
			{
				//System.out.println(e);
				_osIsUnix = false;
			}
		}
		return _osIsUnix;
	}
	//===============================================================
	//===============================================================
	public void sort(Vector[] array)
	{
		for (int i=0 ; i<array.length ; i++)
			Collections.sort(array[i], compare);
	}
	//===============================================================
	//===============================================================
	public void sort(Vector v)
	{
		Collections.sort(v, compare);
	}
	//===============================================================
	//===============================================================




	//======================================================
	/**
	 *	MyCompare class to sort collection
	 */
	//======================================================
	class  MyCompare implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			String	s1 = o1.toString().toLowerCase();
			String	s2 = o2.toString().toLowerCase();
			return s1.compareTo(s2);
		}
	}


}
