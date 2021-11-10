import java.io.RandomAccessFile;
import java.io.File;
import java.lang.reflect.Constructor;
import datastructures.HashExtensivel;

public class CRUDUser extends CRUD<User> {
    // Attributes
    public HashExtensivel<ParEmailID> indiceIndireto;

    // Special methods
    public CRUDUser(String filename) throws Exception {
        super(User.class.getConstructor(), filename);

        File file = new File("dados/" + filename);
        
        if(!file.exists()) {
            file.mkdir();
        }

        indiceIndireto = new HashExtensivel<>(ParEmailID.class.getConstructor(), 4, "dados/" + filename + ".hash_a.db", "dados/" + filename + ".hash_b.db");
    }

    // Functions and methods
    public int create(User objeto) throws Exception {
        int id = super.create(objeto);
        objeto.setID(id);
        indiceIndireto.create(new ParEmailID(objeto.getEmail(), id));
        return id;
    }

    public User read(int id) throws Exception {
        return super.read(id);
    }

    public void delete(User user) throws Exception {
        super.delete(user.getID());
        indiceIndireto.delete(user.getEmail().hashCode());
    }

    public boolean update(User objeto) throws Exception {
        return super.update(objeto) & indiceIndireto.update(new ParEmailID(objeto.getEmail(), objeto.getID()));
    }

    public boolean updateSenha(User objeto) throws Exception {
        return super.update(objeto);
    }

    public ParEmailID search(int hashcode) throws Exception {
        return indiceIndireto.read(hashcode);
    }

    public void showUsers() {
        indiceIndireto.print();
    }
}
