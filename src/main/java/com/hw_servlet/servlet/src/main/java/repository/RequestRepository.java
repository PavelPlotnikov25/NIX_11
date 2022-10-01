package repository;

import model.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestRepository {

    private static RequestRepository instance;
    private final List<Request> requests;

    private RequestRepository() {
        this.requests = new ArrayList<>();
    }


    public static RequestRepository getInstance(){
        if (instance ==  null){
            instance = new RequestRepository();
        }
        return instance;
    }

    public void save(Request request){
        requests.add(request);
    }

    public List<Request> getAll(){
        return requests;
    }
}
