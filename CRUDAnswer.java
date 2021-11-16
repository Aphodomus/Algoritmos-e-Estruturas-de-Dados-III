import java.io.RandomAccessFile;
import java.io.File;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import datastructures.ArvoreBMais;
import datastructures.ListaInvertida;
import java.util.Scanner;

public class CRUDAnswer extends CRUD<Answer> {
    // Attributes
    public ArvoreBMais<ParIDQuestionIDAnswer> arvore;
    public ArvoreBMais<ParIDUserIDAnswer> arvore2;
    public ListaInvertida listaInvertida;

    // Special methods
    public CRUDAnswer(String filename) throws Exception {
        super(Answer.class.getConstructor(), filename);

        File file = new File("dados/" + filename);
        
        if (!file.exists()) {
            file.mkdir();
        }

        arvore = new ArvoreBMais<>(ParIDQuestionIDAnswer.class.getConstructor(), 5, "dados/" + filename + ".db");
        arvore2 = new ArvoreBMais<>(ParIDUserIDAnswer.class.getConstructor(), 5, "dados/" + filename + "2.db");
        listaInvertida = new ListaInvertida(4, "dados/dicionario.listainv.db", "dados/blocos.listainv.db");
    }

    // Functions and methods
    public ArrayList<ParIDQuestionIDAnswer> listQuestion (int idQuestion) throws Exception{
        System.out.println("entrou no ListQuestion");
        System.out.println("Valor IDQuestion = " + idQuestion);
        ArrayList<ParIDQuestionIDAnswer> lista = arvore.read(new ParIDQuestionIDAnswer(idQuestion, -1));
        System.out.println("Tamhno da ListaQuestion = " + lista.size());
        return lista;
    }

    public int create(Answer objeto) throws Exception {
        int IDAnswer = super.create(objeto);
        objeto.setIDAnswer(IDAnswer);
        System.out.println("IDAnswer = " + objeto.getIDAnswer());
        arvore.create(new ParIDQuestionIDAnswer(objeto.getIDQuestion(), objeto.getIDAnswer()));
        arvore2.create(new ParIDUserIDAnswer(objeto.getIDUser(), objeto.getIDAnswer()));

        return IDAnswer;
    }

    public Answer read(int id) throws Exception {
        Answer q = new Answer();
        System.out.println("9");

        try {
            System.out.println("10");
            q = super.read(id);
            System.out.println("11");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("12");

        return q;
    }
}
