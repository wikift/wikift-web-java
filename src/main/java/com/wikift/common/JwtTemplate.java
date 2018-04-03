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
package com.wikift.common;

import com.google.gson.Gson;

/**
 * JwtTemplate <br/>
 * 描述 : JwtTemplate <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-03 下午4:57 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
public class JwtTemplate<T> {

    private String encoding = "UTF-8";
    private int jwt_max_length = 2;
    private int jwt_min_length = 0;

    private JwtTemplate() {
    }

    public static JwtTemplate build() {
        return new JwtTemplate();
    }

    public String decodedJwtToken(String jwtToken) {
        return this.decodedJwtToken(jwtToken, this.encoding, this.jwt_min_length);
    }

    /**
     * 解密jwt token
     *
     * @param jwtToken jwt加密的token
     * @return 解密后的jwt token
     */
    public String decodedJwtToken(String jwtToken, String encoding, int offset) {
        if (offset < this.jwt_min_length || offset >= this.jwt_max_length) {
            throw new RuntimeException("offset must greater than " + this.jwt_min_length + " less than " + this.jwt_max_length);
        }
        String result = "";
        String[] jwts = jwtToken.split("[.]");
        try {
            // 全部解密
            if (offset == this.jwt_min_length || offset == this.jwt_max_length) {
                for (int i = 0, len = jwts.length; i < len; i++) {
                    byte[] partAsBytes = jwts[i].getBytes(encoding);
                    String decodedPart = new String(java.util.Base64.getUrlDecoder().decode(partAsBytes), encoding);
                    result += decodedPart;
                }
            } else {
                // 部分解密
                byte[] partAsBytes = jwts[offset].getBytes(encoding);
                String decodedPart = new String(java.util.Base64.getUrlDecoder().decode(partAsBytes), encoding);
                result += decodedPart;
            }
        } catch (Exception e) {
            throw new RuntimeException("could not decode jwt token", e);
        }
        return result;
    }

    public T decodedJwtToken(String jwtToken, Class<T> clazz, int offset) {
        return new Gson().fromJson(this.decodedJwtToken(jwtToken, this.encoding, offset), clazz);
    }

    public T decodedJwtTokenBody(String jwtToken, Class<T> clazz) {
        return this.decodedJwtToken(jwtToken, clazz, 1);
    }

}