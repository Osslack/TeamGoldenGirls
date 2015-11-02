/**
 * @author Jendrik
 */
package timer;

import eventmanager.*;
import java.lang.System;
import java.lang.Thread;

public class Timer implements EventListener{
	long m_tickdelay;
	long m_starttick;
	private static Timer m_Instance;
	
	private Timer(){
		m_tickdelay=50;
		m_starttick=System.currentTimeMillis();
		Eventmanager.getInstance().registerforEvent((EventListener)this,EventIDs.STARTUP);
	}
	
	public void setTickdelay(long delay){
		m_tickdelay = delay;
	}
	
	public long waitTimer() {
		long now = System.currentTimeMillis();
		long delta = now - m_starttick;
		long wait = m_tickdelay - delta;
		
		if(wait>0){
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		m_starttick = now+wait;
		long elapsed;
		if(wait>0){
			elapsed = delta+wait;
		}else{
			elapsed = delta;
		}
		return elapsed;
	}
	
	public static Timer getInstance(){
		if(m_Instance == null){
			m_Instance = new Timer();
		}
		return m_Instance;
	}
	
	@Override
	public void EventCallback(Eventdata data) {
		switch(data.m_ID){
			case EventIDs.STARTUP:
				Eventmanager.getInstance().registerforEvent((EventListener)this,EventIDs.SHUTDOWN);
				break;
			case EventIDs.SHUTDOWN:
				//do stuff here
				break;
		}
	}
}
