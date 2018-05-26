/*
 * Copyright 2014-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.edu.uniquindio.utils.communication.integration.sender;

import org.springframework.integration.transformer.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author Daniel Andres Pelaez Lopez
 */
public class ExtendedMessageTransformer implements Transformer {
    @Override
    public Message<?> transform(Message<?> message) {
        return MessageBuilder
                .withPayload(new ExtendedMessage((String) message.getHeaders().get(MessageHeaders.REPLY_CHANNEL),
                        (String) message.getHeaders().get(MessageHeaders.ERROR_CHANNEL),
                        (co.edu.uniquindio.utils.communication.message.Message) message.getPayload()))
                .copyHeaders(message.getHeaders())
                .build();
    }
}