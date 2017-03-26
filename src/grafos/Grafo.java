package grafos;

import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author alexandrezamberlam
 * @author sylviovieira
 */
public class Grafo {

    int matrizAdj[][];
    int qtdVertices;
    int qtdArestas;
    ArrayList<String> listaMoleculas;

    public Grafo() {

    }

    public Grafo(ArrayList lista) {
        listaMoleculas = lista;
        qtdVertices = lista.size();
        qtdArestas = 0;
        matrizAdj = new int[qtdVertices][qtdVertices];
        for (int i = 0; i < qtdVertices; i++) {
            for (int j = 0; j < qtdVertices; j++) {
                matrizAdj[i][j] = 0;
            }
        }
    }

    public void inserirArco(int origem, int destino) {
        if (matrizAdj[origem][destino] == 0) {
            matrizAdj[origem][destino] = 1;
            qtdArestas++;
        }
    }

    public void exibirGrafo(ArrayList lista, JTextArea jtaMatrizAdjacencia) {
        //System.out.print("\t");
        jtaMatrizAdjacencia.append("\t");
        for (int i = 0; i < lista.size(); i++) {
            //System.out.print(lista.get(i) + "\t");
            jtaMatrizAdjacencia.append(lista.get(i) + "\t");
        }
        //System.out.println();
        jtaMatrizAdjacencia.append("\n");
        for (int i = 0; i < qtdVertices; i++) {
            //System.out.print(lista.get(i) + ":\t");
            jtaMatrizAdjacencia.append(lista.get(i) + ":\t");
            for (int j = 0; j < qtdVertices; j++) {
                //System.out.print(matrizAdj[i][j] + "\t");
                jtaMatrizAdjacencia.append(matrizAdj[i][j] + "\t");
            }
            //System.out.println();
            jtaMatrizAdjacencia.append("\n");
        }
    }

    public String calcularMaiorGrau() {
        int maiorGrau = 0;
        int qtdGraus = 0;
        int grau;

        for (int vertice = 0; vertice < qtdVertices; vertice++) {
            grau = 0;
            for (int i = 0; i < qtdVertices; i++) {
                if (matrizAdj[vertice][i] == 1) {
                    grau++;
                }
                if (matrizAdj[i][vertice] == 1) {
                    grau++;
                }
            }
            if (grau > qtdGraus) {
                qtdGraus = grau;
                maiorGrau = vertice;
            }
        }
        return listaMoleculas.get(maiorGrau);
    }

    public String calcularMaiorIncidencia() {
        int maiorGrau = 0;
        int qtdGraus = 0;
        int grau;

        for (int vertice = 0; vertice < qtdVertices; vertice++) {
            grau = 0;
            for (int i = 0; i < qtdVertices; i++) {
                if (matrizAdj[i][vertice] == 1) {
                    grau++;
                }
            }
            if (grau > qtdGraus) {
                qtdGraus = grau;
                maiorGrau = vertice;
            }
        }
        return listaMoleculas.get(maiorGrau);
    }

    public String calcularMaiorPartida() {
        int maiorGrau = 0;
        int qtdGraus = 0;
        int grau;

        for (int vertice = 0; vertice < qtdVertices; vertice++) {
            grau = 0;
            for (int i = 0; i < qtdVertices; i++) {
                if (matrizAdj[vertice][i] == 1) {
                    grau++;
                }
            }
            if (grau > qtdGraus) {
                qtdGraus = grau;
                maiorGrau = vertice;
            }
        }
        return listaMoleculas.get(maiorGrau);
    }

    public int calcularGrau(String nomeMolecula) {
        int qtdGraus = 0;
        if (listaMoleculas.contains(nomeMolecula)) {
            for (int i = 0; i < qtdVertices; i++) {
                if (matrizAdj[listaMoleculas.indexOf(nomeMolecula)][i] != 0) {
                    qtdGraus++;
                }
                if (matrizAdj[i][listaMoleculas.indexOf(nomeMolecula)] != 0) {
                    qtdGraus++;
                }
            }
        }
        return qtdGraus;
    }

    private void caminhoR(int no, int visitados[],StringBuffer caminho) {
        int i;
        visitados[no] = 1;
        for (i = 0; i < qtdVertices; i++) {
            if (matrizAdj[no][i] != 0 && visitados[i] == 0) {
                //System.out.println(listaMoleculas.get(i)); //usar o nodo
                caminho.append(listaMoleculas.get(i));
                caminho.append("\n");
                caminhoR(i, visitados, caminho);
            }
        }
    }
    
    public String mostrarCaminho(String nomeMoleculaOrigem) {
        int visitados[] = new int[qtdVertices];
        int no;
        //inicializando o vetor de visitados
        for (no = 0; no < qtdVertices; no++) {
            visitados[no] = 0;
        }
        StringBuffer caminho = new StringBuffer();
        caminhoR(listaMoleculas.indexOf(nomeMoleculaOrigem), visitados, caminho);
        return caminho.toString();
    }
}
