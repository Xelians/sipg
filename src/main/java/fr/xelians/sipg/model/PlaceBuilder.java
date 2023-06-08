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

/**
 * La classe AgentBuilder facilite la création d'un objet Place en suivant le principe de conception du pattern
 * builder.
 *
 * @author Emmanuel Deviller
 * @see Place
 */
public class PlaceBuilder {

    private String geogName;
    private String address;
    private String postalCode;
    private String city;
    private String region;
    private String country;

    private PlaceBuilder() {
    }

    /**
     * Instancie le builder.
     *
     * @return le builder
     */
    public static PlaceBuilder builder() {
        return new PlaceBuilder();
    }

    /**
     * Spécifie le nom géographique.
     *
     * @param geogName le nom géographique.
     * @return le builder
     */
    public PlaceBuilder withGeogName(String geogName) {
        this.geogName = geogName;
        return this;
    }

    /**
     * Spécifie l'adresse.
     *
     * @param address l'adresse
     * @return le builder
     */
    public PlaceBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Spécifie le code postal.
     *
     * @param postalCode le code postal
     * @return le builder
     */
    public PlaceBuilder withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    /**
     * Spécifie la ville.
     *
     * @param city la ville
     * @return le builder
     */
    public PlaceBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * Spécifie la région.
     *
     * @param region la région
     * @return le builder
     */
    public PlaceBuilder withRegion(String region) {
        this.region = region;
        return this;
    }

    /**
     * Spécifie le pays.
     *
     * @param country le pays
     * @return le builder
     */
    public PlaceBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    /**
     * Instancie la classe Place selon les paramètres précédemment spécifiés dans le builder.
     *
     * @return la place
     */
    public Place build() {
        return new Place(geogName, address, postalCode, city, region, country);
    }

}
