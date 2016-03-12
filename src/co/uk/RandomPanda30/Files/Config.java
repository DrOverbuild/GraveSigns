package co.uk.RandomPanda30.Files;

import java.util.ArrayList;

import co.uk.RandomPanda30.GraveSigns.GraveSigns;

public enum Config {

	ARG ("&6"),
	ERROR ("&c"),
	HEADER ("&f"),
	NORMAL ("&a"),
	TAG ("%H[%N" + GraveSigns.pdfFile.getName() + "%H]%N"),
	WARNING ("&4"),

	GRAVESIGNS_PLUGIN_ENABLED (true),

	COMMANDS_CLEAR_ENABLE (true),
	COMMANDS_CLEAR_SYNTAX("%E/gravesigns clear"),

	GRAVESIGN_REMOVED ("%TAG %NYour grave sign has despawned!"),
	GRAVESIGN_PLACED ("%TAG %NYour grave sign has been placed!"),
	GRAVESIGNS_CLEARED ("%TAG %NAll the gravesigns have been cleared off the server"),

	GRAVESIGN_GRAVESIGNS ("%TAG"),

	CRITICAL_COMMANDNULL ("%TAG %EThis command does not exist!"),
	CRITICAL_CMDNOTENABLED ("%TAG %EThe command '%Acmd%E' has not been enabled"),
	CRITICAL_INCREATIVE ("%TAG %EYour grave sign could not be placed because you are in creative mode"),
	CRITICAL_INFLYING ("%TAG %EYour grave sign could not be placed because you are flying"),
	CRITICAL_INLIQUID ("%TAG %EYour grave sign could not be placed because you are in a liquid"),
	CRITICAL_NOPERM ("%TAG %EError, do you not have permission to do this!"),
	CRITICAL_PLAYERONLY ("%TAG %EThis command can only be used by a player in-game"),

	SIGN_DESPAWN_ENABLE (true),
	SIGN_DESPAWN_TIMER (120),

	@SuppressWarnings("serial")
	SIGN_TEXT (new ArrayList<String>() {
		{
			add("%EChange this");
			add("%Ein the");
			add("%Econfig.");
		}
	});

	public Object value;

	Config (Object value) {
		this.value = value;
	}

}
