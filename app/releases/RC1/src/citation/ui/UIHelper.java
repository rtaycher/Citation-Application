package citation.ui;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.util.StringUtilities;


public class UIHelper {

	public static int stringToColor(String colorName)
	{
        if (colorName.equals("RED")) return Color.RED;
        if (colorName.equals("ALICEBLUE")) return Color.ALICEBLUE;
        if (colorName.equals("ANTIQUEWHITE")) return Color.ANTIQUEWHITE;
        if (colorName.equals("AQUA")) return Color.AQUA;
        if (colorName.equals("AQUAMARINE")) return Color.AQUAMARINE;
        if (colorName.equals("AZURE")) return Color.AZURE;
        if (colorName.equals("BEIGE")) return Color.BEIGE;
        if (colorName.equals("BISQUE")) return Color.BISQUE;
        if (colorName.equals("BLACK")) return Color.BLACK;
        if (colorName.equals("BLANCHEDALMOND")) return Color.BLANCHEDALMOND;
        if (colorName.equals("BLUE")) return Color.BLUE;
        if (colorName.equals("BLUEVIOLET")) return Color.BLUEVIOLET;
        if (colorName.equals("BROWN")) return Color.BROWN;
        if (colorName.equals("BURLYWOOD")) return Color.BURLYWOOD;
        if (colorName.equals("CADETBLUE")) return Color.CADETBLUE;
        if (colorName.equals("CHARTREUSE")) return Color.CHARTREUSE;
        if (colorName.equals("CHOCOLATE")) return Color.CHOCOLATE;
        if (colorName.equals("CORAL")) return Color.CORAL;
        if (colorName.equals("CORNFLOWERBLUE")) return Color.CORNFLOWERBLUE;
        if (colorName.equals("CORNSILK")) return Color.CORNSILK;
        if (colorName.equals("CRIMSON")) return Color.CRIMSON;
        if (colorName.equals("CYAN")) return Color.CYAN;
        if (colorName.equals("DARKBLUE")) return Color.DARKBLUE;
        if (colorName.equals("DARKCYAN")) return Color.DARKCYAN;
        if (colorName.equals("DARKGOLDENROD")) return Color.DARKGOLDENROD;
        if (colorName.equals("DARKGRAY")) return Color.DARKGRAY;
        if (colorName.equals("DARKGREEN")) return Color.DARKGREEN;
        if (colorName.equals("DARKKHAKI")) return Color.DARKKHAKI;
        if (colorName.equals("DARKMAGENTA")) return Color.DARKMAGENTA;
        if (colorName.equals("DARKOLIVEGREEN")) return Color.DARKOLIVEGREEN;
        if (colorName.equals("DARKORANGE")) return Color.DARKORANGE;
        if (colorName.equals("DARKORCHID")) return Color.DARKORCHID;
        if (colorName.equals("DARKRED")) return Color.DARKRED;
        if (colorName.equals("DARKSALMON")) return Color.DARKSALMON;
        if (colorName.equals("DARKSEAGREEN")) return Color.DARKSEAGREEN;
        if (colorName.equals("DARKSLATEBLUE")) return Color.DARKSLATEBLUE;
        if (colorName.equals("DARKSLATEGRAY")) return Color.DARKSLATEGRAY;
        if (colorName.equals("DARKTURQUOISE")) return Color.DARKTURQUOISE;
        if (colorName.equals("DARKVIOLET")) return Color.DARKVIOLET;
        if (colorName.equals("DEEPPINK")) return Color.DEEPPINK;
        if (colorName.equals("DEEPSKYBLUE")) return Color.DEEPSKYBLUE;
        if (colorName.equals("DIMGRAY")) return Color.DIMGRAY;
        if (colorName.equals("DODGERBLUE")) return Color.DODGERBLUE;
        if (colorName.equals("FIREBRICK")) return Color.FIREBRICK;
        if (colorName.equals("FLORALWHITE")) return Color.FLORALWHITE;
        if (colorName.equals("FORESTGREEN")) return Color.FORESTGREEN;
        if (colorName.equals("FUCHSIA")) return Color.FUCHSIA;
        if (colorName.equals("GAINSBORO")) return Color.GAINSBORO;
        if (colorName.equals("GHOSTWHITE")) return Color.GHOSTWHITE;
        if (colorName.equals("GOLD")) return Color.GOLD;
        if (colorName.equals("GOLDENROD")) return Color.GOLDENROD;
        if (colorName.equals("GRAY")) return Color.GRAY;
        if (colorName.equals("GREEN")) return Color.GREEN;
        if (colorName.equals("GREENYELLOW")) return Color.GREENYELLOW;
        if (colorName.equals("HONEYDEW")) return Color.HONEYDEW;
        if (colorName.equals("HOTPINK")) return Color.HOTPINK;
        if (colorName.equals("INDIANRED")) return Color.INDIANRED;
        if (colorName.equals("INDIGO")) return Color.INDIGO;
        if (colorName.equals("IVORY")) return Color.IVORY;
        if (colorName.equals("KHAKI")) return Color.KHAKI;
        if (colorName.equals("LAVENDER")) return Color.LAVENDER;
        if (colorName.equals("LAVENDERBLUSH")) return Color.LAVENDERBLUSH;
        if (colorName.equals("LAWNGREEN")) return Color.LAWNGREEN;
        if (colorName.equals("LEMONCHIFFON")) return Color.LEMONCHIFFON;
        if (colorName.equals("LIGHTBLUE")) return Color.LIGHTBLUE;
        if (colorName.equals("LIGHTCORAL")) return Color.LIGHTCORAL;
        if (colorName.equals("LIGHTCYAN")) return Color.LIGHTCYAN;
        if (colorName.equals("LIGHTGOLDENRODYELLOW")) return Color.LIGHTGOLDENRODYELLOW;
        if (colorName.equals("LIGHTGREEN")) return Color.LIGHTGREEN;
        if (colorName.equals("LIGHTGREY")) return Color.LIGHTGREY;
        if (colorName.equals("LIGHTPINK")) return Color.LIGHTPINK;
        if (colorName.equals("LIGHTSALMON")) return Color.LIGHTSALMON;
        if (colorName.equals("LIGHTSEAGREEN")) return Color.LIGHTSEAGREEN;
        if (colorName.equals("LIGHTSKYBLUE")) return Color.LIGHTSKYBLUE;
        if (colorName.equals("LIGHTSLATEGRAY")) return Color.LIGHTSLATEGRAY;
        if (colorName.equals("LIGHTSTEELBLUE")) return Color.LIGHTSTEELBLUE;
        if (colorName.equals("LIGHTYELLOW")) return Color.LIGHTYELLOW;
        if (colorName.equals("LIME")) return Color.LIME;
        if (colorName.equals("LIMEGREEN")) return Color.LIMEGREEN;
        if (colorName.equals("LINEN")) return Color.LINEN;
        if (colorName.equals("MAGENTA")) return Color.MAGENTA;
        if (colorName.equals("MAROON")) return Color.MAROON;
        if (colorName.equals("MEDIUMAQUAMARINE")) return Color.MEDIUMAQUAMARINE;
        if (colorName.equals("MEDIUMBLUE")) return Color.MEDIUMBLUE;
        if (colorName.equals("MEDIUMORCHID")) return Color.MEDIUMORCHID;
        if (colorName.equals("MEDIUMPURPLE")) return Color.MEDIUMPURPLE;
        if (colorName.equals("MEDIUMSEAGREEN")) return Color.MEDIUMSEAGREEN;
        if (colorName.equals("MEDIUMSLATEBLUE")) return Color.MEDIUMSLATEBLUE;
        if (colorName.equals("MEDIUMSPRINGGREEN")) return Color.MEDIUMSPRINGGREEN;
        if (colorName.equals("MEDIUMTURQUOISE")) return Color.MEDIUMTURQUOISE;
        if (colorName.equals("MEDIUMVIOLETRED")) return Color.MEDIUMVIOLETRED;
        if (colorName.equals("MIDNIGHTBLUE")) return Color.MIDNIGHTBLUE;
        if (colorName.equals("MINTCREAM")) return Color.MINTCREAM;
        if (colorName.equals("MISTYROSE")) return Color.MISTYROSE;
        if (colorName.equals("MOCCASIN")) return Color.MOCCASIN;
        if (colorName.equals("NAVAJOWHITE")) return Color.NAVAJOWHITE;
        if (colorName.equals("NAVY")) return Color.NAVY;
        if (colorName.equals("OLDLACE")) return Color.OLDLACE;
        if (colorName.equals("OLIVE")) return Color.OLIVE;
        if (colorName.equals("OLIVEDRAB")) return Color.OLIVEDRAB;
        if (colorName.equals("ORANGE")) return Color.ORANGE;
        if (colorName.equals("ORANGERED")) return Color.ORANGERED;
        if (colorName.equals("ORCHID")) return Color.ORCHID;
        if (colorName.equals("PALEGOLDENROD")) return Color.PALEGOLDENROD;
        if (colorName.equals("PALEGREEN")) return Color.PALEGREEN;
        if (colorName.equals("PALETURQUOISE")) return Color.PALETURQUOISE;
        if (colorName.equals("PALEVIOLETRED")) return Color.PALEVIOLETRED;
        if (colorName.equals("PAPAYAWHIP")) return Color.PAPAYAWHIP;
        if (colorName.equals("PEACHPUFF")) return Color.PEACHPUFF;
        if (colorName.equals("PERU")) return Color.PERU;
        if (colorName.equals("PINK")) return Color.PINK;
        if (colorName.equals("PLUM")) return Color.PLUM;
        if (colorName.equals("POWDERBLUE")) return Color.POWDERBLUE;
        if (colorName.equals("PURPLE")) return Color.PURPLE;
        if (colorName.equals("RED")) return Color.RED;
        if (colorName.equals("ROSYBROWN")) return Color.ROSYBROWN;
        if (colorName.equals("ROYALBLUE")) return Color.ROYALBLUE;
        if (colorName.equals("SADDLEBROWN")) return Color.SADDLEBROWN;
        if (colorName.equals("SALMON")) return Color.SALMON;
        if (colorName.equals("SANDYBROWN")) return Color.SANDYBROWN;
        if (colorName.equals("SEAGREEN")) return Color.SEAGREEN;
        if (colorName.equals("SEASHELL")) return Color.SEASHELL;
        if (colorName.equals("SIENNA")) return Color.SIENNA;
        if (colorName.equals("SILVER")) return Color.SILVER;
        if (colorName.equals("SKYBLUE")) return Color.SKYBLUE;
        if (colorName.equals("SLATEBLUE")) return Color.SLATEBLUE;
        if (colorName.equals("SLATEGRAY")) return Color.SLATEGRAY;
        if (colorName.equals("SNOW")) return Color.SNOW;
        if (colorName.equals("SPRINGGREEN")) return Color.SPRINGGREEN;
        if (colorName.equals("STEELBLUE")) return Color.STEELBLUE;
        if (colorName.equals("TAN")) return Color.TAN;
        if (colorName.equals("TEAL")) return Color.TEAL;
        if (colorName.equals("THISTLE")) return Color.THISTLE;
        if (colorName.equals("TOMATO")) return Color.TOMATO;
        if (colorName.equals("TURQUOISE")) return Color.TURQUOISE;
        if (colorName.equals("VIOLET")) return Color.VIOLET;
        if (colorName.equals("WHEAT")) return Color.WHEAT;
        if (colorName.equals("WHITE")) return Color.WHITE;
        if (colorName.equals("WHITESMOKE")) return Color.WHITESMOKE;
        if (colorName.equals("YELLOW")) return Color.YELLOW;
        if (colorName.equals("YELLOWGREEN")) return Color.YELLOWGREEN;
        
        return Color.BLACK;
	}
	
