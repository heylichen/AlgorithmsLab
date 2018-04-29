package algorithms.visualization.controller.symboltable;

import javax.servlet.http.HttpServletRequest;

import algorithms.search.symboltable.BPlusTree;
import algorithms.search.symboltable.BPlusTreeNode;
import algorithms.visualization.service.BPlusTreeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/indexing/bPlusTree",method = {RequestMethod.POST, RequestMethod.GET})
public class BPlusTreeController {
  private final Logger logger = LoggerFactory.getLogger(BPlusTreeController.class);
  @Autowired
  private BPlusTreeService bPlusTreeService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(HttpServletRequest request) {
    return "btree/bPlusTreeOperate";
  }

  @RequestMapping(value = "/{page}", method = RequestMethod.GET)
  public String page(@PathVariable String page) {
    return "BPlusTree/" + page;
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  @ResponseBody
  public String restIndex(Integer t, Integer nodeCount) {
    BPlusTree<Integer, Integer> BPlusTree = bPlusTreeService.init(t, nodeCount);
    return JSON.toJSONString(BPlusTree.getRoot(), SerializerFeature.DisableCircularReferenceDetect);
  }

  @RequestMapping(value = "/random")
  @ResponseBody
  public String random(Integer t, Integer nodeCount) {
    BPlusTree<Integer, Integer> bPlusTree = bPlusTreeService.random(t, nodeCount);
    return JSON.toJSONString(bPlusTree.getRoot(), SerializerFeature.DisableCircularReferenceDetect);
  }

  @RequestMapping(value = "/addKey")
  @ResponseBody
  public String addKey(String json, Integer key) {
    BPlusTreeNode<Integer, Integer> node = (BPlusTreeNode<Integer, Integer>) JSON.parseObject(json, BPlusTreeNode.class);

    BPlusTree<Integer, Integer> bPlusTree = new BPlusTree<>(node.getT());
    bPlusTree.setRoot(node);
    bPlusTree = bPlusTreeService.addKey(bPlusTree, key);
    return JSON.toJSONString(bPlusTree.getRoot(), SerializerFeature.DisableCircularReferenceDetect);
  }

  @RequestMapping(value = "/deleteKey")
  @ResponseBody
  public String deleteKey(String json, Integer key) {
    BPlusTreeNode<Integer, Integer> node = (BPlusTreeNode<Integer, Integer>) JSON.parseObject(json, BPlusTreeNode.class);
    BPlusTree<Integer, Integer> bPlusTree = new BPlusTree<>(node.getT());
    bPlusTree.setRoot(node);
    bPlusTree = bPlusTreeService.deleteKey(bPlusTree, key);
    return JSON.toJSONString(bPlusTree.getRoot(), SerializerFeature.DisableCircularReferenceDetect);
  }
}
