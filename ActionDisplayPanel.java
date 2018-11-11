package gui;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class ActionDisplayPanel extends JPanel
{
	private Dimension defDim = new Dimension(400, 400);
	private Dimension headerDim = new Dimension(400, 50);
	private Dimension elemPanelDim = new Dimension(400, 350);
	private JPanel headerPanel = new JPanel();
	private JPanel elementPanel = new JPanel();
	private JLabel tendency = new JLabel("Tendency | ");
	private JLabel object = new JLabel("Object | ");
	private JLabel diffToYes = new JLabel("% to Yes. | ");
	private JLabel value = new JLabel("Value (EUR) | ");
	private JLabel status = new JLabel("Status");
	// elements to be displayed
	private Element eurusd;
	private Element gold;
	private SwingWorker<Void, String> worker;
  
	public ActionDisplayPanel() throws IOException
	{
		// basic settings
		setMinimumSize(this.defDim);
		setPreferredSize(this.defDim);
		setLayout(new BorderLayout());
		// components settings
		headerPanel.setPreferredSize(headerDim);
		headerPanel.setMinimumSize(headerDim);
		elementPanel.setPreferredSize(elemPanelDim);
		elementPanel.setMinimumSize(elemPanelDim);
		// add components
		headerPanel.add(this.tendency);
		headerPanel.add(this.object);
		headerPanel.add(this.diffToYes);
		headerPanel.add(this.value);
		headerPanel.add(this.status);
		// element settings
		eurusd = new Element("EUR/USD", "https://kursdaten.teleboerse.de/teleboerse/kurse_einzelkurs_uebersicht.htn?i=2079559",
				"<td class=\"ri\"><span", ",", '>', 1, 5 );
		gold = new Element("Gold", "https://kursdaten.teleboerse.de/teleboerse/kurse_einzelkurs_uebersicht.htn?i=6039682",
				"<td class=\"ri\"><span class=\"bigger\">", ",", '>', 1, 3);
		elementPanel.add(eurusd);
		elementPanel.add(gold);
		add(headerPanel, BorderLayout.NORTH);
		add(elementPanel, BorderLayout.CENTER);
		keepDataUpdated();
		
	}
	
	@SuppressWarnings("unchecked")
	public void keepDataUpdated() {
		this.worker = new SwingWorker() {	

			@Override
			protected Void doInBackground() throws Exception {
				while(true) {
					Thread.sleep(60000);
					eurusd.evaluateData();
					gold.evaluateData();
					System.out.println(1);
				}
				
			}
		};
		worker.execute();
	}
}
