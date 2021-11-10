import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Vote {
    // Attributes
    protected int idVote;
    protected int idUser;
    protected int idVoted;
    protected boolean vote;

    // Special methods
    public Vote() {
        this.idVote = -1;
        this.idUser = -1;
        this.idVoted = -1;
        this.vote = false;
    }

    public Vote(int idUser, int idVoted, boolean vote) {
        this.idUser = idUser;
        this.idVoted = idVoted;
        this.vote = vote;
    }

    // Function and methods
    public String toString() {
        return  "\nidVote: " + this.idVote + 
                "\nidUser: " + this.idUser +
                "\nidVoted: " + this.idVoted +
                "\nvote: " + this.vote;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.idvote);
        dos.writeInt(this.idUser);
        dos.writeInt(this.idVoted);
        dos.writeBoolean(this.vote);

        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        this.idVote = dis.readInt();
        this.idUser = dis.readInt();
        this.idVoted = dis.readInt();
        this.vote = dis.readBoolean();
    }
}
