/*
 * Created on 16/06/2013
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
public class TestHtmlExclamation extends TestCase {

	@Test
	public void testHtmlExclamation() {
		HtmlExclamation htmlItem;
		HtmlExclamation htmlItem2;

		/*
		 * Null parameters.
		 */

		htmlItem = new HtmlExclamation( null, null );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_EXCLAMATION, htmlItem.getType() );
		Assert.assertEquals( null, htmlItem.getText() );

		htmlItem2 = (HtmlExclamation)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_EXCLAMATION, htmlItem2.getType() );
		Assert.assertEquals( null, htmlItem2.getText() );

		/*
		 * Values.
		 */

		htmlItem = new HtmlExclamation( "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">", "DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"" );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_EXCLAMATION, htmlItem.getType() );
		Assert.assertEquals( "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">", htmlItem.getText() );

		htmlItem2 = (HtmlExclamation)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_EXCLAMATION, htmlItem2.getType() );
		Assert.assertEquals( "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">", htmlItem2.getText() );

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
