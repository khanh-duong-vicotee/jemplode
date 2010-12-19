/**
* Copyright (c) 2001, Mike Schrag & Daniel Zimmerman
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
* Redistributions of source code must retain the above copyright notice,
* this list of conditions and the following disclaimer.
*
* Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
*
* Neither the name of Mike Schrag, Daniel Zimmerman, nor the names of any
* other contributors may be used to endorse or promote products derived from
* this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
* "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
* TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
* PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE
* LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
* SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
* INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
* CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
* ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
* POSSIBILITY OF SUCH DAMAGE.
*/
package org.jempeg.empeg.manager.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.jempeg.nodestore.IDeviceSettings;

import com.inzyme.ui.ConfirmationPanel;
import com.inzyme.ui.DialogUtils;

/**
* The options dialog that contains the Options panel
* for configuring JEmplode.
*
* @author Mike Schrag
* @version $Revision: 1.3 $
**/
public class EditConfigIniDialog extends JDialog {
	private EditConfigIniPanel myConfigIniPanel;
	private IDeviceSettings myConfigFile;
	private boolean myChanged;

	public EditConfigIniDialog(JFrame _frame) {
		super(_frame, "/empeg/var/config.ini", true);

    myConfigIniPanel = new EditConfigIniPanel();		
		
		ActionListener changeListener = new ChangeListener();
    myConfigIniPanel.addActionListener(changeListener);
		
    ConfirmationPanel confirmationPanel = new ConfirmationPanel(myConfigIniPanel);
    confirmationPanel.addOkListener(new OKListener());
    confirmationPanel.addCancelListener(new CancelListener());
    
		getContentPane().add(confirmationPanel);
		pack();
    setSize(Math.max(420, getSize().width), 300);

    DialogUtils.centerWindow(this);
	}
	
	public void setChanged(boolean _changed) {
		myChanged = _changed;
	}
	
	public boolean isChanged() {
		return myChanged;
	}
	
	public void setConfigFile(IDeviceSettings _configFile) {
		myConfigFile = _configFile;
		read();
	}

  public IDeviceSettings getConfigFile() {
    return myConfigFile;
  }
  	
	protected void read() {
    myConfigIniPanel.read(myConfigFile);
		setChanged(false);
	}
	
	protected void apply() {
    myConfigIniPanel.write(myConfigFile);
	}
	
	protected void cancel() {
	}
	
	protected class ChangeListener implements ActionListener {
		public void actionPerformed(ActionEvent _event) {
			setChanged(true);
		}
	}
	
	protected class OKListener implements ActionListener {
		public void actionPerformed(ActionEvent _event) {
			apply();
			setVisible(false);
		}
	}
	
	protected class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent _event) {
			cancel();
			setVisible(false);
		}
	}
}	
