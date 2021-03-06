// This class was generated by 172 StubGenerator.
// Contents subject to change without notice.
// @generated

package citation.comm;

import javax.xml.rpc.JAXRPCException;
import javax.xml.namespace.QName;
import javax.microedition.xml.rpc.Operation;
import javax.microedition.xml.rpc.Type;
import javax.microedition.xml.rpc.ComplexType;
import javax.microedition.xml.rpc.Element;

public class CitationCatcher_Stub implements citation.comm.CitationCatcher, javax.xml.rpc.Stub {
	private String[] _propertyNames;
	private Object[] _propertyValues;

	public CitationCatcher_Stub() {
		_propertyNames = new String[] {ENDPOINT_ADDRESS_PROPERTY};
		_propertyValues = new Object[] {"http://capstone10.cs.pdx.edu:8080/MyWebService/services/CitationCatcher"};
	}

	public void _setProperty(String name, Object value) {
		int size = _propertyNames.length;
		for (int i = 0; i < size; ++i) {
			if (_propertyNames[i].equals(name)) {
				_propertyValues[i] = value;
				return;
			}
		}
		// Need to expand our array for a new property
		String[] newPropNames = new String[size + 1];
		System.arraycopy(_propertyNames, 0, newPropNames, 0, size);
		_propertyNames = newPropNames;
		Object[] newPropValues = new Object[size + 1];
		System.arraycopy(_propertyValues, 0, newPropValues, 0, size);
		_propertyValues = newPropValues;

		_propertyNames[size] = name;
		_propertyValues[size] = value;
	}

	public Object _getProperty(String name) {
		for (int i = 0; i < _propertyNames.length; ++i) {
			if (_propertyNames[i].equals(name)) {
				return _propertyValues[i];
			}
		}
		if (ENDPOINT_ADDRESS_PROPERTY.equals(name) || USERNAME_PROPERTY.equals(name) || PASSWORD_PROPERTY.equals(name)) {
			return null;
		}
		if (SESSION_MAINTAIN_PROPERTY.equals(name)) {
			return new java.lang.Boolean(false);
		}
		throw new JAXRPCException("Stub does not recognize property: "+name);
	}

	protected void _prepOperation(Operation op) {
		for (int i = 0; i < _propertyNames.length; ++i) {
			op.setProperty(_propertyNames[i], _propertyValues[i].toString());
		}
	}

	// 
	//  Begin user methods
	// 

	public int ping() throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[0];

		Operation op = Operation.newInstance(_qname_ping, _type_ping, _type_pingResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		int result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object pingReturnObj = ((Object[])resultObj)[0];
		result = ((java.lang.Integer)pingReturnObj).intValue();
		return result;
	}

	public int postString(java.lang.String s) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[1];
		inputObject[0] = s;

		Operation op = Operation.newInstance(_qname_postString, _type_postString, _type_postStringResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		int result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object postStringReturnObj = ((Object[])resultObj)[0];
		result = ((java.lang.Integer)postStringReturnObj).intValue();
		return result;
	}

	public int openCitation(int citationNumber) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[1];
		inputObject[0] = new java.lang.Integer(citationNumber);

		Operation op = Operation.newInstance(_qname_openCitation, _type_openCitation, _type_openCitationResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		int result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object openCitationReturnObj = ((Object[])resultObj)[0];
		result = ((java.lang.Integer)openCitationReturnObj).intValue();
		return result;
	}

	public int postCitationEntry(int citationNumber, java.lang.String key, java.lang.String value) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[3];
		inputObject[0] = new java.lang.Integer(citationNumber);
		inputObject[1] = key;
		inputObject[2] = value;

		Operation op = Operation.newInstance(_qname_postCitationEntry, _type_postCitationEntry, _type_postCitationEntryResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		int result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object postCitationEntryReturnObj = ((Object[])resultObj)[0];
		result = ((java.lang.Integer)postCitationEntryReturnObj).intValue();
		return result;
	}

	public citation.comm.CloseCitationResponse closeCitation() throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[0];

