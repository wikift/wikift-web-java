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
package com.wikift;

import com.wikift.common.JwtTemplate;
import com.wikift.entity.JwtTokenEntity;
import org.junit.Test;

/**
 * JwtTemplateTest <br/>
 * 描述 : JwtTemplateTest <br/>
 * 作者 : qianmoQ <br/>
 * 版本 : 1.0 <br/>
 * 创建时间 : 2018-04-03 下午5:09 <br/>
 * 联系作者 : <a href="mailTo:shichengoooo@163.com">qianmoQ</a>
 */
public class JwtTemplateTest {

    @Test
    public void test() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsid2lraWZ0LXJlc291cmNlIl0sInVzZXJfbmFtZSI6InNoaWNoZW5nIiwic2NvcGUiOlsic2VsZWN0Iiwid3JpdGUiLCJyZWFkIl0sImV4cCI6MTUyMjc4NjQzNiwiYXV0aG9yaXRpZXMiOlsiVVNFUiJdLCJqdGkiOiI0NGMxNzIxNS0zYjllLTQ0ODctYjIxMS1iNjRiZGQxNTYyZDEiLCJjbGllbnRfaWQiOiJ3aWtpZnQtY2xpZW50In0.Zz-vFmBn_LOKNjbTVH4if0sCwlEQZ74A1iBRwGP0L8A";
        JwtTemplate jwtTemplate = JwtTemplate.build();
        System.out.println(jwtTemplate.decodedJwtTokenBody(token, JwtTokenEntity.class));
    }

}