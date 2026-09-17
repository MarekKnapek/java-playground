package mk.gui;
public class gui_starter implements java.lang.Runnable
{
	@Override
	public void run()
	{
		boolean has_decorations;
		frame fr;
		has_decorations = javax.swing.JFrame.isDefaultLookAndFeelDecorated();
		javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);
		fr = new frame();
		javax.swing.JFrame.setDefaultLookAndFeelDecorated(has_decorations);
		fr.pack();
		fr.setLocationRelativeTo(null);
		fr.setVisible(true);
	}
}
