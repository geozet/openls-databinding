package nl.geozet.openls.databinding.common;

public interface XmlNamespaceConstants {
    String OPENLS_NAMESPACE_URI = "http://www.opengis.net/xls";
    String OPENLS_NAMESPACE_PREFIX = "xls";

    String OGC_GML_NAMESPACE_URI = "http://www.opengis.net/gml/3.2";
    String OGC_GML_NAMESPACE_PREFIX = "gml";

    /*
     * public String toXML(){ String xml = "<" +
     * XmlNamespaceConstants.OPENLS_NAMESPACE_PREFIX + ":GeocodeResponse>";
     * 
     * xml += "</" + XmlNamespaceConstants.OPENLS_NAMESPACE_PREFIX +
     * ":GeocodeResponse>"; return xml; }
     */
}
