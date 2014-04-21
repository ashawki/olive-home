package olivecommands;

import org.eclipse.osgi.util.NLS;

public final class OliveCommandsMessages extends NLS {

	private static final String BUNDLE_NAME= "olivecommands.OliveCommandsMessages";//$NON-NLS-1$

	private OliveCommandsMessages() {
		// Do not instantiate
	}

    static {
        NLS.initializeMessages(BUNDLE_NAME, OliveCommandsMessages.class);
    }

	public static String OliveCommands_message_title;
	public static String OliveCommands_openbywindowsexploreraction_confirm_message;
}
