package org.jempeg.nodestore;

import java.io.File;

import org.jempeg.nodestore.model.ContainerModifierFactory;
import org.jempeg.protocol.AbstractSynchronizeClient;
import org.jempeg.protocol.IProtocolClient;
import org.jempeg.protocol.NoConnectionFactory;
import org.jempeg.protocol.NoProtocolClient;
import org.jempeg.protocol.ProtocolException;

import com.inzyme.filesystem.IImportFile;
import com.inzyme.filesystem.LocalImportFolder;
import com.inzyme.model.Reason;
import com.inzyme.progress.IProgressListener;
import com.inzyme.progress.ISimpleProgressListener;

public class LocalSynchronizeClient extends AbstractSynchronizeClient {
  public LocalSynchronizeClient() {
    super(new NoConnectionFactory());
  }

  protected Reason[] download0(PlayerDatabase _playerDatabase, IProtocolClient _protocolClient, boolean _rebuildOnFailure, IProgressListener _progressListener) throws ProtocolException, SynchronizeException {
    _playerDatabase.setCreateUnattachedItems(false);
    _playerDatabase.setTrackFreeSpace(false);
    _playerDatabase.setNestedPlaylistAllowed(true);
    File musicFolder = new File("M:\\test");
    ContainerModifierFactory.getInstance(_playerDatabase.getRootPlaylist()).importFiles(new IImportFile[] { new LocalImportFolder(musicFolder) }, null, _progressListener, true);
    return new Reason[0];
  }

  protected void beforeSynchronize(PlayerDatabase _playerDatabase, IProtocolClient _protocolClient, IProgressListener _progressListener) throws SynchronizeException, ProtocolException {
  }

  protected void afterSynchronize(PlayerDatabase _playerDatabase, IProtocolClient _protocolClient, IProgressListener _progressListener) throws SynchronizeException, ProtocolException {
  }

  public void synchronizeDelete(IFIDNode _node, IProtocolClient _protocolClient) throws SynchronizeException {
  }

  public void synchronizePlaylistTags(FIDPlaylist _playlist, PlaylistPair[] _playlistPairs, IProtocolClient _protocolClient) throws SynchronizeException {
  }

  public void synchronizeTags(IFIDNode _node, IProtocolClient _protocolClient) throws SynchronizeException {
  }
  
  public void synchronizeFile(IFIDNode _node, IProtocolClient _protocolClient) throws SynchronizeException {
  }

  public IProtocolClient getProtocolClient(ISimpleProgressListener _progressListener) {
    return new NoProtocolClient();
  }
}