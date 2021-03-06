/*******************************************************************************
 * Copyright 2016-2017 Dell Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * @microservice: device-domain
 * @author: Tyler Cox, Dell
 * @version: 1.0.0
 *******************************************************************************/

package org.edgexfoundry.device.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.edgexfoundry.domain.core.Reading;

/**
 * Represents a request transaction between the device and device service.
 *
 * @author Jim
 *
 */
public class Transaction {
  private String transactionId;
  private List<Reading> readings;
  private Map<String, Boolean> opIds;

  public Transaction() {
    setTransactionId(UUID.randomUUID().toString());
    setReadings(new ArrayList<Reading>());
    opIds = new HashMap<>();
  }

  public String newOpId() {
    String opId = UUID.randomUUID().toString();
    opIds.put(opId, false);
    return opId;
  }

  public void finishOp(String opId, List<Reading> readings) {
    addReadings(readings);
    opIds.put(opId, true);
  }

  public Boolean isFinished() {
    return !opIds.containsValue(false);
  }

  public String getTransactionId() {
    return transactionId;
  }

  public List<Reading> getReadings() {
    return readings;
  }

  private void setReadings(List<Reading> readings) {
    this.readings = readings;
  }

  private void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  private void addReadings(List<Reading> readings) {
    if (readings != null) {
      if (this.readings == null) {
        setReadings(new ArrayList<Reading>());
      }
      this.readings.addAll(readings);
    }
  }

}
