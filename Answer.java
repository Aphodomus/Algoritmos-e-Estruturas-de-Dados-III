import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Answer implements Register {
    // Attributes
    protected int idAnswer;
    protected int idQuestion;
    protected int idUser;
    protected long creation;
    protected short grade;
    protected String answer;
    protected boolean active;

    // Special methods
    public Answer() {
        this.idAnswer = -1;
        this.idQuestion = -1;
        this.idUser = -1;
        this.creation = -1;
        this.grade = -1;
        this.answer = "";
        this.active = true;
    }

    public Answer(int idQuestion, int idUser, long creation, String answer) {
        this.idQuestion = idQuestion;
        this.idUser = idUser;
        this.creation = creation;
        this.grade = 0;
        this.answer = answer;
        this.active = true;
    }

    public void setID(int id) {
        
    }

    public int getID() {
        return -1;
    }

    public int getIDAnswer() {
        return this.idAnswer;
    }

    public void setIDAnswer(int idAnswer) {
        this.idAnswer = idAnswer;
    }

    public int getIDQuestion() {
        return this.idQuestion;
    }

    public void setIDQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIDUser() {
        return this.idUser;
    }

    public void setIDUser(int idUser) {
        this.idUser = idUser;
    }

    public long getCreation() {
        return this.creation;
    }

    public void setCreation(long creation) {
        this.creation = creation;
    }

    public short getGrade() {
        return this.grade;
    }

    public void setGrade(short grade) {
        this.grade = grade;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Functions and methods
    public String toString() {
        return  "\nidAnswer: " + this.idAnswer + 
                "\nidQuestion: " + this.idQuestion + 
                "\nidUser: " + this.idUser +
                "\ncreation: " + this.creation +
                "\ngrade: " + this.grade +
                "\nanswer: " + this.answer +
                "\nactive: " + this.active;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        
        dos.writeInt(this.idAnswer);
        dos.writeInt(this.idQuestion);
        dos.writeInt(this.idUser);
        dos.writeLong(this.creation);
        dos.writeShort(this.grade);
        dos.writeUTF(this.answer);
        dos.writeBoolean(this.active);

        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        this.idAnswer = dis.readInt();
        this.idQuestion = dis.readInt();
        this.idUser = dis.readInt();
        this.creation = dis.readLong();
        this.grade = dis.readShort();
        this.answer = dis.readUTF();
        this.active = dis.readBoolean();
    }
}
