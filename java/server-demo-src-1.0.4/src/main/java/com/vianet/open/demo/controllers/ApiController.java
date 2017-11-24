package com.vianet.open.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @ClassName: ApiController
 * @Description: 入口页面跳转
 * @author S.J.
 * @date 2017年7月24日 下午6:13:07
 *
 */
@Controller
public class ApiController {

	@RequestMapping("/index")
    public String index()  {
        return "index";
    }
}