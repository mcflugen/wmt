/**
 * <License>
 */
package edu.colorado.csdms.wmt.client.ui;

import java.util.Iterator;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.colorado.csdms.wmt.client.control.DataManager;
import edu.colorado.csdms.wmt.client.ui.handler.ModelActionPanelDeleteHandler;
import edu.colorado.csdms.wmt.client.ui.handler.ModelActionPanelHelpHandler;
import edu.colorado.csdms.wmt.client.ui.handler.ModelActionPanelSaveHandler;
import edu.colorado.csdms.wmt.client.ui.handler.ModelActionPanelStatusHandler;

/**
 * Encapsulates a menu of secondary actions on models -- "Save as", "Delete",
 * "Run status", etc.
 * 
 * @author Mark Piper (mark.piper@colorado.edu)
 */
public class MoreActionsMenu extends PopupPanel {

  @SuppressWarnings("unused")
  private DataManager data;
  private LabelsMenu labelsMenu;
  
  /**
   * Makes a new {@link MoreActionsMenu}.
   * 
   * @param data the DataManager object for the WMT session
   */
  public MoreActionsMenu(DataManager data) {

    super(true); // autohide
    this.getElement().getStyle().setCursor(Cursor.POINTER); // use pointer
    this.data = data;
    this.setStyleName("wmt-PopupPanel");
    
    // A VerticalPanel for the menu items. (PopupPanels have only one child.)
    VerticalPanel menu = new VerticalPanel();
    this.add(menu);

    // Save as
    HTML saveAsButton =
        new HTML(DataManager.FA_SAVE + "Save model as...");
    saveAsButton.setTitle("Save model with a new name");
    saveAsButton.addClickHandler(new ModelActionPanelSaveHandler(data, true));    
    menu.add(saveAsButton);

    // Manage labels
    final HTML labelsButton =
        new HTML(DataManager.FA_TAGS + "Manage labels...");
    labelsButton.setTitle("Manage labels set on models");
    menu.add(labelsButton);
    labelsMenu = new LabelsMenu(data);
    labelsButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        labelsMenu.setPopupPositionAndShow(new PositionCallback() {
          final Integer x = labelsButton.getElement().getAbsoluteRight();
          final Integer y = labelsButton.getAbsoluteTop();
          @Override
          public void setPosition(int offsetWidth, int offsetHeight) {
            labelsMenu.setPopupPosition(x, y);
          }
        });
      }
    });
    
    // Delete
    HTML deleteButton = new HTML(DataManager.FA_DELETE + "Delete model...");
    deleteButton.setTitle("Delete model");
    deleteButton.addClickHandler(new ModelActionPanelDeleteHandler(data));
    menu.add(deleteButton);

    // Run status
    HTML statusButton = new HTML(DataManager.FA_STATUS + "View run status...");
    statusButton.setTitle("Status of model run");
    statusButton.addClickHandler(new ModelActionPanelStatusHandler(data));    
    menu.add(statusButton);
    
    // Help
    HTML helpButton = new HTML(DataManager.FA_HELP + "Help");
    helpButton.setTitle("Help on using WMT");
    helpButton.addClickHandler(new ModelActionPanelHelpHandler(data));        
    menu.add(helpButton);
    
    // Apply a style to each button.
    Iterator<Widget> iter = menu.iterator();
    while (iter.hasNext()) {
      HTML button = (HTML) iter.next();
      button.setStyleName("wmt-PopupPanelItem");
    }    
  }
}
