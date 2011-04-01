//+======================================================================
// $Source:  $
//
// Project:   Tango
//
// Description:  java source code for the StarterStat class definition .
//
// $Author: verdier $
//
// $Revision: $
//
// $Log: StarterStat.java,v $
//
// Copyleft 2010 by European Synchrotron Radiation Facility, Grenoble, France
//               All Rights Reversed
//-======================================================================

package admin.astor.statistics;


/** 
 *	This class is able to manage a vector of ServerStat objects
 *  built from a Starter statistics file.
 *
 * @author  verdier
 */

import fr.esrf.Tango.DevFailed;
import fr.esrf.TangoApi.DeviceAttribute;
import fr.esrf.TangoApi.DeviceData;
import fr.esrf.TangoApi.DeviceProxy;
import fr.esrf.TangoDs.Except;

import java.util.StringTokenizer;
import java.util.Vector;



public class  StarterStat extends Vector<ServerStat>
{
    public String   name;
    public boolean  readOK = false;
    public long     resetTime = System.currentTimeMillis();
    public String   error = ""; 

    public  static final String className = "StarterStat";
    private static final String hostStr   = "host";
    private static final String readStr   = "read";
    private static final String resetStr  = "reset";
    private static final String description =
            "<" +   className + " " +
                    hostStr + "=\"HOST\" " +
                    readStr + "=\"READ\" " +
                    resetStr+ "=\"RESET\">";
    private static final String tab = "\t\t";
	//===============================================================
    /**
     *  Statistics for the specified starter
     * @param name  host name
     */
	//===============================================================
	public StarterStat(String name)
	{
        this.name = name;
        readStatisticsFromStarter(name);
    }
	//===============================================================
    /**
     *  Statistics for the specified starter
     * @param lines  read xml lines
     * @throws DevFailed in case of xml parsing failed.
     */
	//===============================================================
	public StarterStat(Vector<String> lines) throws DevFailed
	{
        super();
        parseXmlStatistics(lines);
    }
	//===============================================================
    /**
     *  Statistics for the specified starter (host)
     * @param name  host name
     */
	//===============================================================
	private void readStatisticsFromStarter(String name)
	{

        String  devName = "Tango/admin/"+name;
        DeviceProxy	dev;
        try {
    		dev = new DeviceProxy(devName);
        }
        catch(DevFailed e) {
            Except.print_exception(e);
            error = e.errors[0].desc;
            return;
        }

        try {
            //  Get server running list
            buildControlledServerList(dev);
            readStatisticsFileFromStarter(dev);
            readOK = true;
        }
        catch(DevFailed e) {
            System.err.println(name+ ":\t" + e.errors[0].desc);
            error = e.errors[0].desc;
            resetTime = System.currentTimeMillis();
        }
	}
	//===============================================================
	//===============================================================
    private void buildControlledServerList(DeviceProxy dev) throws DevFailed
    {
        DeviceAttribute att = dev.read_attribute("Servers");
        String[]    lines = att.extractStringArray();
        for (String line : lines) {
            StringTokenizer stk = new StringTokenizer(line);
            String  serverName = stk.nextToken();
            stk.nextToken();   //String  strState   =
            String  strLevel   = stk.nextToken();
            String  strCtrl    = stk.nextToken();

            if (! strLevel.equals("0") &&
                ! strCtrl.equals("0")) {
                add(new ServerStat(serverName, this));
            }
        }
    }
	//===============================================================
	//===============================================================
	public void readStatisticsFileFromStarter(DeviceProxy dev) throws DevFailed
	{
		DeviceData	argin = new DeviceData();
		argin.insert("Statistics");
		DeviceData	argout = dev.command_inout("DevReadLog", argin);
		String	str = argout.extractString();

		Vector<LogRecord>	records = new Vector<LogRecord>();
		StringTokenizer	stk = new StringTokenizer(str, "\n");
		while (stk.hasMoreTokens()) {
			records.add(new LogRecord(stk.nextToken()));
		}

        resetTime = System.currentTimeMillis();
		buildServerStatistics(records);
		for (ServerStat server : this) {
			server.computeStatistics();
            if (server.size()>0) {
                long    t = server.oldestTime;
                if (t<resetTime)
                    resetTime = t;
            }
		}
	}
	//===============================================================
	//===============================================================
	private void buildServerStatistics(Vector<LogRecord> records)
	{
		for (LogRecord rec : records) {
			ServerStat	server = getServerStat(rec.name);
			if (server==null) {
				add(server=new ServerStat(rec.name, this));
			}
			server.addLog(rec);
		}
	}
	//===============================================================
	//===============================================================
	public  ServerStat getServerStat(String name)
	{
		for (ServerStat server : this) {
			if (server.name.equals(name))
				return server;
		}
		//	not found
		return null;
	}
	//===============================================================
	//===============================================================
    public static final String[]    tableHeader = {
           "Server", "Run Time", "Failures", "Failure Duration"
    };
    public String[][] toTable()
    {
        String[][]  array = new String[size()][tableHeader.length];
        int  row = 0;
        for (ServerStat server : this) {
            int col = 0;
            array[row][col++] = server.name;
            array[row][col++] = Utils.formatDuration(server.runDuration);
            array[row][col++] = Integer.toString(server.nbFailures);
            if (server.nbFailures>0) {
                array[row][col]   = Utils.formatDuration(server.failedDuration);
            }
            else {
                array[row][col]   = "";
            }
            row++;
        }
        return array;
    }
	//===============================================================
	//===============================================================
    public String toString(String serverName)
    {
        StringBuffer    sb = new StringBuffer();

        sb.append(getServerStat(serverName).recordsToString());
        return sb.toString();
    }
	//===============================================================
	//===============================================================
    private String toXmlLine()
    {
        String  str = description;
        str = Utils.strReplace(str, "HOST", name);
        str = Utils.strReplace(str, "READ", Boolean.toString(readOK));
        str = Utils.strReplace(str, "RESET", Long.toString(resetTime));
        return str;
    }
    //=======================================================
    //=======================================================
    private void parseXmlStatistics(Vector<String> lines) throws DevFailed
    {
        //  The first line is the Starter definition
        if (lines.size()>=0)
            parseXmlProperties(lines.get(0));

        Vector<String>  records = new Vector<String>();
        boolean inServer = false;
        for (int i=1 ; i<lines.size() ; i++) {
            String line = lines.get(i);
            if (!inServer && line.startsWith("<"+ServerStat.className)) {
                //  Starting server statistics
                inServer = true;
                records.add(line);
            }
            else {
                //  is it the end of server statistics
                if (line.startsWith("</"+ServerStat.className)) {
                    ServerStat  serverStat = new ServerStat(records);
                    serverStat.starterStat = this;
                    add(serverStat);
                    inServer = false;
                    records.clear();
                }
                else if (inServer) {
                    records.add(line);
                }
            }

        }
    }
    //=======================================================
    //=======================================================
    private void parseXmlProperties(String line) throws DevFailed
    {
        name = Utils.parseXmlProperty(line, hostStr);
        try {
            readOK    = Boolean.parseBoolean(Utils.parseXmlProperty(line, readStr));
            resetTime = Long.parseLong(Utils.parseXmlProperty(line, resetStr));
        }
        catch (NumberFormatException e ) {
            Except.throw_exception("SYNTAX_ERROR", e.toString(), "StarterStat.parseLine()");
        }
    }
    //=======================================================
    //=======================================================
    public String toXml()
    {
        StringBuffer    sb = new StringBuffer();
        sb.append(tab).append(toXmlLine()).append("\n");
        for (ServerStat serverStat : this)
            sb.append(serverStat.toXml()).append("\n");
        sb.append(tab).append("</").append(className).append(">");
        return sb.toString();
    }
	//===============================================================
	//===============================================================
    public String toString()
    {
        StringBuffer    sb = new StringBuffer(name);

        long duration = System.currentTimeMillis() - resetTime;
        sb.append("   Since ").append(Utils.formatDate(resetTime))
                .append("  (").append(Utils.formatDuration(duration)).append(")\n");
        for (ServerStat server : this) {
            sb.append(server).append("\n");
        }
        return sb.toString();
    }
  	//===============================================================
	//===============================================================

	//===============================================================
	//===============================================================
	public static void main (String[] args)
	{
		//String		devName = "esrflinux1-2";
		String		devName = "coral";

		if (args.length>0)
			devName = args[0];
		try {
			StarterStat stat = new StarterStat(devName);
            System.out.println(stat.toString("Dummy/check"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//===============================================================
	//===============================================================
}