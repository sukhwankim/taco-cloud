package tacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import tacos.Taco;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

import javax.validation.Valid;

@Slf4j //Lombok에서 저공 Simple Logging Facade Logger 생성
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private TacoRepository tacoRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo){
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
    }


    @GetMapping
    public String showDesignForm(Model model){
        /*
        System.out.println("Start design");
        //Arrays.asList() < 일반 배열을 ArrayList로 변환한다. Arraysdml private 정적 클래스인 ArrayList를 리턴한다
        //java.util.ArrayList 클래스와는 다른 클래스임.
        //java.util.ArrayList 클래스는 set(), get(), contains() 메서드를 가지고 있지만 원소를 추가하는 메서드는
        // 가지고 있지 않기 때문에 사이즈를 바꿀 수 없다.
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO","Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO","Corn Tortilla" , Ingredient.Type.WRAP),
                new Ingredient("GRBF","Ground Beef"   , Ingredient.Type.PROTEIN),
                new Ingredient("CARN","Carnitas"      , Ingredient.Type.PROTEIN),
                new Ingredient("TMTO","Diced Tomatos" , Ingredient.Type.VEGGIES),
                new Ingredient("LETC","Lettusce"      , Ingredient.Type.VEGGIES),
                new Ingredient("CHED","Cheddar"       , Ingredient.Type.CHEESE),
                new Ingredient("JACK","Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA","Salsa"         , Ingredient.Type.SAUCE),
                new Ingredient("SRCR","Sour Cream"    , Ingredient.Type.SAUCE)
        );
        */

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i->ingredients.add(i));

        Type[] types = Ingredient.Type.values();

        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase() ,filterByType(ingredients,type));
        } //toLowerCase() <소문자로 변환



        model.addAttribute("taco",new Taco());

        System.out.println("model :::"+model);

        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {

        return ingredients.stream()
                          .filter(x -> x.getType().equals(type))
                          .collect(Collectors.toList());
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }


    //@Valid : 각 폼의 POST 요청 처리시 유효성 검사 수행하도록 함.
    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "design";
        }

        //이 지점에서 타코 디자인(선택된 식자재 내역)을 저장한다
        //이 작업은 3장에서 할것이다.
        log.info("Processing design : "+design);
        Taco saved = tacoRepo.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

}
