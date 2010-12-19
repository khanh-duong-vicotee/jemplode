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
package com.inzyme.filesystem;

import java.io.File;
import java.io.IOException;

import com.inzyme.util.Debug;

/**
 * Extended by any implementation of IImportFolder that
 * is backed by a java.io.File directory.
 * 
 * @author Mike Schrag
 * @version $Revision: 1.2 $
 */
public abstract class AbstractLocalImportFolder extends AbstractLocalImportFile implements IImportFolder {
	private IImportFile[] myChildrenCache;

	protected AbstractLocalImportFolder(File _file, boolean _folder) {
		super(_file, _folder);
	}

	public int getSize() {
		try {
			int size;
			IImportFile[] children = getChildren();
			if (children == null) {
				size = 0;
			}
			else {
				size = children.length;
			}
			return size;
		}
		catch (IOException e) {
			Debug.println(e);
			return 0;
		}
	}

	public Object getValueAt(int _index) {
		try {
			Object value;
			IImportFile[] children = getChildren();
			if (children == null) {
				value = null;
			}
			else {
				value = children[_index];
			}
			return value;
		}
		catch (IOException e) {
			Debug.println(e);
			return null;
		}
	}

	public synchronized IImportFile[] getChildren() throws IOException {
		if (myChildrenCache == null) {
			myChildrenCache = getChildren0();
		}
		return myChildrenCache;
	}

	protected abstract IImportFile[] getChildren0() throws IOException;
}
