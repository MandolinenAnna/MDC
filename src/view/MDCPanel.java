package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Takt_DB;

public class MDCPanel extends JPanel {

	JPanel								pnlinks		= new JPanel(new BorderLayout(5, 5));
	JPanel								pnAuswahl	= new JPanel(new GridLayout(7, 1, 5, 12));
	JPanel								pnTakt		= new JPanel(new GridLayout(1, 3, 5, 5));
	JPanel								pnCenter	= new JPanel(new BorderLayout(5, 5));
	JPanel								pnRhythmus	= new JPanel(new GridLayout(7, 1, 5, 5));
	JPanel								pnPlayer	= new JPanel(new BorderLayout(5, 5));
	JPanel								pnPlayStop	= new JPanel(new GridLayout(2, 1, 0, 0));
	JPanel								pnRadio		= new JPanel(new FlowLayout());
	JPanel								pnTempo		= new JPanel(new BorderLayout());

	public JButton						btnMetronom	= new JButton("Metronom AKTIV");
	public JButton						btnPlay		= new JButton("Play");
	public JButton						btnStop		= new JButton("Stop");

	public ButtonGroup					bg			= new ButtonGroup();
	public JRadioButton					rb1			= new JRadioButton("1");
	public JRadioButton					rb2			= new JRadioButton("2");
	public JRadioButton					rb3			= new JRadioButton("3");
	public JRadioButton					rb4			= new JRadioButton("4");
	public JRadioButton					rb5			= new JRadioButton("5");
	public JRadioButton					rb6			= new JRadioButton("6");

	public JSlider						slTempo		= new JSlider(JSlider.HORIZONTAL, 0, 240, 120);
	public JLabel						lbTempo		= new JLabel(
			"Tempo:                                                                           " + slTempo.getValue()
					+ " bpm");
	JSlider								slVolume	= new JSlider(JSlider.VERTICAL, 0, 100, 50);

	DefaultComboBoxModel<String>		cbmTrommel1	= new DefaultComboBoxModel<>();
	JComboBox<String>					cbTrommel1	= new JComboBox<>(cbmTrommel1);
	DefaultComboBoxModel<String>		cbmTrommel2	= new DefaultComboBoxModel<>();
	JComboBox<String>					cbTrommel2	= new JComboBox<>(cbmTrommel2);
	DefaultComboBoxModel<String>		cbmTrommel3	= new DefaultComboBoxModel<>();
	JComboBox<String>					cbTrommel3	= new JComboBox<>(cbmTrommel3);
	DefaultComboBoxModel<String>		cbmBecken1	= new DefaultComboBoxModel<>();
	JComboBox<String>					cbBecken1	= new JComboBox<>(cbmBecken1);
	DefaultComboBoxModel<String>		cbmBecken2	= new DefaultComboBoxModel<>();
	JComboBox<String>					cbBecken2	= new JComboBox<>(cbmBecken2);
	DefaultComboBoxModel<String>		cbmBecken3	= new DefaultComboBoxModel<>();
	JComboBox<String>					cbBecken3	= new JComboBox<>(cbmBecken3);
	DefaultComboBoxModel<String>		cbmEffekt	= new DefaultComboBoxModel<>();
	JComboBox<String>					cbEffekt	= new JComboBox<>(cbmEffekt);
	public DefaultComboBoxModel<String>	cbmTakt		= new DefaultComboBoxModel<String>();
	public JComboBox<String>			cbTakt		= new JComboBox<>(cbmTakt);

	Takt_DB								combo		= new Takt_DB();
	public int							wert;

