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
public class HtmlTag extends HtmlItem {

	private String tagText;
	private String tagName;
	private Map attributes = new HashMap();
	private boolean closed = false;

	public HtmlTag(String tagName) {
		this.tagName = tagName;
	}

	public Object clone() {
		HtmlTag htmlTag = new HtmlTag( tagName );
		htmlTag.tagText = tagText;
		htmlTag.attributes = new HashMap( attributes );
		htmlTag.closed = closed;
		return htmlTag;
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
		return HtmlItem.T_TAG;
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

	public Object setAttribute(String name, String value) {
		return attributes.put( name, value );
	}

	public Object removeAttribute(String name) {
		return attributes.remove( name );
	}

	public boolean getClosed() {
		return closed;
	}

}
