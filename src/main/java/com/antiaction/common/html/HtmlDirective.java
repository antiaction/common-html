/*
 * Created on 10/08/2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import java.util.HashMap;

public class HtmlDirective extends HtmlTagBase {

	public HtmlDirective(String tagName) {
		this.tagName = tagName;
		this.closed = true;
	}

	public Object clone() {
		HtmlDirective htmlDirective = new HtmlDirective( tagName );
		htmlDirective.tagText = tagText;
		htmlDirective.attributes = new HashMap( attributes );
		htmlDirective.closed = closed;
		return htmlDirective;
	}

	public int getType() {
		return HtmlItem.T_DIRECTIVE;
	}

	public void setText(String tagText) {
	}

	public Object setAttribute(String name, String value) {
		return attributes.put( name, value );
	}

	public Object removeAttribute(String name) {
		return attributes.remove( name );
	}

	public void setClosed(boolean closed) {
	}

}
