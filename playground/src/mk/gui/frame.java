package mk.gui;
public class frame extends javax.swing.JFrame implements
	java.awt.event.WindowListener,
	java.awt.event.ActionListener
{
	private javax.swing.JComboBox m_combo_box;
	private javax.swing.JButton m_new_button;
	private javax.swing.JButton m_exit_button;
	private javax.swing.UIManager.LookAndFeelInfo[] m_laf_infos;
	private boolean m_want_ocean;
	public frame()
	{
		super();
		m_want_ocean = false;
		init_components();
		init_window();
	}
	private void init_combo_box()
	{
		javax.swing.LookAndFeel current_laf;
		java.lang.String current_class_name;
		int idx;
		int n;
		java.lang.String[] names;
		int i;
		javax.swing.DefaultComboBoxModel model;
		current_laf = javax.swing.UIManager.getLookAndFeel();
		current_class_name = current_laf.getClass().getName();
		m_laf_infos = javax.swing.UIManager.getInstalledLookAndFeels();
		idx = 0;
		n = m_laf_infos.length;
		names = new java.lang.String[n];
		for(i = 0; i != n; ++i)
		{
			names[i] = m_laf_infos[i].getName();
			if(m_laf_infos[i].getClassName().equals(current_class_name))
			{
				idx = i;
			}
		}
		model = new javax.swing.DefaultComboBoxModel(names);
		m_combo_box = new javax.swing.JComboBox(model);
		m_combo_box.setSelectedIndex(idx);
	}
	private void init_button_new()
	{
		m_new_button = new javax.swing.JButton();
		m_new_button.setText("new");
		m_new_button.addActionListener(this);
	}
	private void init_button_exit()
	{
		m_exit_button = new javax.swing.JButton();
		m_exit_button.setText("close");
		m_exit_button.addActionListener(this);
	}
	private void init_components()
	{
		init_combo_box();
		init_button_new();
		init_button_exit();
	}
	/*@Override*/
	public java.awt.Dimension getMinimumSize()
	{
		java.awt.Dimension min_size;
		min_size = new java.awt.Dimension();
		min_size.setSize(250, 250);
		return min_size;
	}
	private void add_components()
	{
		int gap;
		java.awt.FlowLayout flow_layout;
		int flow_gap_h;
		int flow_gap_v;
		java.awt.Container pane;
		javax.swing.JPanel panel_x;
		javax.swing.BoxLayout layout_x;
		javax.swing.JPanel panel_y;
		javax.swing.BoxLayout layout_y;
		gap = 5;
		flow_layout = new java.awt.FlowLayout();
		flow_gap_h = flow_layout.getHgap();
		flow_gap_v = flow_layout.getVgap();
		gap = mk.utils.max(gap, flow_gap_h);
		gap = mk.utils.max(gap, flow_gap_v);
		m_combo_box.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		m_combo_box.setMaximumSize(m_combo_box.getPreferredSize());
		m_new_button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		m_new_button.setMaximumSize(m_new_button.getPreferredSize());
		m_exit_button.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		m_exit_button.setMaximumSize(m_exit_button.getPreferredSize());
		pane = getContentPane();
		panel_x = new javax.swing.JPanel();
		layout_x = new javax.swing.BoxLayout(panel_x, javax.swing.BoxLayout.X_AXIS);
		panel_x.setLayout(layout_x);
		panel_y = new javax.swing.JPanel();
		layout_y = new javax.swing.BoxLayout(panel_y, javax.swing.BoxLayout.Y_AXIS);
		panel_y.setLayout(layout_y);
		panel_y.add(javax.swing.Box.createVerticalGlue());
		panel_y.add(m_combo_box);
		panel_y.add(javax.swing.Box.createVerticalStrut(gap));
		panel_y.add(m_new_button);
		panel_y.add(javax.swing.Box.createVerticalStrut(gap));
		panel_y.add(m_exit_button);
		panel_y.add(javax.swing.Box.createVerticalGlue());
		panel_x.add(javax.swing.Box.createHorizontalGlue());
		panel_x.add(panel_y);
		panel_x.add(javax.swing.Box.createHorizontalGlue());
		pane.add(panel_x);
	}
	private void init_window()
	{
		add_components();
		setTitle("example");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(this);
	}
	private void set_laf()
	{
		javax.swing.UIManager.LookAndFeelInfo info;
		boolean is_metal;
		javax.swing.plaf.metal.MetalTheme theme;
		java.lang.Class klass;
		java.lang.Object obj;
		info = m_laf_infos[m_combo_box.getSelectedIndex()];
		is_metal = info.getClassName().equals(javax.swing.plaf.metal.MetalLookAndFeel.class.getName());
		if(is_metal)
		{
			theme = null;
			if(m_want_ocean)
			{
				try
				{
					klass = Class.forName("javax.swing.plaf.metal.OceanTheme");
					obj = klass.newInstance();
					theme = ((javax.swing.plaf.metal.MetalTheme)(obj));
				}
				catch(java.lang.InstantiationException ex){}
				catch(java.lang.ClassNotFoundException ex){}
				catch(java.lang.IllegalAccessException ex){}
			}
			if(theme == null)
			{
				theme = new javax.swing.plaf.metal.DefaultMetalTheme();
			}
			javax.swing.plaf.metal.MetalLookAndFeel.setCurrentTheme(theme);
		}
		try
		{
			if(is_metal)
			{
				javax.swing.UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
			}
			else
			{
				javax.swing.UIManager.setLookAndFeel(info.getClassName());
			}
		}
		catch(javax.swing.UnsupportedLookAndFeelException ex){}
		catch(java.lang.ClassNotFoundException ex){}
		catch(java.lang.InstantiationException ex){}
		catch(java.lang.IllegalAccessException ex){}
	}
	private static void make_frame(frame old_frame)
	{
		boolean has_decorations;
		frame fr;
		java.awt.Dimension cur_size;
		java.awt.Dimension min_size;
		has_decorations = javax.swing.JFrame.isDefaultLookAndFeelDecorated();
		javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);
		fr = new frame();
		fr.m_want_ocean = old_frame != null ? !old_frame.m_want_ocean : false;
		javax.swing.JFrame.setDefaultLookAndFeelDecorated(has_decorations);
		fr.pack();
		cur_size = fr.getSize();
		min_size = fr.getMinimumSize();
		fr.setSize
		(
			mk.utils.max(cur_size.width, min_size.width),
			mk.utils.max(cur_size.height, min_size.height)
		);
		fr.setLocationRelativeTo(old_frame);
		fr.setVisible(true);
		if(old_frame != null)
		{
			old_frame.setVisible(false);
			old_frame.dispose();
		}
	}
	public static void make_frame()
	{
		make_frame(null);
	}
	private void on_new_button()
	{
		set_laf();
		make_frame(this);
	}
	private void on_exit_button()
	{
		dispose();
	}
	/*@Override*/
	public void windowOpened(java.awt.event.WindowEvent e)
	{
	}
	/*@Override*/
	public void windowClosing(java.awt.event.WindowEvent e)
	{
	}
	/*@Override*/
	public void windowClosed(java.awt.event.WindowEvent e)
	{
	}
	/*@Override*/
	public void windowIconified(java.awt.event.WindowEvent e)
	{
	}
	/*@Override*/
	public void windowDeiconified(java.awt.event.WindowEvent e)
	{
	}
	/*@Override*/
	public void windowActivated(java.awt.event.WindowEvent e)
	{
	}
	/*@Override*/
	public void windowDeactivated(java.awt.event.WindowEvent e)
	{
	}
	/*@Override*/
	public void actionPerformed(java.awt.event.ActionEvent e)
	{
		java.lang.Object src;
		src = e.getSource();
		if(false)
		{
		}
		else if(src == m_new_button)
		{
			on_new_button();
		}
		else if(src == m_exit_button)
		{
			on_exit_button();
		}
	}
}
