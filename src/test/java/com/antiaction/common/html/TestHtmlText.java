/*
 * Created on 15/06/2013
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
public class TestHtmlText extends TestCase {

	@Test
	public void testHtmlText() {
		HtmlText htmlItem;
		HtmlText htmlItem2;

		/*
		 * Null parameters.
		 */

		htmlItem = new HtmlText( null );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_TEXT, htmlItem.getType() );
		Assert.assertEquals( null, htmlItem.getText() );

		htmlItem2 = (HtmlText)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_TEXT, htmlItem2.getType() );
		Assert.assertEquals( null, htmlItem2.getText() );

		/*
		 * Values.
		 */

		htmlItem = new HtmlText( "Hello world" );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_TEXT, htmlItem.getType() );
		Assert.assertEquals( "Hello world", htmlItem.getText() );

		htmlItem2 = (HtmlText)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_TEXT, htmlItem2.getType() );
		Assert.assertEquals( "Hello world", htmlItem2.getText() );

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
