package nl.geozet.openls.databinding.openls;

import java.util.Vector;

import nl.geozet.openls.databinding.common.XmlNamespaceConstants;

/**
 * http://schemas.opengis.net/ols/1.2.0/LocationUtilityService.xsd
 * 
 * <pre>
 * &lt;!-- ====================================================== --&gt; 
 * &lt;element name="GeocodeRequest" type="xls:GeocodeRequestType" substitutionGroup="xls:_RequestParameters"&gt; 
 *  &lt;annotation&gt;
 *      &lt;documentation&gt;Geocode Service Request&lt;/documentation&gt; 
 *  &lt;/annotation&gt;
 * &lt;/element&gt; 
 * &lt;!-- ====================================================== --&gt; 
 * &lt;complexType name="GeocodeRequestType"&gt; 
 * &lt;annotation&gt;
 *      &lt;documentation&gt;Geocode Request.&lt;/documentation&gt; 
 * &lt;/annotation&gt;
 * &lt;complexContent&gt; 
 *  &lt;extension base="xls:AbstractRequestParametersType"&gt;
 *  &lt;sequence&gt; 
 *      &lt;element ref="xls:Address" maxOccurs="unbounded"/&gt; 
 *  &lt;/sequence&gt;
 *  &lt;attribute name="returnFreeForm" type="boolean" use="optional" default="false"&gt; 
 *      &lt;annotation&gt; 
 *          &lt;documentation&gt;Used to request freeform addresses in the response, as opposed to structured adddresses&lt;/documentation&gt; 
 *      &lt;/annotation&gt; 
 *  &lt;/attribute&gt; 
 * &lt;/extension&gt;
 * &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * 
 * </pre>
 * 
 * @author strampel@atlis.nl
 * @author prinsmc@minlnv.nl
 * @since 1.6
 */

public class GeocodeRequest {
    private final Vector<Address> addressList = new Vector<>();

    public void addAddress(Address val) {
        this.addressList.add(val);
    }

    public Address getAddressAt(int i) {
        return this.addressList.get(i);
    }

    public int getAddressSize() {
        return this.addressList.size();
    }

    public String toXML() {
        String xml = "<" + XmlNamespaceConstants.OPENLS_NAMESPACE_PREFIX
                + ":GeocodeRequest " + "xmlns:"
                + XmlNamespaceConstants.OPENLS_NAMESPACE_PREFIX + "=\""
                + XmlNamespaceConstants.OPENLS_NAMESPACE_URI + "\" " + "xmlns:"
                + XmlNamespaceConstants.OGC_GML_NAMESPACE_PREFIX + "=\""
                + XmlNamespaceConstants.OGC_GML_NAMESPACE_URI + "\">";
        for (final Address addrl : this.addressList) {
            xml += addrl.toXML();
        }
        xml += "</" + XmlNamespaceConstants.OPENLS_NAMESPACE_PREFIX
                + ":GeocodeRequest>";
        return xml;
    }
}
