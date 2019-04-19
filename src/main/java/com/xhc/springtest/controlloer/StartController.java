package com.xhc.springtest.controlloer;

import com.xhc.springtest.config.properties.StartPageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
class StartController {


    @Autowired
    private StartPageProperties startPage;

    /**
     * 起始页
     * @param model
     * @return
     */
    @RequestMapping(value = "/start")
    public String start(ModelMap model){
        Map<String, Object> map = new HashMap<>();
        map.put("links", startPage.getLinks());
        map.put("message", startPage.getMessage());
        model.addAllAttributes(map);
        return "start";
    }
}
