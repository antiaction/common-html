/*
 * Created on 02-02-2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package com.antiaction.common.html;

import java.util.HashMap;

/**
 * @author Nicholas
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class HtmlTag extends HtmlTagBase {

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

	public int getType() {
		return HtmlItem.T_TAG;
	}

	public void setText(String tagText) {
		this.tagText = tagText;
	}

	public Object setAttribute(String name, String value) {
		return attributes.put( name, value );
	}

	public Object removeAttribute(String name) {
		return attributes.remove( name );
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

}
