/*
 *  Communication project implement communication point to point and multicast
 *  Copyright (C) 2010  Daniel Pelaez, Daniel Lopez, Hector Hurtado
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *  
 *  This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
 *  See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
 *  Any modifications to this file will be lost upon recompilation of the source schema. 
 *  Generated on: 2010.05.13 at 08:03:13 AM COT 
 */

package co.edu.uniquindio.utils.communication.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Address complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Address">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="source" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="destination" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address")
public class Address {

	/**
	 * Source name
	 */
	@XmlAttribute(required = true)
	protected String source;
	/**
	 * Destination name
	 */
	@XmlAttribute
	protected String destination;

	/**
	 * Builds address by source and destination names
	 * 
	 * @param source
	 *            Source name
	 * @param destination
	 *            Destination name
	 */
	public Address(String source, String destination) {
		this.source = source;
		this.destination = destination;
	}

	/**
	 * Builds an address empty
	 */
	Address() {
	}

	/**
	 * This method is used for knowing if the message is the same source and
	 * destination node
	 * 
	 * @return Returns true if the message is the same source and destination
	 *         node
	 */
	public boolean isMessageFromMySelf() {
		if (source != null && destination != null) {
			return source.equals(destination);
		} else {
			if (source != null) {
				return false;
			} else {
				if (destination != null) {
					return false;
				} else {
					return true;
				}
			}
		}
	}

	/**
	 * Gets the value of the source property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Sets the value of the source property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSource(String value) {
		this.source = value;
	}

	/**
	 * Gets the value of the destination property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Sets the value of the destination property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDestination(String value) {
		this.destination = value;
	}

}