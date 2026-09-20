package mk.gui;
public class gcstatus extends javax.swing.JFrame implements java.awt.event.WindowListener
{
	private static class gcpanel extends javax.swing.JPanel
	{
		private static class snapshot
		{
			private final long m_total;
			private final long m_free;
			private final long m_time;
			public snapshot(long total, long free, long time)
			{
				super();
				m_total = total;
				m_free = free;
				m_time = time;
			}
			static public snapshot make_now()
			{
				java.lang.Runtime rt;
				long total;
				long free;
				long time;
				snapshot snapshot;
				rt = java.lang.Runtime.getRuntime();
				total = rt.totalMemory();
				free = rt.freeMemory();
				time = mk.utils.nano_time();
				snapshot = new snapshot(total, free, time);
				return snapshot;
			}
			public long get_total()
			{
				return m_total;
			}
			public long get_free()
			{
				return m_free;
			}
			public long get_used()
			{
				return m_total - m_free;
			}
			public long get_time()
			{
				return m_time;
			}
		}
		private static class snapshots
		{
			private static final int k_border_thickness = 1;
			private final java.util.LinkedList m_snapshots;
			public snapshots()
			{
				super();
				m_snapshots = new java.util.LinkedList();
			}
			private void prune_old()
			{
				boolean keep_going;
				snapshot oldest;
				snapshot newest;
				long diff;
				if(!m_snapshots.isEmpty())
				{
					keep_going = true;
					for(;;)
					{
						oldest = ((snapshot)(m_snapshots.getFirst()));
						newest = ((snapshot)(m_snapshots.getLast()));
						diff = newest.get_time() - oldest.get_time();
						keep_going = diff > 1l * 60l * 1000l * 1000l * 1000l;
						if(!keep_going)
						{
							break;
						}
						m_snapshots.removeFirst();
					}
				}
			}
			public void refresh_data()
			{
				boolean take_snapshot;
				long time;
				long diff;
				snapshot s;
				prune_old();
				take_snapshot = false;
				if(!take_snapshot)
				{
					take_snapshot = m_snapshots.isEmpty();
				}
				if(!take_snapshot)
				{
					time = mk.utils.nano_time();
					diff = time - ((snapshot)(m_snapshots.getLast())).get_time();
					take_snapshot = diff >= 100l * 1000l * 1000l;
				}
				if(take_snapshot)
				{
					s = snapshot.make_now();
					m_snapshots.addLast(s);
				}
			}
			private long get_max_mem()
			{
				long max;
				java.util.Iterator it;
				snapshot s;
				long total;
				max = -1;
				it = m_snapshots.iterator();
				while(it.hasNext())
				{
					s = ((snapshot)(it.next()));
					total = s.get_total();
					max = java.lang.Math.max(max, total);
				}
				return max;
			}
			private long get_max_time()
			{
				snapshot oldest;
				snapshot newest;
				long diff;
				oldest = ((snapshot)(m_snapshots.getFirst()));
				newest = ((snapshot)(m_snapshots.getLast()));
				diff = newest.get_time() - oldest.get_time();
				return diff;
			}
			private void paint_data(java.awt.Graphics g, int w, int h)
			{
				int count;
				long total_time;
				long total_bytes;
				int lefti;
				float leftf;
				float rightf;
				java.util.Iterator it;
				snapshot s_prev;
				int n;
				int i;
				snapshot s_curr;
				float bar_width;
				int righti;
				int wi;
				int space_left_a;
				int ha;
				int xa;
				int ya;
				int space_left_b;
				int he_b;
				int xb;
				int yb;
				g.setColor(java.awt.Color.red);
				g.drawRect(0, 0, w - k_border_thickness, h - k_border_thickness);
				count = m_snapshots.size();
				if(count >= 4 && w >= 32 && h >= 32)
				{
					total_time = get_max_time();
					total_bytes = get_max_mem();
					total_bytes = ((long)(((float)(total_bytes)) * 1.10f));
					lefti = k_border_thickness;
					leftf = ((float)(lefti));
					rightf = leftf;
					it = m_snapshots.iterator();
					s_prev = ((snapshot)(it.next()));
					n = count - 1;
					for(i = 0; i != n; ++i)
					{
						s_curr = ((snapshot)(it.next()));
						bar_width =
						((float)(w - 2 * k_border_thickness)) *
						(
							((float)(s_curr.get_time() - s_prev.get_time())) /
							((float)(total_time))
						);
						rightf += bar_width;
						s_prev = s_curr;
						lefti = ((int)(leftf));
						lefti = java.lang.Math.min(lefti, (w - 1) - k_border_thickness);
						righti = ((int)(rightf));
						if(i == n - 1)
						{
							righti = w - k_border_thickness;
							lefti = java.lang.Math.min(lefti, righti - 1);
						}
						if(righti != lefti)
						{
							leftf = rightf;
							wi = righti - lefti;
							space_left_a = h - 2 * k_border_thickness;
							ha = ((int)(((float)(h - 2 * k_border_thickness)) * (((float)(s_curr.get_used())) / ((float)(total_bytes)))));
							ha = java.lang.Math.min(ha, space_left_a);
							xa = lefti;
							ya = 0 + k_border_thickness + (h - 2 * k_border_thickness) - ha;
							space_left_b = space_left_a - ha;
							he_b = ((int)(((float)(h - 2 * k_border_thickness)) * (((float)(s_curr.get_total())) / ((float)(total_bytes)))));
							he_b -= ha;
							he_b = java.lang.Math.min(he_b, space_left_b);
							xb = xa;
							yb = ya - he_b;
							g.setColor(java.awt.Color.blue);
							g.fillRect(xa, ya, wi, ha);
							g.setColor(java.awt.Color.orange);
							g.fillRect(xb, yb, wi, he_b);
						}
					}
				}
			}
		}
		private static class timer_task implements java.awt.event.ActionListener
		{
			private final snapshots m_snapshots;
			private final javax.swing.JPanel m_panel;
			public timer_task(snapshots snapshots, javax.swing.JPanel panel)
			{
				super();
				assert snapshots != null;
				assert panel != null;
				m_snapshots = snapshots;
				m_panel = panel;
			}
			/*@Override*/ /*java.awt.event.ActionListener*/
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				m_snapshots.refresh_data();
				m_panel.repaint();
			}
		}
		private javax.swing.Timer m_timer;
		private java.awt.image.BufferedImage m_image;
		private long m_last_draw;
		private final snapshots m_snapshots;
		public gcpanel()
		{
			super();
			m_snapshots = new snapshots();
			start_timer();
		}
		private void start_timer()
		{
			timer_task tt;
			tt = new timer_task(m_snapshots, this);
			m_timer = new javax.swing.Timer(100, tt);
			m_timer.start();
		}
		private void stop_timer()
		{
			m_timer.stop();
		}
		private void make_image_if_needed()
		{
			java.awt.Dimension size;
			if(m_image == null)
			{
				make_image_always();
			}
			size = getSize();
			if(m_image.getWidth() != size.width || m_image.getHeight() != size.height)
			{
				make_image_always();
			}
		}
		private void make_image_always()
		{
			java.awt.Dimension size;
			size = getSize();
			m_image = new java.awt.image.BufferedImage(size.width, size.height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
		}
		private void refresh_image_if_needed()
		{
			long cur_time;
			long dif_time;
			cur_time = mk.utils.nano_time();
			dif_time = cur_time - m_last_draw;
			m_last_draw = cur_time;
			if(dif_time >= 1 && dif_time <= 10l * 1000l * 1000l * 1000l)
			{
				refresh_image_always();
			}
		}
		private void refresh_image_always()
		{
			int w;
			int h;
			java.awt.Graphics g;
			w = m_image.getWidth();
			h = m_image.getHeight();
			g = m_image.getGraphics();
			try
			{
				refresh_image_always(g, w, h);
			}
			finally
			{
	      g.dispose();
			}
		}
		private void refresh_image_always(java.awt.Graphics g, int w, int h)
		{
			assert g != null;
			assert w >= 1;
			assert h >= 1;
			paint_black(g, w, h);
			m_snapshots.paint_data(g, w, h);
		}
		private void paint_black(java.awt.Graphics g, int w, int h)
		{
			g.setColor(java.awt.Color.black);
			g.fillRect(0, 0, w, h);
		}
		/*@Override*/ /*java.awt.Component*/
		public java.awt.Dimension getMinimumSize()
		{
			java.awt.Dimension cur_min_size;
			java.awt.Dimension our_min_size;
			java.awt.Dimension ret_min_size;
			cur_min_size = super.getMinimumSize();
			our_min_size = new java.awt.Dimension();
			our_min_size.setSize(1, 1);
			ret_min_size = new java.awt.Dimension();
			ret_min_size.setSize
			(
				java.lang.Math.max(cur_min_size.width, our_min_size.width),
				java.lang.Math.max(cur_min_size.height, our_min_size.height)
			);
			return ret_min_size;
		}
		/*@Override*/ /*javax.swing.JComponent*/
		protected void paintComponent(java.awt.Graphics g)
		{
			super.paintComponent(g);
			make_image_if_needed();
			refresh_image_if_needed();
			g.drawImage(m_image, 0, 0, null);
		}
	}
	private gcpanel m_gcpanel;
	public gcstatus()
	{
		super();
		setTitle("gcstatus");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		add_components();
		addWindowListener(this);
	}
	private void add_components()
	{
		java.awt.Container pane;
		pane = getContentPane();
		m_gcpanel = new gcpanel();
		pane.add(m_gcpanel);
	}
	/*@Override*/ /*java.awt.Component*/
	public java.awt.Dimension getMinimumSize()
	{
		java.awt.Dimension cur_min_size;
		java.awt.Dimension our_min_size;
		java.awt.Dimension ret_min_size;
		cur_min_size = super.getMinimumSize();
		our_min_size = new java.awt.Dimension();
		our_min_size.setSize(250, 250);
		ret_min_size = new java.awt.Dimension();
		ret_min_size.setSize
		(
			java.lang.Math.max(cur_min_size.width, our_min_size.width),
			java.lang.Math.max(cur_min_size.height, our_min_size.height)
		);
		return ret_min_size;
	}
	/*@Override*/ /*java.awt.event.WindowListener*/
	public void windowOpened(java.awt.event.WindowEvent e)
	{
	}
	/*@Override*/ /*java.awt.event.WindowListener*/
	public void windowClosing(java.awt.event.WindowEvent e)
	{
	}
	/*@Override*/ /*java.awt.event.WindowListener*/
	public void windowClosed(java.awt.event.WindowEvent e)
	{
		m_gcpanel.stop_timer();
	}
	/*@Override*/ /*java.awt.event.WindowListener*/
	public void windowIconified(java.awt.event.WindowEvent e)
	{
	}
	/*@Override*/ /*java.awt.event.WindowListener*/
	public void windowDeiconified(java.awt.event.WindowEvent e)
	{
	}
	/*@Override*/ /*java.awt.event.WindowListener*/
	public void windowActivated(java.awt.event.WindowEvent e)
	{
	}
	/*@Override*/ /*java.awt.event.WindowListener*/
	public void windowDeactivated(java.awt.event.WindowEvent e)
	{
	}
	private static class runner implements java.lang.Runnable
	{
		/*@Override*/ /*java.lang.Runnable*/
		public void run()
		{
			gcstatus d;
			java.awt.Dimension cur_size;
			java.awt.Dimension min_size;
			d = new gcstatus();
			d.pack();
			cur_size = d.getSize();
			min_size = d.getMinimumSize();
			d.setSize
			(
				mk.utils.max(cur_size.width, min_size.width),
				mk.utils.max(cur_size.height, min_size.height)
			);
			d.setLocationRelativeTo(null);
			d.setVisible(true);
			try
			{
				d.setAlwaysOnTop(true);
			}
			catch(java.lang.NoSuchMethodError ex)
			{
			}
		}
	}
	public static void main(java.lang.String[] args)
	{
		runner r;
		r = new runner();
		javax.swing.SwingUtilities.invokeLater(r);
	}
}
