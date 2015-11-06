/**
 * @author Jendrik
 */
package sample.processmanager;

import sample.eventmanager.*;
import sample.eventmanager.EventListener;
//import sample.timer.Timer;

import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.LinkedList;

public class Processmanager implements EventListener {
	private List<Process> m_Processlist;
	private static Processmanager m_Instance;
	private int m_Processcounter;
	private boolean m_Running;
	
	
	private Processmanager(){
		m_Processcounter = 0;
		m_Running = true;
		m_Processlist = new LinkedList<Process>();
		Eventmanager.getInstance().registerforEvent((EventListener)this,EventIDs.STARTUP);
	}
	
	public static Processmanager getInstance(){
		if(m_Instance == null){
			m_Instance = new Processmanager();
		}
		return m_Instance;
	}
	
	public void Run(){
		Process process;
		long elapsed;
		while(m_Running == true){
			elapsed = sample.timer.Timer.getInstance().waitTimer(); // Timer
			for(ListIterator<Process> it = m_Processlist.listIterator();it.hasNext();){
				process = it.next();
				process.Run(elapsed);
			}
		}
	}
	
	public void addProcess(Process process){
		m_Processcounter += 1;
		process.setID(m_Processcounter);
		m_Processlist.add(process);
	}
	
	public void removeProcess(int id){
		for(ListIterator<Process> it = m_Processlist.listIterator();it.hasNext();){
			if(it.next().getID() == id){
				it.remove();
			}
		}
	}
	
	@Override
	public void EventCallback(Eventdata data) {
		switch(data.m_ID){
			case EventIDs.STARTUP:
				Eventmanager.getInstance().registerforEvent((EventListener)this,EventIDs.SHUTDOWN);
				sample.timer.Timer.getInstance().setTickdelay(50);
				break;
			case EventIDs.SHUTDOWN:
				m_Running = false; 
				break;
		}
	}

}
