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
public class HTMLExclamation extends HTMLItem {

	private String tagText;
	private String exclamation;

	public HTMLExclamation(String tagText, String exclamation) {
		this.tagText = tagText;
		this.exclamation = exclamation;
	}

	public Object clone() {
		return new HTMLExclamation( tagText, exclamation );
	}

	public int getType() {
		return HTMLItem.T_EXCLAMATION;
	}

	public String getText() {
		return tagText;
	}

}
