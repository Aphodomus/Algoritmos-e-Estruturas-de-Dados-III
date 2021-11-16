import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ParIDQuestionIDAnswer implements datastructures.RegistroArvoreBMais<ParIDQuestionIDAnswer> {
    // Attributes
    private int IDQuestion;
    private int IDAnswer;
    private short tamanho = 8;

    // Special methods
    public ParIDQuestionIDAnswer() {
        this(-1, -1);
    }

    public ParIDQuestionIDAnswer(int idquestion, int IDAnswer) {
        try {
            this.IDAnswer = IDAnswer;
            this.IDQuestion = idquestion;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getIDAnswer() {
        return this.IDAnswer;
    }

    public int getIDQuestion() {
        return this.IDQuestion;
    }

    // Functions and methods
    @Override
    public ParIDQuestionIDAnswer clone() {
        return new ParIDQuestionIDAnswer(this.IDQuestion, this.IDAnswer);
    }

    public short size() {
        return this.tamanho;
    }

    public int compareTo(ParIDQuestionIDAnswer par) {
        if (this.IDAnswer != par.IDAnswer) {
            return this.IDAnswer - par.IDAnswer;
        } else {
            // Só compara os valores de Num2, se o Num2 da busca for diferente de -1
            // Isso é necessário para que seja possível a busca de lista
            return this.IDQuestion == -1 ? 0 : this.IDQuestion - par.IDQuestion;
        }
    }

    public String toString() {
        return String.format("%3d", this.IDQuestion) + ";" + String.format("%-3d", this.IDAnswer);
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.IDQuestion);
        dos.writeInt(this.IDAnswer);

        return baos.toByteArray();
    }
    
    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        this.IDQuestion = dis.readInt();
        this.IDAnswer = dis.readInt();
    }
}