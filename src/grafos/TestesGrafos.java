/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author alexandrezamberlam
 */
public class TestesGrafos {

    public static void main(String args[]) {
        Scanner teclado = new Scanner(System.in);
        
        //para controlar duplicacao - HashSet
        Set<String> lista = new HashSet<String>();
        
        String linha;
        for (int i = 0; i < 5; i++) {
            linha = teclado.nextLine();
            String[] palavrasDaLinha = linha.split(" ");
            for (int j = 0; j < palavrasDaLinha.length; j++) {
                lista.add(palavrasDaLinha[j]);
            }
        }
        //para garantir ordenacao
        Set<String> listaOrdenada = new TreeSet<String>();
        listaOrdenada.addAll(lista);
        
        ArrayList<String> listaNumerada = new ArrayList<String>();
        listaNumerada.addAll(listaOrdenada);

        System.out.println("elementos da lista");
        int i = 0;
        for (Iterator<String> indice = listaOrdenada.iterator(); indice.hasNext(); i++) {
            String resposta = indice.next();
            System.out.println(resposta +  " - " + listaNumerada.indexOf(resposta));    
        }  
    }

}
