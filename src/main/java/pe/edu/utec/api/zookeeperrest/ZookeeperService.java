package pe.edu.utec.api.zookeeperrest;

import java.util.HashMap;
import java.util.List;

import org.apache.zookeeper.KeeperException;

public interface ZookeeperService {

  public void create(String path, byte[] data) throws KeeperException, InterruptedException;
  public Object getZNodeData(String path, boolean watchFlag);
  public void update(String path, byte[] data) throws KeeperException, InterruptedException, KeeperException;
  public List<String> getChildren(String path, boolean watchFlag) throws KeeperException, InterruptedException;
  public HashMap<String,String> getZNodesData(String path, boolean wathFlag) throws KeeperException, InterruptedException;;
}
