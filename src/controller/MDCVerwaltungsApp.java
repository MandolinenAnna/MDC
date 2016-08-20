package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.perform_playMetronom;
import view.MDCPanel;

public class MDCVerwaltungsApp extends JFrame {
	public static void main(String[] args) {
		new MDCVerwaltungsApp();
	}

	JPanel					pnMain	= new JPanel(new BorderLayout(5, 5));
	MDCPanel				pnMDC	= new MDCPanel();
	JPanel					pnNord	= new JPanel(new BorderLayout(5, 5));

	// InputStream in = null;
	perform_playMetronom	t1		= null;
	int a;

	public MDCVerwaltungsApp() {

		// -------------------------------------
		// EreignisListener registrieren
		// -------------------------------------
		pnMDC.btnMetronom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				perform_metronomAktiv();

			}

		});

		pnMDC.btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!perform_playMetronom.isRunning && pnMDC.btnMetronom.getText() == "Metronom AKTIV"
						&& pnMDC.cbmTakt.getSelectedItem() != "bitte wählen") {

					t1 = null;
					t1 = new perform_playMetronom(1000 * 60 / pnMDC.slTempo.getValue(), pnMDC);
//					
//					String taktart = (String) pnMDC.cbTakt.getSelectedItem();
					
//					String count = getSelectedButtonText(pnMDC.bg);
//					int ct = Integer.parseInt(count);
//					int ta=taktart.charAt(0);
					// t2= new perform_MetronomAkzent((1000 * 60 /
					// pnMDC.slTempo.getValue())+(1000 * 60 /
					// pnMDC.slTempo.getValue()*(ct-1)), pnMDC);
//					t2 = new perform_MetronomAkzent((1000 * 60 / pnMDC.slTempo.getValue()) * ta, pnMDC);

					perform_playMetronom.isRunning = true;
//					perform_MetronomAkzent.isRunning = true;

					t1.start();
//					synchronized (t2) {
//						try {
//							System.out.println("CT:" + ct);
//							if (ct > 1) {
//								t2.wait(1000 * 60 / pnMDC.slTempo.getValue() * (ct - 1));
//							}
//							t2.start();
//						} catch (InterruptedException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					}

				}

			}
		});

		pnMDC.btnStop.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		perform_playMetronom.isRunning = false;


	}});

	this.add(pnMain);

	pnMain.add(pnMDC,BorderLayout.CENTER);pnMain.add(pnNord,BorderLayout.NORTH);pnNord.add(new JLabel(new ImageIcon("logo.png")));

	this.setTitle("MDCVerwaltungsApp");

	this.setVisible(true);this.pack();this.setResizable(false);this.setDefaultCloseOperation(EXIT_ON_CLOSE);this.setAlwaysOnTop(true);

	// Icon ändern
	ImageIcon img = new ImageIcon("minilogo.png");this.setIconImage(img.getImage());;

	}

	protected void perform_metronomAktiv() {
		if (pnMDC.btnMetronom.getText() == "Metronom AKTIV") {
			pnMDC.btnMetronom.setText("Metronom PASSIV");
		} else {
			pnMDC.btnMetronom.setText("Metronom AKTIV");

		}
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
