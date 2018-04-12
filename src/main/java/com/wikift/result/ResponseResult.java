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
package com.wikift.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wikift.common.JsonTemplate;
import lombok.Data;
import lombok.ToString;

/**
 * ResponseResult <br/>
 * 描述 : ResponseResult <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-12 下午4:17 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
@Data
@ToString
public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;

    private static JsonTemplate template = new JsonTemplate();

    public static ResponseResult getResponse(String result, String defaultPath) {
        ResponseResult response = new ResponseResult();
        response.setCode(template.getJsonObject(result).getInteger("code"));
        response.setMessage(getErrorMessage(result, defaultPath));
        return response;
    }

    private static String getErrorMessage(String response, String defaultPath) {
        JSONObject object = template.getJsonObject(response);
        if (object.getInteger("code") > 0) {
            StringBuffer buffer = new StringBuffer();
            object.getJSONObject("data").getJSONArray("error").forEach(v -> {
                buffer.append(JSON.parseObject(JSON.toJSONString(v)).getString("message") + "\n");
            });
            return buffer.toString();
        }
        return defaultPath;
    }

}