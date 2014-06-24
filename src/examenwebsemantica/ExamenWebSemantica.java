/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examenwebsemantica;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author hp
 */
public class ExamenWebSemantica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        OntModel propiedadesOntology = ModelFactory.createOntologyModel();
        OntModel modeloOntology = ModelFactory.createOntologyModel();
        try {
            InputStream file = new FileInputStream("C:/Users/hp/Desktop/WebSemantica.owl");
//            System.out.println(file.read());
            //leer RDF cargar el XML
            modeloOntology.read(file, "");
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        //    Iterator<DatatypeProperty> individuos = propiedadesOntology.listDatatypeProperties();
        Iterator<OntClass> clases = modeloOntology.listClasses();
        Iterator<Individual> individuos = modeloOntology.listIndividuals();
        Iterator<ObjectProperty> propiedades = modeloOntology.listObjectProperties();

//       AllValuesFromRestriction restriccion= modeloOntology.getAllValuesFromRestriction(null);
//         
//   
//          System.out.println(restriccion.getLocalName());



        // ArrayList<OntClass> clasesL = (ArrayList<OntClass>) modeloOntology.listClasses();
//         ArrayList<Individual> individuosL = (ArrayList<Individual>) modeloOntology.listIndividuals();



        /*
         System.out.println("Individuos");
         while (individuos.hasNext()) {

         System.out.println(individuos.next().getLocalName());
         //Statement s=individuos.next().getProperty(null);
         RDFNode t = individuos.next().getPropertyValue(null);
         OntClass u = individuos.next().getOntClass();
         //System.out.println("property1: "+ s.getString());
         System.out.println("property: " + t.toString());
         System.out.println("property: " + u.getLocalName());

         }
         System.out.println("Propiedades");
         while (individuos.hasNext()) {
         System.out.println(propiedades.next().getLocalName());
         }

         System.out.println("-------------------------------------------");
         System.out.println("Clases");
         while (clases.hasNext()) {
         System.out.println("Clase:");
         System.out.println(clases.next().getLocalName());
         System.out.println(clases.next().getSubClass());
         //System.out.println("individuos"+clases.next().); 

         ExtendedIterator instancias = clases.next().listInstances();
         System.out.println("Instancias:");
         while (instancias.hasNext()) {

         System.out.println(instancias.next());
         //System.out.println("individuos"+clases.next().); 
         }
         }
         */
        System.out.println("Propiedades");
        while (propiedades.hasNext()) {
            
            ObjectProperty p=propiedades.next();
            System.out.println(p.getLocalName());
          //  System.out.println("Rango2: " + propiedades.next().getRange().getLocalName());
            Iterator inversa = p.listInverse();
            
             Iterator rangeIt = p.listRange();
             
//                while(rangeIt.hasNext()){
//
//                    OntClass rango = (OntClass) rangeIt.next();
//                    System.out.println("rango: " + rango.getLocalName());
//                }
                
                
                
                
                
            while (inversa.hasNext()) {
                System.out.println("Inversa: " + inversa.next().toString());
           
            }
            /* 
            Iterator dominio = propiedades.next().listDomain();
            while (dominio.hasNext()) {
                System.out.println("Dominio: " + dominio.next().toString());
           
            }
            Iterator rango = propiedades.next().listRange();
            while (rango.hasNext()) {
                System.out.println("Rango: " + rango.next().toString());
           
            }
            */
            
        }

        
        
        System.out.println("Clases/Instancias/Individuos:");
        for (Iterator<OntClass> i = modeloOntology.listClasses(); i.hasNext();) {
            OntClass cls = i.next();
            if (cls.getLocalName().equalsIgnoreCase("null") == false) {
//            if (cls != null) {
                System.out.println("Clase" + cls.getLocalName() + ": ");
                //         }
                for (Iterator it = cls.listInstances(true); it.hasNext();) {
                    Individual ind = (Individual) it.next();
                    if (ind.isIndividual()) {
                        System.out.println("Individuo:" + ind.getLocalName() + " ");
                    }
                }
            }
            System.out.println();
        }



    }
}
