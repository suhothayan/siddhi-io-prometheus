/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.siddhi.extension.io.prometheus.source;

import org.wso2.transport.http.netty.contract.HttpConnectorListener;
import org.wso2.transport.http.netty.message.HTTPCarbonMessage;
import org.wso2.transport.http.netty.message.Http2PushPromise;

import java.util.concurrent.CountDownLatch;

/**
 * {@code PrometheusHTTPClientListener} Handles the HTTP client listener.
 */
public class PrometheusHTTPClientListener implements HttpConnectorListener {
    private final CountDownLatch latch;
    private HTTPCarbonMessage httpMessage;

    PrometheusHTTPClientListener(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onMessage(HTTPCarbonMessage httpMessage) {
        this.httpMessage = httpMessage;
        latch.countDown();
    }

    @Override
    public void onError(Throwable throwable) {
        latch.countDown();
    }

    @Override
    public void onPushPromise(Http2PushPromise pushPromise) {

    }

    HTTPCarbonMessage getHttpResponseMessage() {
        return httpMessage;
    }
}
