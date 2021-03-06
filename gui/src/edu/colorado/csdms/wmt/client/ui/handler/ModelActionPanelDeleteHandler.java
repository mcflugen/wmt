/**
 * <License>
 */
package edu.colorado.csdms.wmt.client.ui.handler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;

import edu.colorado.csdms.wmt.client.Constants;
import edu.colorado.csdms.wmt.client.control.DataManager;
import edu.colorado.csdms.wmt.client.ui.widgets.DroplistDialogBox;

/**
 * Handles click on the "Delete" button in the ModelActionPanel. It presents an
 * instance of {@link DroplistDialogBox} with a "Delete" button. Events are sent
 * to {@link DeleteModelHandler} (on clicking "OK" button or hitting
 * <code>Enter</code> key) and {@link DialogCancelHandler} (on clicking "Cancel"
 * or hitting <code>Esc</code> key).
 * 
 * @author Mark Piper (mark.piper@colorado.edu)
 */
public class ModelActionPanelDeleteHandler implements ClickHandler {

  private DataManager data;
  private DroplistDialogBox deleteDialog;

  /**
   * Creates a new instance of {@link ModelActionPanelDeleteHandler}.
   * 
   * @param data the DataManager object for the WMT session
   */
  public ModelActionPanelDeleteHandler(DataManager data) {
    this.data = data;
  }

  @Override
  public void onClick(ClickEvent event) {

    // Hide the MoreActionsMenu.
    data.getPerspective().getActionButtonPanel().getMoreMenu().hide();

    deleteDialog = new DroplistDialogBox();
    deleteDialog.setText("Delete Model...");
    deleteDialog.getChoicePanel().getOkButton().setHTML(
        Constants.FA_DELETE + "Delete");

    // Populate the ModelDroplist with the available models on the server.
    for (int i = 0; i < data.modelNameList.size(); i++) {
      deleteDialog.getDroplistPanel().getDroplist().addItem(
          data.modelNameList.get(i));
    }

    // Define handlers.
    final DeleteModelHandler deleteHandler =
        new DeleteModelHandler(data, deleteDialog);
    final DialogCancelHandler cancelHandler =
        new DialogCancelHandler(deleteDialog);

    // Apply handlers to OK and Cancel buttons.
    deleteDialog.getChoicePanel().getOkButton().addClickHandler(deleteHandler);
    deleteDialog.getChoicePanel().getCancelButton().addClickHandler(
        cancelHandler);

    // Also apply handlers to "Enter" and "Esc" keys.
    deleteDialog.addDomHandler(
        new ModalKeyHandler(deleteHandler, cancelHandler), KeyDownEvent
            .getType());

    deleteDialog.center();

    // Give the droplist focus.
    deleteDialog.getDroplistPanel().getDroplist().setFocus(true);
  }
}
