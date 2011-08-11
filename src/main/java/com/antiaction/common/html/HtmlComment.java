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
public class HtmlComment extends HtmlItem {

	private String tagText;
	private String comment;

	public HtmlComment(String tagText, String comment) {
		this.tagText = tagText;
		this.comment = comment;
	}

	public Object clone() {
		return new HtmlComment( tagText, comment );
	}

	public int getType() {
		return HtmlItem.T_COMMENT;
	}

	public String getText() {
		return tagText;
	}

}
