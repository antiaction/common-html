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
public class HtmlEndTag extends HtmlItem {

	private String tagText;
	private String tagName;

	public HtmlEndTag(String tagText, String tagName) {
		this.tagText = tagText;
		this.tagName = tagName;
	}

	public Object clone() {
		return new HtmlEndTag( tagText, tagName );
	}

	public int getType() {
		return HtmlItem.T_ENDTAG;
	}

	public String getText() {
		return tagText;
	}

	public String getTagname() {
		return tagName;
	}

}
