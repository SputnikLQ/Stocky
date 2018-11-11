package listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClosingListener extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
}
