package olivecommands.popup.actions;

import java.io.IOException;

import olivecommands.OliveCommandsUtil;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.statushandlers.StatusManager;

public class SelectInWindowsExplorerAction implements IObjectActionDelegate {

	private Shell shell;
	protected ISelection selection;

	/**
	 * Constructor for Action1.
	 */
	public SelectInWindowsExplorerAction() {
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
		if ((null != this.getSelection())
			&& (!(this.getSelection().isEmpty()))
			&& (this.getSelection() instanceof IStructuredSelection)) {
			IStructuredSelection structuredSelection = (IStructuredSelection)this.getSelection();
			if (structuredSelection.size() > 1) {
				OliveCommandsUtil.beep(getShell());
			} else {
				Object object1 = structuredSelection.getFirstElement();
				if (object1 instanceof IResource) {
					IResource resource1 = (IResource)object1;
					try {
						Runtime.getRuntime().exec(new String[] { "EXPLORER.EXE", "/select," + resource1.getLocation().toOSString() });
					} catch (IOException e) {
						OliveCommandsUtil.statusManager_handleError(e, StatusManager.LOG);
					}
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
