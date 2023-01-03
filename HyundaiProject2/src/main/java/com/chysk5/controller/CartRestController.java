package com.chysk5.controller;



import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chysk5.domain.CartDTO;
import com.chysk5.domain.ProductOptionDTO;
import com.chysk5.service.CartSerivce;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/cartAjax/")
@AllArgsConstructor

public class CartRestController {
   
	private CartSerivce service;
	
	
	// 장바구니 담기 -- session 으로 mem_id 받아와야 한다
	@Secured({"ROLE_MEMBER"})
	@PostMapping("/addCart")
	public String addCart(@RequestParam("pro_name") String pro_name,@RequestParam("pro_opt_size") String pro_opt_size,@RequestParam("pro_id") String pro_id)throws Exception{
	  	   
	    log.info("add cart");
	    String mem_id="yoon";
	    log.info(pro_name);
	  //cart.setMember_mem_id(mem_id);
	    
	    //product_opt_id 조회
	    ProductOptionDTO product=new ProductOptionDTO(pro_id,pro_name,pro_opt_size);
	    log.info(product);
 	    String opt_id=service.searchOptid(product); 
     	// cart 삽입(존재여부 체크 )
 	    CartDTO cart=new CartDTO(mem_id,opt_id);
        log.info("opt_id:"+opt_id);
	    log.info("cart:"+cart);   
	    log.info("add cart 서비스 호출 전");
	    
	    if(service.checkCart(cart)>0) {
	    	service.increaseCount(cart);
	    	log.info("장바구니 존재 o 수량 증가");
	    	return "update";
	    }
	    else {
	    	 service.addCart(cart);
	    	 log.info("장바구니 존재 x 장바구니 등록");
	    	 return "insert";
	    
	    }
	    
	   
		
	} 
	// 장바구니 삭제
	@Secured({"ROLE_MEMBER"})
	@PostMapping("/delete")
	public void deleteCart()throws Exception{
	  	   
	}
}
