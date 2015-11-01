package input;

import java.awt.event.KeyEvent;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

import eventmanager.*;
import java.util.Vector;

public class KeyboardManager implements EventListener{
	private static KeyboardManager m_Instance;
	private int m_activeControlSet;
	private Vector<ControlSet> m_Controls;
	
	private KeyboardManager(){
		m_Controls = new Vector<ControlSet>();
		m_activeControlSet = -1;
		Eventmanager.getInstance().registerforEvent((EventListener)this, EventIDs.STARTUP);
	}
	
	public static KeyboardManager getInstance(){
		if(m_Instance == null){
			m_Instance = new KeyboardManager();
		}
		return m_Instance;
	}
	
	public int addControlSet(ControlSet controlset){
		m_Controls.add(controlset);
		int id = m_Controls.size()-1;
		if(m_activeControlSet==-1){
			m_activeControlSet = id;
		}
		return id;
	}
	
	public void setActiveControlSet(int i){
		if(i<m_Controls.size()){
			m_activeControlSet = i;
		}
	}
	
	

	@Override
	public void EventCallback(Eventdata data) {
		switch(data.m_ID){
			case EventIDs.STARTUP:
				Eventmanager.getInstance().registerforEvent((EventListener)this, EventIDs.SHUTDOWN);
				KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
		            @Override
		            public boolean dispatchKeyEvent(KeyEvent keyevent) {
		                switch (keyevent.getID()) {
			                case KeyEvent.KEY_PRESSED:
			                	System.out.println("keypressed");
			            		if(m_activeControlSet != -1){
			            			int eventid = m_Controls.get(m_activeControlSet).onKeyPressed(keyevent.getKeyCode());
			            			if(eventid != -1){
			            				eventmanager.Eventmanager.getInstance().queueEvent(new Event(eventid,new Eventdata()));
			            			}
			            		}
			                    break;
			                case KeyEvent.KEY_RELEASED:
			                	System.out.println("keyreleased");
			            		if(m_activeControlSet != -1){
			            			int eventid = m_Controls.get(m_activeControlSet).onKeyReleased(keyevent.getKeyCode());
			            			if(eventid != -1){
			            				eventmanager.Eventmanager.getInstance().queueEvent(new Event(eventid,new Eventdata()));
			            			}
			            		}
			                    break;
		                }
		                return false;
		            }
		        });
				break;
			case EventIDs.SHUTDOWN:
				
				break;
		}

	}

}
