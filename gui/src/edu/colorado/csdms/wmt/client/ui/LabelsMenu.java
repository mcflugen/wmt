/**
 * <License>
 */
package edu.colorado.csdms.wmt.client.ui;

import java.util.Iterator;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.colorado.csdms.wmt.client.control.DataManager;

/**
 * Encapsulates a scrollable list of labels used to tag and classify models.
 * This menu is modeled on the "Labels" menu in Gmail.
 * 
 * @author Mark Piper (mark.piper@colorado.edu)
 */
public class LabelsMenu extends PopupPanel {

  @SuppressWarnings("unused")
  private DataManager data;
  
  /**
   * Makes a new {@link LabelsMenu}.
   * 
   * @param data the DataManager object for the WMT session
   */
  public LabelsMenu(DataManager data) {

    super(true); // autohide
    this.getElement().getStyle().setCursor(Cursor.POINTER); // use pointer
    this.data = data;
    this.setStyleName("wmt-MoreActionsMenu"); // TODO update name

    // A VerticalPanel for the menu items. (PopupPanels have only one child.)
    VerticalPanel menu = new VerticalPanel();
    this.add(menu);
    
    // All labels are listed on the labelPanel, which sits on a ScrollPanel.
    VerticalPanel labelPanel = new VerticalPanel();
    ScrollPanel scroller = new ScrollPanel(labelPanel);
    menu.add(scroller);
    
    // XXX Temporary labels.
    HTML label0 = new HTML("low avulsion");
    labelPanel.add(label0);
    HTML label1 = new HTML("Ganges");
    labelPanel.add(label1);
    HTML label2 = new HTML("thesis");
    labelPanel.add(label2);
    HTML label3 = new HTML("AGU talk");
    labelPanel.add(label3);
    HTML label4 = new HTML("2013");
    labelPanel.add(label4);
    
    // Apply a style to each label.
    Iterator<Widget> iter = labelPanel.iterator();
    while (iter.hasNext()) {
      HTML button = (HTML) iter.next();
      button.setStyleName("wmt-MoreActionsMenuItem"); // TODO update name
    }
    
    // These items are always visible on the bottom of the menu.
    HTML separator1 = new HTML("");
    separator1.setStyleName("wmt-MoreActionsMenuSeparator");
    HTML addNewHtml = new HTML("Add new label");
    HTML separator2 = new HTML("");
    separator2.setStyleName("wmt-MoreActionsMenuSeparator");
    HTML manageHtml = new HTML("Manage labels");
    menu.add(separator1);
    menu.add(addNewHtml);
    menu.add(separator2);
    menu.add(manageHtml);
  }
}
