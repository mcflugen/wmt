/**
 * <License>
 */
package edu.colorado.csdms.wmt.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.colorado.csdms.wmt.client.Constants;
import edu.colorado.csdms.wmt.client.control.DataManager;
import edu.colorado.csdms.wmt.client.ui.handler.ComponentDeleteCommand;
import edu.colorado.csdms.wmt.client.ui.handler.ComponentGetInformationCommand;
import edu.colorado.csdms.wmt.client.ui.handler.ComponentShowParametersCommand;

/**
 * A {@link PopupPanel} menu that defines actions that can be performed on a
 * component. Shown after a component has been selected in a
 * {@link ComponentCell}.
 * 
 * @author Mark Piper (mark.piper@colorado.edu)
 */
public class ComponentActionMenu extends PopupPanel {

  private DataManager data;
  private ComponentCell cell;
  
  /**
   * Makes a new {@link ComponentActionMenu}, showing actions that can be
   * performed on a component in a {@link ComponentCell}.
   * 
   * @param data the DataManager object for the WMT session
   * @param cell the {@link ComponentCell} this menu depends on
   */
  public ComponentActionMenu(DataManager data, ComponentCell cell) {

    super(true); // autohide
    this.data = data;
    this.cell = cell;
    this.setStyleName("wmt-PopupPanel");

    // A VerticalPanel for the menu items. (PopupPanels have only one child.)
    VerticalPanel menu = new VerticalPanel();
    this.add(menu);

    HTML showParameters = new HTML(Constants.FA_WRENCH + "Show parameters");
    showParameters.addClickHandler(new ComponentActionHandler("show"));
    showParameters.setStyleName("wmt-PopupPanelItem");
    showParameters.setTitle(Constants.COMPONENT_SHOW);
    menu.add(showParameters);

    HTML getInformation = new HTML(Constants.FA_HELP + "Get information");
    getInformation.addClickHandler(new ComponentActionHandler("info"));
    getInformation.setStyleName("wmt-PopupPanelItem");
    getInformation.setTitle(Constants.COMPONENT_INFO);
    menu.add(getInformation);

    HTML separator = new HTML();
    separator.setStyleName("wmt-PopupPanelSeparator");
    menu.add(separator);

    HTML deleteComponent = new HTML(Constants.FA_CLOBBER + "Delete");
    deleteComponent.addClickHandler(new ComponentActionHandler("delete"));
    deleteComponent.setStyleName("wmt-PopupPanelItem");
    deleteComponent.setTitle(Constants.COMPONENT_DELETE);
    menu.add(deleteComponent);
  }
  
  /**
   * Handles a click on a menu item in the {@link ComponentActionMenu}.
   * <p>
   * <b>Note:</b> This class wraps {@link ComponentShowParametersCommand},
   * {@link ComponentGetInformationCommand} and {@link ComponentDeleteCommand}.
   * It might be helpful to port the code from these classes to this handler.
   */
  public class ComponentActionHandler implements ClickHandler {

    private String type;
    
    /**
     * Makes a new {@link ComponentActionHandler}.
     * 
     * @param type the type of action, "show", "info" or "delete"
     */
    public ComponentActionHandler(String type) {
      this.type = type;
    }
    
    @Override
    public void onClick(ClickEvent event) {
      ComponentActionMenu.this.hide();
      data.showWaitCursor();      
      Command cmd = null;
      if (type.equalsIgnoreCase("show")) {
        cmd = new ComponentShowParametersCommand(data, cell);
      } else if (type.equalsIgnoreCase("info")) {
        cmd = new ComponentGetInformationCommand(data, cell);
      } else if (type.equalsIgnoreCase("delete")) {
        cmd = new ComponentDeleteCommand(data, cell);
      }
      cmd.execute();
      data.showDefaultCursor();
    }
  }
}
