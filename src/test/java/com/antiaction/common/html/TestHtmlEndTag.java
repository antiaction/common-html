/*
 * Created on 21/04/2014
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestHtmlEndTag extends TestCase {

	@Test
	public void testHtmlEndTag() {
		HtmlEndTag htmlItem;
		HtmlEndTag htmlItem2;

		/*
		 * Null parameters.
		 */

		htmlItem = new HtmlEndTag( null, null );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_ENDTAG, htmlItem.getType() );
		Assert.assertEquals( null, htmlItem.getText() );
		Assert.assertEquals( null, htmlItem.getTagname() );

		htmlItem2 = (HtmlEndTag)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_ENDTAG, htmlItem2.getType() );
		Assert.assertEquals( null, htmlItem2.getText() );
		Assert.assertEquals( null, htmlItem.getTagname() );

		/*
		 * Values.
		 */

		htmlItem = new HtmlEndTag( "</html>", "html" );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_ENDTAG, htmlItem.getType() );
		Assert.assertEquals( "</html>", htmlItem.getText() );
		Assert.assertEquals( "html", htmlItem.getTagname() );

		htmlItem2 = (HtmlEndTag)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_ENDTAG, htmlItem2.getType() );
		Assert.assertEquals( "</html>", htmlItem2.getText() );
		Assert.assertEquals( "html", htmlItem.getTagname() );

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
