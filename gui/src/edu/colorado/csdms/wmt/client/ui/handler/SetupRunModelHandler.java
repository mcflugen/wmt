/**
 * <License>
 */
package edu.colorado.csdms.wmt.client.ui.handler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.Window;

import edu.colorado.csdms.wmt.client.control.DataManager;
import edu.colorado.csdms.wmt.client.ui.widgets.RunDialogBox;

/**
 * Displays the {@link RunDialogBox}, which allows a user to login to a 
 * computer to run their saved model.
 * 
 * @author Mark Piper (mark.piper@colorado.edu)
 */
public class SetupRunModelHandler implements ClickHandler {

  private DataManager data;
  private RunDialogBox runDialog;
  
  /**
   * Displays the {@link RunDialogBox} to start a model run.
   * 
   * @param data the DataManager object for the WMT session
   */
  public SetupRunModelHandler(DataManager data) {
    this.data = data;
  }

  @Override
  public void onClick(ClickEvent event) {

    if (!data.modelIsSaved()) {
      String msg =
          "The model must be saved before it can be run.";
      Window.alert(msg);
      return;
    }
    
    runDialog = new RunDialogBox();

    // TODO This should be configured. Can't desensitize ListBox elements.
    String hosts[] = {"beach.colorado.edu", "janus.colorado.edu", "localhost"};
    for (int i = 0; i < hosts.length; i++) {
      runDialog.getHostPanel().getDroplist().addItem(hosts[i]);
    }

    // Define handlers.
    final RunModelHandler runHandler = new RunModelHandler(data, runDialog);
    final DialogCancelHandler cancelHandler =
        new DialogCancelHandler(runDialog);

    // Apply handlers to OK and Cancel buttons.
    runDialog.getChoicePanel().getOkButton().addClickHandler(runHandler);
    runDialog.getChoicePanel().getCancelButton()
        .addClickHandler(cancelHandler);

    // Also apply handlers to "Enter" and "Esc" keys.
    runDialog.addDomHandler(new ModalKeyHandler(runHandler, cancelHandler),
        KeyDownEvent.getType());

    runDialog.center();
    runDialog.getHostPanel().getDroplist().setFocus(true);
  }
}
