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
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProviderCreditSummary", propOrder = {
    "providerId",
    "providerCreditId"
})
public class ProviderCreditSummary {

    @XmlElement(name = "ProviderId", required = true)
    protected String providerId;
    @XmlElement(name = "ProviderCreditId", required = true)
    protected String providerCreditId;

    public ProviderCreditSummary() {
        super();
    }

    public String getProviderId() {
        return providerId;
    }
  
    public String getProviderCreditId() {
        return providerCreditId;
    }

}
