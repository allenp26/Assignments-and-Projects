public class EmpException extends Exception {
    private String id;

    public EmpException() {
        id = null;
    }

    public EmpException(String idNum) {
        super("Invalid Number: " + idNum);
        this.id = idNum;
    }

    public String getId() {
        return id;
    }
}