	public MDCPanel() {

		combo.selectComboInhalt(cbmTakt, "takt", "Taktart");
		combo.selectComboInhalt(cbmTrommel1, "sound", "Komponente");
		combo.selectComboInhalt(cbmTrommel2, "sound", "Komponente");
		combo.selectComboInhalt(cbmTrommel3, "sound", "Komponente");
		combo.selectComboInhalt(cbmBecken1, "sound", "Komponente");
		combo.selectComboInhalt(cbmBecken2, "sound", "Komponente");
		combo.selectComboInhalt(cbmBecken3, "sound", "Komponente");
		combo.selectComboInhalt(cbmEffekt, "sound", "Komponente");

		this.setLayout(new BorderLayout(5, 5));

		this.add(pnlinks, BorderLayout.WEST);
		pnlinks.add(btnMetronom, BorderLayout.NORTH);
		pnlinks.add(pnAuswahl, BorderLayout.CENTER);
		pnAuswahl.add(cbTrommel1);
		pnAuswahl.add(cbTrommel2);
		pnAuswahl.add(cbTrommel3);
		pnAuswahl.add(cbBecken1);
		pnAuswahl.add(cbBecken2);
		pnAuswahl.add(cbBecken3);
		pnAuswahl.add(cbEffekt);
		this.add(pnCenter, BorderLayout.CENTER);
		pnCenter.add(pnTakt, BorderLayout.NORTH);
		pnTakt.add(pnTempo);
		pnTempo.add(lbTempo, BorderLayout.NORTH);
		pnTempo.add(slTempo, BorderLayout.CENTER);

		pnTakt.add(cbTakt);

		rb1.setBounds(3, 3, 3, 3);
		pnTakt.add(pnRadio);
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		bg.add(rb4);
		bg.add(rb5);
		bg.add(rb6);

		pnRadio.add(rb1);
		pnRadio.add(rb2);
		pnRadio.add(rb3);
		pnRadio.add(rb4);
		pnRadio.add(rb5);
		pnRadio.add(rb6);
		pnCenter.add(pnRhythmus, BorderLayout.CENTER);

		cbTakt.addActionListener(cbTaktAl);

		slTempo.addChangeListener(slTempoCl);
		slVolume.addChangeListener(slVolumeCl);

		// Knöpfe

		// for(int i=1;i<=7;i++){
		// for(int j=1;j<=16;j++){
		// JButton btnKnopf = new JButton(""+i+""+j);
		// pnRhythmus.add(new JButton(""+i+""+j));
		//
		// }
		// }
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 15; j++) {
				JButton tabButton = new JButton(String.valueOf(j));
				tabButton.setBackground(Color.BLACK);
				pnRhythmus.add(tabButton);
				tabButton.setText(null);
			}
		}
