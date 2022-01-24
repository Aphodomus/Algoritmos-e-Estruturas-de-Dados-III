import java.io.RandomAccessFile;
import java.io.File;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import datastructures.ArvoreBMais;
import datastructures.ListaInvertida;
import java.util.Scanner;

public class CRUDVoteAnswer extends CRUD<Vote> {
    // Attributes
    public ArvoreBMais<ParIDUserIDAnswer> arvore;
    public ArvoreBMais<ParIDUserIDVote> arvore2;
    public ListaInvertida listaInvertida;

    // Special methods
    public CRUDVoteAnswer(String filename) throws Exception {
        super(Vote.class.getConstructor(), filename);

        File file = new File("dados/" + filename);
        
        if (!file.exists()) {
            file.mkdir();
        }

        arvore = new ArvoreBMais<>(ParIDUserIDAnswer.class.getConstructor(), 5, "dados/" + filename + ".db");
        arvore2 = new ArvoreBMais<>(ParIDUserIDVote.class.getConstructor(), 5, "dados/" + filename + "2.db");
        listaInvertida = new ListaInvertida(4, "dados/dicionario.listainv.db", "dados/blocos.listainv.db");
    }

    // Functions and methods
}
