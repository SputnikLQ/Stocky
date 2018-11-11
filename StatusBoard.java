package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StatusBoard extends JPanel
{
	private final Dimension defDim = new Dimension(400, 100);
	private final JTextArea tArea = new JTextArea(4, 32);
	private final JScrollPane scroll = new JScrollPane(tArea);
  
	public StatusBoard()
	{
		setMinimumSize(this.defDim);
		setPreferredSize(this.defDim);
    
		tArea.setEditable(false);
		tArea.setLineWrap(true);
		tArea.setBackground(Color.LIGHT_GRAY);
    
		add(scroll);
	}
}
