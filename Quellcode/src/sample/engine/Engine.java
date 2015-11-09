package sample.engine;
import java.awt.event.KeyEvent;
import sample.input.*;
import processmanager.*;
import timer.*;


public class Engine {
	private Processmanager m_processmanager;
	private Timer m_timer;
	private KeyboardManager m_keyboardmanager;
	
	public Engine(){
		System.out.println("starting up");
		m_processmanager = new Processmanager(this);
		m_timer = new Timer(this);
		m_keyboardmanager = new KeyboardManager(this);
		ControlSet cs1 = new ControlSet();
		cs1.setControl(KeyEvent.VK_ESCAPE, -1, KeyboardActions.ESCAPE);
		m_keyboardmanager.addControlSet(cs1);
	}
	
	public Processmanager getProcessmanager(){
		return m_processmanager;
	}
	
	public Timer getTimer(){
		return m_timer;
	}
	
	public KeyboardManager getKeyboardManager(){
		return m_keyboardmanager;
	}
	
	public void shutdown(){
		System.out.println("shutting down");
		m_processmanager.setRunning(false);
	}
}
