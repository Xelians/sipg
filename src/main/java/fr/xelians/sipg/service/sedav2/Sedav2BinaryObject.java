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
package fr.xelians.sipg.service.sedav2;

import java.util.Objects;

/**
 * La classe Sedav2BinaryObject représente un objet binaire (ie. un fichier PDF, JPEG, MPEG, etc.) .
 *
 * @author Emmanuel Deviller
 */
class Sedav2BinaryObject {

  private String uri;
  private long size;
  private String digest;
  private String algorithm;
  private String format;

  /**
   * Gets uri.
   *
   * @return the uri
   */
  public String getUri() {
    return uri;
  }

  /**
   * Sets uri.
   *
   * @param uri the uri
   */
  public void setUri(String uri) {
    this.uri = uri;
  }

  /**
   * Gets size.
   *
   * @return the size
   */
  public long getSize() {
    return size;
  }

  /**
   * Sets size.
   *
   * @param size the size
   */
  public void setSize(long size) {
    this.size = size;
  }

  /**
   * Gets digest.
   *
   * @return the digest
   */
  public String getDigest() {
    return digest;
  }

  /**
   * Sets digest.
   *
   * @param digest the digest
   */
  public void setDigest(String digest) {
    this.digest = digest;
  }

  /**
   * Gets algorithm.
   *
   * @return the algorithm
   */
  public String getAlgorithm() {
    return algorithm;
  }

  /**
   * Sets algorithm.
   *
   * @param algorithm the algorithm
   */
  public void setAlgorithm(String algorithm) {
    this.algorithm = algorithm;
  }

  /**
   * Gets format.
   *
   * @return the format
   */
  public String getFormat() {
    return format;
  }

  /**
   * Sets format.
   *
   * @param format the format
   */
  public void setFormat(String format) {
    this.format = format;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 41 * hash + Objects.hashCode(this.uri);
    hash = 41 * hash + (int) (this.size ^ (this.size >>> 32));
    hash = 41 * hash + Objects.hashCode(this.digest);
    hash = 41 * hash + Objects.hashCode(this.algorithm);
    hash = 41 * hash + Objects.hashCode(this.format);
    return hash;
  }

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
    final Sedav2BinaryObject other = (Sedav2BinaryObject) obj;
    if (this.size != other.size) {
      return false;
    }
    if (!Objects.equals(this.uri, other.uri)) {
      return false;
    }
    if (!Objects.equals(this.digest, other.digest)) {
      return false;
    }
    if (!Objects.equals(this.algorithm, other.algorithm)) {
      return false;
    }
    return Objects.equals(this.format, other.format);
  }

  @Override
  public String toString() {
    return "BinaryData{"
        + "uri="
        + uri
        + ", size="
        + size
        + ", digest="
        + digest
        + ", algorithm="
        + algorithm
        + ", format="
        + format
        + '}';
  }
}
