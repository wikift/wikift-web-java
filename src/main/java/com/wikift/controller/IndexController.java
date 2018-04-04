/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wikift.controller;

import com.wikift.common.HttpTemplate;
import com.wikift.common.WikiftConstant;
import com.wikift.entity.RemoteServerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * IndexController <br/>
 * 描述 : IndexController <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-03 上午10:33 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Controller
@RequestMapping(value = "index")
public class IndexController {

    @Autowired
    private HttpTemplate httpTemplate;

    @Autowired
    private Environment environment;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model,
                        @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                        @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        RemoteServerEntity remoteServer = new RemoteServerEntity(environment);
        String result = httpTemplate.getRemoteResponseToString(remoteServer.fullPath() + "public/article/list?orderBy=NATIVE_CREATE_TIME");
        model.addAttribute("result", result);
        return WikiftConstant.INDEX_ANF_ROOT_PAGE_TEMPLATE;
    }

    @RequestMapping(value = {"navbar/latest"}, method = RequestMethod.GET)
    public String navbarLatest() {
        return "redirect:/index";
    }

    @RequestMapping(value = {"navbar/hottest"}, method = RequestMethod.GET)
    public String navbarHottest(Model model,
                                @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        RemoteServerEntity remoteServer = new RemoteServerEntity(environment);
        String result = httpTemplate.getRemoteResponseToString(remoteServer.fullPath() + "public/article/list?orderBy=VIEW");
        model.addAttribute("hottest", result);
        return WikiftConstant.INDEX_NAVBAR_TEMPLATE_ROOT_PATH + "hottest";
    }

    @RequestMapping(value = {"navbar/recommend"}, method = RequestMethod.GET)
    public String navbarRecommend(Model model,
                                  @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                  @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        RemoteServerEntity remoteServer = new RemoteServerEntity(environment);
        String result = httpTemplate.getRemoteResponseToString(remoteServer.fullPath() + "public/article/list?orderBy=FABULOU");
        model.addAttribute("hottest", result);
        return WikiftConstant.INDEX_NAVBAR_TEMPLATE_ROOT_PATH + "recommend";
    }

    @PreAuthorize(value = "hasRole('USER')")
    @RequestMapping(value = {"navbar/forme"}, method = RequestMethod.GET)
    public String navbarForme(Model model,
                              @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                              @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
//        RemoteServerEntity remoteServer = new RemoteServerEntity(environment);
//        String result = httpTemplate.getRemoteResponseToString(remoteServer.fullPath() + "public/article/list?orderBy=FABULOU");
//        model.addAttribute("forme", result);
        return WikiftConstant.INDEX_NAVBAR_TEMPLATE_ROOT_PATH + "forme";
    }

}