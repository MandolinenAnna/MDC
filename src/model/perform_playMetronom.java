package model;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

import view.MDCPanel;

public class perform_playMetronom extends Thread {
	MDCPanel				pnMDC		= null;

	// Attribute
	private Clip			clip1		= null;
	private Clip			clip2		= null;
	private int				delay;

	public static boolean	isRunning	= false;

	// Konstruktor ohne Delay
	public perform_playMetronom(MDCPanel m_panel) {
		super();
		this.pnMDC = m_panel;

		// Midi laden
		this.loadMidi1();
		this.loadMidi2();
	}

	// Konstruktor mit Delay
	public perform_playMetronom(int delay, MDCPanel m_panel) {
		super();
		this.pnMDC = m_panel;

		// Pause setzen
		this.delay = delay;
		// Midi laden
		this.loadMidi1();
		this.loadMidi2();
	}

	// Midi laden
	private void loadMidi1() {
		try {
			clip1 = AudioSystem.getClip();
			clip1.open(AudioSystem.getAudioInputStream(new File("Click1.mid")));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
			this.clip1 = null;
		}
	}

	// Midi laden
	private void loadMidi2() {
		try {
			clip2 = AudioSystem.getClip();
			clip2.open(AudioSystem.getAudioInputStream(new File("Click2.mid")));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
			this.clip2 = null;
		}
	}

	// Thread ausführen
	@Override
	public void run() {
		int counter = 1;
		// Endlosschleife für Thread
		while (isRunning == true && this.clip1 != null && this.clip2 != null) {
			// Takt
			int takt = Integer.parseInt(pnMDC.cbTakt.getSelectedItem().toString().substring(0,1));
			int ct = Integer.parseInt(getSelectedButtonText(pnMDC.bg));
			
			if(counter != ct){
				clip2.stop();
				clip1.start();
			} else {
				clip1.stop();
				clip2.start();
			}
			counter++;
			if(counter > takt) counter = 1;


				try {

					sleep(this.delay);

				} catch (InterruptedException e) {
				}
				clip1.setFramePosition(0);
				clip2.setFramePosition(0);
				// Auf Anfangsposition des Midifiles
				
			}

		this.clip1.close();
		this.clip2.close();
		
	}

	public String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
	}
}
