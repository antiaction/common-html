/*
 * Created on 16/06/2013
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestHtmlComment extends TestCase {

	public void testHtmlComment() {
		HtmlComment htmlItem;
		HtmlComment htmlItem2;

		htmlItem = new HtmlComment( null, null );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_COMMENT, htmlItem.getType() );
		Assert.assertEquals( null, htmlItem.getText() );

		htmlItem2 = (HtmlComment)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_COMMENT, htmlItem2.getType() );
		Assert.assertEquals( null, htmlItem2.getText() );

		try {
			htmlItem.getTagname();
			Assert.fail( "Exception expected!" );
		}
		catch (UnsupportedOperationException e) {
		}
		try {
			htmlItem.getAttributes();
			Assert.fail( "Exception expected!" );
		}
		catch (UnsupportedOperationException e) {
		}
		try {
			htmlItem.getAttribute( "name" );
			Assert.fail( "Exception expected!" );
		}
		catch (UnsupportedOperationException e) {
		}
		try {
			htmlItem.setAttribute( "name", "value" );
			Assert.fail( "Exception expected!" );
		}
		catch (UnsupportedOperationException e) {
		}
		try {
			htmlItem.removeAttribute( "name" );
			Assert.fail( "Exception expected!" );
		}
		catch (UnsupportedOperationException e) {
		}
		try {
			htmlItem.getClosed();
			Assert.fail( "Exception expected!" );
		}
		catch (UnsupportedOperationException e) {
		}
	}

}
