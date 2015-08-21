package noLinksGenerator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class Main {

	public static void main(String[] args) throws IOException {
		Reader drugFile = new FileReader("./drugs.tsv");
		Reader geneFile = new FileReader("./genes.tsv");

		CSVReader readerDrug = new CSVReader(drugFile,'\t');
		CSVReader readerGene = new CSVReader(geneFile,'\t');

	    List<String[]> genesList = readerGene.readAll();
	    List<String[]> drugsList = readerDrug.readAll();
	    ArrayList<String> relatedDrugs = new ArrayList<String>();

	    for(String[] genes : genesList) {
	    	String geneId = genes[0];
//	    	String geneSymbol = "EGFR";
	    	String geneSymbol = genes[4];
	    	if(geneSymbol != "null") {
	    		Request requester = new Request();
			    relatedDrugs = requester.getRelatedDrugs(geneSymbol);
			    for(String[] drugs : drugsList) {
			    	String drugId = drugs[0];
			    	String drugName = drugs[1].toUpperCase();
			    	if(relatedDrugs.contains('"'+drugName+'"')) {
			    		System.out.println(drugName+" est contenu dans "+ relatedDrugs.toString());
			    	}
			    }
	    	}
		    
		    
	    }


	}

}
