package olivecommands.popup.actions;

import java.io.IOException;
import java.util.Iterator;

import olivecommands.OliveCommandsMessages;
import olivecommands.OliveCommandsUtil;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.statushandlers.StatusManager;

public class OpenByWindowsExplorerAction implements IObjectActionDelegate {

	private Shell shell;
	protected ISelection selection;

	/**
	 * Constructor for OpenByWindowsExplorerAction.
	 */
	public OpenByWindowsExplorerAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
//		MessageDialog.openInformation(shell, "OliveCommands", "Open by Explorer was executed.");
		if ((null != this.getSelection())
			&& (!(this.getSelection().isEmpty()))
			&& (this.getSelection() instanceof IStructuredSelection)) {
			IStructuredSelection structuredSelection = (IStructuredSelection)this.getSelection();
			boolean doOpenByWindowsExplorerAction = true;
			if (structuredSelection.size() > 1) {
				doOpenByWindowsExplorerAction = MessageDialog.open(MessageDialog.CONFIRM, getShell(), OliveCommandsMessages.OliveCommands_message_title, OliveCommandsMessages.OliveCommands_openbywindowsexploreraction_confirm_message, SWT.NONE);
			}
			if (doOpenByWindowsExplorerAction) {
				boolean errorOccured = false;
				Iterator iterator = structuredSelection.iterator();
				while (iterator.hasNext()) {
					Object object1 = iterator.next();
					if (object1 instanceof IResource) {
						IResource resource1 = (IResource)object1;
						try {
							Runtime.getRuntime().exec(new String[] { "EXPLORER.EXE", resource1.getLocation().toOSString() });
						} catch (IOException e) {
							errorOccured = true;
							//OliveCommandsUtil.statusManager_handleError(e, StatusManager.LOG | StatusManager.BLOCK);
							OliveCommandsUtil.statusManager_handleError(e, StatusManager.LOG);
						}
					}
				}
				if (errorOccured) {
					OliveCommandsUtil.beep(getShell());
				}
			}
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	/**
	 * @return the shell
	 */
	public Shell getShell() {
		return shell;
	}

	/**
	 * @param shell the shell to set
	 */
	public void setShell(Shell shell) {
		this.shell = shell;
	}
	
	/**
	 * @return the selection
	 */
	public ISelection getSelection() {
		return selection;
	}

	/**
	 * @param selection the selection to set
	 */
	public void setSelection(ISelection selection) {
		this.selection = selection;
	}

}
