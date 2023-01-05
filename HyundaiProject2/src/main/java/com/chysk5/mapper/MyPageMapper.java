package com.chysk5.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chysk5.domain.BuyProductDTO;
import com.chysk5.domain.MyResellProductDTO;

public interface MyPageMapper {

//	public List<MyResellProductDTO> getMyResellList(String mem_id);
	public List<MyResellProductDTO> getMyResellList(String mem_id);
//	public int removeMyResellProduct(String pro_opt_id, String mem_id);
	public int removeMyResellProduct(@Param("pro_opt_id") String pro_opt_id, @Param("mem_id") String mem_id);
	public int modifyPrice(@Param("re_id") String re_id, @Param("re_price") int re_price);
	public List<BuyProductDTO> getBuyProduct(String mem_id);
	
}
