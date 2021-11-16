import java.io.RandomAccessFile;
import java.io.File;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import datastructures.ArvoreBMais;
import datastructures.ListaInvertida;
import java.util.Scanner;

public class CRUDQuestion extends CRUD<Question> {
    // Attributes
    public ArvoreBMais<ParIDUserIDQuestion> arvore;
    public ListaInvertida listaInvertida;

    // Special methods
    public CRUDQuestion(String filename) throws Exception {
        super(Question.class.getConstructor(), filename);

        File file = new File("dados/" + filename);
        
        if (!file.exists()) {
            file.mkdir();
        }

        arvore = new ArvoreBMais<>(ParIDUserIDQuestion.class.getConstructor(), 5, "dados/" + filename + ".db");
        listaInvertida = new ListaInvertida(4, "dados/dicionario.listainv.db", "dados/blocos.listainv.db");
    }

    // Functions and methods
    public ArrayList<ParIDUserIDQuestion> list (int IDUser) throws Exception{
        ArrayList<ParIDUserIDQuestion> lista = arvore.read(new ParIDUserIDQuestion(IDUser, -1));
        return lista;
    }

    public int create(int IDUser, Question objeto) throws Exception {
        int IDQuestion = super.create(objeto);
        objeto.setID(IDQuestion);
        arvore.create(new ParIDUserIDQuestion(IDUser, IDQuestion));

        String[] strip = objeto.getKeyWords().split(" ");

        for (int i = 0; i < strip.length; i++) {
            listaInvertida.create(strip[i], objeto.getID());
        }

        return IDQuestion;
    }

    public void deleteKeywords(Question objeto) throws Exception {
        String[] strip = objeto.getKeyWords().split(" ");

        for (int i = 0; i < strip.length; i++) {
            listaInvertida.delete(strip[i], objeto.getID());
        }
    }

    public Question read(int id) throws Exception {
        Question q = new Question();

        try {
            q = super.read(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return q;
    }

    public int updateQuestion(Question objeto) throws Exception {
        try {
            super.update(objeto);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
