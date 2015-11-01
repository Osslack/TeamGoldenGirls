package eventmanager;

import processmanager.*;
import engine.Engine;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;
import java.util.LinkedList;


public class Eventmanager implements EventListener, Processowner {
	private List<Event> m_Eventlist;
	private Vector<List<EventListener>> m_Listenervector;
	private static Eventmanager m_Instance;
	
	private Eventmanager(){
		m_Eventlist = new LinkedList<Event>();
		m_Listenervector = new Vector<List<EventListener>>(20);
		m_Listenervector.setSize(20);
		registerforEvent((EventListener)this,EventIDs.STARTUP);
	}
	
	public static Eventmanager getInstance(){
		if(m_Instance == null){
			m_Instance = new Eventmanager();
		}
		return m_Instance;
	}
	
	public void queueEvent(Event event){
		
		m_Eventlist.add(event);
	}
	
	public void registerforEvent(EventListener listener, int id){
		List<EventListener> list = m_Listenervector.get(id);
		if(list == null){
			list = new LinkedList<EventListener>();
			m_Listenervector.set(id, list);
		}
		list.add(listener);
	}
	
	// process all queued Events
	@Override
	public void ProcessCallback(long elapsed) {
		for(ListIterator<Event> it = m_Eventlist.listIterator();it.hasNext();){
			triggerEvent(it.next());
		}
	}
	
	public void triggerEvent(Event event){
		if(event.getID()>m_Listenervector.capacity()){
			return;
		}
		List<EventListener> listenerlist = m_Listenervector.get(event.getID());
		if(listenerlist != null){
			for(ListIterator<EventListener> it2 = listenerlist.listIterator();it2.hasNext();){
				EventListener listener = it2.next();
				listener.EventCallback(event.getData());
			}
		}
	}
	
	@Override
	public void EventCallback(Eventdata data) {
		switch(data.m_ID){
			case EventIDs.STARTUP:
				processmanager.Process process = new processmanager.Process(50,(Processowner)this);
				Processmanager.getInstance().addProcess(process);
				registerforEvent((EventListener)this,EventIDs.SHUTDOWN);
				break;
			case EventIDs.SHUTDOWN:
				//do stuff here
				break;
		}
		
	}

}
