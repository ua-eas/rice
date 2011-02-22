package org.kuali.rice.shareddata.api.campus;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.kuali.rice.core.mo.ModelBuilder;
import org.kuali.rice.core.mo.ModelObjectComplete;

@XmlRootElement(name = CampusType.Constants.ROOT_ELEMENT_NAME)
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = CampusType.Constants.TYPE_NAME, propOrder = {
		CampusType.Elements.CODE,
		CampusType.Elements.NAME,
		CampusType.Elements.ACTIVE,
		"_elements"
})
public final class CampusType implements CampusTypeContract, ModelObjectComplete{
	private static final long serialVersionUID = -6325716665728047946L;

	@XmlElement(name = Elements.CODE, required=true)
	private String code;
	@XmlElement(name = Elements.NAME, required=false)
	private String name;
	@XmlElement(name = Elements.ACTIVE, required=false)
	private boolean active;
	
	@SuppressWarnings("unused")
    @XmlAnyElement
    private final Collection<org.w3c.dom.Element> _elements = null;
	
	 /** 
     * This constructor should never be called.  It is only present for use during JAXB unmarshalling. 
     */
    private CampusType() {
    	this.code = null;
    	this.name = null;
    	this.active = false;
    }
    
    /**
	 * Constructs a CampusType from the given builder.  This constructor is private and should only
	 * ever be invoked from the builder.
	 * 
	 * @param builder the Builder from which to construct the campus type
	 */
    private CampusType(Builder builder) {
        this.code = builder.getCode();
        this.name = builder.getName();
        this.active = builder.isActive();
    }
    
	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean isActive() {
		return this.active; 
	}

	/**
     * This builder is used to construct instances of CampusType.  It enforces the constraints of the {@link CampusTypeContract}.
     */
    public static class Builder implements CampusTypeContract, ModelBuilder, Serializable {
		private static final long serialVersionUID = 1807428861457935238L;
		
		private String code;
        private String name;
        private boolean active;

		/**
		 * Private constructor for creating a builder with all of it's required attributes.
		 */
        private Builder(String code) {
            setCode(code);
			setActive(true);
        }

        /**
         * Creates a builder from the given campus type code.
         * 
         * @param code the campus type code
         * @return an instance of the builder with the code already populated
         * @throws IllegalArgumentException if the code is null or blank
         */
        public static Builder create(String code) {
            return new Builder(code);
        }

        /**
         * Creates a builder by populating it with data from the given {@link CampusTypeContract}.
         * 
         * @param contract the contract from which to populate this builder
         * @return an instance of the builder populated with data from the contract
         */
        public static Builder create(CampusTypeContract contract) {
            Builder builder =  new Builder(contract.getCode());
            builder.setName(contract.getName());
            builder.setActive(contract.isActive());
            return builder;
        }

		/**
		 * Sets the value of the code on this builder to the given value.
		 * 
		 * @param code the code value to set, must not be null or blank
		 * @throws IllegalArgumentException if the code is null or blank
		 */
        public void setCode(String code) {
            if (StringUtils.isBlank(code)) {
                throw new IllegalArgumentException("code is blank");
            }
            this.code = code;
        }

		public void setName(String name) {
			this.name = name;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

		@Override
		public String getCode() {
			return code;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public boolean isActive() {
			return active;
		}

		/**
		 * Builds an instance of a CampusType based on the current state of the builder.
		 * 
		 * @return the fully-constructed CampusType
		 */
        @Override
        public CampusType build() {
            return new CampusType(this);
        }
		
    }
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, Constants.HASH_CODE_EQUALS_EXCLUDE);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(obj, this, Constants.HASH_CODE_EQUALS_EXCLUDE);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	/**
	 * Defines some internal constants used on this class.
	 */
	static class Constants {
		final static String ROOT_ELEMENT_NAME = "campusType";
		final static String TYPE_NAME = "CampusTypeType";
		final static String[] HASH_CODE_EQUALS_EXCLUDE = { "_elements" };
	}
	
	/**
	 * A private class which exposes constants which define the XML element names to use
	 * when this object is marshalled to XML.
	 */
	static class Elements {
		final static String CODE = "code";
		final static String NAME = "name";
		final static String ACTIVE = "active";
	}
}
