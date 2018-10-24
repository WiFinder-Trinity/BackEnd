package ifinsa.rsdm.wifinder.endpoint;

class Greeting {

    private final long id;
    private final String content;

    Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    long getId() {
        return id;
    }

    String getContent() {
        return content;
    }
}