package output.print;

import citation.data.Citation;

public class TextPrinter implements CitationPrinter {

	private int citation_hash;
	public int numPagesToPrint(Citation cite)
	{
		return 1;//how many should we return for a string print?
	}
	public String PrintPreview(Citation cite)
	{
		if(citation_hash==0||citation_hash!=cite.hashCode())
		{
			build_temp_Citation_storage(cite);
			Citation_Hash(cite);
		}
		return (String) temp_Citation_formated_output_storage;
	}
	public boolean Print(Citation cite)
	{
		if(citation_hash==0||citation_hash!=cite.hashCode())
		{
			build_temp_Citation_storage(cite);
			Citation_Hash(cite);
			System.out.println(temp_Citation_formated_output_storage);
			return false;
		}
		System.out.println(temp_Citation_formated_output_storage);
		return true;
	}
	public boolean connectToPrinter(String PrinterLocation)
	{
		return true;
	}
    private void Citation_Hash(Citation cite)
    {
    	citation_hash=cite.hashCode();
    }
    
    private void build_temp_Citation_storage(Citation cite)//Gets built once for, used to query pages, print, or return print previews as needed.
    {
    	temp_Citation_formated_output_storage= cite.toPrettyString();
    }
    private Object temp_Citation_formated_output_storage;//store temp as something, probably postcipt or image, regenerate if Citation_Hash of the citation changes(meaning the citation changes). 



}
