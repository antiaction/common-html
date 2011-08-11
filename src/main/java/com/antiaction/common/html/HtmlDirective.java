/*
 * Created on 10/08/2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import java.util.HashMap;
import java.util.Map;

public class HtmlDirective extends HtmlTag {

	private String tagText;
	private String tagName;
	private Map attributes = new HashMap();
	private boolean closed = true;

	public HtmlDirective(String tagName) {
		super( tagName );
		//this.tagName = tagName;
	}

	public Object clone() {
		HtmlDirective htmlDirective = new HtmlDirective( tagName );
		htmlDirective.tagText = tagText;
		htmlDirective.attributes = new HashMap( attributes );
		htmlDirective.closed = closed;
		return htmlDirective;
	}

	public void attribute(String name, String value) {
		attributes.put( name, value );
	}

	public void setClosed(boolean closed) {
		//this.closed = closed;
	}

	public void setText(String tagText) {
		this.tagText = tagText;
	}

	public int getType() {
		return HtmlItem.T_DIRECTIVE;
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
