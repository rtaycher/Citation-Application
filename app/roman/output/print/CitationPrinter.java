package output.print;
import citation.data.*;

public interface CitationPrinter{
	public int numPagesToPrint(Citation cite);
	public String PrintPreview(Citation cite);
	public boolean Print(Citation cite);
	public boolean connectToPrinter(String PrinterLocation);
    //private int Citation_Hash;
    //private void build_temp_Citation_storage//Gets built once for, used to query pages, print, or return print previews as needed.
    //private Citation temp_Citation_storage;//store temp as something, probably postcipt or image, regenerate if Citation_Hash of the citation changes(meaning the citation changes). 

}
