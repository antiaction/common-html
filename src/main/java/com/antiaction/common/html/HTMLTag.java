/*
 * Created on 02-02-2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.antiaction.common.html;

import java.util.Map;
import java.util.HashMap;

/**
 * @author Nicholas
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class HTMLTag extends HTMLItem {

	private String tagText;
	private String tagName;
	private Map attributes = new HashMap();
	private boolean closed = false;

	public HTMLTag(String tagName) {
		this.tagName = tagName;
	}

	public void attribute(String name, String value) {
		attributes.put( name, value );
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public void setText(String tagText) {
		this.tagText = tagText;
	}

	public int getType() {
		return HTMLItem.T_TAG;
	}

	public String getText() {
		return tagText;
	}

	public String getTagname() {
		return tagName;
	}

	public String getAttribute(String attrib) {
		return (String)attributes.get( attrib );
	}

	public Map getAttributes() {
		return attributes;
	}

	public boolean getClosed() {
		return closed;
	}

}
