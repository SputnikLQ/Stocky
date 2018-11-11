package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import listeners.ClosingListener;

public class MainFrame extends JFrame 
{
	private Dimension defDim = new Dimension(400, 650);
	private StatusPanel panelTop = new StatusPanel();
	private ActionDisplayPanel panelMid;
	private StatusBoard panelBot = new StatusBoard();
	private Border loweredetched = BorderFactory.createEtchedBorder(1);
	private TitledBorder borderTitledTop = BorderFactory.createTitledBorder(this.loweredetched, "Statusbar");
	private TitledBorder borderTitledMid = BorderFactory.createTitledBorder(this.loweredetched, "Display");
	private TitledBorder borderTitledBot = BorderFactory.createTitledBorder(this.loweredetched, "StatusBoard");
  
	public MainFrame() throws IOException
	{
		// basic settings
		setTitle("Stocky by Sputnik");
		setMinimumSize(this.defDim);
		setPreferredSize(this.defDim);
		addWindowListener(new ClosingListener());
		setLayout(new BorderLayout(4, 4));
		panelMid = new ActionDisplayPanel();
		// component settings
		this.panelTop.setBorder(this.borderTitledTop);
		this.panelMid.setBorder(this.borderTitledMid);
		this.panelBot.setBorder(this.borderTitledBot);
		// add components
		add(this.panelTop, "North");
		add(this.panelMid, "Center");
		add(this.panelBot, "South");
    
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
}
