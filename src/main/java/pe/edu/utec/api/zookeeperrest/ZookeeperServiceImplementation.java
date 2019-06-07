package pe.edu.utec.api.zookeeperrest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Service;

@Service
public class ZookeeperServiceImplementation implements ZookeeperService {

  private static ZooKeeper zkeeper;
  private static ZkConnection zkConnection;

  public ZookeeperServiceImplementation() {
    initialize();
  }

  /** * Initialize connection */
  private void initialize() {
    try {
      zkConnection = new ZkConnection();
      zkeeper = zkConnection.connect("127.0.0.1:2181");
      System.out.println("conectado");

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void closeConnection() {
    try {
      zkConnection.close();
      System.out.println("Desconectado");

    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }

  public void create(String path, byte[] data) throws KeeperException, InterruptedException {
    zkeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
  }

  public Object getZNodeData(String path, boolean watchFlag) {
    try {
      byte[] b = null;
      b = zkeeper.getData(path, null, null);
      String data = new String(b, "UTF-8");
      System.out.println(data);
      return data;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  public void update(String path, byte[] data) throws KeeperException, InterruptedException {
    int version = zkeeper.exists(path, true).getVersion();
    zkeeper.setData(path, data, version);
  }

  public List<String> getChildren(String path, boolean watchFlag)
          throws KeeperException, InterruptedException {
    try {
      List<String> zNodes = zkeeper.getChildren(path, watchFlag);
      return zNodes;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  public HashMap<String, String> getZNodesData(String path, boolean watchFlag)
          throws KeeperException, InterruptedException {
    try {
      List<String> zNodes = zkeeper.getChildren(path, watchFlag);
      HashMap<String, String> zNodesData = new HashMap<String, String>();
      for (String zNode : zNodes) {
        String data = (String) getZNodeData(path + "/" + zNode, true);
        zNodesData.put(zNode, data);
      }
      return zNodesData;
    } catch (Exception e) {
      System.out.println(e.getMessage());

    }
    return null;
  }
}
