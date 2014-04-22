/*
 * Created on 21/04/2014
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.antiaction.common.html;

import junit.framework.TestCase;

import org.junit.Assert;

public class TestHtmlTag extends TestCase {

	public void testHtmlTag() {
		HtmlTag htmlItem;
		HtmlTag htmlItem2;

		/*
		 * Null parameters.
		 */

		htmlItem = new HtmlTag( null );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_TAG, htmlItem.getType() );
		Assert.assertEquals( null, htmlItem.getText() );
		Assert.assertEquals( null, htmlItem.getTagname() );
		Assert.assertEquals( false, htmlItem.getClosed() );
		Assert.assertEquals( 0, htmlItem.getAttributes().size() );

		htmlItem2 = (HtmlTag)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_TAG, htmlItem2.getType() );
		Assert.assertEquals( null, htmlItem2.getText() );
		Assert.assertEquals( null, htmlItem.getTagname() );
		Assert.assertEquals( false, htmlItem.getClosed() );
		Assert.assertEquals( 0, htmlItem.getAttributes().size() );

		/*
		 * Values.
		 */

		htmlItem = new HtmlTag( "<html lang=\"en\">" );
		Assert.assertNotNull( htmlItem );
		Assert.assertEquals( HtmlItem.T_TAG, htmlItem.getType() );
		Assert.assertEquals( null, htmlItem.getText() );
		Assert.assertEquals( "<html lang=\"en\">", htmlItem.getTagname() );
		Assert.assertEquals( false, htmlItem.getClosed() );
		Assert.assertEquals( 0, htmlItem.getAttributes().size() );

		htmlItem.attribute( "lang", "dk");

		Assert.assertEquals( 1, htmlItem.getAttributes().size() );
		Assert.assertEquals( "dk", htmlItem.getAttribute( "lang" ) );

		Assert.assertEquals( "dk", htmlItem.setAttribute( "lang", "en") );

		Assert.assertEquals( 1, htmlItem.getAttributes().size() );
		Assert.assertEquals( "en", htmlItem.getAttribute( "lang" ) );

		htmlItem2 = (HtmlTag)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_TAG, htmlItem2.getType() );
		Assert.assertEquals( null, htmlItem2.getText() );
		Assert.assertEquals( "<html lang=\"en\">", htmlItem2.getTagname() );
		Assert.assertEquals( false, htmlItem2.getClosed() );
		Assert.assertEquals( 1, htmlItem2.getAttributes().size() );
		Assert.assertEquals( "en", htmlItem2.getAttribute( "lang" ) );

		htmlItem2.setClosed( true );

		Assert.assertEquals( true, htmlItem2.getClosed() );

		Assert.assertEquals( "en", htmlItem.removeAttribute( "lang" ) );
		Assert.assertEquals( 0, htmlItem.getAttributes().size() );
		Assert.assertEquals( null, htmlItem.removeAttribute( "lang" ) );
		Assert.assertEquals( 0, htmlItem.getAttributes().size() );
		htmlItem.setClosed( true );

		htmlItem2 = (HtmlTag)htmlItem.clone();
		Assert.assertNotNull( htmlItem2 );
		Assert.assertEquals( HtmlItem.T_TAG, htmlItem2.getType() );
		Assert.assertEquals( null, htmlItem2.getText() );
		Assert.assertEquals( "<html lang=\"en\">", htmlItem2.getTagname() );
		Assert.assertEquals( true, htmlItem2.getClosed() );
		Assert.assertEquals( 0, htmlItem2.getAttributes().size() );

		htmlItem2.setText( "text" );
		Assert.assertEquals( "text", htmlItem2.getText() );
	}

}
