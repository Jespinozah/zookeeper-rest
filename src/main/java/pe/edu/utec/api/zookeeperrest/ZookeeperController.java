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
    return "Welcome to Zookeeper";
  }

  @RequestMapping(value = "/getznodedata", method = RequestMethod.GET)
  public String getZNodeData(final @RequestParam("node") String node) {
    return (String) zookeeperImpl.getZNodeData(node, true);
  }

  @RequestMapping(value = "/getchildren", method = RequestMethod.GET)
  public List<String> getChildren(final @RequestParam("children") String children)
          throws KeeperException, InterruptedException {
    return zookeeperImpl.getChildren(children, true);
  }

  @RequestMapping(value = "/getchildrenvalue", method = RequestMethod.GET)
  public HashMap<String, String> getChildrenValue(final @RequestParam("node") String node)
          throws KeeperException, InterruptedException {
    return zookeeperImpl.getChildrenValue(node, true);

  }

}
