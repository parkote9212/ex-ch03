package com.pgc.mybatis.controller;

import com.pgc.mybatis.domain.Product;
import com.pgc.mybatis.dto.ProductCreateRequest;
import com.pgc.mybatis.dto.ProductUpdateRequest;
import com.pgc.mybatis.exception.ResourceNotFoundException;
import com.pgc.mybatis.service.ProServ;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/products")
@Controller
@RequiredArgsConstructor
public class ProCon {

    private final ProServ proServ;

    @GetMapping
    public String showList(Model model) {
        List<Product> products = proServ.getAllProducts();
        model.addAttribute("products", products);
        return "products/list";
    }

    //    추가 폼
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new ProductCreateRequest());
        return "products/create";
    }

    // 추가 처리
    @PostMapping
    public String create(@Valid @ModelAttribute ProductCreateRequest createRequest,
                         BindingResult bindingResult) { // [수정됨]

        // 2. [추가] 유효성 검사 실패 시, '추가 폼'으로 다시 돌려보냄
        if (bindingResult.hasErrors()) {
            // (createRequest 객체는 "product"라는 이름으로 자동 전달됨)
            return "products/create";
        }

        // [수정됨] DTO를 서비스로 바로 전달합니다.
        proServ.createProduct(createRequest);
        return "redirect:/products";
    }

    //    수정 폼
    @GetMapping("/{proId}/edit")
    public  String showEditForm(@PathVariable Long proId, Model model){
        // [수정됨] getProductId가 예외를 던질 수 있으므로 try-catch 또는 @ExceptionHandler 필요
        // 여기서는 간단히 getProductId를 호출합니다. (예외는 아래 핸들러가 처리)
        Product product = proServ.getProductId(proId);

        // 폼을 채우기 위해 엔티티를 그대로 전달합니다.
        // (폼이 submit될 때 update 메서드는 ProductUpdateRequest로 받습니다.)
        model.addAttribute("product", product);
        return "products/edit"; // 👈 'edit.html' 템플릿 필요
    }

    //    수정 처리
    @PostMapping("/{proId}/edit")
    public String update(@PathVariable Long proId,
                         @Valid @ModelAttribute ProductUpdateRequest updateRequest,
                         BindingResult bindingResult, Model model){ // [수정됨]
        // [수정됨] DTO를 서비스로 바로 전달합니다.
//        검증 실패시
        if (bindingResult.hasErrors()) {
            // 5. [중요] 수정 폼은 ID를 포함한 엔티티가 필요하므로 다시 모델에 담아줘야 함
            Product product = proServ.getProductId(proId);
            model.addAttribute("product", product);
            // 폼의 th:object는 "product"인데, 실패 시 넘어온 객체는 "productUpdateRequest"라
            // 필드 에러가 매칭되지 않을 수 있습니다.
            // 지금은 간단히 폼으로만 돌려보냅니다.
            return "products/edit"; // 👈 다시 '수정 폼'으로 돌려보냄
        }

        proServ.updateProduct(proId, updateRequest);
        return "redirect:/products";
    }

    @PostMapping("/{proId}/delete")
    public String delete(@PathVariable Long proId) {
        // [수정됨] deleteProduct가 예외를 던질 수 있음 (아래 핸들러가 처리)
        proServ.deleteProduct(proId);
        return "redirect:/products"; // 목록으로 이동
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFoundException(ResourceNotFoundException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", ex.getMessage());
        mav.setViewName("error/404"); // 👈 404 HTML 페이지 반환
        return mav;
    }




}

