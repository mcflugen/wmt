/**
 * <License>
 */
package edu.colorado.csdms.wmt.client.data;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;

/**
 * A GWT JavaScript overlay (JSO) type that describes the JSON returned on a
 * HTTP GET call to <a
 * href="http://csdms.colorado.edu/wmt/api/tag/model/query">tag/model/query</a>
 * or to <a href="http://csdms.colorado.edu/wmt/api/tag/model/">tag/model/</a>.
 * 
 * @see <a
 *      href="http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsOverlay.html">http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsOverlay.html</a>
 * @author Mark Piper (mark.piper@colorado.edu)
 */
public class LabelQueryJSO extends JavaScriptObject {

  // Overlay types always have protected, zero-arg constructors.
  protected LabelQueryJSO() {
  }

  /**
   * Gets a JsArrayInteger of model or label ids. This is a JSNI method.
   */
  public final native JsArrayInteger getIds() /*-{
		return this;
  }-*/;
}
