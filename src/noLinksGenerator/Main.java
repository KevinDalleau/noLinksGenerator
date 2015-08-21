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

	    for(String[] entry : genesList) {
	    	String geneId = entry[0];
	    	String geneSymbol = entry[4];
		    Request requester = new Request();
		    requester.getRelatedDrugs(geneSymbol);
	    }


	}

}
