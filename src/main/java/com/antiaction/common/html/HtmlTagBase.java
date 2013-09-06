/*
 * Created on 14/08/2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import java.util.HashMap;
import java.util.Map;

public abstract class HtmlTagBase extends HtmlItem {

	protected String tagText;

	protected String tagName;
	
	protected Map attributes = new HashMap();
	
	protected boolean closed = false;

	void attribute(String name, String value) {
		attributes.put( name, value );
	}

	public String getText() {
		return tagText;
	}

	public String getTagname() {
		return tagName;
	}

	public Map getAttributes() {
		return attributes;
	}

	public String getAttribute(String name) {
		return (String)attributes.get( name );
	}

	public boolean getClosed() {
		return closed;
	}

}
