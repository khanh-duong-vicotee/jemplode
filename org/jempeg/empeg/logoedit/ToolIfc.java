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
package org.jempeg.empeg.logoedit;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
* The interface for editor tools.
*
* @author Mike Schrag
*/
public interface ToolIfc {
  public String getName();

  public void start(LogoEditPanel _editPanel, Graphics _g);
  
  public void stop(LogoEditPanel _editPanel, Graphics _g);
  
  public void click(LogoEditPanel _editPanel, Graphics _g, int _x, int _y, int _modifiers);

  public void drag(LogoEditPanel _editPanel, Graphics _g, int _x, int _y, int _modifiers);

  public void release(LogoEditPanel _editPanel, Graphics _g, int _x, int _y, int _modifiers);

  public void type(LogoEditPanel _editPanel, Graphics _g, KeyEvent _event);

  public void paintOverlay(LogoEditPanel _editPanel, Graphics _g);
}
