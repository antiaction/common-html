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
public abstract class HtmlItem {

	public static final int T_TEXT = 0;
	public static final int T_PROCESSING = 1;
	public static final int T_EXCLAMATION = 2;
	public static final int T_COMMENT = 3;
	public static final int T_ENDTAG = 4;
	public static final int T_TAG = 5;
	public static final int T_DIRECTIVE = 6;

	public abstract Object clone();

	public abstract int getType();

	public abstract String getText();

	public String getTagname() {
		throw new UnsupportedOperationException( "Unsupported method." );
	}

	public Map getAttributes() {
		throw new UnsupportedOperationException( "Unsupported method." );
	}

	public String getAttribute(String name) {
		throw new UnsupportedOperationException( "Unsupported method." );
	}

	public Object setAttribute(String name, String value) {
		throw new UnsupportedOperationException( "Unsupported method." );
	}

	public Object removeAttribute(String name) {
		throw new UnsupportedOperationException( "Unsupported method." );
	}

	public boolean getClosed() {
		throw new UnsupportedOperationException( "Unsupported method." );
	}

}
