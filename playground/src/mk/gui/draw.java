package mk.gui;
public class draw extends javax.swing.JFrame implements java.awt.event.WindowListener
{
	private static class timer_task implements java.awt.event.ActionListener
	{
		private final javax.swing.JPanel m_panel;
		public timer_task(javax.swing.JPanel panel)
		{
			super();
			assert panel != null;
			m_panel = panel;
		}
		/*@Override*/ /*java.awt.event.ActionListener*/
		public void actionPerformed(java.awt.event.ActionEvent e)
		{
			m_panel.repaint();
		}
	}
	private static class panel extends javax.swing.JPanel implements java.awt.event.MouseListener
	{
		private static class ball
		{
			private static final java.awt.Color k_colors[] =
			{
				java.awt.Color.blue,
				java.awt.Color.cyan,
				java.awt.Color.green,
				java.awt.Color.lightGray,
				java.awt.Color.magenta,
				java.awt.Color.orange,
				java.awt.Color.pink,
				java.awt.Color.red,
				java.awt.Color.white,
				java.awt.Color.yellow,
			};
			private final int k_w = 10;
			private final int k_h = 10;
			java.awt.Color m_color;
			private float m_x;
			private float m_y;
			private float m_speed;
			private int m_dir;
			public ball(java.util.Random random)
			{
				super();
				m_color = k_colors[random.nextInt(k_colors.length)];
				m_x = random.nextInt(1000);
				m_y = random.nextInt(1000);
				m_speed = 1.0f + ((0.0f + random.nextFloat() * (0.2f - 0.0f)) - 0.1f);
				m_dir = random.nextInt(4);
			}
			private void move_one_step(int w, int h)
			{
				float speed;
				boolean conda;
				boolean condb;
				assert w >= 1;
				assert h >= 1;
				speed = 1.0f / 10.0f;
				speed *= m_speed;
				if(m_dir == 0)
				{
					m_x += speed;
					m_y += speed;
					conda = m_x > w - k_w;
					condb = m_y > h - k_h;
					if(conda && condb)
					{
						m_dir = 2;
					}
					else if(conda)
					{
						m_dir = 1;
					}
					else if(condb)
					{
						m_dir = 3;
					}
				}
				else if(m_dir == 1)
				{
					m_x -= speed;
					m_y += speed;
					conda = m_x < 0;
					condb = m_y > h - k_h;
					if(conda && condb)
					{
						m_dir = 3;
					}
					else if(conda)
					{
						m_dir = 0;
					}
					else if(condb)
					{
						m_dir = 2;
					}
				}
				else if(m_dir == 2)
				{
					m_x -= speed;
					m_y -= speed;
					conda = m_x < 0;
					condb = m_y < 0;
					if(conda && condb)
					{
						m_dir = 0;
					}
					else if(conda)
					{
						m_dir = 3;
					}
					else if(condb)
					{
						m_dir = 1;
					}
				}
				else if(m_dir == 3)
				{
					m_x += speed;
					m_y -= speed;
					conda = m_x > w - k_w;
					condb = m_y < 0;
					if(conda && condb)
					{
						m_dir = 1;
					}
					else if(conda)
					{
						m_dir = 2;
					}
					else if(condb)
					{
						m_dir = 0;
					}
				}
				else
				{
					assert false;
				}
			}
			public void move_all_steps(int w, int h, int steps)
			{
				int n;
				int i;
				assert w >= 1;
				assert h >= 1;
				assert steps >= 1;
				assert steps <= 100;
				n = steps;
				for(i = 0; i != n; ++i)
				{
					move_one_step(w, h);
				}
			}
		}
		private final java.util.Random m_random;
		ball[] m_balls;
		private javax.swing.Timer m_timer;
		private java.awt.image.BufferedImage m_image;
		private long m_last_drawn;
		private long m_last_fps;
		private long m_frames_painted;
		java.lang.String m_fps_text;
		public panel()
		{
			super();
			m_random = new java.util.Random();
			m_balls = new ball[1];
			m_balls[0] = new ball(m_random);
			m_balls[0].m_color = java.awt.Color.red;
			m_balls[0].m_x = 10;
			m_balls[0].m_y = 10;
			m_balls[0].m_dir = 0;
			start_timer();
			addMouseListener(this);
		}
		private void start_timer()
		{
			timer_task tt;
			tt = new timer_task(this);
			m_timer = new javax.swing.Timer(1, tt);
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
			cur_time = java.lang.System.currentTimeMillis();
			dif_time = cur_time - m_last_drawn;
			m_last_drawn = cur_time;
			if(dif_time >= 1 && dif_time <= 100)
			{
				refresh_image_always(((int)(dif_time)));
			}
		}
		private void refresh_image_always(int dif_time)
		{
			java.awt.Graphics g;
			int w;
			int h;
			g = m_image.getGraphics();
			w = m_image.getWidth();
			h = m_image.getHeight();
			try
			{
				refresh_image_always(g, w, h, dif_time);
			}
			finally
			{
	      g.dispose();
			}
		}
		private void refresh_image_always(java.awt.Graphics g, int w, int h, int dif_time)
		{
			assert g != null;
			assert w >= 1;
			assert h >= 1;
			assert dif_time >= 1;
			assert dif_time <= 100;
			move_balls(w, h, dif_time);
			paint_black(g, w, h, dif_time);
			paint_balls(g, w, h, dif_time);
			paint_status_text(g, w, h, dif_time);
		}
		private void move_balls(int w, int h, int steps)
		{
			int n;
			int i;
			ball ball;
			n = m_balls.length;
			for(i = 0; i != n; ++i)
			{
				ball = m_balls[i];
				ball.move_all_steps(w, h, steps);
			}
		}
		private void paint_black(java.awt.Graphics g, int w, int h, int dif_time)
		{
			g.setColor(java.awt.Color.black);
			g.fillRect(0, 0, w, h);
		}
		private void paint_ball(java.awt.Graphics g, int w, int h, int dif_time, ball ball)
		{
			g.setColor(ball.m_color);
			g.fillOval(((int)(ball.m_x)), ((int)(ball.m_y)), ball.k_w, ball.k_h);
		}
		private void paint_balls(java.awt.Graphics g, int w, int h, int dif_time)
		{
			int n;
			int i;
			ball ball;
			n = m_balls.length;
			for(i = 0; i != n; ++i)
			{
				ball = m_balls[i];
				paint_ball(g, w, h, dif_time, ball);
			}
		}
		private java.lang.String format_float(float x)
		{
			java.text.NumberFormat format;
			java.lang.String r;
			format = java.text.NumberFormat.getNumberInstance();
			format.setGroupingUsed(false);
			format.setMinimumFractionDigits(2);
			format.setMaximumFractionDigits(2);			
			r = format.format(x);
			return r;
		}
		private void paint_status_text(java.awt.Graphics g, int w, int h, int dif_time)
		{
			long dif_fps;
			float fps_float;
			java.awt.geom.Rectangle2D fps_rect;
			java.lang.String text;
			java.awt.geom.Rectangle2D rect;
			assert g != null;
			assert w >= 1;
			assert h >= 1;
			assert dif_time >= 1;
			++m_frames_painted;
			dif_fps = m_last_drawn - m_last_fps;
			if(dif_fps > 1000l)
			{
				fps_float = ((float)(m_frames_painted)) / ((float)(dif_fps)) * 1000.0f;
				m_fps_text = "FPS: " + format_float(fps_float);
				m_last_fps = m_last_drawn;
				m_frames_painted = 0;
			}
			fps_rect = g.getFontMetrics().getStringBounds(m_fps_text, g);
			g.setColor(java.awt.Color.white);
			g.drawString(m_fps_text, 0, ((int)(fps_rect.getHeight())) + 1);
			text = "Balls: " + m_balls.length;
			rect = g.getFontMetrics().getStringBounds(text, g);
			g.drawString(text, 0, ((int)(fps_rect.getHeight())) + 1 + ((int)(rect.getHeight())) + 1);
		}
		private void add_balls(int count, int x, int y)
		{
			ball[] balls;
			int n;
			int i;
			balls = new ball[m_balls.length + count];
			java.lang.System.arraycopy(m_balls, 0, balls, 0, m_balls.length);
			n = count;
			for(i = 0; i != n; ++i)
			{
				balls[m_balls.length + i] = new ball(m_random);
			}
			balls[m_balls.length].m_x = x;
			balls[m_balls.length].m_y = y;
			m_balls = balls;
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
		/*@Override*/ /*java.awt.event.MouseListener*/
    public void mouseClicked(java.awt.event.MouseEvent e)
		{
			if(e.getButton() == java.awt.event.MouseEvent.BUTTON1)
			{
				add_balls(1, e.getX(), e.getY());
			}
			else if(e.getButton() == java.awt.event.MouseEvent.BUTTON3)
			{
				add_balls(100, e.getX(), e.getY());
			}
		}
		/*@Override*/ /*java.awt.event.MouseListener*/
    public void mousePressed(java.awt.event.MouseEvent e)
		{
		}
		/*@Override*/ /*java.awt.event.MouseListener*/
    public void mouseReleased(java.awt.event.MouseEvent e)
		{
		}
		/*@Override*/ /*java.awt.event.MouseListener*/
    public void mouseEntered(java.awt.event.MouseEvent e)
		{
		}
		/*@Override*/ /*java.awt.event.MouseListener*/
    public void mouseExited(java.awt.event.MouseEvent e)
		{
		}
	}
	private panel m_panel;
	public draw()
	{
		super();
		setTitle("draw");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		add_components();
		addWindowListener(this);
	}
	private void add_components()
	{
		java.awt.Container pane;
		pane = getContentPane();
		m_panel = new panel();
		pane.add(m_panel);
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
		m_panel.stop_timer();
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
			draw d;
			java.awt.Dimension cur_size;
			java.awt.Dimension min_size;
			d = new draw();
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
		}
	}
	public static void main(java.lang.String[] args)
	{
		runner r;
		r = new runner();
		javax.swing.SwingUtilities.invokeLater(r);
	}
}
