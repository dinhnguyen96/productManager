package model;

public class Producer {

    private long id;

    private String producerName;

    public Producer(long id, String producerName) {
        this.id = id;
        this.producerName = producerName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }
}
