public class DuplicateEmpException extends Exception {
    private String id;

    public DuplicateEmpException() {
        id = null;
    }

    public DuplicateEmpException(String idNum) {
        super("An Employee already exist within the system: " +idNum);
        this.id = idNum;
    }

    public String getId() {
        return id;
    }
}
