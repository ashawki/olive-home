package olivecommands.popup.actions;

import java.util.Iterator;

import olivecommands.OliveCommandsUtil;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class CopyResourcePathsAction implements IObjectActionDelegate {

	private Shell shell;
	protected ISelection selection;

	/**
	 * Constructor for Action1.
	 */
	public CopyResourcePathsAction() {
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
			String resourcesPaths = null;
			IStructuredSelection structuredSelection = (IStructuredSelection)this.getSelection();
			Iterator iterator = structuredSelection.iterator();
			while (iterator.hasNext()) {
				Object object1 = iterator.next();
				if (object1 instanceof IResource) {
					IResource resource1 = (IResource)object1;
					if (null == resourcesPaths) {
						resourcesPaths = resource1.getLocation().toOSString();
					} else {
						resourcesPaths += "\r\n";
						resourcesPaths += resource1.getLocation().toOSString();
					}
				}
			}
			if (null == resourcesPaths) {
				OliveCommandsUtil.beep(getShell());
			} else {
				OliveCommandsUtil.copyToClipboard(getShell(), resourcesPaths);
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