	private static long stringToStyle(String _style)
	{
		if (_style.equals("EDITABLE")) return Field.EDITABLE;
		if (_style.equals("BOTTOM")) return Field.FIELD_BOTTOM;
		if (_style.equals("HCENTER")) return Field.FIELD_HCENTER;
		if (_style.equals("LEFT")) return Field.FIELD_LEFT;
		if (_style.equals("RIGHT")) return Field.FIELD_RIGHT;
		if (_style.equals("TOP")) return Field.FIELD_TOP;
		if (_style.equals("VCENTER")) return Field.FIELD_VCENTER;
		if (_style.equals("FOCUSABLE")) return Field.FOCUSABLE;
		if (_style.equals("HIGHLIGHT_FOCUS")) return Field.HIGHLIGHT_FOCUS;
		if (_style.equals("HIGHLIGHT_SELECT")) return Field.HIGHLIGHT_SELECT;
		if (_style.equals("READONLY")) return Field.READONLY;
		if (_style.equals("USE_ALL_HEIGHT")) return Field.USE_ALL_HEIGHT;
		if (_style.equals("USE_ALL_WIDTH")) return Field.USE_ALL_WIDTH;
		
		return Field.FIELD_LEFT;
	}
	
	public static long stringListToStyle(String _styleList)
	{
		long retValue = Field.FIELD_LEFT;
		String[] styles = StringUtilities.stringToKeywords(_styleList);
		for (int i = 0; i < styles.length; i++)
		{
			retValue |= UIHelper.stringToStyle(styles[i]);
		}
		return retValue;
	}
	
