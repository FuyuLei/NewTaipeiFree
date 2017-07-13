package comn.example.user.newtaipeifree.model;

import java.util.ArrayList;

public class Result {
    private ArrayList<Place> records;

    public Result(){
        records = new ArrayList<>();
    }

    public ArrayList<Place> getPlace(){
        return records;
    }

    @Override
    public String toString() {
        return "Result{" +
                "records=" + records +
                '}';
    }
}
