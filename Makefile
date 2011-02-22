#+======================================================================
# $Source$
#
# Project:      Tango Device Server
#
# Description:  Makefile to generate the JAVA Tango classes package
#
# $Author$
#
# $Version$
#
# $Log$
# Revision 3.21  2005/09/15 13:43:01  pascal_verdier
# *** empty log message ***
#
# Revision 3.20  2005/04/25 08:55:36  pascal_verdier
# Start/Stop servers from shell command line added.
#
# Revision 3.19  2005/03/15 10:22:31  pascal_verdier
# Sort servers before creating panel buttons.
#
# Revision 3.18  2005/03/11 14:07:54  pascal_verdier
# Pathes have been modified.
#
# Revision 3.17  2005/02/10 15:38:19  pascal_verdier
# Event subscritions have been serialized.
#
# Revision 3.16  2005/02/03 13:31:58  pascal_verdier
# Display message if subscribe event failed.
# Display hosts using events (Starter/Astor).
#
# Revision 3.15  2005/01/18 08:48:19  pascal_verdier
# Tools menu added.
# Not controlled servers list added.
#
# Revision 3.14  2004/12/08 09:54:06  pascal_verdier
# *** empty log message ***
#
# Revision 3.13  2004/11/23 14:07:22  pascal_verdier
# Minor changes.
#
# Revision 3.12  2004/09/28 07:01:51  pascal_verdier
# Problem on two events server list fixed.
#
# Revision 3.11  2004/07/09 08:12:49  pascal_verdier
# HostInfoDialog is now awaken only on servers change.
#
# Revision 3.9  2004/06/17 09:19:58  pascal_verdier
# Refresh performence problem solved by removing tool tips on JTree.
#
# Revision 3.8  2004/04/13 12:17:29  pascal_verdier
# DeviceTree class uses the new browsing database commands.
#
# Revision 3.7  2004/03/03 08:31:04  pascal_verdier
# The server restart command has been replaced by a stop and start command in a thread.
# The delete startup level info has been added.
#
# Revision 3.6  2004/02/04 14:37:43  pascal_verdier
# Starter logging added
# Database info added on CtrlServersDialog.
#
# Revision 3.5  2003/11/25 15:56:45  pascal_verdier
# Label on hosts added.
# Notifyd begin to be controled.
#
# Revision 3.4  2003/11/07 09:58:46  pascal_verdier
# Host info dialog automatic resize implemented.
#
# Revision 3.3  2003/10/20 08:55:15  pascal_verdier
# Bug on tree popup menu position fixed.
#
# Revision 3.2  2003/09/08 11:05:28  pascal_verdier
# *** empty log message ***
#
# Revision 3.1  2003/06/19 12:57:57  pascal_verdier
# Add a new host option.
# Controlled servers list option.
#
# Revision 3.0  2003/06/04 12:37:52  pascal_verdier
# Main window uses now a Jtree to display hosts.
#
# Revision 2.1  2003/06/04 12:33:11  pascal_verdier
# Main window uses now a Jtree to display hosts.
#
# Revision 2.0  2003/01/16 15:22:35  verdier
#
# copyleft :    European Synchrotron Radiation Facility
#               BP 220, Grenoble 38043
#               FRANCE
#
#-======================================================================

APPLI_VERS	=	4.0.5

PACKAGE    = Astor
TANGO_HOME = /segfs/tango
PACK_HOME  = $(TANGO_HOME)/tools
DOC_HOME   = $(TANGO_HOME)/doc/www/tango/tango_doc/tools_doc/astor_doc/

# -----------------------------------------------------------------
#
#		The compiler flags
#
#------------------------------------------------------------------

BIN_DIR   = ../bin
JAVAFLAGS = -g -deprecation -d $(BIN_DIR)
JAVAC = javac $(JAVAFLAGS)

#-------------------------------------------------------------------


#-----------------------------------------------------------------

all:	 trace $(PACKAGE)  exe

trace:
	@echo $(CLASSPATH)

$(PACKAGE):
	$(JAVAC) *.java

exe:
	@./astor
#	java -DTANGO_HOST=corvus:10000 admin.astor.ServArchitectureDialog PowerSupply/pv

clean:
	rm  -f $(BIN_DIR)/admin/astor/*.class

MAIN_CLASS=		$(PACKAGE)
jar :
	echo "Updating date and revision number..."
	upd_rev  -f $(MAIN_CLASS).java  -r $(APPLI_VERS)
	$(JAVAC) *.java
	@make_jar $(PACKAGE) $(APPLI_VERS)


DOC_HEADER=	"Astor  -  The TANGO Management"
doc:
	@javadoc 				\
	-private 				\
	-version -author		\
	-nodeprecated			\
	-windowtitle $(DOC_HEADER)		\
	-header $(DOC_HEADER)	\
	-d ../doc/pr_guide		\
	$(PACK_HOME)/admin/astor/*.java	\
	$(PACK_HOME)/app_util/*.java

install_doc:
	cp ../doc/*.html $(DOC_HOME)
	cp ../doc/img/*  $(DOC_HOME)/img


FTP_TARGET	=	mars:/ftp/pub/cs/tango/Astor
JAR_DIR		=	$(TANGO_HOME)/bin/java
JAR_FILE	=	Astor-$(APPLI_VERS).jar
install_ftp:
	@echo "rcp $(JAR_FILE) $(FTP_TARGET)"
	@cd $(JAR_DIR); rcp $(JAR_FILE) $(FTP_TARGET)
	rcp ReleaseNote $(FTP_TARGET)

