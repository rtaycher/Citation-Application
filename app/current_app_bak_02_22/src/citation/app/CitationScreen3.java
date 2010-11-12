/*
 * CitationScreen3.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

package citation.app;

import java.util.Date;
import java.util.Vector;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.io.http.HttpDateParser;
import citation.data.*;
import citation.ui.*;
import citation.query.*;

//import java.util.Calendar;

/**
 * 
 */
class CitationScreen3 extends MainScreen {
    
    public static GPSLocation currentLocation = null;  
    
    private EditField field_gps;
    private ObjectChoiceField field_gps_addrlist;
    private EditField field_address;
    private EditField field_city;
    private EditField field_state;
    private EditField field_zip;
    
    private DateField field_date;
    private DateField field_time;
    
    private CheckboxField field_highway;
    private CheckboxField field_commonplace;

    
    private Citation c = null;
    
    CitationScreen3(Citation _c) {    
        super();
        c = _c;
                
        // get a fix for the current location
        // current location up-to-date as GPS changes through the life of the app
        QueryMgr query = ((CitationApp)(UiApplication.getUiApplication())).getQueryMgr();
        CitationScreen3.currentLocation = (GPSLocation)query.getGPSLocationObj();

        String cNumberString = Integer.toString(c.Number);
        CustomLabelField lf = new CustomLabelField("OREGON UNIFORM CITATION: #" + cNumberString, LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        lf.setBackgroundColor(0x00194E84);
        lf.setTextColor(0x00ffffff);
        FontFamily fontFamily0[] = FontFamily.getFontFamilies(); 
        Font font = fontFamily0[1].getFont(FontFamily.CBTF_FONT, 14); 
        lf.setFont(font); 
        
        add(lf);         
        
        add(new SeparatorField());
      
        CustomLabelField lbl = new CustomLabelField("********* TIME & PLACE INFORMATION *********", Field.FIELD_HCENTER | LabelField.USE_ALL_WIDTH);
        lbl.setBackgroundColor(Color.LIGHTGREY);
        lbl.setTextColor(Color.BLUE);
    
        FontFamily fontFamily2[] = FontFamily.getFontFamilies(); 
        Font fnt2 = fontFamily2[1].getFont(FontFamily.CBTF_FONT, 14); 
        lbl.setFont(fnt2);
        add(lbl);
    
        add(new SeparatorField());

        // ButtonField row - set all buttons at the bottom row of the form
        HorizontalFieldManager gpsButtonManager = new HorizontalFieldManager();

        // Add gps button
        ButtonField btn_gps = new ButtonField("Get GPS Location", ButtonField.CONSUME_CLICK);
        btn_gps.setChangeListener((new GPSButtonListener3(this)));
        gpsButtonManager.add(btn_gps);

        // Add map button
        MapButtonField btn_map = new MapButtonField("Map", ButtonField.CONSUME_CLICK);
        btn_map.setChangeListener((new MapButtonListener()));
        gpsButtonManager.add(btn_map);
        
        add(gpsButtonManager);
        
        add(new SeparatorField());
        
        
        // EditField
        field_gps = new EditField("GPS Coord: ", "");
        add(field_gps);
        
        field_gps_addrlist = new ObjectChoiceField("GPS Address(es): ", null, 0);
        field_gps_addrlist.setChangeListener(new GSPSelectionChanged3(this));
        add(field_gps_addrlist);
        
        add(new SeparatorField());

         // EditField
        field_address = new EditField("Address (approx): ", "");
        add(field_address);   
        
        // ObjectChoiceField
        field_city = new EditField("City: ", "");
        add(field_city);                      
       
        // ObjectChoiceField
        field_state = new EditField("State: ", "");
        add(field_state); 
        
         // EditField
        field_zip = new EditField("Zip: ", "");
        add(field_zip);    
        
        add(new SeparatorField()); 
    
        field_date = new DateField("Date: ", Long.MIN_VALUE, DateField.DATE);
        field_date.setDate(System.currentTimeMillis());
        add(field_date);
        field_time = new DateField("Time: ", Long.MIN_VALUE, DateField.TIME);
        field_time.setDate(System.currentTimeMillis());
        add(field_time);  
        
        add(new SeparatorField()); 
        
         // CheckboxField
        field_highway = new CheckboxField("Highway", false);
        add(field_highway);
    
        // CheckboxField
        field_commonplace = new CheckboxField("Commonplace", false);
        add(field_commonplace);
        
        add(new SeparatorField());
        
        // ButtonField row - set all buttons at the bottom row of the form
        HorizontalFieldManager navButtonManager = new HorizontalFieldManager();

        // ButtonField
        ButtonField btn_prev;        
        btn_prev = new ButtonField("Prev", ButtonField.CONSUME_CLICK);
        btn_prev.setChangeListener(new ButtonListener3(this, c)); 
        navButtonManager.add(btn_prev);
                
        ButtonField btn_next;
        btn_next = new ButtonField("Next", ButtonField.CONSUME_CLICK);
        btn_next.setChangeListener(new ButtonListener3(this, c)); 
        navButtonManager.add(btn_next);

        add(navButtonManager);

        add(new SeparatorField());


        addMenuItem(saveItem3);
        addMenuItem(getItem3);
        
        
        restoreUIFieldsFromCitation();
     }
     
    public static class ButtonListener3 implements FieldChangeListener {
        Citation c;
        CitationScreen3 cs3;
                    
        public ButtonListener3(CitationScreen3 _cs3, Citation _n3){
            cs3 = _cs3;
            c = _n3;
        }
                    
        public void fieldChanged(Field field, int context) {
            ButtonField btn4 = (ButtonField) field;
            if(btn4.getLabel().toString() == "Next"){ 
                cs3.storeUIFieldsToCitation();
                UiApplication.getUiApplication().pushScreen(new CitationScreen4(c));
            }
            else if(btn4.getLabel().toString() == "Prev"){
                cs3.storeUIFieldsToCitation();
                UiApplication.getUiApplication().popScreen(cs3);
            }   
        }
    } 
    
     static final class MapButtonListener implements FieldChangeListener {
       
        public MapButtonListener(){
        }
        
        public void fieldChanged(Field field, int context) {
                MapButtonField btn = (MapButtonField)field;
                if (btn!=null) {
                        // update location on button selection
                        if (CitationScreen3.currentLocation != null && CitationScreen3.currentLocation.isValid()) {
                                btn.setCoordinates(currentLocation.getCoordinates());
                                btn.run();
                        }
                        else {
                                Dialog.inform("Unable to determine current location.");
                        }
                }
        }
    }
    
    static final class GPSButtonListener3 implements FieldChangeListener {
        private CitationScreen3 cs3 = null;
        
        public GPSButtonListener3(CitationScreen3 _cs3){
                cs3 = _cs3;
        }
        
        public void fieldChanged(Field field, int context) {
                ButtonField btn = (ButtonField)field;
                if (btn != null) {
	                if (CitationScreen3.currentLocation != null && CitationScreen3.currentLocation.isValid()) {
	                        double lat = CitationScreen3.currentLocation.getCoordinates().getLatitude();
	                        double lon = CitationScreen3.currentLocation.getCoordinates().getLongitude();
	                        String mapResult = HTTPGoogleAPI.ReverseGeoQuery(lat, lon);
	                        
	                        // update form fields with information
	                        String gpsString = Double.toString(lat) + ", " + Double.toString(lon);
	                        cs3.field_gps.setText(gpsString);
	                        
	                        String[] addressList = HTTPGoogleAPI.getAddressList(mapResult);
	                        
	                        if (addressList.length > 0)
	                        {
	                                cs3.field_gps_addrlist.setChoices(addressList);
	                                cs3.field_address.setText(HTTPGoogleAPI.parseAddressResult(addressList[0], HTTPGoogleAPI.ParseType.ADDRESS));
	                                cs3.field_city.setText(HTTPGoogleAPI.parseAddressResult(addressList[0], HTTPGoogleAPI.ParseType.CITY));
	                                cs3.field_state.setText(HTTPGoogleAPI.parseAddressResult(addressList[0], HTTPGoogleAPI.ParseType.STATE));
	                                cs3.field_zip.setText(HTTPGoogleAPI.parseAddressResult(addressList[0], HTTPGoogleAPI.ParseType.ZIP));
	                        }
	                }
	                else {
	                	StringBuffer msg = new StringBuffer("Unable to determine current location. ");
	                    Dialog.inform(msg.toString());
	                }
                }
        }
    }
    
    static final class GSPSelectionChanged3 implements FieldChangeListener {
        
        private CitationScreen3 cs3;
        
        public GSPSelectionChanged3(CitationScreen3 _cs3){
                cs3 = _cs3;
        }
        
        public void fieldChanged(Field field, int context) {
                        ObjectChoiceField fld = null;
                        try {
                                 fld = (ObjectChoiceField)field;
                        }
                        catch (ClassCastException ex){
                                return;
                        }
                        
                // only update address fields if user has selected an entry
                if (fld != null && context == 2) 
                {
                        int idx = fld.getSelectedIndex();
                        String fullAddress = (String)fld.getChoice(idx);
                        
                        cs3.field_address.setText(HTTPGoogleAPI.parseAddressResult(fullAddress, HTTPGoogleAPI.ParseType.ADDRESS));
                        cs3.field_city.setText(HTTPGoogleAPI.parseAddressResult(fullAddress, HTTPGoogleAPI.ParseType.CITY));
                        cs3.field_state.setText(HTTPGoogleAPI.parseAddressResult(fullAddress, HTTPGoogleAPI.ParseType.STATE));
                        cs3.field_zip.setText(HTTPGoogleAPI.parseAddressResult(fullAddress, HTTPGoogleAPI.ParseType.ZIP));
                }
         }
     }
      
    
    
 
 
    private MenuItem saveItem3 = new MenuItem("Save", 110, 10) {
        public void run() {
                
                storeUIFieldsToCitation();
                CitationStore.Save(); 
                
        }
    };

    private MenuItem getItem3 = new MenuItem("Get", 110, 11) {
        public void run() {
                
                CitationStore.Restore();
                restoreUIFieldsFromCitation();
           
        }
    };     
    
    /**
     * storeUIFieldsToCitation - transfer the UI field values to the citation record
     */
    private void storeUIFieldsToCitation()
    {
        c.Loc.setElement(CLocation.ADDRESS, field_address.getText());
        c.Loc.setElement(CLocation.CITY, field_city.getText());
        c.Loc.setElement(CLocation.STATE, field_state.getText());
        c.Loc.setElement(CLocation.ZIP, field_zip.getText()); 
        
        // get the time value and re-convert back to a full date string
        c.Violation.setElement(CViolation.DATE, (new Date(field_date.getDate()).toString()));
        c.Violation.setElement(CViolation.TIME, (new Date(field_time.getDate()).toString()));
        
        String hwyStatus = field_highway.getChecked() ? "true" : "false";
        String commonStatus = field_commonplace.getChecked() ? "true" : "false";
        c.Loc.setElement(CLocation.HIGHWAY, hwyStatus);  
        c.Loc.setElement(CLocation.COMMONPLACE, commonStatus);  
        System.out.println("in storeUIFieldsToCitation: " + hwyStatus);
        System.out.println("in storeUIFieldsToCitation: " + commonStatus);
    }
  
    
    /**
     * restoreUIFieldsFromCitation - populate the UI fields from those currently
     *                               stored in the citation record
     */
    private void restoreUIFieldsFromCitation()
    {
        field_address.setText(c.Loc.getElement(CLocation.ADDRESS));
        field_city.setText(c.Loc.getElement(CLocation.CITY));
        field_state.setText(c.Loc.getElement(CLocation.STATE));
        field_zip.setText(c.Loc.getElement(CLocation.ZIP));
        
        String temp_date = c.Violation.getElement(CViolation.DATE);
        // that's dumb - date.toString() inserts GMT which HttpDateParser can't seem to handle
        int startGMT = temp_date.indexOf("GMT ");
        int endGMT = startGMT + 4; 
        if ( startGMT > 0 )
        {
                temp_date = temp_date.substring(0, startGMT) + temp_date.substring(endGMT);
        }
        long td = HttpDateParser.parse(temp_date);
        field_date.setDate(td);
        
        String temp_time = c.Violation.getElement(CViolation.TIME);
        // that's dumb - date.toString() inserts GMT which HttpDateParser can't seem to handle
        startGMT = temp_time.indexOf("GMT ");
        endGMT = startGMT + 4; 
        if ( startGMT > 0 )
        {   // strip 'GMT ' out of the time string
                temp_time = temp_time.substring(0, startGMT) + temp_time.substring(endGMT);
        }
        long tt = HttpDateParser.parse(temp_time);
        field_time.setDate(tt);
        
        boolean hwyStatus = c.Loc.getElement(CLocation.HIGHWAY).equals("true");
        boolean commonStatus = c.Loc.getElement(CLocation.COMMONPLACE).equals("true");
        System.out.println("commonStatus: " + commonStatus);
        System.out.println("getElement: " + c.Loc.getElement(CLocation.COMMONPLACE).equals("true"));
        field_highway.setChecked(hwyStatus);
        field_commonplace.setChecked(commonStatus);    
    } 
     
} 
