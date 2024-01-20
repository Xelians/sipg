/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package fr.xelians.sipg.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

public class HoldRule extends Rule {

    protected LocalDate holdEndDate;

    protected String holdOwner;

    protected String holdReason;

    protected LocalDate holdReassessingDate;

    protected Boolean preventRearrangement;

    @JsonCreator
    public HoldRule(@JsonProperty("name") String name, @JsonProperty("startDate") LocalDate startDate,
                    @JsonProperty("holdEndDate") LocalDate holdEndDate,
                    @JsonProperty("holdOwner") String holdOwner,
                    @JsonProperty("holdReason") String holdReason,
                    @JsonProperty("holdReassessingDate") LocalDate holdReassessingDate,
                    @JsonProperty("preventRearrangement") Boolean preventRearrangement) {

        super(name, startDate);
        this.holdOwner = holdOwner;
        this.holdReason = holdReason;
        this.holdEndDate = holdEndDate;
        this.holdReassessingDate = holdReassessingDate;
        this.preventRearrangement = preventRearrangement;
    }

    public LocalDate getHoldEndDate() {
        return holdEndDate;
    }

    public String getHoldOwner() {
        return holdOwner;
    }

    public String getHoldReason() {
        return holdReason;
    }

    public LocalDate getHoldReassessingDate() {
        return holdReassessingDate;
    }

    public Boolean getPreventRearrangement() {
        return preventRearrangement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HoldRule holdRule = (HoldRule) o;
        return Objects.equals(getHoldEndDate(), holdRule.getHoldEndDate()) && Objects.equals(getHoldOwner(), holdRule.getHoldOwner()) && Objects.equals(getHoldReason(), holdRule.getHoldReason()) && Objects.equals(getHoldReassessingDate(), holdRule.getHoldReassessingDate()) && Objects.equals(getPreventRearrangement(), holdRule.getPreventRearrangement());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHoldEndDate(), getHoldOwner(), getHoldReason(), getHoldReassessingDate(), getPreventRearrangement());
    }
}
