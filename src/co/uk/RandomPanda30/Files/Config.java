package co.uk.RandomPanda30.Files;

import java.util.ArrayList;

import co.uk.RandomPanda30.GraveSigns.GraveSigns;

public enum Config {

	ARG ("&6"),
	ERROR ("&c"),
	HEADER ("&6"),
	NORMAL ("&a"),
	TAG ("%A[%N" + GraveSigns.pdfFile.getName() + "%A]%N"),
	WARNING ("&4"),

	GRAVESIGNS_ENABLED (true),
	CRITICAL_INLIQUID ("%EYour grave sign could not be placed because you are in a liquid"),
	CRITICAL_INFLYING ("%EYour grave sign could not be placed because you are flying"),
	CRITICAL_INCREATIVE ("%EYour grave sign could not be placed because you are in creative mode"),

	GRAVESIGN_REMOVED ("%NYour grave sign has despawned!"),
	GRAVESIGN_PLACED ("%NYour grave sign has been placed!"),

	SIGN_DESPAWN_TIMER (120),

	@SuppressWarnings("serial")
	SIGN_TEXT (new ArrayList<String>() {
		{
			add("%EChange this in the ");
			add("%Econfig.");
		}
	});

	public Object value;

	Config (Object value) {
		this.value = value;
	}

}
