//+======================================================================
// $Source$
//
// Project:   Tango
//
// Description:  java source code for the Utils class definition .
//
// $Author$
//
// $Revision$
//
// $Log$
// Revision 1.2  2011/01/04 14:34:18  pascal_verdier
// Password added for access control dialog.
//
// Revision 1.1  2008/11/19 10:01:34  pascal_verdier
// New tests done on Access control.
// Allowed commands tree added.
//
//
// Copyleft 2008 by European Synchrotron Radiation Facility, Grenoble, France
//               All Rights Reversed
//-======================================================================

package admin.astor.access;


/** 
 *	This class is able to
 *
 * @author  verdier
 */
 
import javax.swing.ImageIcon;
import java.awt.*;


class  Utils
{
	static private Utils	instance = null;
	//===============================================================
	//===============================================================
	private Utils()
	{
	}
	//===============================================================
	//===============================================================
	static Utils getInstance()
	{
		if (instance==null)
			instance = new Utils();
		return instance;
	}
	//===============================================================
	//===============================================================
	ImageIcon getIcon(String filename)
	{
        return admin.astor.tools.Utils.getInstance().getIcon(filename);
	}
    //===============================================================
    //===============================================================
    public ImageIcon getIcon(String filename, double ratio)
    {
        ImageIcon	icon = getIcon(filename);
        return getIcon(icon, ratio);
    }
    //===============================================================
    //===============================================================
    public ImageIcon getIcon(ImageIcon icon, double ratio)
    {
        if (icon != null)
        {
            int	width  = icon.getIconWidth();
            int	height = icon.getIconHeight();

            width  = (int) (ratio * width);
            height = (int) (ratio * height);

            icon = new ImageIcon(
               icon.getImage().getScaledInstance(
                        width, height, Image.SCALE_SMOOTH ));
        }
        return icon;
    }
	//===============================================================
	//===============================================================
}