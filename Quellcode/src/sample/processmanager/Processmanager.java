/*unused*/

package sample.processmanager;

import java.util.*;
import sample.engine.Engine;

public class Processmanager {
	private final List<Process> m_Processlist;
	private       int           m_processcounter;
	private       boolean       m_running;
	private final Engine        m_engine;

	public Processmanager(Engine engine) {
		m_engine = engine;
		m_processcounter = 0;
		m_running = true;
		m_Processlist = new LinkedList<Process>();
	}

	public void Run() {
		Process process;
		long elapsed;
		while (m_running) {
			elapsed = m_engine.getTimer().waitTimer(); // Timer
			for (final Process aM_Processlist : m_Processlist) {
				process = aM_Processlist;
				process.Run(elapsed);
			}
		}
	}

	public void addProcess(Process process) {
		m_processcounter += 1;
		process.setID(m_processcounter);
		m_Processlist.add(process);
	}

	public void removeProcess(int id) {
		for (ListIterator<Process> it = m_Processlist.listIterator(); it.hasNext(); ) {
			if (it.next().getID() == id) {
				it.remove();
			}
		}
	}

	public void setRunning(boolean x) {
		m_running = x;
	}
}
