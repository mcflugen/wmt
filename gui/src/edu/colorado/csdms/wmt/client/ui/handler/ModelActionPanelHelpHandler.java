/**
 * <License>
 */
package edu.colorado.csdms.wmt.client.ui.handler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;

import edu.colorado.csdms.wmt.client.control.DataManager;

/**
 * Handles click on "Help" button in the ModelActionPanel.
 * 
 * @author Mark Piper (mark.piper@colorado.edu)
 */
public class ModelActionPanelHelpHandler implements ClickHandler {
  
  private DataManager data;

  /**
   * Creates a new instance of {@link ModelActionPanelHelpHandler}.
   * 
   * @param data the DataManager object for the WMT session
   */
  public ModelActionPanelHelpHandler(DataManager data) {
    this.data = data;
  }
  
  @Override
  public void onClick(ClickEvent event) {
    data.getPerspective().getActionButtonPanel().getMoreMenu().hide();    
    Window.alert("Help");
  }
}
