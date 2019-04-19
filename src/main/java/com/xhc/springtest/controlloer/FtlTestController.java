package com.xhc.springtest.controlloer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FtlTestController {

    /**
     * 测试ftl
     * @param model
     * @return
     */
    @RequestMapping("ftl/test")
    public String test(ModelMap model){
        model.addAttribute("message","hello world");
        return "test";
    }
}
