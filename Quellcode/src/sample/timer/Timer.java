/*unused*/
package sample.timer;

import sample.engine.Engine;

public class Timer {
	private       long   m_tickdelay;
	private       long   m_starttick;
	private final Engine m_engine;

	public Timer(Engine engine) {
		m_engine = engine;
		m_tickdelay = 50;
		m_starttick = System.currentTimeMillis();
	}

	public void setTickdelay(long delay) {
		m_tickdelay = delay;
	}

	public long waitTimer() {
		long now = System.currentTimeMillis();
		long delta = now - m_starttick;
		long wait = m_tickdelay - delta;

		if (wait > 0) {
			try {
				Thread.sleep(wait);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		m_starttick = now + wait;
		long elapsed;
		if (wait > 0) {
			elapsed = delta + wait;
		}
		else {
			elapsed = delta;
		}
		return elapsed;
	}
}