//
//		String[] btn1 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" };
//		String[] btn2 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" };
//		String[] btn3 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" };
//		String[] btn4 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" };
//		String[] btn5 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" };
//		String[] btn6 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" };
//		String[] btn7 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" };
//
//		for (String buttonName : btn1) {
//			JButton tabButton = new JButton(buttonName);
//			tabButton.setBackground(Color.BLACK);
//			pnRhythmus.add(tabButton);
//			tabButton.setText(null);
//		}
//
//		for (String buttonName : btn2) {
//			JButton tabButton = new JButton(buttonName);
//			tabButton.setBackground(Color.BLACK);
//			pnRhythmus.add(tabButton);
//			tabButton.setText(null);
//
//		}
//		for (String buttonName : btn3) {
//			JButton tabButton = new JButton(buttonName);
//			tabButton.setBackground(Color.BLACK);
//			pnRhythmus.add(tabButton);
//			tabButton.setText(null);
//
//		}
//		for (String buttonName : btn4) {
//			JButton tabButton = new JButton(buttonName);
//			tabButton.setBackground(Color.BLACK);
//			pnRhythmus.add(tabButton);
//			tabButton.setText(null);
//
//		}
//		for (String buttonName : btn5) {
//			JButton tabButton = new JButton(buttonName);
//			tabButton.setBackground(Color.BLACK);
//			pnRhythmus.add(tabButton);
//			tabButton.setText(null);
//
//		}
//		for (String buttonName : btn6) {
//			JButton tabButton = new JButton(buttonName);
//			tabButton.setBackground(Color.BLACK);
//			pnRhythmus.add(tabButton);
//			tabButton.setText(null);
//
//		}
//		for (String buttonName : btn7) {
//			JButton tabButton = new JButton(buttonName);
//			tabButton.setBackground(Color.BLACK);
//			pnRhythmus.add(tabButton);
//			tabButton.setText(null);
//
//		}

		this.add(pnPlayer, BorderLayout.EAST);
		pnPlayer.add(pnPlayStop, BorderLayout.CENTER);
		pnPlayStop.add(btnPlay);
		pnPlayStop.add(btnStop);
		pnPlayer.add(slVolume, BorderLayout.SOUTH);

		// Border
		// slTempo.setBorder(new TitledBorder("Tempo: " + slTempo.getValue() + "
		// bpm"));
		slVolume.setBorder(new TitledBorder("Vol"));
		pnlinks.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnAuswahl.setBorder(new EmptyBorder(27, 10, 10, 10));
		pnPlayer.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnRhythmus.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnTakt.setBorder(new EmptyBorder(5, 10, 5, 10));
		cbTakt.setBorder(new TitledBorder("Takt"));
		pnRadio.setBorder(new TitledBorder("Zählweise"));

		// Farben
		pnTakt.setBackground(Color.orange);
		pnRhythmus.setBackground(Color.orange);
		pnAuswahl.setBackground(Color.orange);
		pnlinks.setBackground(Color.black);
		pnPlayer.setBackground(Color.black);

	}

	public ActionListener	cbTaktAl	= new ActionListener() {														// add
											// change
											@Override
											public void actionPerformed(ActionEvent e) {

												String takt = (String) cbTakt.getSelectedItem();						// get

												switch (takt) {															// check
												// match
												case "4/4":
													rb1.setSelected(true);
													rb3.setEnabled(true);
													rb4.setEnabled(true);
													rb5.setEnabled(false);
													rb6.setEnabled(false);

													// System.out.println("4/4
													// selected");
													break;

												case "2/4":
													rb1.setSelected(true);
													rb3.setEnabled(false);
													rb4.setEnabled(false);
													rb5.setEnabled(false);
													rb6.setEnabled(false);
													// System.out.println("2/4
													// selected");
													break;

												case "3/4":
													rb1.setSelected(true);
													rb3.setEnabled(true);
													rb4.setEnabled(false);
													rb5.setEnabled(false);
													rb6.setEnabled(false);
													// System.out.println("3/4
													// selected");
													break;

												case "6/8":
													rb1.setSelected(true);
													rb3.setEnabled(true);
													rb4.setEnabled(true);
													rb5.setEnabled(true);
													rb6.setEnabled(true);
													// System.out.println("6/8
													// selected");
													break;
												default:

													System.out.println("No match selected");
													break;
												}
											}
										};

	ChangeListener			slVolumeCl	= new ChangeListener() {

											@Override
											public void stateChanged(ChangeEvent arg0) {

												float value = slVolume.getValue() / 100.0f;
												try {

													getVolumeControl().setValue(value);
													// you can put a click play
													// code
													// here to have nice
													// feedback when
													// moving slider
												} catch (Exception ex) {
													System.out.println(ex);
												}
												// and this is for getting the
												// value
												try {
													System.out.println(value);
													slVolume.setValue((int) (getVolumeControl().getValue() * 100.0f));
												} catch (Exception e) {
													System.out.println(e);
												}

											}
										};

	private FloatControl getVolumeControl() throws Exception {
		try {
			Mixer.Info mixers[] = AudioSystem.getMixerInfo();
			for (Mixer.Info mixerInfo : mixers) {
				Mixer mixer = AudioSystem.getMixer(mixerInfo);
				mixer.open();

				// we check only target type lines, because we are looking for
				// "SPEAKER target port"
				for (Line.Info info : mixer.getTargetLineInfo()) {
					if (info.toString().contains("SPEAKER")) {
						Line line = mixer.getLine(info);
						try {

							line.open();
						} catch (IllegalArgumentException iae) {
						}
						return (FloatControl) line.getControl(FloatControl.Type.VOLUME);
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("problem creating volume control object:" + ex);
			throw ex;
		}
		throw new Exception("unknown problem creating volume control object");
	}

	ChangeListener slTempoCl = new ChangeListener() {

		@Override
		public void stateChanged(ChangeEvent arg0) {
			wert = slTempo.getValue();
			lbTempo.setText("Tempo:                                                                           " + wert
					+ " bpm");

		}
	};

}
