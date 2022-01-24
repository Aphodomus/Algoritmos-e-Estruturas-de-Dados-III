import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Vote implements Register {
    // Attributes
    public int IDVote;
    public int IDUser;
    public int IDVoted;
    public boolean vote;

    // Special methods
    public Vote() {
        this.IDUser = -1;
        this.IDVote = -1;
        this.IDVoted = -1;
        this.vote = false;
    }

    public Vote(int iduser, int idvoted) {
        this.IDUser = iduser;
        this.IDVoted = idvoted;
        this.IDVote = -1;
        this.vote = false;
    }

    public int getIDUser() {
        return this.IDUser;
    }

    public void setIDUser(int iduser) {
        this.IDUser = iduser;
    }

    public int getID() {
        return this.IDVote;
    }

    public void setID(int idvote) {
        this.IDVote = idvote;
    }

    public int getIDVoted() {
        return this.IDVoted;
    }

    public void setIDVoted(int idvoted) {
        this.IDVoted = idvoted;
    }

    public boolean getVote() {
        return this.vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    // Functions and methods
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.IDVote);
        dos.writeInt(this.IDUser);
        dos.writeInt(this.IDVoted);
        dos.writeBoolean(this.vote);

        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        this.IDVote = dis.readInt();
        this.IDUser = dis.readInt();
        this.IDVoted = dis.readInt();
        this.vote = dis.readBoolean();
    }
}
