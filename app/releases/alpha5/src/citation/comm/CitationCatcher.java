// This class was generated by 172 StubGenerator.
// Contents subject to change without notice.
// @generated

package citation.comm;

public interface CitationCatcher extends java.rmi.Remote {
	public int ping() throws java.rmi.RemoteException;

	public int postString(java.lang.String s) throws java.rmi.RemoteException;

	public int openCitation(int citationNumber) throws java.rmi.RemoteException;

	public int postCitationEntry(int citationNumber, java.lang.String key, java.lang.String value) throws java.rmi.RemoteException;

	public citation.comm.CloseCitationResponse closeCitation() throws java.rmi.RemoteException;

	public int postCitationAsXML(java.lang.String xml) throws java.rmi.RemoteException;

}
