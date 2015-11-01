package engine;
import java.awt.event.KeyEvent;

import eventmanager.*;
import input.*;
import processmanager.*;
import timer.*;


public class Engine implements EventListener{
	
	private static Engine m_Instance;
	
	private Engine(){
		Eventmanager.getInstance().registerforEvent((EventListener)this, EventIDs.STARTUP);
		Processmanager.getInstance();
		Timer.getInstance();
		KeyboardManager.getInstance();
	}
	
	public static Engine getInstance(){
		if(m_Instance == null){
			m_Instance = new Engine();
		}
		return m_Instance;
	}
	
	@Override
	public void EventCallback(Eventdata data){
		switch(data.m_ID){
			case EventIDs.STARTUP:
				Eventmanager.getInstance().registerforEvent((EventListener)this, EventIDs.SHUTDOWN);
				ControlSet cs1 = new ControlSet();
				cs1.setControl(KeyEvent.VK_ESCAPE, -1, EventIDs.SHUTDOWN);
				KeyboardManager.getInstance().addControlSet(cs1);
				System.out.println("Startup");
				break;
			case EventIDs.SHUTDOWN:
				System.out.println("Shutdown");
				break;
		}	
	} 
	
	public static void main(String[] args) {
		Engine engine = Engine.getInstance(); //initialize Engine
		Eventmanager.getInstance().triggerEvent(new Event(EventIDs.STARTUP,new Eventdata()));
		Processmanager.getInstance().Run();
	}
}
