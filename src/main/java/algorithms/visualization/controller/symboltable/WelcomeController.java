package algorithms.visualization.controller.symboltable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WelcomeController {
  private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(HttpServletRequest request) {
    logger.info("request from :{}", request.getRemoteAddr());
    return "index";
  }

  @RequestMapping(value = "/indexing/{page}", method = RequestMethod.GET)
  public String indexingPage(@PathVariable String page, HttpServletRequest request) {
    String path =  "indexing/" + page;
    request.setAttribute("currentPath", page);
    return path;
  }

  @RequestMapping(value = "/{page}", method = RequestMethod.GET)
  public String page(@PathVariable String page) {
    return page + "/index";
  }

  @RequestMapping(value = "/rest", method = RequestMethod.GET)
  @ResponseBody
  public Map restIndex() {
    Map<String, String> map = new HashMap<>();
    map.put("message", "hello, this web support restful service!");
    return map;
  }
}
