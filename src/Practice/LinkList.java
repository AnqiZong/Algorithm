package Practice;

public interface LinkList {
    public void add(int e);

    public  void insert(int e,int index);

    public void delete(int index);

    public int get(int index);

    public void set(int data);

    public void update(int index, int newValue);

    public int getSize();

    public Node getNext();
    public void setNext(Node next);

}