		Operation op = Operation.newInstance(_qname_closeCitation, _type_closeCitation, _type_closeCitationResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		citation.comm.CloseCitationResponse result;
		// Convert the result into the right Java type.
		if (resultObj == null) {
			result = null;
		} else {
			result = new citation.comm.CloseCitationResponse();
		}
		return result;
	}

	public int postCitationAsXML(java.lang.String xml) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[1];
		inputObject[0] = xml;

		Operation op = Operation.newInstance(_qname_postCitationAsXML, _type_postCitationAsXML, _type_postCitationAsXMLResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		int result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object postCitationAsXMLReturnObj = ((Object[])resultObj)[0];
		result = ((java.lang.Integer)postCitationAsXMLReturnObj).intValue();
		return result;
	}
	// 
	//  End user methods
	// 

	protected static final QName _qname_citationNumber = new QName("http://DefaultNamespace", "citationNumber");
	protected static final QName _qname_closeCitation = new QName("http://DefaultNamespace", "closeCitation");
	protected static final QName _qname_closeCitationResponse = new QName("http://DefaultNamespace", "closeCitationResponse");
	protected static final QName _qname_key = new QName("http://DefaultNamespace", "key");
	protected static final QName _qname_openCitation = new QName("http://DefaultNamespace", "openCitation");
	protected static final QName _qname_openCitationResponse = new QName("http://DefaultNamespace", "openCitationResponse");
	protected static final QName _qname_openCitationReturn = new QName("http://DefaultNamespace", "openCitationReturn");
	protected static final QName _qname_ping = new QName("http://DefaultNamespace", "ping");
	protected static final QName _qname_pingResponse = new QName("http://DefaultNamespace", "pingResponse");
	protected static final QName _qname_pingReturn = new QName("http://DefaultNamespace", "pingReturn");
	protected static final QName _qname_postCitationAsXML = new QName("http://DefaultNamespace", "postCitationAsXML");
	protected static final QName _qname_postCitationAsXMLResponse = new QName("http://DefaultNamespace", "postCitationAsXMLResponse");
	protected static final QName _qname_postCitationAsXMLReturn = new QName("http://DefaultNamespace", "postCitationAsXMLReturn");
	protected static final QName _qname_postCitationEntry = new QName("http://DefaultNamespace", "postCitationEntry");
	protected static final QName _qname_postCitationEntryResponse = new QName("http://DefaultNamespace", "postCitationEntryResponse");
	protected static final QName _qname_postCitationEntryReturn = new QName("http://DefaultNamespace", "postCitationEntryReturn");
	protected static final QName _qname_postString = new QName("http://DefaultNamespace", "postString");
	protected static final QName _qname_postStringResponse = new QName("http://DefaultNamespace", "postStringResponse");
	protected static final QName _qname_postStringReturn = new QName("http://DefaultNamespace", "postStringReturn");
	protected static final QName _qname_s = new QName("http://DefaultNamespace", "s");
	protected static final QName _qname_value = new QName("http://DefaultNamespace", "value");
	protected static final QName _qname_xml = new QName("http://DefaultNamespace", "xml");
	protected static final Element _type_ping;
	protected static final Element _type_pingResponse;
	protected static final Element _type_postString;
	protected static final Element _type_postStringResponse;
	protected static final Element _type_openCitation;
	protected static final Element _type_openCitationResponse;
	protected static final Element _type_postCitationEntry;
	protected static final Element _type_postCitationEntryResponse;
	protected static final Element _type_closeCitation;
	protected static final Element _type_closeCitationResponse;
	protected static final Element _type_postCitationAsXML;
	protected static final Element _type_postCitationAsXMLResponse;
	static {
		// Create all of the Type's that this stub uses, once.
		ComplexType _complexType_ping;
		_complexType_ping = new ComplexType();
		_complexType_ping.elements = new Element[0];
		_type_ping = new Element(_qname_ping, _complexType_ping);
		Element _type_pingReturn;
		_type_pingReturn = new Element(_qname_pingReturn, Type.INT);
		ComplexType _complexType_pingResponse;
		_complexType_pingResponse = new ComplexType();
		_complexType_pingResponse.elements = new Element[1];
		_complexType_pingResponse.elements[0] = _type_pingReturn;
		_type_pingResponse = new Element(_qname_pingResponse, _complexType_pingResponse);
		Element _type_s;
		_type_s = new Element(_qname_s, Type.STRING);
		ComplexType _complexType_postString;
		_complexType_postString = new ComplexType();
		_complexType_postString.elements = new Element[1];
		_complexType_postString.elements[0] = _type_s;
		_type_postString = new Element(_qname_postString, _complexType_postString);
		Element _type_postStringReturn;
		_type_postStringReturn = new Element(_qname_postStringReturn, Type.INT);
		ComplexType _complexType_postStringResponse;
		_complexType_postStringResponse = new ComplexType();
		_complexType_postStringResponse.elements = new Element[1];
		_complexType_postStringResponse.elements[0] = _type_postStringReturn;
		_type_postStringResponse = new Element(_qname_postStringResponse, _complexType_postStringResponse);
		Element _type_citationNumber;
		_type_citationNumber = new Element(_qname_citationNumber, Type.INT);
		ComplexType _complexType_openCitation;
		_complexType_openCitation = new ComplexType();
		_complexType_openCitation.elements = new Element[1];
		_complexType_openCitation.elements[0] = _type_citationNumber;
		_type_openCitation = new Element(_qname_openCitation, _complexType_openCitation);
		Element _type_openCitationReturn;
		_type_openCitationReturn = new Element(_qname_openCitationReturn, Type.INT);
		ComplexType _complexType_openCitationResponse;
		_complexType_openCitationResponse = new ComplexType();
		_complexType_openCitationResponse.elements = new Element[1];
		_complexType_openCitationResponse.elements[0] = _type_openCitationReturn;
		_type_openCitationResponse = new Element(_qname_openCitationResponse, _complexType_openCitationResponse);
		Element _type_key;
		_type_key = new Element(_qname_key, Type.STRING);
		Element _type_value;
		_type_value = new Element(_qname_value, Type.STRING);
		ComplexType _complexType_postCitationEntry;
		_complexType_postCitationEntry = new ComplexType();
		_complexType_postCitationEntry.elements = new Element[3];
		_complexType_postCitationEntry.elements[0] = _type_citationNumber;
		_complexType_postCitationEntry.elements[1] = _type_key;
		_complexType_postCitationEntry.elements[2] = _type_value;
		_type_postCitationEntry = new Element(_qname_postCitationEntry, _complexType_postCitationEntry);
		Element _type_postCitationEntryReturn;
		_type_postCitationEntryReturn = new Element(_qname_postCitationEntryReturn, Type.INT);
		ComplexType _complexType_postCitationEntryResponse;
		_complexType_postCitationEntryResponse = new ComplexType();
		_complexType_postCitationEntryResponse.elements = new Element[1];
		_complexType_postCitationEntryResponse.elements[0] = _type_postCitationEntryReturn;
		_type_postCitationEntryResponse = new Element(_qname_postCitationEntryResponse, _complexType_postCitationEntryResponse);
		_type_closeCitation = new Element(_qname_closeCitation, _complexType_ping);
		_type_closeCitationResponse = new Element(_qname_closeCitationResponse, _complexType_ping);
		Element _type_xml;
		_type_xml = new Element(_qname_xml, Type.STRING);
		ComplexType _complexType_postCitationAsXML;
		_complexType_postCitationAsXML = new ComplexType();
		_complexType_postCitationAsXML.elements = new Element[1];
		_complexType_postCitationAsXML.elements[0] = _type_xml;
		_type_postCitationAsXML = new Element(_qname_postCitationAsXML, _complexType_postCitationAsXML);
		Element _type_postCitationAsXMLReturn;
		_type_postCitationAsXMLReturn = new Element(_qname_postCitationAsXMLReturn, Type.INT);
		ComplexType _complexType_postCitationAsXMLResponse;
		_complexType_postCitationAsXMLResponse = new ComplexType();
		_complexType_postCitationAsXMLResponse.elements = new Element[1];
		_complexType_postCitationAsXMLResponse.elements[0] = _type_postCitationAsXMLReturn;
		_type_postCitationAsXMLResponse = new Element(_qname_postCitationAsXMLResponse, _complexType_postCitationAsXMLResponse);
	}

}
