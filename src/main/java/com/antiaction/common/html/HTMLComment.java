/*
 * Created on 02-02-2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.antiaction.common.html;

/**
 * @author Nicholas
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class HTMLComment extends HTMLItem {

	private String tagText;
	private String comment;

	public HTMLComment(String tagText, String comment) {
		this.tagText = tagText;
		this.comment = comment;
	}

	public Object clone() {
		return new HTMLComment( tagText, comment );
	}

	public int getType() {
		return HTMLItem.T_COMMENT;
	}

	public String getText() {
		return tagText;
	}

}
