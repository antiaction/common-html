/*
 * Created on 02-02-2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.antiaction.common.html;

import java.util.Map;

/**
 * @author Nicholas
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class HTMLItem {

	public static int T_TEXT = 0;
	public static int T_PROCESSING = 1;
	public static int T_EXCLAMATION = 2;
	public static int T_COMMENT = 3;
	public static int T_ENDTAG = 4;
	public static int T_TAG = 5;

	public abstract int getType();

	public abstract String getText();

	public String getTagname() {
		throw new UnsupportedOperationException( "Unsupported method." );
	}

	public String getAttribute(String attrib) {
		throw new UnsupportedOperationException( "Unsupported method." );
	}

	public Map getAttributes() {
		throw new UnsupportedOperationException( "Unsupported method." );
	}

	public boolean getClosed() {
		throw new UnsupportedOperationException( "Unsupported method." );
	}

}