	private static long stringToFontStyle(String _style)
	{
		// ANTIALIAS not supported on all builds
		// if (_style.equals("ANTIALIAS_STANDARD")) return Font.ANTIALIAS_STANDARD;
		// if (_style.equals("ANTIALIAS_LOW_RES")) return Font.ANTIALIAS_LOW_RES;

		if (_style.equals("ANTIALIAS_NONE")) return Font.ANTIALIAS_NONE;
		if (_style.equals("ANTIALIAS_SUBPIXEL")) return Font.ANTIALIAS_SUBPIXEL;
		if (_style.equals("APPLICATION")) return Font.APPLICATION;
		if (_style.equals("COLORED_OUTLINE_EFFECT")) return Font.COLORED_OUTLINE_EFFECT;
		if (_style.equals("DOTTED_UNDERLINED")) return Font.DOTTED_UNDERLINED;
		if (_style.equals("DROP_SHADOW_RIGHT_EFFECT")) return Font.DROP_SHADOW_RIGHT_EFFECT;
		if (_style.equals("EMBOSSED_EFFECT")) return Font.EMBOSSED_EFFECT;
		if (_style.equals("ENGRAVED_EFFECT")) return Font.ENGRAVED_EFFECT;
		if (_style.equals("ITALIC")) return Font.ITALIC;
		if (_style.equals("PLAIN")) return Font.PLAIN;
		if (_style.equals("UNDERLINED")) return Font.UNDERLINED;
		
		return Font.PLAIN;
	}

	public static int stringListToFontStyle(String _styleList)
	{
		int retValue = Font.PLAIN;
		String[] styles = StringUtilities.stringToKeywords(_styleList);
		for (int i = 0; i < styles.length; i++)
		{
			retValue |= UIHelper.stringToFontStyle(styles[i]);
		}
		return retValue;
	}
	
	public static Font getFont(int _size, int _style)
	{
        FontFamily fontFamily[] = FontFamily.getFontFamilies(); 
        return fontFamily[1].getFont(FontFamily.CBTF_FONT, _size).derive(_style); 
	}
}
