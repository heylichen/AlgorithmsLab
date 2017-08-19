package algorithms.visualization.controller.symboltable;

import algorithms.sedgewick.ch3_search.symboltable.BTree;
import algorithms.sedgewick.ch3_search.symboltable.BTreeNode;
import algorithms.visualization.service.BTreeService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/btree")
public class BtreeController {
  private final Logger logger = LoggerFactory.getLogger(BtreeController.class);
  @Autowired
  private BTreeService bTreeService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(HttpServletRequest request) {
    return "btree/index";
  }

  @RequestMapping(value = "/{page}", method = RequestMethod.GET)
  public String page(@PathVariable String page) {
    return "btree/" + page;
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  @ResponseBody
  public String restIndex(Integer t, Integer nodeCount) {
    BTree<Integer, Integer> btree = bTreeService.initBtree(t, nodeCount);
    return JSON.toJSONString(btree.getRoot());
  }

  @RequestMapping(value = "/addKey", method = RequestMethod.GET)
  @ResponseBody
  public String addKey(String json, Integer key) {
    BTreeNode<Integer, Integer> node = (BTreeNode<Integer, Integer>) JSON.parseObject(json, BTreeNode.class);
    BTree<Integer, Integer> btree = new BTree<>(node.getT());
    btree.setRoot(node);
    btree = bTreeService.addKey(btree, key);
    return JSON.toJSONString(btree.getRoot());
  }

  @RequestMapping(value = "/deleteKey", method = RequestMethod.GET)
  @ResponseBody
  public String deleteKey(String json, Integer key) {
    BTreeNode<Integer, Integer> node = (BTreeNode<Integer, Integer>) JSON.parseObject(json, BTreeNode.class);
    BTree<Integer, Integer> btree = new BTree<>(node.getT());
    btree.setRoot(node);
    btree = bTreeService.deleteKey(btree, key);
    return JSON.toJSONString(btree.getRoot());
  }
}
