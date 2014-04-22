/*
 * Created on 16/06/2013
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import junit.framework.TestCase;

import org.junit.Assert;

public class TestHtmlComment extends TestCase {

	public void testHtmlComment() {
		HtmlComment htmlItem;
		HtmlComment htmlItem2;

		/*
		 * Null parameters.
		 */

		htmlItem = new HtmlComment( null, null );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_COMMENT, htmlItem.getType() );
		Assert.assertEquals( null, htmlItem.getText() );

		htmlItem2 = (HtmlComment)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_COMMENT, htmlItem2.getType() );
		Assert.assertEquals( null, htmlItem2.getText() );

		/*
		 * Values.
		 */

		htmlItem = new HtmlComment( "<!-- #include virtual=\"inc_menu.html\" -->", " #include virtual=\"inc_menu.html\" " );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_COMMENT, htmlItem.getType() );
		Assert.assertEquals( "<!-- #include virtual=\"inc_menu.html\" -->", htmlItem.getText() );

		htmlItem2 = (HtmlComment)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_COMMENT, htmlItem2.getType() );
		Assert.assertEquals( "<!-- #include virtual=\"inc_menu.html\" -->", htmlItem2.getText() );

		/*
		 * UnsupportedOperationException.
		 */

		try {
			htmlItem.setText( "test" );
			Assert.fail( "Exception expected!" );
		}
		catch (UnsupportedOperationException e) {
		}
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
		try {
			htmlItem.setClosed( true );
			Assert.fail( "Exception expected!" );
		}
		catch (UnsupportedOperationException e) {
		}
	}

}
