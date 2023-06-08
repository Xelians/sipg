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

import java.util.Objects;

/**
 * La classe Place représente un lieu ou un emplacement.
 */
public class Place {

    /**
     * Le nom géographique.
     */
    protected String geogName;

    /**
     * L'adresse.
     */
    protected String address;

    /**
     * Le code postal.
     */
    protected String postalCode;

    /**
     * La ville.
     */
    protected String city;

    /**
     * La région.
     */
    protected String region;

    /**
     * Le pays.
     */
    protected String country;

    /**
     * Instantiates a new Place.
     *
     * @param geogName   the geogName
     * @param address    the address
     * @param postalCode the postal code
     * @param city       the city
     * @param region     the region
     * @param country    the country
     */
    @JsonCreator
    public Place(@JsonProperty("geogName") String geogName,
                 @JsonProperty("address") String address,
                 @JsonProperty("postalCode") String postalCode,
                 @JsonProperty("city") String city,
                 @JsonProperty("region") String region,
                 @JsonProperty("country") String country) {

        this.geogName = geogName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.region = region;
        this.country = country;
    }

    /**
     * Indique le nom géographique.
     *
     * @return le nom géographique.
     */
    public String getGeogName() {
        return geogName;
    }

    /**
     * Spécifie le nom géographique.
     *
     * @param value le nom géographique.
     */
    public void setGeogName(String value) {
        this.geogName = value;
    }

    /**
     * Indique l'adresse.
     *
     * @return l'adresse
     */
    public String getAddress() {
        return address;
    }

    /**
     * Spécifie l'adresse.
     *
     * @param value l'adresse
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Indique le code postal.
     *
     * @return le code postal
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Spécifie le code postal.
     *
     * @param value le code postal
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Indique la ville.
     *
     * @return la ville
     */
    public String getCity() {
        return city;
    }

    /**
     * Spécifie la ville.
     *
     * @param value la ville
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Indique la région.
     *
     * @return la région
     */
    public String getRegion() {
        return region;
    }

    /**
     * Spécifie la région.
     *
     * @param value la région
     */
    public void setRegion(String value) {
        this.region = value;
    }

    /**
     * Indique le pays.
     *
     * @return le pays
     */
    public String getCountry() {
        return country;
    }

    /**
     * Spécifie le pays.
     *
     * @param value le pays
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Indique la valeur du hash code de l'objet.
     *
     * @return le hash code de l'objet
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.geogName);
        hash = 29 * hash + Objects.hashCode(this.address);
        hash = 29 * hash + Objects.hashCode(this.postalCode);
        hash = 29 * hash + Objects.hashCode(this.city);
        hash = 29 * hash + Objects.hashCode(this.region);
        hash = 29 * hash + Objects.hashCode(this.country);
        return hash;
    }

    /**
     * Indique si un autre objet est égal à celui-ci.
     *
     * @param obj l'objet à vérifier
     * @return true si l'objet est identique, false sinon
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Place other = (Place) obj;
        if (!Objects.equals(this.geogName, other.geogName)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.postalCode, other.postalCode)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.region, other.region)) {
            return false;
        }
        return Objects.equals(this.country, other.country);
    }

    /**
     * Indique la représentation en tant que String de l'objet.
     *
     * @return la représentation en tant que String
     */
    @Override
    public String toString() {
        return "Place{" + "geogName=" + geogName + ", address=" + address + ", postalCode=" + postalCode + ", city=" + city
                + ", region=" + region + ", country=" + country + '}';
    }

}
