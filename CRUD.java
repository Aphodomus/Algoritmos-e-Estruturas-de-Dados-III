import java.io.RandomAccessFile;
import java.io.File;
import java.lang.reflect.Constructor;
import datastructures.HashExtensivel;

public class CRUD<T extends Register> {
    // Attributes
    private final int lengthHeader = 4; // 4 bytes for register amount
    public RandomAccessFile raf;
    public Constructor<T> constructor;
    public HashExtensivel<ParIDEndereco> extendableHash;
    
    // Special methods
    public CRUD() {
        
    }

    public CRUD(Constructor<T> constructor, String filename) throws Exception {
        File file = new File("dados");
        if (!file.exists()) {
            file.mkdir();
        }
        
        file = new File("dados/" + filename);
        if(!file.exists()) {
            file.mkdir();
        }
        
        extendableHash = new HashExtensivel<>(ParIDEndereco.class.getConstructor(), 4, "dados/" + filename + ".hash_d.db", "dados/" + filename + ".hash_c.db");


        this.constructor = constructor;
        this.raf = new RandomAccessFile("dados/" + filename + "/arquivo.db", "rw");

        if (raf.length() == 0) {
            raf.writeInt(0);
        }
    }

    // Methods and functions
    public int create(T object) throws Exception {
        raf.seek(0);
        int lastID = raf.readInt();
        int nextID = lastID + 1;
        raf.seek(0);
        raf.writeInt(nextID);
        raf.seek(raf.length());
        object.setID(nextID);
        byte[] byteArray = object.toByteArray();
        // Write the register
        extendableHash.create(new ParIDEndereco(nextID, raf.getFilePointer()));
        raf.writeByte(1); // Tombstone 1 -> exist | 0 -> not exist
        raf.writeInt(byteArray.length); // Register length
        raf.write(byteArray); // Bytes

        return nextID;
    }

    public T read(int ID_search) throws Exception {
        // Variables
        byte tombStone;
        int length;
        T object = constructor.newInstance();
        byte[] byteArray;

        // Search id in hash
        ParIDEndereco par = extendableHash.read(ID_search);
        long pos = -1;

        // Cannot call getEndereco from a null
        if (par != null) {
            pos = par.getEndereco();
        }

        // If pos is different -1, read the data and return the object
        if (pos != -1) {
            raf.seek(pos); // Move pointer to position
            tombStone = raf.readByte(); // Read the tombstone of the register
            length = raf.readInt(); // Read the length of the register

            if (tombStone == 1) { // Only for security, but isn't necessary
                byteArray = new byte[length];
                raf.read(byteArray);
                object.fromByteArray(byteArray);

                return object;
            }
        }

        return null;
    }

    public boolean update(T new_object) throws Exception {
        // Variables
        byte tombStone;
        int length;
        T object = constructor.newInstance();
        byte[] byteArray;

        // Search id in hash
        ParIDEndereco par = extendableHash.read(new_object.getID());
        long pos = -1;

        // Cannot call getEndereco from a null
        if (par != null) {
            pos = par.getEndereco();
        }

        // If pos is different -1, read the data and return the object
        if (pos != -1) {
            raf.seek(pos); // Move pointer to position
            tombStone = raf.readByte(); // Read the tombstone of the register
            length = raf.readInt(); // Read the length of the register

            if (tombStone == 1) { // Only for security, but isn't necessary
                byteArray = new byte[length];
                raf.read(byteArray);
                object.fromByteArray(byteArray);

                if (object.getID() == new_object.getID()) {
                    byte[] new_Register = new_object.toByteArray(); // Transforma o novo objeto em bytes

                    if (new_Register.length <= byteArray.length) { // Se o novo registro for <= o registro antigo
                        raf.seek(pos);
                        raf.readByte();
                        raf.readInt();
                        raf.write(new_Register);
                    } else {
                        raf.seek(pos);
                        raf.writeByte(0); // tombstone -> delete
                        raf.seek(raf.length()); // Move to end of gile
                        pos = raf.getFilePointer();
                        raf.writeByte(1); // Tombstone 1 -> exist | 0 -> not exist
                        raf.writeInt(new_Register.length); // Register length
                        raf.write(new_Register); // Bytes

                        ParIDEndereco novo = new ParIDEndereco(new_object.getID(), pos);

                        extendableHash.update(novo); // Att the register
                    }

                    return true;
                }
            }
        }

        return false;
    }

    public boolean delete(int ID) throws Exception {
        // Variables
        byte tombStone;
        int length;
        T object = constructor.newInstance();
        byte[] byteArray;

        // Search id in hash
        ParIDEndereco par = extendableHash.read(ID);
        long pos = -1;

        // Cannot call getEndereco from a null
        if (par != null) {
            pos = par.getEndereco();
        }

        // If pos is different -1, read the data and return the object
        if (pos != -1) {
            raf.seek(pos); // Move pointer to position
            tombStone = raf.readByte(); // Read the tombstone of the register
            length = raf.readInt(); // Read the length of the register

            if (tombStone == 1) {
                byteArray = new byte[length];
                raf.read(byteArray);
                object.fromByteArray(byteArray);

                raf.seek(pos);
                raf.writeByte(0); // Write tombstone like delete
                extendableHash.delete(ID); // Delete the register

                return true;
            }
        }

        return false;
    }
}
