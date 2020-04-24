package it.polito.tdp.nobel.model;

import it.polito.tdp.nobel.db.EsameDAO;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Model model = new Model();
		EsameDAO E = new EsameDAO();
		
		System.out.println(E.getTuttiEsami());
	
		model.calcolaSottoinsiemeEsami(50);
		
		
	}

}
