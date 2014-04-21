package olivecommands;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.statushandlers.StatusManager;


public class OliveCommandsUtil {
	
	public static void copyToClipboard(Shell shell, String value) {
		Clipboard clipboard = null;
		try {
			clipboard = new Clipboard(shell.getDisplay());
			clipboard.setContents(new Object[] { value }, new Transfer[] { TextTransfer.getInstance() });
		} finally {
			if (clipboard != null) {
				clipboard.dispose();
			}
		}
	}
	
	public static void beep(Shell shell) {
		shell.getDisplay().beep();
	}
	
	public static void statusManager_handleError(Throwable exception, int style) {
		statusManager_handleError(null, exception, style);	
	}
	
	public static void statusManager_handleError(String message, Throwable exception, int style) {
		StatusManager.getManager().handle(new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.ERROR, message, exception), style);	
	}
}
