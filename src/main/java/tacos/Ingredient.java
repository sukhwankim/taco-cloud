package tacos;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String id;   //final 은 상수인데.
    private final String name;
    private final Type type; //아래 타입객체 생성

    public static enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
