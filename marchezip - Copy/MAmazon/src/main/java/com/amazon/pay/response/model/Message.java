/**
 * Copyright 2016-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazon.pay.response.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This represents the Message node parsed from the
 * Amazon Pay GetServiceStatusResponse API response.
 *
 *       &lt;Message&gt;
 *         &lt;Locale&gt;en_US&lt;/Locale&gt;
 *         &lt;Text&gt;Service is once again operating at normal capacity at 6:53 PST.&lt;/Text&gt;
 *       &lt;/Message&gt;
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Messages", propOrder = {
    "locale",
    "text"
})
@XmlRootElement(name = "Messages")
public class Message {

    @XmlElement(name = "Locale")
    protected String locale;

    @XmlElement(name = "Text")
    protected String text;

    /**
     * The operational status message parent element.
     */
    public Message() {
        super();
    }

    /**
     * Operational status message locale.
     * @return Operational status message locale.
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Operational status message text.
     * @return Operational status message text.
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the string representation of Message
     */
    @Override
    public String toString() {
        return "Message{" + "locale=" + locale +
                ", text=" + text + '}';
    }
}
