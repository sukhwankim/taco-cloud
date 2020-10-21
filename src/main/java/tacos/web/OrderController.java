package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.Order;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
    @RequestMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("order",new Order());
        return "orderForm";
    }

    //org.springframework.validation.Errors
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors){
        //@Valid : 각 폼의 POST 요청 처리시 유효성 검사 수행하도록 함.

        //hasErrors() 오류가 있을시 반환함
        if(errors.hasErrors()){
            return "orderForm";
        }

        log.info("Order submitted " + order);
        return "redirect:/";
    }
}
