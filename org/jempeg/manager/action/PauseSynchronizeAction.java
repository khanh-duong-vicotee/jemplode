package org.jempeg.manager.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.jempeg.ApplicationContext;

import com.inzyme.progress.IProgressListener;

public class PauseSynchronizeAction extends AbstractAction {
	private ApplicationContext myContext;

	public PauseSynchronizeAction(ApplicationContext _context) {
		myContext = _context;
	}

	public void actionPerformed(ActionEvent _event) {
		IProgressListener progressListener = myContext.getSynchronizeProgressListener();
		progressListener.setStopRequested(true);
	}
}
