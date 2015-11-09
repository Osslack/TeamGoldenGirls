package input;
import engine.*;

import java.awt.event.KeyEvent;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

import java.util.Vector;

public class KeyboardManager {
	private int m_activeControlSet;
	private Vector<ControlSet> m_Controls;
	private Engine m_engine;
	
	public KeyboardManager(Engine engine){
		m_engine = engine;
		m_Controls = new Vector<ControlSet>();
		m_activeControlSet = -1;
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent keyevent) {
                switch (keyevent.getID()) {
	                case KeyEvent.KEY_PRESSED:
	            		if(m_activeControlSet != -1){
	            			int id = m_Controls.get(m_activeControlSet).onKeyPressed(keyevent.getKeyCode());
	            			if(id != -1){
	            				evalKeyboardAction(id);
	            			}
	            		}
	                    break;
	                case KeyEvent.KEY_RELEASED:
	            		if(m_activeControlSet != -1){
	            			int id = m_Controls.get(m_activeControlSet).onKeyReleased(keyevent.getKeyCode());
	            			if(id != -1){
	            				evalKeyboardAction(id);
	            			}
	            		}
	                    break;
                }
                return false;
            }
        });
	}
	
	public void evalKeyboardAction(int id)
	{
		switch(id){
			case KeyboardActions.ESCAPE:
				m_engine.shutdown();
		}
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
}
