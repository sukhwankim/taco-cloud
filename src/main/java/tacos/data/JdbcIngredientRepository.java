package tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository //Repository로 지정 - > 스프링 컴포넌트 검색에서 이 클래스를 자동으로 찾아서 스프링 애플리케이션 켄스트의 빈
public class JdbcIngredientRepository implements IngredientRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll(){
        return jdbc.query(
                "select id, name, type from Ingredient", this::mapRowToIngredient);
    }

    @Override
    public Ingredient findById(String id){
        return jdbc.queryForObject(
          "select id, name, type from Ingredient where id = ?" , this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient){
        jdbc.update(
            "insert into Ingredient (id, name, type) values(?,?,?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());

        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet rs , int rowNum) throws SQLException {
        return new Ingredient(
          rs.getString("id"),
          rs.getString("name"),
          Ingredient.Type.valueOf(rs.getString("type"))
        );
    }

}
