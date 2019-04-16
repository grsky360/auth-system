package ilio.oauth.core.controller;

import ilio.oauth.core.config.properties.FacebookProperties;
import ilio.oauth.core.config.properties.QQProperties;
import ilio.oauth.core.config.properties.WeiboProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestCtrl {

    @Autowired
    private QQProperties qqProperties;

    @Autowired
    private WeiboProperties weiboProperties;

    @Autowired
    private FacebookProperties facebookProperties;

    @GetMapping({"", "/"})
    public ModelAndView testOauth() {
        ModelAndView model = new ModelAndView("test");
        model.addObject("weibo", weiboProperties);
        model.addObject("qq", qqProperties);
        model.addObject("fb", facebookProperties);
        return model;
    }
}
