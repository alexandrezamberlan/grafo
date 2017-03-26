package grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author alexandrezamberlam
 * @author sylviovieira
 */
public class Util {

    public static Grafo lerArquivo(File arquivo, JTextArea jtaTextoArquivoAberto, JTextArea jtaMatrizAdjacencia) {
        if (arquivo == null) {
            return null;
        }        
        //para garantir controle de duplicidade
        Set<String> listaMoleculas = new HashSet<String>();
        
        //para a matriz de adjacencia
        Grafo grafoRedeInteracoes;
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(arquivo));
            String linha;
            while (entrada.ready()) {
                linha = entrada.readLine();
                //para splitar as moleculas da linha
                String[] moleculasDaLinha = linha.split(" |\t");

                listaMoleculas.add(moleculasDaLinha[0]);
                listaMoleculas.add(moleculasDaLinha[1]);
            }
            entrada.close();
         
            //para garantir ordenacao
            Set<String> listaOrdenadaMoleculas = new TreeSet<String>();
            listaOrdenadaMoleculas.addAll(listaMoleculas);
            
            //para controlar posicao na lista
            ArrayList<String> listaNumeradaMoleculas = new ArrayList<String>();
            listaNumeradaMoleculas.addAll(listaOrdenadaMoleculas);
            
            //para gerar a matriz de adjacencia
            grafoRedeInteracoes = new Grafo(listaNumeradaMoleculas);
            entrada = new BufferedReader(new FileReader(arquivo));
            while (entrada.ready()) {
                linha = entrada.readLine();
                //para splitar as moleculas da linha
                String[] moleculasDaLinha = linha.split(" |\t");
                //System.out.println(moleculasDaLinha[0] + "  " + moleculasDaLinha[1]);
                grafoRedeInteracoes.inserirArco(listaNumeradaMoleculas.indexOf(moleculasDaLinha[0]), listaNumeradaMoleculas.indexOf(moleculasDaLinha[1]));
            }
            entrada.close();
            
            //para preencher JTAreas
            for (Iterator<String> indice = listaOrdenadaMoleculas.iterator(); indice.hasNext();) {
                jtaTextoArquivoAberto.append(indice.next());
                jtaTextoArquivoAberto.append("\n");
            }
            //para preencher JTFields
            grafoRedeInteracoes.exibirGrafo(listaNumeradaMoleculas, jtaMatrizAdjacencia);
            return grafoRedeInteracoes;
        } catch (IOException e) {
        }
        return null;
    }
    
    public static File carregarArquivo() {
        JFileChooser janelaCarregarArquivo = new JFileChooser();
        janelaCarregarArquivo.setMultiSelectionEnabled(false);
        if (janelaCarregarArquivo.showOpenDialog(janelaCarregarArquivo) == JFileChooser.APPROVE_OPTION) {
            return janelaCarregarArquivo.getSelectedFile();
        }
        return null;
    }
}
