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
public class TestHtmlProcessing extends TestCase {

	@Test
	public void testHtmlProcessing() {
		HtmlProcessing htmlItem;
		HtmlProcessing htmlItem2;

		/*
		 * Null parameters.
		 */

		htmlItem = new HtmlProcessing( null, null );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_PROCESSING, htmlItem.getType() );
		Assert.assertEquals( null, htmlItem.getText() );

		htmlItem2 = (HtmlProcessing)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_PROCESSING, htmlItem2.getType() );
		Assert.assertEquals( null, htmlItem2.getText() );

		/*
		 * Values.
		 */

		htmlItem = new HtmlProcessing( "<?Monkeys?>", "Monkeys" );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_PROCESSING, htmlItem.getType() );
		Assert.assertEquals( "<?Monkeys?>", htmlItem.getText() );

		htmlItem2 = (HtmlProcessing)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_PROCESSING, htmlItem2.getType() );
		Assert.assertEquals( "<?Monkeys?>", htmlItem2.getText() );

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
