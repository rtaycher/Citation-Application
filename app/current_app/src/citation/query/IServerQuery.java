/*
 * IServerQuery.java
 *
 * Copyright (c) 2009-2010 PSU Capstone Team D
 * Scott Glazer, Cong Hoang, Ba Nguyen, Marek Dolgos,
 * Steve Phelps, Mark Smith, Roman Taycher
 *
 * Citation Application is free/open source software released under
 * the unmodified MIT/X11 license. A copy can be found in the
 * LICENSE file or at:
 *
 *     http://www.opensource.org/licenses/mit-license.php
 *
 */
package citation.query;


/**
 * IServerQuery - interface definition for citiation.query and QueryMgr
 *
 */
public interface IServerQuery {

	Object findDMVRecord(String _license);
	Object getCourtInfo();
	Object getGPSLocationObj();
	
}
