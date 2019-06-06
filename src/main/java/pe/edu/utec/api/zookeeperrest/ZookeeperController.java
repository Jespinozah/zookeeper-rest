package pe.edu.utec.api.zookeeperrest;

import java.util.HashMap;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZookeeperController {

  @Autowired
  private ZookeeperServiceImplementation zookeeperImpl;

  @RequestMapping("/")
  public String helloZookeeper() {
    return "Hola";
  }

  @RequestMapping(value = "/getznodedata", method = RequestMethod.GET)
  public String getZNodeData() {
    return (String) zookeeperImpl.getZNodeData("/config/myapplication/name", true);
  }

  @RequestMapping(value = "/getchildren", method = RequestMethod.GET)
  public List<String> getChildren() throws KeeperException, InterruptedException {
    return zookeeperImpl.getChildren("/config/myapplication", true);
  }

  @RequestMapping(value = "/getznodesdata", method = RequestMethod.GET)
  public HashMap<String, String> getznodesdata(final @RequestParam("node") String node)
          throws KeeperException, InterruptedException {
    return zookeeperImpl.getZNodesData(node, true);

  }

}
